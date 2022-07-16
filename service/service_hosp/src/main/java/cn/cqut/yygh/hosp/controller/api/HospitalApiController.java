package cn.cqut.yygh.hosp.controller.api;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
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

}
