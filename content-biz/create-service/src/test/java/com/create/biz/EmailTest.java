package com.create.biz;

import com.create.biz.service.MailService;
import com.create.common.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/7 11:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Resource
    private MailService mailService;

    private static final String TO = "1743185983@qq.com"; //2443056742@qq.com
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";

    /**
     * 测试发送普通邮件
     */
    @Test
    public void sendSimpleMailMessage() {
        mailService.sendSimpleMailMessge(TO, SUBJECT, CONTENT);
    }

    @Test
    public void sendHtmlMessage() {
        String htmlStr = "<h1>Test</h1>";
        mailService.sendMimeMessge(TO, SUBJECT, htmlStr);
    }

}
