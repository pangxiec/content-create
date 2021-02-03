package com.create.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.common.utils.PageResult;
import com.create.pojo.domain.User;
import com.create.pojo.dto.LoginDTO;
import com.create.pojo.dto.RegisterDTO;
import com.create.pojo.dto.UserInfoDTO;
import com.create.pojo.dto.UserQueryDTO;
import com.create.pojo.vo.LoginInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xmy
 * @since 2021-02-02
 */
public interface UserService extends IService<User> {


    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    String login(LoginDTO loginDTO);

    /**
     * 用户注册
     * @param registerDTO
     */
    void register(RegisterDTO registerDTO);

    /**
     * 根据token获取用户信息
     * @param memberId
     * @return
     */
    LoginInfoVO getLoginInfo(String memberId);

    /**
     * 分页查询用户
     * @param current
     * @param limit
     * @param userQueryDTO
     */
    PageResult<User> selectPage(long current, long limit, UserQueryDTO userQueryDTO);

    Boolean updateUserInfo(UserInfoDTO userInfoDTO);
}
