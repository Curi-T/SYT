package cn.cqut.yygh.hosp.service.impl;

import cn.cqut.yygh.hosp.repository.ScheduleRepository;
import cn.cqut.yygh.hosp.service.ScheduleService;
import cn.cqut.yygh.model.hosp.Schedule;
import cn.cqut.yygh.vo.hosp.ScheduleQueryVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author CuriT
 * @Date 2022-7-13 11:10
 */
@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * 6.上传排班接口
     *
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        Schedule schedule = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Schedule.class);
        Schedule targetSchedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());
        if (null != targetSchedule) {
            //值copy不为null的值，该方法为自定义方法
            BeanUtils.copyProperties(schedule, targetSchedule, Schedule.class);
            scheduleRepository.save(targetSchedule);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }

    }

    /**
     * 7.分页、条件查询排班接口
     *
     * @param page            页数
     * @param limit           每页大小
     * @param scheduleQueryVo 查询条件
     * @return Page<Schedule>
     */
    @Override
    public Page<Schedule> selectPage(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //0为第一页
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);
        schedule.setIsDeleted(0);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        //创建实例
        Example<Schedule> example = Example.of(schedule, matcher);
        Page<Schedule> pages = scheduleRepository.findAll(example, pageable);
        return pages;

    }

    /**
     * 8.删除排班
     *
     * @param hoscode
     * @param hosScheduleId
     */
    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule schedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (null != schedule) {
            scheduleRepository.deleteById(schedule.getId());
        }

    }
}
