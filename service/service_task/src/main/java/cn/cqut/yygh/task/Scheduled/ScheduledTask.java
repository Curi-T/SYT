package cn.cqut.yygh.task.Scheduled;

import cn.cqut.common.rabbit.constant.MqConst;
import cn.cqut.common.rabbit.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author CuriT
 * @Date 2022-7-24 16:23
 */
@Component
public class ScheduledTask {

    @Autowired
    private RabbitService rabbitService;

    /**
     * 每天8点执行 提醒就诊
     */
    @Scheduled(cron = "0 0 8 * * ? ")
    public void task1() {
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK, MqConst.ROUTING_TASK_8, "");
    }
}
