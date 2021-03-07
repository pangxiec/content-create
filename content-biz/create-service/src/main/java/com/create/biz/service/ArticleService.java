package com.create.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.common.enums.SuggestionEnum;
import com.create.common.utils.PageResult;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ArticleDTO;
import com.create.pojo.dto.ChangeAuditStatusDto;
import com.create.pojo.dto.OutputBlockReasonDTO;
import com.create.pojo.vo.ArticleQueryVO;

import java.util.List;

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

    /**
     * 创作内容
     * @param articleDto
     */
    void insertArticle(ArticleDTO articleDto);

    /**
     * 更新内容
     * @param articleDto
     */
    void updateArticleById(ArticleDTO articleDto);

    /**
     * 统计某天的创作总数
     * @param day
     * @return
     */
    Integer createCount(String day);

    /**
     * 增加文章评论数
     * @param articleId
     * @param commentsCount
     */
    void incCommentsCount(Long articleId, Integer commentsCount);

    void changeAuditStatus(List<SuggestionEnum> statusCollection, Long id);

    /**
     * 修改审核状态
     * @param inputDto
     */
    void updateAuditStatus(ChangeAuditStatusDto inputDto);

    /**
     * 获取未通过的原因
     * @param id
     * @return
     */
    List<OutputBlockReasonDTO> getFailureReasons(Long articleId);
}
