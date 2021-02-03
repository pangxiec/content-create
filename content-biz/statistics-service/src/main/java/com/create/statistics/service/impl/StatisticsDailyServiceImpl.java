package com.create.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.common.utils.R;
import com.create.mapper.StatisticsDailyMapper;
import com.create.pojo.domain.StatisticsDaily;
import com.create.statistics.client.UcenterClient;
import com.create.statistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/3 21:51
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    private UcenterClient ucenterClient;


    @Override
    public void createStatisticsByDay(String day) {
        //远程调用得到某一天的注册人数
        R registerR = ucenterClient.registerCount(day);
        Integer registerCount = (Integer)registerR.getData().get("registerCount");

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        //注册人数
        statisticsDaily.setRegisterNum(registerCount);
        //统计日期
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setCreateNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));

        baseMapper.insert(statisticsDaily);
    }
}
