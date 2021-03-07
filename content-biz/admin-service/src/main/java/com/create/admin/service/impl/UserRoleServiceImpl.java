package com.create.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.admin.service.UserRoleService;
import com.create.mapper.UserRoleMapper;
import com.create.pojo.domain.UserRole;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/23 12:18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
