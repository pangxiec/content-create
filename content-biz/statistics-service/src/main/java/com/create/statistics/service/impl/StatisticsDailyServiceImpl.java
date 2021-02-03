package com.create.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.mapper.StatisticsDailyMapper;
import com.create.pojo.domain.StatisticsDaily;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/3 21:51
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements IService<StatisticsDaily> {
}
