package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author CuriT
 */
public interface HospitalService {
    /**
     * 1.上传医院接口
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 2.查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);
}
