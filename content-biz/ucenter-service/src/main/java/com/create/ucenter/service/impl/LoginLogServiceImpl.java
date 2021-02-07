package com.create.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.common.utils.PageResult;
import com.create.mapper.LoginLogMapper;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.dto.LoginLogQueryDTO;
import com.create.ucenter.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/5 11:13
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public PageResult<LoginLog> selectPage(long current, long limit, LoginLogQueryDTO logQueryDTO) {
        Page<LoginLog> page = new Page<>(current,limit);
        QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
        String begin = logQueryDTO.getBegin();
        String end = logQueryDTO.getEnd();
        if (!StringUtils.isEmpty(begin)) { wrapper.ge("create_time", begin); }
        if (!StringUtils.isEmpty(end)) { wrapper.le("create_time", end); }
        wrapper.orderByDesc("create_time");
        baseMapper.selectPage(page, wrapper);

        long total = page.getTotal();
        List<LoginLog> records = page.getRecords();

        PageResult<LoginLog> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setRecords(records);

        return pageResult;
    }
}
