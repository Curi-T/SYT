package cn.cqut.yygh.order.service;

import cn.cqut.yygh.model.order.OrderInfo;
import cn.cqut.yygh.vo.order.OrderQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 分页列表
     */
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

}
