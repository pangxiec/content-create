package com.create.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.create.pojo.domain.Permission;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/23 10:53
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    /**
     * 递归删除菜单
     * @param id
     */
    void removeChildById(Long id);

    /**
     * 根据用户id 获取用户菜单
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(Long id);

    /**
     * 根据用户id获取用户菜单权限
     * @param userId
     * @return
     */
    List<JSONObject> selectPermissionByUserId(Long userId);

    /**
     * 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenuContent();

    /**
     * 递归删除菜单
     * @param id
     */
    void removeChildByIdContent(Long id);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}