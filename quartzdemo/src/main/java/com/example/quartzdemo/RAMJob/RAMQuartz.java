package com.example.quartzdemo.RAMJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by yuhuijuan on 2018/2/22
 */
public class RAMQuartz {
    private final static Logger _log = LoggerFactory.getLogger(RAMQuartz.class);

    public static void main(String[] args) throws SchedulerException {
        //1.创建Scheduler工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //2.从工厂获取Scheduler实例
        Scheduler scheduler = schedulerFactory.getScheduler();

        //3.创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(RAMJob.class)
                .withDescription("this is a ram job") //job描述
                .withIdentity("ramJob", "ramGroup")//job的name 和group
                .build();

        //任务运行的时间，SimpleSchedle类型触发器有效
        long time = System.currentTimeMillis() + 3 * 1000L; //3秒后启动任务
        Date statTime = new Date(time);
        //4.创建Trigger

        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramTrigger", "ramTriggerGroup")
                .startAt(statTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))//每2秒 执行一次
                .build();

        //5.注册任务和定时器
        scheduler.scheduleJob(jobDetail, trigger);

        //6.启动调度器

        scheduler.start();
        _log.info("启动时间：" + new Date());

    }
}
