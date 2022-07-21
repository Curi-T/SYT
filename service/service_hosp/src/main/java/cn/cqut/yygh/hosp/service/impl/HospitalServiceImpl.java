package cn.cqut.yygh.hosp.service.impl;

import cn.cqut.yygh.cmn.client.DictFeignClient;
import cn.cqut.yygh.enums.DictEnum;
import cn.cqut.yygh.hosp.repository.HospitalRepository;
import cn.cqut.yygh.hosp.service.HospitalService;
import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-12 10:55
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DictFeignClient dictFeignClient;

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

    /**
     * 3.获取分页、条件列表
     *
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @Override
    public Page selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);
        Example<Hospital> example = Example.of(hospital, matcher);
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        //获取查询list集合，遍历进行医院等级封装
        pages.getContent().stream().forEach(item -> {
            this.setHosptiallHosType(item);
        });
        return pages;
    }

    /**
     * 2.更新医院上线状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, Integer status) {
        if (status.intValue() == 0 || status.intValue() == 1) {
            Hospital hospital = hospitalRepository.findById(id).get();
            hospital.setStatus(status);
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }
    }

    /**
     * 3.获取医院详情
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> show(String id) {
        Map<String, Object> result = new HashMap<>();

        Hospital hospital = this.setHosptiallHosType(hospitalRepository.findById(id).get());
        //医院基本信息，包含等级
        result.put("hospital", hospital);

        //单独处理更直观
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }

    /**
     * 3.根据hoscode获取医院名称
     *
     * @param hoscode
     * @return
     */
    @Override
    public String getHospName(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if (null != hospital) {
            return hospital.getHosname();
        }
        return null;
    }

    /**
     * 2.根据医院名称获取医院列表
     *
     * @param hosname
     * @return
     */
    @Override
    public List<Hospital> findByHosname(String hosname) {
        return hospitalRepository.findHospitalByHosnameLike(hosname);
    }

    /**
     * 4.根据医院编号获取医院预约挂号详情
     *
     * @param hoscode
     * @return
     */
    @Override
    public Map<String, Object> item(String hoscode) {
        Map<String, Object> result = new HashMap<>();
        //医院详情
        Hospital hospital = this.setHosptiallHosType(this.getByHoscode(hoscode));
        result.put("hospital", hospital);
        //预约规则
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }


    /**
     * 获取查询list集合，遍历进行医院等级封装
     *
     * @param hospital
     */
    private Hospital setHosptiallHosType(Hospital hospital) {
        //根据dictCode和value获取医院等级名称
        String hostypeString = dictFeignClient.getName(DictEnum.HOSTYPE.getDictCode(), hospital.getHostype());
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", provinceString + cityString + districtString + hospital.getAddress());
        return hospital;
    }
}
