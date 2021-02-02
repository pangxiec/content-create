package com.create.pojo.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.create.common.enums.AuditStatusEnum;
import com.create.common.enums.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author xmy
 * @date 2021/2/1 14:30
 */
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "作者ID")
    private String authorId;

    @ApiModelProperty(value = "作者昵称")
    private String authorName;

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

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "发表日期")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
