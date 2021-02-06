package com.create.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.create.common.enums.SuggestionEnum;
import com.create.pojo.domain.Article;
import com.create.pojo.domain.AuditArticle;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/6 14:44
 */
public interface AuditArticleService extends IService<AuditArticle> {

    /**
     * 文本审核
     *
     * @param article
     * @param statusCollection
     */
    void auditText(Article article, List<SuggestionEnum> statusCollection);

}
