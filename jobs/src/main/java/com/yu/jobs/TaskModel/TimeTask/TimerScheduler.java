package com.yu.jobs.TaskModel.TimeTask;

import com.yu.jobs.TaskModel.InterFace.SchedulerInterface;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by yuhuijuan on 2018/5/22
 */
//Java Timer的实现

public class TimerScheduler implements SchedulerInterface<TimerTask> {

    private TimerScheduler() {
    }

    private static TimerScheduler timerTask = new TimerScheduler();
    //守护线程
    private static Timer scheduler = new Timer(true);

    public static TimerScheduler getInstance() {
        return timerTask;
    }

    @Override
    public boolean isStealedSuc(TimerTask task) {
        //TODO:getting state from mysql
        return true;
    }

    @Override
    public boolean addjob(TimerTask task, String cron) {
        scheduler.scheduleAtFixedRate(task, (long) 0, Long.parseLong(cron));
        return true;
    }

    @Override
    public boolean removeJob(TimerTask task) {
        task.cancel();
        scheduler.purge();
        return true;
    }


    //停止整个调度系统
    @Override
    public void end() {
        scheduler.cancel();
    }


}


