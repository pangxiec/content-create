package com.create.pojo.dto;

import com.create.pojo.enums.AuditStatusEnum;
import com.create.pojo.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xmy
 * @date 2021/2/1 17:53
 */
@Data
public class ArticleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者ID")
    private String authorId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章分类")
    private CategoryEnum categoryId;

    @ApiModelProperty(value = "文章概述")
    private String summary;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "审核状态")
    private AuditStatusEnum auditStatus;

    @ApiModelProperty(value = "审核人Id")
    private Long auditId;

    @ApiModelProperty(value = "审核人姓名")
    private String auditName;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "浏览量")
    private Integer viewsCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentsCount;

    @ApiModelProperty(value = "置顶（0:不置顶，1:置顶）")
    private Boolean isTop;

    @ApiModelProperty(value = "文章图片")
    private String imageUrl;

    @ApiModelProperty(value = "发表日期")
    private Date createTime;

    @ApiModelProperty(value = "修改日期")
    private Date updateTime;

}
