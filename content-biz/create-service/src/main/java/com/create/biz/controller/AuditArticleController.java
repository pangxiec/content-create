package com.create.biz.controller;

import com.create.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xmy
 * @date 2021/2/6 18:06
 */
@Api(tags = "否词管理")
@RestController
@RequestMapping("/negative")
@CrossOrigin
public class AuditArticleController {

    @ApiOperation("添加否词")
    @PostMapping("/")
    public R addNegativeWord(){
        return null;
    }

}
