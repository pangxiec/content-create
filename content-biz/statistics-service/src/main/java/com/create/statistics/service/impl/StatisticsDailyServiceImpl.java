package com.create.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.common.utils.R;
import com.create.mapper.StatisticsDailyMapper;
import com.create.pojo.domain.StatisticsDaily;
import com.create.statistics.client.ArticleClient;
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

    @Resource
    private ArticleClient articleClient;


    @Override
    public void createStatisticsByDay(String day) {
        //在添加之前先删除
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        //远程调用得到某一天的注册人数
        R registerR = ucenterClient.registerCount(day);
        Integer registerCount = (Integer)registerR.getData().get("registerCount");

        R createR = articleClient.createCount(day);
        Integer createCount = (Integer)createR.getData().get("createCount");

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        //注册人数
        statisticsDaily.setRegisterNum(registerCount);
        //文章数
        statisticsDaily.setCreateNum(createCount);
        //统计日期
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));

        baseMapper.insert(statisticsDaily);
    }
}
