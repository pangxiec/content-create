package com.create.biz.service;

import java.util.Map;

/**
 * @author xmy
 * @date 2021/2/20 15:28
 */
public interface MailService {

    void sendSimpleMailMessge(String to, String subject, String content);

    void sendMimeMessge(String to, String subject, String content);

    void sendMimeMessge(String to, String subject, String content, String filePath);

    void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap);

}
