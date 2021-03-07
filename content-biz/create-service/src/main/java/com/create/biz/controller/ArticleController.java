package com.create.biz.controller;

import com.create.biz.service.ArticleService;
import com.create.common.annotation.OperateLog;
import com.create.common.constant.BusinessTypeConstants;
import com.create.common.constant.ModuleConstants;
import com.create.common.enums.AuditStatusEnum;
import com.create.common.utils.PageResult;
import com.create.common.utils.R;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ArticleDTO;
import com.create.pojo.dto.OutputBlockReasonDTO;
import com.create.pojo.vo.ArticleQueryVO;
import com.create.pojo.vo.ArticleVO;
import com.create.pojo.vo.RespBlockReasonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xmy
 * @date 2021/2/1 14:39
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/creation/article")
@CrossOrigin
public class ArticleController {

    @ApiOperation(value = "测试登录")
    @PostMapping("/adminuser/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "测试登录信息")
    @GetMapping("/adminuser/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://edu-929.oss-cn-beijing.aliyuncs.com/2021/02/04/845ea08910c047ff972abb6d3349164a15.png");
    }

    @Resource
    private ArticleService articleService;

    @ApiOperation("分页查询文章")
    @GetMapping("/{current}/{limit}")
    @OperateLog(module = ModuleConstants.CREATE_SERVICE, businessType = BusinessTypeConstants.SELECT)
    public R selectPage(@PathVariable("current") long current,
                        @PathVariable("limit") long limit,
                        @Validated ArticleQueryVO articleQueryVO){
        PageResult<Article> pageResult = articleService.selectPage(current, limit, articleQueryVO);
        return R.ok().data("total",pageResult.getTotal()).data("rows",pageResult.getRecords());
    }

    @ApiOperation("获取文章状态")
    @GetMapping("/status")
    public R getPostStatus(){
        AuditStatusEnum[] enums = AuditStatusEnum.values();
        List<String> typeList = Arrays.asList(enums).stream().map(AuditStatusEnum::getMsg).collect(Collectors.toList());
        return R.ok().data("data",typeList);
    }

    @ApiOperation("创作文章")
    @PostMapping("/addArticle")
    public R addArticle(@RequestBody ArticleVO articleVO){
        ArticleDTO articleDto = new ArticleDTO();
        BeanUtils.copyProperties(articleVO,articleDto);
        articleService.insertArticle(articleDto);
        return R.ok().message("创作成功");
    }

    @ApiOperation("根据文章Id获取文章")
    @GetMapping("/selectArticleById/{id}")
    public R selectArticleById(@PathVariable("id") Long id){
        Article article = articleService.getById(id);
        return R.ok().data("article",article);
    }

    @ApiOperation("删除文章")
    @PostMapping("/deleteArticle/{id}")
    public R deleteArticle(@PathVariable("id") Long id){
        boolean b = articleService.removeById(id);
        if (b){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation(value = "获取审核未通过的原因")
    @GetMapping("/getFailureReason/{articleId}")
    public R getFailureReason(@PathVariable("articleId") Long articleId){
        List<OutputBlockReasonDTO> failureReasons = articleService.getFailureReasons(articleId);
        List<RespBlockReasonVO> result = failureReasons.stream().map(dto -> {
            RespBlockReasonVO respBlockReasonVO = new RespBlockReasonVO();
            BeanUtils.copyProperties(dto, respBlockReasonVO);
            return respBlockReasonVO;
        }).collect(Collectors.toList());
        return R.ok().data("result", result);
    }

    @ApiOperation("修改文章")
    @PostMapping("/updateArticle/{id}")
    public R updateArticle(@PathVariable("id") Long id,@RequestBody ArticleVO articleVO){
        ArticleDTO articleDto = new ArticleDTO();
        BeanUtils.copyProperties(articleVO,articleDto);
        articleService.updateArticleById(articleDto);
        return R.ok();
    }

    @ApiOperation(value = "统计某一天的文章数")
    @GetMapping("/createCount/{day}")
    public R createCount(@PathVariable("day") String day){
        Integer count = articleService.createCount(day);
        return R.ok().data("createCount",count);
    }


}
