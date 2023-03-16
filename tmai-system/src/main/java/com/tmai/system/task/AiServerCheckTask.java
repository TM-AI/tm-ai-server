package com.tmai.system.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Tommy Zeng
 * 2023/3/14 19:55
 **/
@Component
public class AiServerCheckTask {



    @Scheduled(cron = "0 */5 * * * ?\n") // 每天凌晨执行
    public void myTask() {
        // 执行任务逻辑
    }
}
