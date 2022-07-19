package cn.cqut.yygh.user.service;

import cn.cqut.yygh.model.user.UserInfo;
import cn.cqut.yygh.vo.user.LoginVo;
import cn.cqut.yygh.vo.user.UserAuthVo;
import cn.cqut.yygh.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 用户认证接口
     *
     * @param userId
     * @param userAuthVo
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);

    /**
     * 用户列表（条件查询带分页）
     *
     * @param pageParam
     * @param userInfoQueryVo
     * @return
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    /**
     * 锁定
     *
     * @param userId
     * @param status
     */
    void lock(Long userId, Integer status);

    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    Map<String, Object> show(Long userId);

    /**
     * 认证审批
     *
     * @param userId
     * @param authStatus
     */
    void approval(Long userId, Integer authStatus);
}
