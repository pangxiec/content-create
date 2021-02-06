package com.create.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/6 14:29
 */
@Data
public class ArticleAuditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;

}
