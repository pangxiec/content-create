package com.create.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.CommentService;
import com.create.mapper.CommentMapper;
import com.create.pojo.domain.Comment;
import org.springframework.stereotype.Service;

/**
 * @author xmy
 * @date 2021/2/7 14:06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
