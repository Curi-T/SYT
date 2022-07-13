package cn.cqut.yygh.hosp.repository;

import cn.cqut.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 科室接口
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    /**
     * 根据hoscode医院编号和depcode科室编号查询科室信息
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return
     */
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);

}
