package cn.cqut.yygh.order.service;

import cn.cqut.yygh.model.order.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author CuriT
 * @Date 2022-7-21 11:09
 */
public interface OrderService extends IService<OrderInfo> {
    /**
     * 保存订单
     *
     * @param scheduleId
     * @param patientId
     * @return
     */
    Long saveOrder(String scheduleId, Long patientId);
}
