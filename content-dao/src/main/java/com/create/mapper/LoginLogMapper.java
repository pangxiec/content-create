package com.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.create.pojo.domain.LoginLog;

/**
 * @author xmy
 * @date 2021/2/5 11:12
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 统计某天的登录人数
     *
     * @param day
     * @return
     */
    Integer selectLoginCount(String day);
}
