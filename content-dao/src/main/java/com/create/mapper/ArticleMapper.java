package com.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.create.pojo.domain.Article;

/**
 * @author xmy
 * @date 2021/2/1 14:33
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询某一天的创作文章数
     * @param day
     * @return
     */
    Integer createCount(String day);

}
