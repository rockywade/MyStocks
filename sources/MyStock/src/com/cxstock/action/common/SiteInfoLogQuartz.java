package com.cxstock.action.common;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class SiteInfoLogQuartz {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	
	//date执行节点时间  logId场地id
	public static void addJob(String date, String logId)throws Exception {
		removeJob(logId);
		Scheduler scheduler = schedulerFactory.getScheduler();
		//可以通过SchedulerFactory创建一个Scheduler实例		
		//设置工作详情	
		JobDetail job = newJob(SiteInfoLogJob.class).withIdentity("endtask_"+logId, "endgroup_"+logId).requestRecovery().build(); 	
		job.getJobDataMap().put("logId", logId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(date);
		//Date转String	//设置触发器	
		SimpleTrigger trigger = (SimpleTrigger)newTrigger().withIdentity("endtask_"+logId, "endgroup_"+logId).startAt(startDate).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();	
	}
	
	public static void removeJob(String logId) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobKey jobKey = JobKey.jobKey("endtask_"+logId, "endgroup_"+logId);
		TriggerKey triggerKey = TriggerKey.triggerKey("endtask_"+logId, "endgroup_"+logId);
		if(sched.checkExists(triggerKey)){
			sched.pauseTrigger(triggerKey);// 停止触发器  
	        sched.unscheduleJob(triggerKey);// 移除触发器  
		}
		if(sched.checkExists(jobKey)){
	        sched.deleteJob(jobKey);// 删除任务  
		}
	}
	
		
}