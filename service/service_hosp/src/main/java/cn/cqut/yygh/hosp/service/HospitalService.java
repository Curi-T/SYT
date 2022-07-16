package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author CuriT
 */
public interface HospitalService {
    /**
     * 1.上传医院接口
     *
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 2.查询医院
     *
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);

    /**
     * 1.获取分页、条件列表
     *
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    Page selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 2.更新医院上线状态
     *
     * @param id
     * @param status
     */
    void updateStatus(String id, Integer status);

    /**
     * 3.获取医院详情
     *
     * @param id
     * @return
     */
    Map<String, Object> show(String id);

    /**
     * 3.根据hoscode获取医院名称
     *
     * @param hoscode
     * @return
     */
    String getHospName(String hoscode);

    /**
     * 2.根据医院名称获取医院列表
     *
     * @param hosname
     * @return
     */
    List<Hospital> findByHosname(String hosname);

    /**
     * 4.根据医院编号获取医院预约挂号详情
     *
     * @param hoscode
     * @return
     */
    Map<String, Object> item(String hoscode);
}
