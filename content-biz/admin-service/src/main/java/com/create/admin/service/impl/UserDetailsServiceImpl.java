package com.create.admin.service.impl;

import com.create.admin.service.PermissionService;
import com.create.pojo.domain.UcenterMember;
import com.create.security.entity.SecurityUser;
import com.create.ucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义userDetailsService - 认证用户详情
 *
 * @author xmy
 * @date 2021/2/23 15:07
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UcenterMemberService userService;

    @Resource
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UcenterMember user = userService.selectByUsername(username);
        // 判断用户是否存在
        if (null == user){
            //throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        UcenterMember curUser = new UcenterMember();
        BeanUtils.copyProperties(user,curUser);
        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
