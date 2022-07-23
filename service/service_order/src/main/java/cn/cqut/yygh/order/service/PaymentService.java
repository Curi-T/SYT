package cn.cqut.yygh.order.service;

import cn.cqut.yygh.model.order.OrderInfo;
import cn.cqut.yygh.model.order.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-23 10:44
 */
public interface PaymentService extends IService<PaymentInfo> {
    /**
     * 保存交易记录
     *
     * @param order
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    /**
     * 更改订单状态，处理支付结果
     *
     * @param out_trade_no
     * @param status
     * @param resultMap
     */
    void paySuccess(String out_trade_no, Integer status, Map<String, String> resultMap);
}

