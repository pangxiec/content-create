package com.create.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/3/5 11:19
 */
@Data
public class OutputBlockReasonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private String label;

    private Double rate;

    private String context;

    private String image_url;

}
