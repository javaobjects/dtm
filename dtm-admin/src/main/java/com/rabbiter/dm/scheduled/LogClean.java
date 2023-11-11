package com.rabbiter.dm.scheduled;

import com.rabbiter.dm.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时删除日志
 *
 * @author xianxian
 * @date 2022-01-21
 */
@Component
public class LogClean {
    @Autowired
    private SystemLogService systemLogService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void logClean() {
        systemLogService.deleteByMonth();
    }
}
