package cn.cqut.yygh.user.api;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.user.service.UserInfoService;
import cn.cqut.yygh.user.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信操作的接口
 *
 * @author CuriT
 * @Date 2022-7-17 15:27
 */
@Controller
@RequestMapping("/api/ucenter/wx")
public class WeixinApiController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取微信登录参数，生成微信扫描二维码，返回生成二维码需要的参数
     *
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("getLoginParam")
    @ResponseBody
    public Result genQrConnect(HttpSession session) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", ConstantPropertiesUtil.WX_OPEN_APP_ID);
        String redirectUri = URLEncoder.encode(ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL, "UTF-8");
        map.put("redirectUri", redirectUri);
        map.put("scope", "snsapi_login");
        map.put("state", System.currentTimeMillis() + "");
        return Result.ok(map);
    }
}

