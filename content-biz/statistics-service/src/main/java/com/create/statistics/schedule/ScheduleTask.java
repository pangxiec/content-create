package com.create.statistics.schedule;

import com.create.common.utils.DateUtil;
import com.create.statistics.service.StatisticsDailyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xmy
 * @date 2021/2/4 23:26
 */
@Component
public class ScheduleTask {

    @Resource
    private StatisticsDailyService staService;

    /**
     * 在每天凌晨1点去执行 把前一天的数据进行添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        staService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

}
