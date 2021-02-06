package com.create.biz.listener;

import com.create.common.enums.SuggestionEnum;
import com.create.biz.event.ArticleAuditEvent;
import com.create.biz.service.ArticleService;
import com.create.biz.service.AuditArticleService;
import com.create.pojo.domain.Article;
import com.create.pojo.dto.ChangeAuditStatusDto;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云智能审核事件监听
 *
 * @author xmy
 * @date 2021/2/6 14:37
 */
@Component
public class ArticleAuditListener extends BaseListener implements ApplicationListener<ArticleAuditEvent> {

    @Resource
    private ArticleService articleService;

    @Resource
    private AuditArticleService auditArticleService;

    @Override
    @Async("articleAuditExecutor")
    public void onApplicationEvent(ArticleAuditEvent event) {
        List<SuggestionEnum> statusCollection = new ArrayList<>();
        ChangeAuditStatusDto changeAuditStatusDto = new ChangeAuditStatusDto();
        Article article = articleService.getById(event.getEventData().getArticleId());
        Long articleId = article.getId();
        changeAuditStatusDto.setArticleId(articleId);

        auditArticleService.auditText(article, statusCollection);
        articleService.changeAuditStatus(statusCollection, article.getId());
    }
}
