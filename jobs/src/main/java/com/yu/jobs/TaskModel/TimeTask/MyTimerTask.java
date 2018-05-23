package com.yu.jobs.TaskModel.TimeTask;

import com.yu.jobs.TaskModel.InterFace.SchedulerInterface;
import com.yu.jobs.TaskModel.InterFace.TaskInterface;
import com.yu.jobs.TaskModel.ThreadPoolScheduler.ThreadPoolScheduler;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * Created by yuhuijuan on 2018/5/22
 */

public class MyTimerTask implements TaskInterface<TimerScheduler> {

    private String period = "2000";
    private volatile boolean isRunning = false;
    private volatile boolean ifStop = false;
    private int count = 0;
    private TimerScheduler scheduler;
    private TimerTask currentTask;


    private void execute() {
        isRunning = true;
        System.out.println("TimerTask -start success");
        try {
            while (!ifStop && count < 5) {
                System.out.println("TimerTask -running success");
                Thread.sleep(1000);
                count++;
            }
            ifStop = false;
            count = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("TimerTask -end success");
        isRunning = false;
    }

    @Override
    public boolean isTaskRunning() {
        return isRunning;
    }

    @Override
    public void reStart() {
        checkIfSchedulerExist();
        clear();
        register(scheduler);
    }

    @Override
    public boolean isRegistered() {
        return scheduler != null;
    }


    @Override
    public void register(TimerScheduler schedulerInterface) {

        this.scheduler = schedulerInterface;
        if (currentTask == null) {
            currentTask = new TimerTask() {
                @Override
                public void run() {
                    execute();
                }
            };
        }
        checkIfSchedulerExist();
        scheduler.addjob(this.currentTask, period);
    }

    @Override
    public void stopTask() {
        ifStop = true;
    }

    @Override
    public void clear() {
        checkIfSchedulerExist();
        scheduler.removeJob(this.currentTask);
        this.currentTask = null;
    }

    @Override
    public void setCron(String cron) {
        period = cron;
        reStart();
    }

    private void checkIfSchedulerExist() {
        if (scheduler == null || currentTask == null) {
            throw new NullPointerException(this.getClass() + "doesn't register a scheduler or has a vaild task");
        }
    }
}
