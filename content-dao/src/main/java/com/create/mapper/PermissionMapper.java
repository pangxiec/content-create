package com.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.create.pojo.domain.Permission;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/23 14:03
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取所有得权限值
     * @return
     */
    List<String> selectAllPermissionValue();

    List<String> selectPermissionValueByUserId(Long id);

    List<Permission> selectPermissionByUserId(Long userId);
}
