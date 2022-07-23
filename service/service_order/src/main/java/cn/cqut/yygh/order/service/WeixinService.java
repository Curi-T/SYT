package cn.cqut.yygh.order.service;

import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-23 10:37
 */
public interface WeixinService {

    /**
     * 下单 生成二维码
     *
     * @param orderId
     * @return
     */
    Map createNative(Long orderId);

    /**
     * 查询微信支付官方接口，获取支付状态
     * @param orderId
     * @param name
     * @return
     */
    Map<String, String> queryPayStatus(Long orderId, String name);
}
