package com.create.pojo.dto;

import com.create.common.enums.SuggestionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/6 14:56
 */
@Data
public class OutputAuditArticleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long articleId;

    private SuggestionEnum suggestion;

    private Double rate;

    private String label;

    private String context;

}
