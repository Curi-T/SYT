package cn.cqut.yygh.user.service;

import cn.cqut.yygh.model.user.UserInfo;
import cn.cqut.yygh.vo.user.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author CuriT
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 1.会员手机号登录
     *
     * @param loginVo
     * @return
     */
    Map<String, Object> login(LoginVo loginVo);

    /**
     * 先根据openid进行数据库查询
     *
     * @param openId
     * @return
     */
    UserInfo getByOpenid(String openId);
}
