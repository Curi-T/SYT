package cn.cqut.yygh.hosp.controller;

import cn.cqut.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CuriT
 * @Date 2022-7-8 17:31
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {


    private HospitalSetService hospitalSetService;

    @Autowired
    public void setHospitalSetService(HospitalSetService hospitalSetService) {
        this.hospitalSetService = hospitalSetService;
    }

}
