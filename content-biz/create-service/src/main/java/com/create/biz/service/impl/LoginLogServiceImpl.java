package com.create.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.LoginLogService;
import com.create.mapper.LoginLogMapper;
import com.create.pojo.domain.LoginLog;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/19 16:33
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper,LoginLog> implements LoginLogService {
}
