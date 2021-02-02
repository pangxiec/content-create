package com.create.biz.controller;

import com.create.biz.service.ArticleService;
import com.create.common.utils.PageResult;
import com.create.common.utils.R;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ArticleDto;
import com.create.pojo.vo.ArticleQueryVO;
import com.create.pojo.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/1 14:39
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/creation")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("分页查询贴子")
    @GetMapping("/article/{current}/{limit}")
    public R selectPage(@PathVariable("current") long current,
                        @PathVariable("limit") long limit,
                        @Validated ArticleQueryVO articleQueryVO){
        PageResult<Article> pageResult = articleService.selectPage(current, limit, articleQueryVO);
        return R.ok().data("total",pageResult.getTotal()).data("rows",pageResult.getRecords());
    }

    @ApiOperation("创作帖子")
    @PostMapping("/addArticle")
    public R addArticle(@RequestBody ArticleVO articleVO){
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(articleVO,articleDto);
        articleService.insertArticle(articleDto);
        return R.ok().message("创作成功");
    }


}
