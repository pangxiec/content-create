package com.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.create.pojo.domain.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xmy
 * @since 2021-02-02
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询某天的注册人数
     * @param day
     * @return
     */
    Integer selectRegisterCount(String day);
}
