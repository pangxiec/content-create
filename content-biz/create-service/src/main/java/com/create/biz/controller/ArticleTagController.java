package com.create.biz.controller;

import com.create.biz.service.ArticleTagService;
import com.create.common.utils.R;
import com.create.pojo.domain.ArticleTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xmy
 * @date 2021/2/19 11:58
 */
@Api(tags = "文章标签管理")
@RestController
@RequestMapping("/creation/tag")
@CrossOrigin
public class ArticleTagController {

    @Resource
    private ArticleTagService articleTagService;

    @ApiOperation(value = "文章标签展示")
    @GetMapping("/selectArticleTag")
    public R selectArticleTag(){
        List<ArticleTag> list = articleTagService.list();
        return R.ok().data("tag",list);
    }

    @ApiOperation(value = "增加文章标签")
    @PostMapping("/addArticleTag")
    public R addArticleTag(){
        return R.ok();
    }

}
