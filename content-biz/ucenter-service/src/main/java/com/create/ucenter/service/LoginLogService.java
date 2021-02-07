package com.create.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.common.utils.PageResult;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.dto.LoginLogQueryDTO;
import com.create.pojo.vo.LoginLogQueryVO;

/**
 * @author xmy
 * @date 2021/2/5 11:13
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 分页查询登录日志
     *
     * @param current
     * @param limit
     * @param logQueryDTO
     * @return
     */
    PageResult<LoginLog> selectPage(long current, long limit, LoginLogQueryDTO logQueryDTO);
}
