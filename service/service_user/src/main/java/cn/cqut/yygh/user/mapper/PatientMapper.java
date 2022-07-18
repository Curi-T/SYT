package cn.cqut.yygh.user.mapper;

import cn.cqut.yygh.model.user.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;

/**
 * @author CuriT
 * @Date 2022-7-18 13:29
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}

