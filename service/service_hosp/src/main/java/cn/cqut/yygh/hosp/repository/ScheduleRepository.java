package cn.cqut.yygh.hosp.repository;

import cn.cqut.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author CuriT
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    /**
     * 根据医院编号和排班编号查询排班表
     *
     * @param hoscode       医院编号
     * @param hosScheduleId 排班编号
     * @return Schedule 排班表
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    /**
     * 根根据医院编号 、科室编号和工作日期，查询排班详细信息
     *
     * @param hoscode
     * @param depcode
     * @param toDate
     * @return
     */
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}
