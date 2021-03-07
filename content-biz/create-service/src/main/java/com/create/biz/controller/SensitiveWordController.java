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
import java.util.List;

/**
 * @author xmy
 * @date 2021/2/6 18:06
 */
@Api(tags = "否词管理")
@RestController
@RequestMapping("/creation/negative")
@CrossOrigin
public class SensitiveWordController {

    @Resource
    private SensitiveWordService sensitiveWordService;

    @ApiOperation(value = "展示否词")
    @GetMapping("/selectWord")
    public R selectNegativeWord(){
        List<SensitiveWord> list = sensitiveWordService.list();
        return R.ok().data("word",list);
    }

    @ApiOperation(value = "添加否词")
    @PostMapping("/addWord")
    public R addNegativeWord(@RequestBody SensitiveWordVO sensitiveWordVO){
        SensitiveWord sensitiveWord = new SensitiveWord();
        BeanUtils.copyProperties(sensitiveWordVO,sensitiveWord);
        sensitiveWordService.save(sensitiveWord);
        return R.ok();
    }

    @ApiOperation(value = "删除否词")
    @PostMapping("/deleteWord/{id}")
    public R deleteNegativeWord(@PathVariable("id") Long id){
        boolean b = sensitiveWordService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "根据id查询否词")
    @GetMapping("/selectWordById/{id}")
    public R selectByIdNegativeWord(@PathVariable Long id){
        SensitiveWord byId = sensitiveWordService.getById(id);
        return R.ok().data("byId",byId);
    }

    @ApiOperation(value = "修改否词")
    @PostMapping("/updateWord")
    public R updateNegativeWord(String word){
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setWord(word);
        sensitiveWordService.updateById(sensitiveWord);
        return R.ok();
    }



}
