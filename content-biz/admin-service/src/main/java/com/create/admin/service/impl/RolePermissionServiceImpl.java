package com.create.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.admin.service.RolePermissionService;
import com.create.mapper.RolePermissionMapper;
import com.create.pojo.domain.RolePermission;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/23 15:03
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
