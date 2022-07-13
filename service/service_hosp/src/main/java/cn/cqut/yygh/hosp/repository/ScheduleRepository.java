package cn.cqut.yygh.hosp.repository;

import cn.cqut.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CuriT
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    /**
     * 根据医院编号和排班编号查询排班表
     * @param hoscode 医院编号
     * @param hosScheduleId 排班编号
     * @return Schedule 排班表
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
