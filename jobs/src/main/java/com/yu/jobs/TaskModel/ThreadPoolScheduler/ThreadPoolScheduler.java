package com.yu.jobs.TaskModel.ThreadPoolScheduler;

import com.yu.jobs.TaskModel.InterFace.SchedulerInterface;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Created by yuhuijuan on 2018/5/22
 */
@Component
public class ThreadPoolScheduler implements SchedulerInterface<MySchedulerTask> {

    private ScheduledExecutorService scheduledExecutorService;

    private ConcurrentHashMap<String, Future> futureMap = new ConcurrentHashMap<>();

    @Override
    public boolean isStealedSuc(MySchedulerTask task) {
        return false;
    }

    @Override
    public boolean addjob(MySchedulerTask task, String cron) {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newScheduledThreadPool(3);
        }
        ScheduledFuture future = scheduledExecutorService.scheduleAtFixedRate(task, 0, Integer.parseInt(cron), TimeUnit.SECONDS);
        futureMap.put(task.getTaskName(), future);
        return true;
    }

    public Future getTaskfuture(MySchedulerTask task) {
        return futureMap.get(task.getTaskName());
    }

    @Override
    public boolean removeJob(MySchedulerTask task) {
        Future future = futureMap.get(task.getTaskName());
        if (future != null) {
            future.cancel(false);
            futureMap.remove(task.getTaskName());
        }
        return true;
    }


    @Override
    public void end() {
        scheduledExecutorService.shutdownNow();
        scheduledExecutorService = null;
    }


}
