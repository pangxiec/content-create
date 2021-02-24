package com.create.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.pojo.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * @author xmy
 * @date 2021/2/23 10:52
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id获取角色
     * @param id
     * @return
     */
    List<Role> selectRoleByUserId(Long id);

    /**
     * 根据用户分配角色
     * @param userId
     * @param roleId
     */
    void saveUserRoleRealtionShip(Long userId, Long[] roleId);

    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(Long userId);
}
