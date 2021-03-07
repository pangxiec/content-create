package com.create.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.LoginLogService;
import com.create.common.utils.PageResult;
import com.create.mapper.LoginLogMapper;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.dto.LoginLogQueryDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/19 16:33
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper,LoginLog> implements LoginLogService {
    @Override
    public PageResult<LoginLog> selectPage(long current, long limit, LoginLogQueryDTO logQueryDTO) {
        Page<LoginLog> logPage = new Page<>(current,limit);
        Page<LoginLog> page = baseMapper.selectPage(logPage, Wrappers.<LoginLog>lambdaQuery()
                .ge(!StringUtils.isEmpty(logQueryDTO.getBegin()), LoginLog::getCreateTime, logQueryDTO.getBegin())
                .le(!StringUtils.isEmpty(logQueryDTO.getEnd()), LoginLog::getCreateTime, logQueryDTO.getEnd()));

        PageResult<LoginLog> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getRecords());
        return pageResult;
    }
}
