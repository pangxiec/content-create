package com.create.ucenter.controller;

import com.create.common.utils.PageResult;
import com.create.common.utils.R;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.dto.LoginLogQueryDTO;
import com.create.pojo.vo.LoginLogQueryVO;
import com.create.ucenter.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/1 16:07
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping("/loginlog")
@CrossOrigin
public class LoginLogController {

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