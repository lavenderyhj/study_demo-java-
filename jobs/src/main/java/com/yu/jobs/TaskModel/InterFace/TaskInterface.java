package com.yu.jobs.TaskModel.InterFace;

/**
 * Created by yuhuijuan on 2018/5/22
 */
public interface TaskInterface<T> {

    boolean isRegistered();

    void register(T scheduler);

    //打断job的执行
    void stopTask();

    //清除当前job，此次执行完成后，再也不会执行
    void clear();

    //更改调度频次，此次执行完成后，以新的调度频次开始
    void setCron(String cron);

    //是否正在运行
    boolean isTaskRunning();

    //clear+start
    void reStart();
}
