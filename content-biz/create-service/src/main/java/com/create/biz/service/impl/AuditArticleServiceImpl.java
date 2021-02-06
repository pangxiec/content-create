package com.create.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.utils.AliAuditUtil;
import com.create.common.enums.SuggestionEnum;
import com.create.biz.service.AuditArticleService;
import com.create.mapper.AuditArticleMapper;
import com.create.pojo.domain.Article;
import com.create.pojo.domain.AuditArticle;
import com.create.pojo.domain.audit.textaudit.Detail;
import com.create.pojo.domain.audit.textaudit.Element;
import com.create.pojo.domain.audit.textaudit.TextAuditResult;
import com.create.pojo.domain.audit.textaudit.TextResult;
import com.create.pojo.dto.OutputAuditArticleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xmy
 * @date 2021/2/6 14:45
 */
@Service
public class AuditArticleServiceImpl extends ServiceImpl<AuditArticleMapper, AuditArticle> implements AuditArticleService {

    @Resource
    private AliAuditUtil aliAuditUtils;

    @Override
    public void auditText(Article article, List<SuggestionEnum> statusCollection) {
        OutputAuditArticleDTO outputAuditArticleDTO = new OutputAuditArticleDTO();
        outputAuditArticleDTO.setArticleId(article.getId());

        List<String> contentList = new ArrayList<>();
        contentList.add(article.getContent());
        contentList.add(article.getTitle());

        TextAuditResult textAuditResult = null;
        try {
            textAuditResult = aliAuditUtils.auditText(contentList);
        } catch (Exception e) {
            statusCollection.add(SuggestionEnum.REVIEW);
            return;
        }

        if(textAuditResult != null) {
            List<String> contextList = new ArrayList<>();
            List<String> textLabelList = new ArrayList<>();

            int flag = 0;
            for (Element e : textAuditResult.getElements()) {
                for (TextResult r : e.getResults()) {
                    switch (r.getSuggestion()) {
                        case "pass":
                            break;
                        case "block":
                            flag = 1;
                            break;
                        default:
                            flag = 2;
                    }
                    textLabelList.add(r.getLabel());
                    outputAuditArticleDTO.setRate(r.getRate());
                    for (Detail d : r.getDetails()) {
                        if (d.getContexts().size() > 0) {
                            JSONObject jsonObject = d.getContexts().getJSONObject(0);
                            contextList.add(jsonObject.getString("context"));
                        }
                    }
                }
            }
            String contextStr = contextList.stream().collect(Collectors.joining(","));
            outputAuditArticleDTO.setContext(contextStr);
            String labelStr = textLabelList.stream().collect(Collectors.joining(","));
            outputAuditArticleDTO.setLabel(labelStr);

            switch (flag) {
                case 0:
                    outputAuditArticleDTO.setSuggestion(SuggestionEnum.PASS);
                    break;
                case 1:
                    outputAuditArticleDTO.setSuggestion(SuggestionEnum.BLOCK);
                    break;
                default:
                    outputAuditArticleDTO.setSuggestion(SuggestionEnum.REVIEW);
            }
            statusCollection.add(outputAuditArticleDTO.getSuggestion());
            AuditArticle auditArticle = new AuditArticle();
            BeanUtils.copyProperties(outputAuditArticleDTO, auditArticle);
            save(auditArticle);
        }

    }
}

