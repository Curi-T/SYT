package cn.cqut.yygh.hosp.repository;

import cn.cqut.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    /**
     * 根据hoscode查询hospital
     *
     * @param hoscode
     * @return
     */
    Hospital getHospitalByHoscode(String hoscode);

    /**
     * 2.根据医院名称获取医院列表
     *
     * @param hosname
     * @return
     */
    List<Hospital> findHospitalByHosnameLike(String hosname);
}
