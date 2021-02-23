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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Transactional(rollbackFor = Exception.class)
    public void createStatisticsByDay(String day) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        R registerR = ucenterClient.registerCount(day);
        Integer registerCount = (Integer)registerR.getData().get("registerCount");

        R loginR = ucenterClient.loginCount(day);
        Integer loginCount = (Integer)loginR.getData().get("loginCount");

        R createR = articleClient.createCount(day);
        Integer createCount = (Integer)createR.getData().get("createCount");

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(registerCount);
        statisticsDaily.setLoginNum(loginCount);
        statisticsDaily.setCreateNum(createCount);
        statisticsDaily.setDateCalculated(day);

        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staDailyList = baseMapper.selectList(wrapper);

        //需要返回两部分数据 日期和日期对应的数据
        //日期List
        List<String> dateList = new ArrayList<>();
        //数据List
        List<Integer> numDataList = new ArrayList<>();

        for (int i = 0; i < staDailyList.size(); i++) {
            StatisticsDaily statisticsDaily = staDailyList.get(i);
            //日期集合
            dateList.add(statisticsDaily.getDateCalculated());
            //封装对应数量
            switch (type){
                case "login_num":
                    numDataList.add(statisticsDaily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(statisticsDaily.getRegisterNum());
                    break;
                case "create_num":
                    numDataList.add(statisticsDaily.getCreateNum());
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dateList",dateList);
        map.put("numDataList",numDataList);
        return map;
    }
}
