package cn.cqut.yygh.hosp.controller;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.hosp.service.DepartmentService;
import cn.cqut.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CuriT
 * @Date 2022-7-14 17:59
 */
@ApiOperation(value = "课设排班")
@RestController
@RequestMapping("/admin/hosp/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 1.根据医院编号，查询医院所有科室列表
     *
     * @param hoscode
     * @return
     */
    @ApiOperation(value = "1.查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}

