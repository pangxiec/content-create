package com.create.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.create.admin.service.IndexService;
import com.create.admin.service.PermissionService;
import com.create.admin.service.RoleService;
import com.create.common.handler.ContentException;
import com.create.pojo.domain.Role;
import com.create.pojo.domain.User;
import com.create.ucenter.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xmy
 * @date 2021/2/23 10:48
 */
public class IndexServiceImpl implements IndexService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getUserInfo(String nikeName) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getOne(new QueryWrapper<User>().eq("nike_name",nikeName));
        if (null == user) {
            throw new ContentException(20001,"error");
        }

        //根据用户id获取角色
        List<Role> roleList = roleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(nikeName, permissionValueList);

        result.put("name", user.getNikeName());
        result.put("avatar", "https://edu-929.oss-cn-beijing.aliyuncs.com/2021/02/04/845ea08910c047ff972abb6d3349164a15.png");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     * @param nikeName
     * @return
     */
    @Override
    public List<JSONObject> getMenu(String nikeName) {
        User user = userService.getOne(new QueryWrapper<User>().eq("nike_name",nikeName));
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}
