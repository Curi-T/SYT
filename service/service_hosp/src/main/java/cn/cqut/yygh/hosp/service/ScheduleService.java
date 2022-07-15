package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Schedule;
import cn.cqut.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author CuriT
 */
public interface ScheduleService {
    /**
     * 6.上传排班接口
     *
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 7.分页、条件查询排班接口
     *
     * @param page
     * @param limit
     * @param scheduleQueryVo
     * @return
     */
    Page<Schedule> selectPage(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 8.删除排班
     *
     * @param hoscode
     * @param hosScheduleId
     */
    void remove(String hoscode, String hosScheduleId);

    /**
     * 1.查询排班规则数据
     *
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    /**
     * 2.根据医院编号 、科室编号和工作日期，查询排班详细信息
     *
     * @param hoscode
     * @param depcode
     * @param workDate
     * @return
     */
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
