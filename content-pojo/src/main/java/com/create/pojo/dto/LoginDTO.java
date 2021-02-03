package com.create.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/3 9:49
 */
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

}
