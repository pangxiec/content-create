package com.create.statistics.controller;

import com.create.common.utils.R;
import com.create.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/3 22:17
 */
@Api(tags = "数据统计")
@RestController
@RequestMapping("/create/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService dailyService;

    @ApiOperation(value = "统计一天的注册人数")
    @PostMapping("/{day}")
    public R createStatisticsByDate(@PathVariable("day") String day){
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

}
