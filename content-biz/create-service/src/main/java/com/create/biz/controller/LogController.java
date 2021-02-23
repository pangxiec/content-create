package com.create.biz.controller;

import com.create.biz.service.LoginLogService;
import com.create.common.utils.PageResult;
import com.create.common.utils.R;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.dto.LoginLogQueryDTO;
import com.create.pojo.vo.LoginLogQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/20 15:01
 */
@Api(tags = "日志管理")
@RestController
@RequestMapping("/creation/log")
public class LogController {

    @Resource
    private LoginLogService loginLogService;

    @ApiOperation(value = "查询登录日志")
    @GetMapping("/pageLoginLog/{current}/{limit}")
    public R pageLoginLog(@PathVariable("current") long current,
                          @PathVariable("limit") long limit,
                          @Validated LoginLogQueryVO loginLogQueryVO){
        LoginLogQueryDTO logQueryDTO = new LoginLogQueryDTO();
        BeanUtils.copyProperties(loginLogQueryVO,logQueryDTO);
        PageResult<LoginLog> pageResult = loginLogService.selectPage(current, limit, logQueryDTO);
        return R.ok().data("total",pageResult.getTotal()).data("rows",pageResult.getRecords());
    }

}
