package com.create.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/8 0:18
 */
@Data
public class ArticleTagQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询开始时间", example = "2021-02-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-02-02 10:10:10")
    private String end;
}
