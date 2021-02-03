package com.create.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/3 9:41
 */
@Data
@ApiModel(value = "注册对象",description = "注册对象")
public class RegisterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

}
