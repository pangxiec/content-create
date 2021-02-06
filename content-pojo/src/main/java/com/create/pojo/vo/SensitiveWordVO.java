package com.create.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/6 21:42
 */
@Data
public class SensitiveWordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String word;
}
