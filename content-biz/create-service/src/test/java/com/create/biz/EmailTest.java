package com.create.biz;

import com.create.common.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xmy
 * @date 2021/2/7 11:29
 */
@SpringBootTest
public class EmailTest {

    @Test
    public void testSend(){
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendSimpleMailMessage("1743185983@qq.com","测试","测试");
    }

}
