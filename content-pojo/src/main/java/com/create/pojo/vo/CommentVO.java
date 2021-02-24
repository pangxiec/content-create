package com.create.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/20 14:14
 */
@Data
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "上级评论id")
    private String pid;

    @ApiModelProperty(value = "评论人id")
    private String fromUserId;

    @ApiModelProperty(value = "被评论人id")
    private String toUserId;

    @ApiModelProperty(value = "评论内容")
    private String content;

}
