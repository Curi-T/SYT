package cn.cqut.yygh.order.mapper;

import cn.cqut.yygh.model.order.OrderInfo;
import cn.cqut.yygh.vo.order.OrderCountQueryVo;
import cn.cqut.yygh.vo.order.OrderCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CuriT
 * @Date 2022-7-21 11:08
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 统计订单数据，获取医院每天平台预约数据接口
     * @param orderCountQueryVo
     * @return
     */
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}
