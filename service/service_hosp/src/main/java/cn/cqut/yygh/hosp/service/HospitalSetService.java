package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.HospitalSet;
import cn.cqut.yygh.vo.order.SignInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author CuriT
 */
public interface HospitalSetService extends IService<HospitalSet> {
    /**
     * 2.根据传递过来的医院编码，查询数据库，查询签名
     *
     * @param hoscode
     * @return
     */
    String getSignKey(String hoscode);

    /**
     * 获取医院签名信息
     *
     * @param hoscode
     * @return
     */
    SignInfoVo getSignInfoVo(String hoscode);
}
