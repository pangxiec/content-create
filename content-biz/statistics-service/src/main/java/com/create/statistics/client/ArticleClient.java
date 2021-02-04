package com.create.statistics.client;

import com.create.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xmy
 * @date 2021/2/4 23:58
 */
@Component
@FeignClient("create-service")
public interface ArticleClient {

    /**
     * 统计一天的文章创作数
     * @param day
     * @return
     */
    @GetMapping("/creation/article/createCount/{day}")
    public R createCount(@PathVariable("day") String day);
}
