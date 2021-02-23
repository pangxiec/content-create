package com.create.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.ArticleService;
import com.create.biz.service.CommentService;
import com.create.mapper.CommentMapper;
import com.create.pojo.domain.Comment;
import com.create.pojo.domain.User;
import com.create.pojo.dto.CommentDTO;
import com.create.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xmy
 * @date 2021/2/7 14:06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Override
    public void insertComment(CommentDTO commentDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentDTO.setFromUserId(user.getEmail());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        this.baseMapper.insert(comment);
        if (comment.getPid() == null){
            articleService.incCommentsCount(comment.getArticleId(),1);
        }
        User u = userService.getById(comment.getToUserId());
        if(u.getEmail() != null && !u.getEmail().equals("")){
            String content;
            if (comment.getPid() == null){
                content = user.getNikeName() + "评论了你的文章" + comment.getContent();
            }else {
                content = user.getNikeName() + " 回复了你: " + comment.getContent();
            }
            //TODO 邮件通知
            //emailUtil.sendEmail(u.getEmail(), "信息", content);
        }


    }

    @Override
    public List<Comment> selectArticleComment(Long articleId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("id",articleId);
        return null;
    }


}
