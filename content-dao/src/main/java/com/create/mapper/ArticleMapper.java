package com.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.create.pojo.domain.Article;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 增加文章评论数
     * @param articleId
     * @param counts
     * @return
     */
    int incCommentsCount(@Param("articleId") Long articleId, @Param("counts") int counts);
}
