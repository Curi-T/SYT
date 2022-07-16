package cn.cqut.yygh.hosp.controller;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CuriT
 * @Date 2022-7-13 14:14
 */
@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 1.获取分页、条件列表
     *
     * @param page            页数
     * @param limit           每页大小
     * @param hospitalQueryVo 医院信息
     * @return
     */
    @ApiOperation(value = "1.获取分页、条件列表")
    @GetMapping("list/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,

            @ApiParam(name = "hospitalQueryVo", value = "查询对象", required = false)
                    HospitalQueryVo hospitalQueryVo) {
        return Result.ok(hospitalService.selectPage(page, limit, hospitalQueryVo));
    }

    /**
     * 2.更新医院上线状态
     *
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "2.更新医院上线状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result lock(
            @ApiParam(name = "id", value = "医院id", required = true)
            @PathVariable("id") String id,
            @ApiParam(name = "status", value = "状态（0：未上线 1：已上线）", required = true)
            @PathVariable("status") Integer status) {
        hospitalService.updateStatus(id, status);
        return Result.ok();
    }

    /**
     * 3.获取医院详情
     * @param id
     * @return
     */
    @ApiOperation(value = "3.获取医院详情")
    @GetMapping("showHospDetail/{id}")
    public Result show(
            @ApiParam(name = "id", value = "医院id", required = true)
            @PathVariable String id) {
        return Result.ok(hospitalService.show(id));
    }



}
