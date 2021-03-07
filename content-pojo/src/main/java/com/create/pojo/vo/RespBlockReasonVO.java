package com.create.pojo.vo;

import lombok.Data;

/**
 * @author xmy
 * @date 2021/3/5 11:22
 */
@Data
public class RespBlockReasonVO {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private String label;

    private Double rate;

    private String context;

    private String image_url;

}
