package com.create.ucenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.mapper.LoginLogMapper;
import com.create.pojo.domain.LoginLog;
import com.create.ucenter.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/5 11:13
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
