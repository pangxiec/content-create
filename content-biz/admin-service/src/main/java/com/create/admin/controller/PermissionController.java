package com.create.admin.controller;

import com.create.admin.service.PermissionService;
import com.create.common.utils.R;
import com.create.pojo.domain.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限 菜单管理
 *
 * @author xmy
 * @date 2021/2/23 15:35
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/findAll")
    public R indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuContent();
        return R.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @PostMapping("/remove/{id}")
    public R remove(@PathVariable Long id) {
        permissionService.removeChildByIdContent(id);
        return R.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public R doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return R.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return R.ok().data("children", list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("/save")
    public R save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("/update")
    public R updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

}
