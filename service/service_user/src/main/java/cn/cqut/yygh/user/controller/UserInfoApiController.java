package cn.cqut.yygh.user.controller;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.user.service.UserInfoService;
import cn.cqut.yygh.vo.user.LoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

