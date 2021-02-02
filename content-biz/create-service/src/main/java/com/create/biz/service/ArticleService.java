package com.create.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.common.util.PageResult;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ArticleDto;
import com.create.pojo.vo.ArticleQueryVO;

/**
 * @author xmy
 * @date 2021/2/1 14:38
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询创作内容
     * @param current
     * @param limit
     * @param articleQueryVO
     * @return
     */
    PageResult<Article> selectPage(long current, long limit, ArticleQueryVO articleQueryVO);

    void insertArticle(ArticleDto articleDto);
}
