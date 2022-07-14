package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Hospital;
import cn.cqut.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

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
    Page selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

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
}
