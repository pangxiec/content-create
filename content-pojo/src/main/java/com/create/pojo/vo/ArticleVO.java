package com.create.pojo.vo;

import com.create.common.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @data 2021/2/1 21:21
 */
@Data
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章分类")
    private CategoryEnum categoryId;

    @ApiModelProperty(value = "文章概述")
    private String summary;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "置顶（0:不置顶，1:置顶）")
    private Boolean isTop;

    @ApiModelProperty(value = "文章图片")
    private String imageUrl;

}
