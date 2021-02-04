package com.create.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.ArticleService;
import com.create.common.handler.ContentException;
import com.create.common.utils.PageResult;
import com.create.mapper.ArticleMapper;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ArticleDto;
import com.create.pojo.vo.ArticleQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xmy
 * @date 2021/2/1 14:39
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PageResult<Article> selectPage(long current, long limit, ArticleQueryVO articleQueryVO) {

        Page<Article> articlePage = new Page<>(current,limit);

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        String authorId = articleQueryVO.getAuthorId();
        String title = articleQueryVO.getTitle();
        String begin = articleQueryVO.getBegin();
        String end = articleQueryVO.getEnd();

        if (!StringUtils.isEmpty(authorId)){
            queryWrapper.eq("author_id",authorId);
        }
        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("create_time",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("update_time",end);
        }
        queryWrapper.orderByDesc("create_time");

        articleMapper.selectPage(articlePage, queryWrapper);

        long total = articlePage.getTotal();
        List<Article> records = articlePage.getRecords();

        PageResult<Article> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setRecords(records);

        return pageResult;
    }

    @Override
    public void insertArticle(ArticleDto articleDto) {
        if (articleDto == null){
            throw new ContentException(201,"文章不能为空");
        }
        //过滤敏感词

        articleDto.setAuthorId("xmy");
        //TODO 得到当前登录用户
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        //TODO 创作之后需要审核
        articleMapper.insert(article);
    }

    @Override
    public void updateArticleById(ArticleDto articleDto) {
        //TODO 修改也需要审核

    }

    @Override
    public Integer createCount(String day) {
        Integer count = articleMapper.createCount(day);
        return count;
    }
}
