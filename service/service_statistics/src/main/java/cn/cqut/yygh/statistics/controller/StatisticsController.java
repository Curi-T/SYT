package cn.cqut.yygh.statistics.controller;

import cn.cqut.yygh.client.OrderFeignClient;
import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.vo.order.OrderCountQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计管理接口
 *
 * @author CuriT
 * @Date 2022-7-24 22:03
 */
@Api(tags = "统计管理接口")
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private OrderFeignClient orderFeignClient;


    /**
     * 获取订单统计数据
     *
     * @param orderCountQueryVo
     * @return
     */
    @ApiOperation(value = "获取订单统计数据")
    @GetMapping("getCountMap")
    public Result getCountMap(@ApiParam(name = "orderCountQueryVo", value = "查询对象", required = false) OrderCountQueryVo orderCountQueryVo) {
        return Result.ok(orderFeignClient.getCountMap(orderCountQueryVo));
    }
}

