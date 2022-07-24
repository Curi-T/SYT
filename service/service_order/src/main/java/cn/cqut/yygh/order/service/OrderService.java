package cn.cqut.yygh.order.service;

import cn.cqut.yygh.model.order.OrderInfo;
import cn.cqut.yygh.vo.order.OrderQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 订单列表（条件查询带分页）
     * @param pageParam
     * @param orderQueryVo
     * @return
     */
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    OrderInfo getOrder(String orderId);

    /**
     * 获取订单
     * @param id
     * @return
     */
    Map<String,Object> show(Long id);

    /**
     * 取消预约
     * @param orderId
     * @return
     */
    Object cancelOrder(Long orderId);
}
