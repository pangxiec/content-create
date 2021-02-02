package com.create.ucenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.mapper.UserMapper;
import com.create.pojo.domain.User;
import com.create.ucenter.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
