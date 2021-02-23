package com.create.biz.controller;

import com.create.biz.service.CommentService;
import com.create.common.utils.R;
import com.create.pojo.dto.CommentDTO;
import com.create.pojo.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/20 14:09
 */
@Api(tags = "文章评论")
@RestController
@RequestMapping("/creation/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "增加评论")
    @PostMapping("/addComment")
    public R addComment(@RequestBody CommentVO commentVO){
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO,commentDTO);
        commentService.insertComment(commentDTO);
        return R.ok();
    }

    @ApiOperation(value = "查询评论")
    @GetMapping("/getArticleComment/{articleId}")
    public R getArticleComment(@PathVariable("articleId") Long articleId){
        commentService.selectArticleComment(articleId);
        return R.ok();
    }


}
