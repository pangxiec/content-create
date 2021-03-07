package com.create.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.create.pojo.domain.UcenterMember;
import com.create.ucenter.service.UcenterMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/23 11:07
 */
@SpringBootTest
public class AdminTest {

    @Resource
    private UcenterMemberService userService;

    @Test
    public void test2(){
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email","244@qq.com");
        UcenterMember user = userService.getOne(wrapper);
        System.out.println(user);
    }

}
