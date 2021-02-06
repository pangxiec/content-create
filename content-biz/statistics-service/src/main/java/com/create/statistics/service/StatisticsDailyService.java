package com.create.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.pojo.domain.StatisticsDaily;

import java.util.Map;

/**
 * @author xmy
 * @date 2021/2/3 21:50
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计数据
     * @param day
     */
    void createStatisticsByDay(String day);

    /**
     * 得到数据进行展示
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type
     * @param begin 开始时间
     * @param end 结束时间
     * @return
     */
    Map<String, Object> showData(String type, String begin, String end);
}
