package com.create.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/7 10:22
 */
@Data
public class LoginLogQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询开始时间", example = "2021-02-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-02-02 10:10:10")
    private String end;
}
