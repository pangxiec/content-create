package com.create.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.create.pojo.domain.User;
import com.create.ucenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xmy
 * @date 2021/2/23 11:07
 */
@SpringBootTest
public class AdminTest {

    @Resource
    private UserService userService;

    @Test
    public void test2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email","244@qq.com");
        User user = userService.getOne(wrapper);
        System.out.println(user);
    }

}
