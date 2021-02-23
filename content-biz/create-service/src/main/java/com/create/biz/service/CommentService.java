package com.create.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.pojo.domain.Comment;
import com.create.pojo.dto.CommentDTO;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/7 14:06
 */
public interface CommentService extends IService<Comment> {

    /**
     * 增加评论
     * @param commentDTO
     */
    void insertComment(CommentDTO commentDTO);

    /**
     * 获取文章评论
     * @param articleId
     * @return 文章列表
     */
    List<Comment> selectArticleComment(Long articleId);
}

