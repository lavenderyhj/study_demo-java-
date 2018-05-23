package com.yu.jobs.TaskModel.InterFace;

/**
 * Created by yuhuijuan on 2018/5/22
 */
public interface SchedulerInterface<T> {

    //停止这个调度器，所以job都停止
    void end();

    //该服务器是否抢到job
    //TODO:GET state from db
    boolean isStealedSuc(T task);
    //加入job
    boolean addjob(T task,String cron);

    boolean removeJob(T task);
}
