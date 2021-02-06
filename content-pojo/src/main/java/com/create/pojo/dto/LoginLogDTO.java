package com.create.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xmy
 * @date 2021/2/5 11:31
 */
@Data
public class LoginLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginUser;

    private String loginIp;

    private Date loginTime;
}
