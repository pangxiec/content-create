package com.create.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.create.biz.utils.AliAuditUtil;
import com.create.biz.utils.SensitiveWordInit;
import com.create.biz.utils.SensitiveWordUtil;
import com.create.common.utils.JsonUtil;
import com.create.mapper.SensitiveWordMapper;
import com.create.pojo.domain.Article;
import com.create.pojo.domain.SensitiveWord;
import com.create.pojo.domain.audit.textaudit.TextAuditResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author xmy
 * @date 2021/2/6 10:57
 */
@SpringBootTest
public class AliTest {

    @Resource
    private SensitiveWordMapper sensitiveWordMapper;

    @Test
    public void testAudit() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("今天天气真的很好, 但是法轮功NMSL!");
        list.add("你就是一个傻逼、脑残。");
        AliAuditUtil auditUtils = new AliAuditUtil();
        TextAuditResult textAuditResult = auditUtils.auditText(list);
        System.out.println(textAuditResult);
    }

    @Test
    public void beanToJson(){
        Article article = new Article();
        article.setTitle("智障");
        article.setContent("傻逼");

        List<String> articles = new ArrayList<>();
        articles.add(article.getTitle());
        articles.add(article.getContent());

        Set<String> set = checkWords(String.join(",", articles));
        if (set != null){
            System.out.println("创作失败 文章中含有敏感词" + set);
        }
    }

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

    @Test
    public void testJson(){
        Article article = new Article();
        article.setTitle("智障");
        article.setContent("傻逼");

        System.out.println(article);

        String s = JsonUtil.parseToJSON(article);
        System.out.println(s);


    }

}
