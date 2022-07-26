package cn.cqut.yygh.hosp.controller.api;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.hosp.service.DepartmentService;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.hosp.service.HospitalSetService;
import cn.cqut.yygh.hosp.service.ScheduleService;
import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
import cn.cqut.yygh.vo.hosp.ScheduleOrderVo;
import cn.cqut.yygh.vo.order.SignInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 医院管理接口
 *
 * @author CuriT
 * @Date 2022-7-16 16:22
 */
@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospitalApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 1.获取分页列表
     *
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @ApiOperation(value = "1.获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            HospitalQueryVo hospitalQueryVo) {
        //显示上线的医院
        //hospitalQueryVo.setStatus(1);
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 2.根据医院名称获取医院列表
     *
     * @param hosname
     * @return
     */
    @ApiOperation(value = "2.根据医院名称获取医院列表")
    @GetMapping("findByHosname/{hosname}")
    public Result findByHosname(
            @ApiParam(name = "hosname", value = "医院名称", required = true)
            @PathVariable String hosname) {
        return Result.ok(hospitalService.findByHosname(hosname));
    }

    /**
     * 3.根据医院编号获取科室列表
     *
     * @param hoscode
     * @return
     */
    @ApiOperation(value = "3.根据医院编号获取科室列表")
    @GetMapping("department/{hoscode}")
    public Result index(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        return Result.ok(departmentService.findDeptTree(hoscode));
    }

    /**
     * 4.根据医院编号获取医院预约挂号详情
     *
     * @param hoscode
     * @return
     */
    @ApiOperation(value = "4.根据医院编号获取医院预约挂号详情")
    @GetMapping("{hoscode}")
    public Result item(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        return Result.ok(hospitalService.item(hoscode));
    }


    /**
     * 5.获取可预约排班数据
     *
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    @ApiOperation(value = "5.获取可预约排班数据")
    @GetMapping("auth/getBookingScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result getBookingSchedule(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode) {
        return Result.ok(scheduleService.getBookingScheduleRule(page, limit, hoscode, depcode));
    }

    /**
     * 6.获取排班数据
     *
     * @param hoscode
     * @param depcode
     * @param workDate
     * @return
     */
    @ApiOperation(value = "6.获取排班数据")
    @GetMapping("auth/findScheduleList/{hoscode}/{depcode}/{workDate}")
    public Result findScheduleList(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode,
            @ApiParam(name = "workDate", value = "排班日期", required = true)
            @PathVariable String workDate) {
        return Result.ok(scheduleService.getDetailSchedule(hoscode, depcode, workDate));
    }

    /**
     * 根据排班id获取排班数据
     *
     * @param scheduleId
     * @return
     */
    @ApiOperation(value = "根据排班id获取排班数据")
    @GetMapping("getSchedule/{scheduleId}")
    public Result getSchedule(
            @ApiParam(name = "scheduleId", value = "排班id", required = true)
            @PathVariable String scheduleId) {
        return Result.ok(scheduleService.getScheduleById(scheduleId));
    }

    @ApiOperation(value = "根据排班id获取预约下单数据")
    @GetMapping("inner/getScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo getScheduleOrderVo(
            @ApiParam(name = "scheduleId", value = "排班id", required = true)
            @PathVariable("scheduleId") String scheduleId) {
        return scheduleService.getScheduleOrderVo(scheduleId);
    }

    @ApiOperation(value = "获取医院签名信息")
    @GetMapping("inner/getSignInfoVo/{hoscode}")
    public SignInfoVo getSignInfoVo(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable("hoscode") String hoscode) {
        return hospitalSetService.getSignInfoVo(hoscode);
    }




}
