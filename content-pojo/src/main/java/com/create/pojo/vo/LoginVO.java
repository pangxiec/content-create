package com.create.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/3 9:39
 */
@Data
@ApiModel(value = "登录对象",description = "登录对象")
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

}
