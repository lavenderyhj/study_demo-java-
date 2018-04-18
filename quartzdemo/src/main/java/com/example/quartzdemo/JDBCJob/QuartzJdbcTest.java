package com.example.quartzdemo.JDBCJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

/**
 * Created by yuhuijuan on 2018/2/22
 */
public class QuartzJdbcTest {
    public static void main(String[] args) {
//		startSchedule();
        resumeJob();
    }

    //开始一个simpleSchedule调度

    public static void startSchedule() {
        try {
            //1。创建一个JObDetail实例，指定Quartz
            JobDetail jobDetail = JobBuilder.newJob(JDBCJob.class)
                    .withIdentity("job1_1", "jGroup1")
                    .storeDurably(true)
                    .build();

            // 触发器类型   设置执行次数
//			SimpleScheduleBuilder builder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(5);
            CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
            //2.创建Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("trigger1_1", "tGroup1")
                    .startNow()
                    .withSchedule(builder)
                    .build();

            //3.创建 Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            //4.调度执行

            scheduler.scheduleJob(jobDetail, trigger);

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //5.关闭调度器

            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //从数据库找到已存在的job ，并重新开户调度
    private static void resumeJob() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = new JobKey("job1_1", "jGroup1");

            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            //SELECT TRIGGER_NAME, TRIGGER_GROUP FROM {0}TRIGGERS WHERE SCHED_NAME = {1} AND JOB_NAME = ? AND JOB_GROUP = ?
            // 重新恢复在jGroup1组中，名为job1_1的 job的触发器运行

            if (triggers.size() > 0) {
                for (Trigger trigger : triggers) {
                    if ((trigger instanceof CronTrigger) || (trigger instanceof SimpleTrigger)) {
                        // 恢复job运行
                        scheduler.resumeJob(jobKey);
                    }
                }
                scheduler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
