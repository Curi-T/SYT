package cn.cqut.yygh.hosp.service.impl;

import cn.cqut.yygh.hosp.repository.HospitalRepository;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.model.hosp.Hospital;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-12 10:55
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    private HospitalRepository hospitalRepository;

    @Autowired
    public void setHospitalRepository(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    /**
     * 1.上传医院接口
     *
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数map集合转换对象 Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        //判断是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);

        if (null != hospitalExist) {
            System.err.println("========================");
            //如果存在，进行修改
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }

    }

    /**
     * 2.查询医院
     *
     * @param hoscode
     * @return
     */
    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        return hospital;
    }
}
