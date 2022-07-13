package cn.cqut.yygh.hosp.repository;

import cn.cqut.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    /**
     * 根据hoscode查询hospital
     * @param hoscode
     * @return
     */
    Hospital getHospitalByHoscode(String hoscode);
}
