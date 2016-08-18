package com.cxstock.action.common;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule; 

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class StarOfSharingQuartz {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	
	//每月1号0点
	public static void addJob()throws Exception {
		removeJob();
		
		Scheduler scheduler = schedulerFactory.getScheduler();
		//可以通过SchedulerFactory创建一个Scheduler实例		
		//设置工作详情	
		JobDetail job = newJob(StarOfSharingJob.class).withIdentity("StarOfSharingTask", "StarOfSharingGroup").requestRecovery().build(); 	
		Trigger trigger =   (CronTrigger)newTrigger().withIdentity("StarOfSharingTask", "StarOfSharingGroup").
									withSchedule(cronSchedule("0 0 0 1 * ?")).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();	
	}
	
	public static void removeJob() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobKey jobKey = JobKey.jobKey("StarOfSharingTask", "StarOfSharingGroup");
		TriggerKey triggerKey = TriggerKey.triggerKey("StarOfSharingTask", "StarOfSharingGroup");
		if(sched.checkExists(triggerKey)){
			sched.pauseTrigger(triggerKey);// 停止触发器  
	        sched.unscheduleJob(triggerKey);// 移除触发器  
		}
		if(sched.checkExists(jobKey)){
	        sched.deleteJob(jobKey);// 删除任务  
		}
	}
	
		
}