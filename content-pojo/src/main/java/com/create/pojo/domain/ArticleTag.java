package com.create.pojo.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章标签表
 *
 * @author xmy
 * @date 2021/2/7 14:15
 */
@Data
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tag_id", type = IdType.ASSIGN_ID)
    private Long tagId;

    @ApiModelProperty(value = "所属分组")
    private String groupName;

    @ApiModelProperty(value = "名称")
    private String tagName;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "引入统计")
    private Long refCount;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
