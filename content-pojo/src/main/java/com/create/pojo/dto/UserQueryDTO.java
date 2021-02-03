package com.create.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/3 16:34
 */
@Data
public class UserQueryDTO implements Serializable {

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "查询开始时间", example = "2021-02-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-02-02 10:10:10")
    private String end;
}
