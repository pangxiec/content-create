package com.create.biz.controller;

import com.create.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author xmy
 * @date 2021/2/1 16:07
 */
@Api(tags = "登录管理")
@RestController
@RequestMapping("/creation/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}