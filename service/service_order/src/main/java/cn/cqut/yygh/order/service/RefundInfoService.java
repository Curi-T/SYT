package cn.cqut.yygh.order.service;

import cn.cqut.yygh.model.order.PaymentInfo;
import cn.cqut.yygh.model.order.RefundInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author CuriT
 * @Date 2022-7-24 10:58
 */
public interface RefundInfoService extends IService<RefundInfo> {
    /**
     * 保存退款记录
     * @param paymentInfo
     */
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);

}
