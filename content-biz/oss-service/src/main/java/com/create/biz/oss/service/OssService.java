package com.create.biz.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xmy
 * @date 2021/2/1 12:15
 */
public interface OssService {

    /**
     * 上传头像到 OSS
     *
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);

}
