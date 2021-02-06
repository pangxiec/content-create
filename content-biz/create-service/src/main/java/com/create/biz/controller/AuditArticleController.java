package com.create.biz.controller;

import com.create.biz.service.SensitiveWordService;
import com.create.common.utils.R;
import com.create.pojo.domain.SensitiveWord;
import com.create.pojo.vo.SensitiveWordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmy
 * @date 2021/2/6 18:06
 */
@Api(tags = "否词管理")
@RestController
@RequestMapping("/negative")
@CrossOrigin
public class AuditArticleController {

    @Resource
    private SensitiveWordService sensitiveWordService;

    @ApiOperation("添加否词")
    @PostMapping("/addWord")
    public R addNegativeWord(@RequestBody SensitiveWordVO sensitiveWordVO){
        SensitiveWord sensitiveWord = new SensitiveWord();
        BeanUtils.copyProperties(sensitiveWordVO,sensitiveWord);
        sensitiveWordService.save(sensitiveWord);
        return R.ok();
    }

}
