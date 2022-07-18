package cn.cqut.yygh.user.service.impl;

import cn.cqut.yygh.common.exception.YyghException;
import cn.cqut.yygh.common.helper.JwtHelper;
import cn.cqut.yygh.common.result.ResultCodeEnum;
import cn.cqut.yygh.enums.AuthStatusEnum;
import cn.cqut.yygh.model.user.UserInfo;
import cn.cqut.yygh.user.mapper.UserInfoMapper;
import cn.cqut.yygh.user.service.UserInfoService;
import cn.cqut.yygh.vo.user.LoginVo;
import cn.cqut.yygh.vo.user.UserAuthVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-17 08:54
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 1.会员手机号登录
     *
     * @param loginVo
     * @return
     */
    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();

        //验证手机号、验证码是否为空
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }

        //登录-校验校验验证码
        //校验校验验证码
//        String mobleCode = redisTemplate.opsForValue().get(phone)+"";
        //TODO 验证码校验 123456
        String mobleCode = "123456";
        if (!code.equals(mobleCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }

        //绑定手机号码
        UserInfo userInfo = null;
        if (!StringUtils.isEmpty(loginVo.getOpenid())) {
            userInfo = this.getByOpenid(loginVo.getOpenid());
            if (null != userInfo) {
                userInfo.setPhone(loginVo.getPhone());
                this.updateById(userInfo);
            } else {
                throw new YyghException(ResultCodeEnum.DATA_ERROR);
            }
        }


        //userInfo=null 说明手机直接登录
        if (null == userInfo) {
            //手机号已被使用
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            //获取会员
            userInfo = baseMapper.selectOne(queryWrapper);
            //TODO 暂时解决先手机号登录、后微信登录绑定统一手机号问题，采取后微信登录的数据
//            List<UserInfo> userInfos = baseMapper.selectList(queryWrapper);
//            userInfo = userInfos.get(userInfos.size() - 1);
            if (null == userInfo) {
                userInfo = new UserInfo();
                userInfo.setName("");
                userInfo.setPhone(phone);
                userInfo.setStatus(1);
                this.save(userInfo);
            }
        }
        //校验是否被禁用
        if (userInfo.getStatus() == 0) {
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //TODO 记录登录

        //不是第一次，直接登录
        //返回登录信息
        //返回登录用户名
        //返回页面显示名称
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();
        if (StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if (StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }
        map.put("name", name);

        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token", token);
        return map;
    }

    /**
     * 先根据openid进行数据库查询
     *
     * @param openId
     * @return
     */
    @Override
    public UserInfo getByOpenid(String openId) {
        return baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("openid", openId));
    }

    /**
     * 用户认证接口
     * @param userId
     * @param userAuthVo
     */
    @Override
    public void userAuth(Long userId, UserAuthVo userAuthVo) {
        //根据用户id查询用户信息
        UserInfo userInfo = baseMapper.selectById(userId);
        //设置认证信息
        //认证人姓名
        userInfo.setName(userAuthVo.getName());
        //其他认证信息
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());
        //进行信息更新
        baseMapper.updateById(userInfo);
    }
}
