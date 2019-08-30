package com.example.springbootdemo.common.task;

import com.example.springbootdemo.config.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.common.task
 * @ClassName: SchedulerTask
 * @Author: limingxing
 * @Description: TODO 测试定时任务类
 * @Date: 2019/7/25 12:26
 * @Version: 1.0
 */
@Component
public class SchedulerTask {

    /**
     * @Method taskTest
     * @Author limingxing
     * @Version  1.0
     * @Description 每30分钟执行一次
     * @param
     * @Return void
     * @Exception
     * @Date 2019/7/25 12:30
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    private void taskTest(){
        System.err.println(TimeUtil.dateFormat("yyyy-MM-dd HH:mm:ss") + " 定时任务");;
    }
}
