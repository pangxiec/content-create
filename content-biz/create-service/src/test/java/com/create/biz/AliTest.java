package com.create.biz;

import com.create.biz.service.SensitiveWordService;
import com.create.biz.utils.AliAuditUtil;
import com.create.biz.utils.SensitiveWordInit;
import com.create.biz.utils.SensitiveWordUtil;
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
    private SensitiveWordService sensitiveWordService;

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
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();

        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setWord("傻逼");

        SensitiveWord sensitiveWord2 = new SensitiveWord();
        sensitiveWord2.setWord("智障");

        List<SensitiveWord> sensitiveWords = new ArrayList<>();
        sensitiveWords.add(sensitiveWord);
        sensitiveWords.add(sensitiveWord2);

        Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        // 传入SensitivewordEngine类中的敏感词库
        SensitiveWordUtil.sensitiveWordMap = sensitiveWordMap;
        // 得到敏感词有哪些，传入2表示获取所有敏感词
        String text = "傻逼 哈哈哈哈哈哈 智障";
        Set<String> set = SensitiveWordUtil.getSensitiveWord(text, 2);
        if (set == null || set.size() == 0){
            System.out.println("没有敏感词");
        }else {
            System.out.println("含有敏感词" + set);
        }
    }


}
