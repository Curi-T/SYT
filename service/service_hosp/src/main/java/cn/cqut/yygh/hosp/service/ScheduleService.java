package cn.cqut.yygh.hosp.service;

import cn.cqut.yygh.model.hosp.Schedule;
import cn.cqut.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

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
}
