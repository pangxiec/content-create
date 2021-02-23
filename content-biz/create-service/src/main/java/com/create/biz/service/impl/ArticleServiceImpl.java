package com.create.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.helper.ArticleAuditNotifyHelper;
import com.create.biz.service.ArticleService;
import com.create.biz.utils.SensitiveWordInit;
import com.create.biz.utils.SensitiveWordUtil;
import com.create.common.enums.AuditStatusEnum;
import com.create.common.enums.SuggestionEnum;
import com.create.common.handler.ContentException;
import com.create.common.utils.PageResult;
import com.create.mapper.ArticleMapper;
import com.create.mapper.SensitiveWordMapper;
import com.create.pojo.domain.Article;
import com.create.pojo.domain.SensitiveWord;
import com.create.pojo.dto.ArticleDTO;
import com.create.pojo.dto.ChangeAuditStatusDto;
import com.create.pojo.vo.ArticleQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xmy
 * @date 2021/2/1 14:39
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    ArticleAuditNotifyHelper articleAuditNotifyHelper;

    @Resource
    private SensitiveWordMapper sensitiveWordMapper;

    @Override
    public PageResult<Article> selectPage(long current, long limit, ArticleQueryVO articleQueryVO) {

        Page<Article> articlePage = new Page<>(current,limit);

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        String authorId = articleQueryVO.getAuthorId();
        String title = articleQueryVO.getTitle();
        String begin = articleQueryVO.getBegin();
        String end = articleQueryVO.getEnd();

        if (!StringUtils.isEmpty(authorId)) { queryWrapper.eq("author_id", authorId); }
        if (!StringUtils.isEmpty(title)) { queryWrapper.like("title", title); }
        if (!StringUtils.isEmpty(begin)) { queryWrapper.ge("create_time", begin); }
        if (!StringUtils.isEmpty(end)) { queryWrapper.le("create_time", end); }
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
    public void insertArticle(ArticleDTO articleDto) {
        if (StringUtils.isEmpty(articleDto.getContent())){
            throw new ContentException(20001,"创作失败 文章不能为空");
        }
        List<String> articles = new ArrayList<>();
        articles.add(articleDto.getTitle());
        articles.add(articleDto.getContent());

        Set<String> set = checkWords(String.join(",", articles));
        if (set != null){
            throw new ContentException(20001,"创作失败 文章中含有敏感词" + set);
        }
        //TODO 得到当前登录用户
        articleDto.setAuthorId("xmy");
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        articleMapper.insert(article);
        articleAuditNotifyHelper.articleEditNotify(article.getId());
    }

    @Override
    public void updateArticleById(ArticleDTO articleDto) {
        List<String> articles = new ArrayList<>();
        articles.add(articleDto.getTitle());
        articles.add(articleDto.getContent());

        Set<String> set = checkWords(String.join(",", articles));
        if (set != null){
            throw new ContentException(20001,"修改失败 文章中含有敏感词" + set);
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setAuditStatus(AuditStatusEnum.WAIT);
        articleMapper.updateById(article);
        articleAuditNotifyHelper.articleEditNotify(article.getId());
    }

    /**
     * 敏感词检测
     *
     * @param text
     * @return
     */
    public Set<String> checkWords(String text){
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        QueryWrapper<SensitiveWord> wrapper = new QueryWrapper<>();
        wrapper.select("word");
        List<SensitiveWord> sensitiveWords = sensitiveWordMapper.selectList(wrapper);
        Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        SensitiveWordUtil.sensitiveWordMap = sensitiveWordMap;
        Set<String> set = SensitiveWordUtil.getSensitiveWord(text, 2);
        if (set == null || set.size() == 0){
            return null;
        }else {
            return set;
        }
    }

    @Override
    public Integer createCount(String day) {
        Integer count = articleMapper.createCount(day);
        return count;
    }

    @Override
    public void incCommentsCount(Long articleId, Integer commentsCount) {
        this.baseMapper.incCommentsCount(articleId, commentsCount);
    }

    @Override
    public void changeAuditStatus(List<SuggestionEnum> statusCollection, Long id) {
        ChangeAuditStatusDto changeAuditStatusDto = new ChangeAuditStatusDto();
        changeAuditStatusDto.setArticleId(id);
        int flag = 0;//通过
        for (SuggestionEnum suggestionEnum : statusCollection) {
            if (suggestionEnum.getCode() == 1) { //不通过
                flag = 1;
                break;
            } else if (suggestionEnum.getCode() == 2) { //人工审核
                flag = 2;
            }
        }
        switch (flag) {
            case 0:
                changeAuditStatusDto.setStatus(AuditStatusEnum.PASS);
                break;
            case 1:
                changeAuditStatusDto.setStatus(AuditStatusEnum.BLOCK);
                break;
            default:
                changeAuditStatusDto.setStatus(AuditStatusEnum.WAIT);
                break;
        }

        updateAuditStatus(changeAuditStatusDto);

    }

    @Override
    @Transactional
    public void updateAuditStatus(ChangeAuditStatusDto inputDto) {
        boolean result = update(Wrappers.<Article>lambdaUpdate()
                .eq(Article::getId, inputDto.getArticleId())
                .set(Article::getAuditStatus, inputDto.getStatus())
                .set(Article::getAuditName, inputDto.getAuditAdmin())
                .set(Article::getAuditTime, LocalDateTime.now()));
        if (!result) {
            throw new ContentException(20001, "内容不存在或状态不需要修改!");
        }
    }
}
