package com.create.statistics.controller;

import com.create.common.utils.R;
import com.create.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xmy
 * @date 2021/2/3 22:17
 */
@Api(tags = "数据统计")
@RestController
@RequestMapping("/data/sta")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService dailyService;

    @ApiOperation(value = "统计数据")
    @PostMapping("/dataCount/{day}")
    public R createStatisticsByDate(@PathVariable("day") String day){
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @ApiOperation(value = "得到统计数据")
    @PostMapping("/getShowData/{type}/{begin}/{end}")
    public R getShowData(@PathVariable("type") String type,@PathVariable("begin") String begin,
                         @PathVariable("end") String end){
        Map<String,Object> map = dailyService.showData(type,begin,end);
        return R.ok().data(map);
    }

}
