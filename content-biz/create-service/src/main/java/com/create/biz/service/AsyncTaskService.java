package com.create.biz.service;

import com.create.common.utils.ThreadsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author xmy
 * @date 2021/2/19 16:46
 */
@Service
public class AsyncTaskService {
    @Value("${other.asyncTaskDelayTime}")
    private int asyncTaskDelayTime;

    @Resource
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 执行异步任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        scheduledExecutorService.schedule(task, asyncTaskDelayTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadsUtils.shutdownAndAwaitTermination(scheduledExecutorService);
    }
}
