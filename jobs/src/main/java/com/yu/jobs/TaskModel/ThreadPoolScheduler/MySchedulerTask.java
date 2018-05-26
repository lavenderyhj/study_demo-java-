package com.yu.jobs.TaskModel.ThreadPoolScheduler;

import com.yu.jobs.TaskModel.InterFace.TaskInterface;

/**
 * Created by yuhuijuan on 2018/5/22
 */

public class MySchedulerTask extends Thread implements TaskInterface<ThreadPoolScheduler> {
    private volatile boolean isRunning = false;
    private ThreadPoolScheduler scheduler;
    private boolean isStop = false;
    private String period = "10";
    private int count = 0;


    public String getTaskName() {
        return name;
    }

    private String name = getId() + "-" + System.currentTimeMillis();

    @Override
    public void run() {
        isRunning = true;
        execute();
        isRunning = false;
    }

    private void execute() {
        System.out.println(name + "**********************TimerTask -start success");
        try {
            while (!Thread.currentThread().isInterrupted() && count < 5 && !isStop) {
                System.out.println(name + "TimerTask -running success");
                Thread.sleep(1000);
                count++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count = 0;
        isStop = false;
        System.out.println(name + "**********************TimerTask -end success");

    }

    @Override
    public boolean isTaskRunning() {
        return isRunning;
    }

    @Override
    public void reStart() {
        clear();
        register(scheduler);
    }

    @Override
    public boolean isRegistered() {
        return scheduler != null;
    }

    @Override
    public void register(ThreadPoolScheduler scheduler) {
        this.scheduler = scheduler;
        scheduler.addjob(this, period);
    }

    @Override
    public void stopTask() {
        isStop = true;
    }

    @Override
    public void clear() {
        checkIfSchedulerExist();
        scheduler.removeJob(this);
    }

    @Override
    public void setCron(String cron) {
        period = cron;
        reStart();
    }

    private void checkIfSchedulerExist() {
        if (this.scheduler == null) {
            throw new NullPointerException(getClass() + " doesn't register a valid scheduler");
        }
    }
}
