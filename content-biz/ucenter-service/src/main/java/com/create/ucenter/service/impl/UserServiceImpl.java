package com.create.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.common.handler.ContentException;
import com.create.common.utils.JwtUtils;
import com.create.common.utils.MD5Utils;
import com.create.common.utils.PageResult;
import com.create.mapper.UserMapper;
import com.create.pojo.domain.Article;
import com.create.pojo.domain.User;
import com.create.pojo.dto.LoginDTO;
import com.create.pojo.dto.RegisterDTO;
import com.create.pojo.dto.UserInfoDTO;
import com.create.pojo.dto.UserQueryDTO;
import com.create.pojo.vo.LoginInfoVO;
import com.create.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public String login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new ContentException(20001,"error");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User user = baseMapper.selectOne(wrapper);
        if (null == user){
            throw new ContentException(20001,"error");
        }
        if (!MD5Utils.encrypt(password).equals(user.getPassword())){
            throw new ContentException(20001,"error");
        }
        if (user.getIsDisabled()){
            throw new ContentException(20001,"error");
        }
        String token = JwtUtils.getJwtToken(user.getId(),user.getNikeName());
        return token;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        String nikeName = registerDTO.getNikeName();
        String password = registerDTO.getPassword();
        //TODO 手机验证码
        String code = registerDTO.getCode();

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(nikeName)
                || StringUtils.isEmpty(password)){
            throw new ContentException(20001,"error");
        }

        Integer count = baseMapper.selectCount(new QueryWrapper<User>().eq("email", email));
        if (count.intValue() > 0){
            throw new ContentException(20001,"error");
        }

        User user = new User();
        user.setEmail(email);
        user.setNikeName(nikeName);
        user.setPassword(MD5Utils.encrypt(password));
        user.setIsDisabled(false);
        user.setAvatar("https://edu-929.oss-cn-beijing.aliyuncs.com/2021/02/02/00faf414b1de45088a4ce7416cb425131.jpg");
        this.save(user);

    }

    @Override
    public LoginInfoVO getLoginInfo(String memberId) {
        User member = baseMapper.selectById(memberId);
        LoginInfoVO loginInfoVo = new LoginInfoVO();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }

    @Override
    public PageResult<User> selectPage(long current, long limit, UserQueryDTO userQueryDTO) {
        Page<User> userPage = new Page<>(current,limit);

        String nikeName = userQueryDTO.getNikeName();
        String email = userQueryDTO.getEmail();
        String begin = userQueryDTO.getBegin();
        String end = userQueryDTO.getEnd();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(nikeName)){
            queryWrapper.eq("nike_name",nikeName);
        }
        if (!StringUtils.isEmpty(email)){
            queryWrapper.eq("email",email);
        }
        if(!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("create_time",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("create_time",end);
        }
        queryWrapper.orderByDesc("create_time");
        baseMapper.selectPage(userPage,queryWrapper);

        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        System.out.println(total);

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setRecords(records);

        return pageResult;
    }

    @Override
    public Boolean updateUserInfo(UserInfoDTO userInfoDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInfoDTO,user);
        int update = baseMapper.updateById(user);
        if (update > 0){
            return true;
        }
        return false;
    }

    @Override
    public Integer countRegisterDay(String day) {
        Integer count = baseMapper.selectRegisterCount(day);
        return count;
    }
}
