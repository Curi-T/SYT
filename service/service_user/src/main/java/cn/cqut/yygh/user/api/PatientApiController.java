package cn.cqut.yygh.user.api;

import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.common.utils.AuthContextHolder;
import cn.cqut.yygh.model.user.Patient;
import cn.cqut.yygh.user.service.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 就诊人管理接口
 *
 * @author CuriT
 * @Date 2022-7-18 13:32
 */
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {
    @Autowired
    private PatientService patientService;

    /**
     * 获取就诊人列表
     *
     * @param request
     * @return
     */
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllByUserId(userId);
        return Result.ok(list);
    }

    /**
     * 添加就诊人
     *
     * @param patient
     * @param request
     * @return
     */
    @PostMapping("auth/save")
    public Result savePatient(@RequestBody Patient patient, HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    /**
     * 根据id获取就诊人信息
     *
     * @param id
     * @return
     */
    @GetMapping("auth/get/{id}")
    public Result getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);
        return Result.ok(patient);
    }

    /**
     * 修改就诊人
     *
     * @param patient
     * @return
     */
    @PostMapping("auth/update")
    public Result updatePatient(@RequestBody Patient patient) {
        patientService.updateById(patient);
        return Result.ok();
    }

    /**
     * 删除就诊人
     *
     * @param id
     * @return
     */
    @DeleteMapping("auth/remove/{id}")
    public Result removePatient(@PathVariable Long id) {
        patientService.removeById(id);
        return Result.ok();
    }

    /**
     * 获取就诊人
     * @param id
     * @return
     */
    @ApiOperation(value = "获取就诊人")
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(
            @ApiParam(name = "id", value = "就诊人id", required = true)
            @PathVariable("id") Long id) {
        return patientService.getPatientId(id);
    }

}

