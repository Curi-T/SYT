package cn.cqut.yygh.user.api;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.common.utils.AuthContextHolder;
import cn.cqut.yygh.model.user.UserInfo;
import cn.cqut.yygh.user.service.UserInfoService;
import cn.cqut.yygh.vo.user.LoginVo;
import cn.cqut.yygh.vo.user.UserAuthVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-17 08:55
 */
@ApiOperation("用户登录")
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 1.会员手机号登录
     *
     * @param loginVo
     * @param request
     * @return
     */
    @ApiOperation(value = "1.会员手机号登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
//        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.login(loginVo);
        return Result.ok(info);
    }

    /**
     * 用户认证接口
     *
     * @param userAuthVo
     * @param request
     * @return
     */
    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request), userAuthVo);
        return Result.ok();
    }

    /**
     * 获取用户id信息接口
     *
     * @param request
     * @return
     */
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }


}

