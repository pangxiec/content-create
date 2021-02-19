package com.create.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.SysLogOperateService;
import com.create.mapper.SysLogOperateMapper;
import com.create.pojo.domain.SysLogOperate;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/19 16:42
 */
@Service
public class SysLogOperateServiceImpl extends ServiceImpl<SysLogOperateMapper,SysLogOperate> implements SysLogOperateService {
}
