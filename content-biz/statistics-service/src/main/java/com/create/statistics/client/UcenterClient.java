package com.create.statistics.client;

import com.create.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xmy
 * @date 2021/2/3 22:36
 */
@Component
@FeignClient("ucenter-service")
public interface UcenterClient {

    /**
     * 远程调用 统计一天的注册人数
     * @param day
     * @return
     */
    @GetMapping("/creation/user/registerCount/{day}")
    public R registerCount(@PathVariable("day") String day);

    /**
     * 统计某天的登录人数
     * @param day
     * @return
     */
    @GetMapping("/loginCount/{day}")
    public R loginCount(@PathVariable("day") String day);
}
