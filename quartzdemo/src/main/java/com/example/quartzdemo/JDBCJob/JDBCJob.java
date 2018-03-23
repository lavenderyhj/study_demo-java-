package com.example.quartzdemo.JDBCJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuhuijuan on 2018/2/22
 */
public class JDBCJob implements Job {
	private static final Logger log = LoggerFactory.getLogger(JDBCJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.warn("JDBC job is start------------------------");
		log.warn("Hello Quartz " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		log.warn("JDBC job is end -------------------------");
	}
}
