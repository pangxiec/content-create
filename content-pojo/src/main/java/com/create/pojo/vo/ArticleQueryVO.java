package com.create.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @data 2021/2/1 20:51
 */
@Data
public class ArticleQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者Id")
    private String authorId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "查询开始时间", example = "2021-02-01")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-03-02")
    private String end;

}
