package com.create.ucenter.controller;

import com.create.common.utils.JwtUtils;
import com.create.common.utils.PageResult;
import com.create.common.utils.R;
import com.create.pojo.domain.User;
import com.create.pojo.dto.LoginDTO;
import com.create.pojo.dto.RegisterDTO;
import com.create.pojo.dto.UserInfoDTO;
import com.create.pojo.dto.UserQueryDTO;
import com.create.pojo.vo.*;
import com.create.ucenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xmy
 * @date 2021/2/3 9:46
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/creation/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO){
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(loginVO,loginDTO);
        String token = userService.login(loginDTO);
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO){
        RegisterDTO registerDTO = new RegisterDTO();
        BeanUtils.copyProperties(registerVO,registerDTO);
        userService.register(registerDTO);
        return R.ok().message("注册成功");
    }

    @ApiOperation("根据token获取用户登录信息")
    @GetMapping("/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        LoginInfoVO loginInfoVO = userService.getLoginInfo(memberId);
        return R.ok().data("item",loginInfoVO);
    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping("/selectPage/{current}/{limit}")
    public R selectPage(@PathVariable("current") long current, @PathVariable long limit,
                        @Validated UserQueryVO userQueryVO){
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        BeanUtils.copyProperties(userQueryVO,userQueryDTO);
        PageResult<User> pageResult = userService.selectPage(current,limit,userQueryDTO);
        return R.ok().data("total",pageResult.getTotal()).data("rows",pageResult.getRecords());
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable("id") Long id){
        boolean b = userService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/selectUserInfo/{id}")
    public R selectUserInfo(@PathVariable("id") Long id){
        User user = userService.getById(id);
        return R.ok().data("user",user);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserInfoVO userInfoVO){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfoVO,userInfoDTO);
        Boolean b = userService.updateUserInfo(userInfoDTO);
        if (b){
            return R.ok();
        }
        return R.error();
    }
}
