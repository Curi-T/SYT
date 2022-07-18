package cn.cqut.yygh.user.service;

import cn.cqut.yygh.model.user.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author CuriT
 * @Date 2022-7-18 13:30
 */
public interface PatientService extends IService<Patient> {
    /**
     * 获取就诊人列表
     * @param userId
     * @return
     */
    List<Patient> findAllByUserId(Long userId);

    /**
     * 根据id获取就诊人信息
     * @param id
     * @return
     */
    Patient getPatientId(Long id);
}

