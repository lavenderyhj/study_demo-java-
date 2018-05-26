package com.yu.jobs.Service;

import com.yu.jobs.TaskModel.InterFace.SchedulerInterface;
import com.yu.jobs.TaskModel.InterFace.TaskInterface;
import com.yu.jobs.TaskModel.ThreadPoolScheduler.MySchedulerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuhuijuan on 2018/5/22
 */
@Service
public class SchedulerService2 {
    @Autowired
    private SchedulerInterface scheduler;

    private TaskInterface task = new MySchedulerTask();

    public void start() {

        task.register(scheduler);

    }

    public void stop() {
        task.stopTask();
    }

    public void clear() {
        task.clear();
    }

    public void end() {
        scheduler.end();
    }

    public void setCron(String cron) {
        task.setCron(cron);
    }

    public void reStart() {
        task.reStart();
    }

    public boolean isRunning() {
        return task.isTaskRunning();
    }


}
