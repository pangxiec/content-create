package com.create.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/3 14:13
 */
@Data
public class LoginInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "个人简介")
    private String description;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;


}
