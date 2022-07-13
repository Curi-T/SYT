package cn.cqut.yygh.hosp.controller.api;

import cn.cqut.yygh.common.exception.YyghException;
import cn.cqut.yygh.common.helper.HttpRequestHelper;
import cn.cqut.yygh.common.result.Result;
import cn.cqut.yygh.common.result.ResultCodeEnum;
import cn.cqut.yygh.common.utils.MD5;
import cn.cqut.yygh.hosp.service.DepartmentService;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.hosp.service.HospitalSetService;
import cn.cqut.yygh.hosp.service.ScheduleService;
import cn.cqut.yygh.model.hosp.Department;
import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.model.hosp.Schedule;
import cn.cqut.yygh.vo.hosp.DepartmentQueryVo;
import cn.cqut.yygh.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-12 10:58
 */
@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    private HospitalService hospitalService;
    private HospitalSetService hospitalSetService;
    private DepartmentService departmentService;
    private ScheduleService scheduleService;

    @Autowired

    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Autowired
    public void setHospitalSetService(HospitalSetService hospitalSetService) {
        this.hospitalSetService = hospitalSetService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 1.上传医院接口
     *
     * @param request 请求体
     * @return 结果集
     */
    @ApiOperation(value = "1.上传医院接口")
    @PostMapping("saveHospital")
    public Result 坧saveHospital(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());

        checkSign(paramMap);
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replace(" ", "+");
        paramMap.put("logoData", logoData);

        hospitalService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "2.查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        checkSign(paramMap);
        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    @ApiOperation(value = "3.上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        departmentService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "4.查询科室")
    @PostMapping("department/list")
    public Result department(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String) paramMap.get("limit"));
        checkSign(paramMap);

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        departmentQueryVo.setDepcode(depcode);
        Page<Department> pageModel = departmentService.selectPage(page, limit, departmentQueryVo);
        System.err.println(depcode);
        return Result.ok(pageModel);
    }

    /**
     * 5.删除科室
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "5.删除科室")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        //必须参数校验 略
        String hoscode = (String) paramMap.get("hoscode");
        //必填
        String depcode = (String) paramMap.get("depcode");
        if (StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }

    /**
     * 6.上传排班接口
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "6.上传排班接口")
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        scheduleService.save(paramMap);
        return Result.ok();
    }

    /**
     * 7.查询排班接口
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "7.查询排班接口")
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);

        String hoscode = (String) paramMap.get("hoscode");
        //非必填
        String depcode = (String) paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String) paramMap.get("limit"));

        if (StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.selectPage(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }


    /**
     * 8.删除排班
     *
     * @return
     */
    @ApiOperation(value = "8.删除排班")
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) paramMap.get("hoscode");
        //必填
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        if (StringUtils.isEmpty(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();

    }

    /**
     * 0.校验医院签名
     *
     * @param paramMap
     */
    private void checkSign(Map<String, Object> paramMap) {
        //1.获取医院系统传递过来的签名，签名已经MD5加密
        String hospSign = (String) paramMap.get("sign");

        //2.根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3.把数据库查询签名进行MD5加密
        String signKeyMD5 = MD5.encrypt(signKey);
        System.out.println();

        //4.判断签名是否一致
        if (!hospSign.equals(signKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }
}
