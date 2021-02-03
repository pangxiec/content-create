package com.create.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.pojo.domain.StatisticsDaily;

/**
 * @author xmy
 * @date 2021/2/3 21:50
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);
}
