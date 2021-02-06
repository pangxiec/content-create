package com.create.biz.helper;

import com.create.biz.event.ArticleAuditEvent;
import com.create.common.utils.SpringContextUtils;
import com.create.pojo.dto.ArticleAuditDTO;
import org.springframework.stereotype.Component;

/**
 * @author xmy
 * @date 2021/2/6 14:26
 */
@Component
public class ArticleAuditNotifyHelper {

    public void articleEditNotify(Long articleId) {
        ArticleAuditDTO auditDTO = new ArticleAuditDTO();
        auditDTO.setArticleId(articleId);

        SpringContextUtils.getApplicationContext().publishEvent(new ArticleAuditEvent(this, auditDTO));
    }

}
