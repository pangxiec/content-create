package com.create.biz.event;

import com.create.pojo.dto.ArticleAuditDTO;

/**
 * @author xmy
 * @date 2021/2/6 14:34
 */
public class ArticleAuditEvent extends BaseEvent<ArticleAuditDTO>  {

    public ArticleAuditEvent(Object source, ArticleAuditDTO eventData) {
        super(source, eventData);
    }

}
