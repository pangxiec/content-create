package com.create.pojo.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.create.common.enums.SuggestionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章审核表
 *
 * @author xmy
 * @date 2021/2/6 14:42
 */
@Data
public class AuditArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "文章Id")
    private Long articleId;

    @ApiModelProperty(value = "处理意见（pass、block、review）")
    private SuggestionEnum suggestion;

    @ApiModelProperty(value = "检测命中率")
    private Double rate;

    @ApiModelProperty(value = "检测结果命中类型（normal、porn...）")
    private String label;

    @ApiModelProperty(value = "检测内容(违规内容)")
    private String context;

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
