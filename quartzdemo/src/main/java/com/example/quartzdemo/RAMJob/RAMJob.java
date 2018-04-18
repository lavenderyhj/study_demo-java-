package com.example.quartzdemo.RAMJob;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by yuhuijuan on 2018/2/22
 */
public class RAMJob implements Job {
    private final static Logger _log = LoggerFactory.getLogger(RAMJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        _log.info("Say hello to Quartz " + new Date());
    }
}
