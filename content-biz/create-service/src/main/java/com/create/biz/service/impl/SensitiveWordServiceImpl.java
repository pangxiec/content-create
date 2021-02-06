package com.create.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.create.biz.service.SensitiveWordService;
import com.create.biz.utils.SensitiveWordInit;
import com.create.biz.utils.SensitiveWordUtil;
import com.create.mapper.SensitiveWordMapper;
import com.create.pojo.domain.SensitiveWord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xmy
 * @date 2021/2/6 17:35
 */
@Service
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordMapper, SensitiveWord> implements SensitiveWordService {

    @Override
    public Set<String> sensitiveWordFiltering(String text) {
        // 初始化敏感词库对象
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        QueryWrapper<SensitiveWord> wrapper = new QueryWrapper<>();
        wrapper.select("word");
        // 从数据库中获取敏感词对象集合（调用的方法来自Dao层，此方法是service层的实现类）
        List<SensitiveWord> sensitiveWords = baseMapper.selectList(wrapper);
        // 构建敏感词库
        Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        // 传入SensitivewordEngine类中的敏感词库
        SensitiveWordUtil.sensitiveWordMap = sensitiveWordMap;
        // 得到敏感词有哪些，传入2表示获取所有敏感词
        Set<String> set = SensitiveWordUtil.getSensitiveWord(text, 2);
        return set;
    }
}

