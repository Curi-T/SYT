package cn.cqut.yygh.order.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author CuriT
 * @Date 2022-7-23 10:32
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${weixin.pay.appid}")
    private String appid;

    @Value("${weixin.pay.partner}")
    private String partner;

    @Value("${weixin.pay.partnerkey}")
    private String partnerkey;

    public static String APPID;
    public static String PARTNER;
    public static String PARTNERKEY;
    @Override
    public void afterPropertiesSet() throws Exception {
        APPID = appid;
        PARTNER = partner;
        PARTNERKEY = partnerkey;
    }
}

