package com.create.biz.oss.controller;

import com.create.biz.oss.service.OssService;
import com.create.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传
 *
 * @author xmy
 * @date 2021/2/1 12:17
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/file/oss")
@CrossOrigin
public class OssController {

    @Resource
    private OssService ossService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
