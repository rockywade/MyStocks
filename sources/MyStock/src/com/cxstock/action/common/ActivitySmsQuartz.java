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

public class ActivitySmsQuartz {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	
	//date执行节点时间  logId场地id
	public static void addJob(String date, String activityid,String phone,String content)throws Exception {
		removeJob(activityid);
		Scheduler scheduler = schedulerFactory.getScheduler();
		//可以通过SchedulerFactory创建一个Scheduler实例		
		//设置工作详情	
		JobDetail job = newJob(ActivitySmsJob.class).withIdentity("ActivitySmsTask_"+activityid, "ActivitySmsTaskGroup_"+activityid).requestRecovery().build(); 	
		job.getJobDataMap().put("activityid", activityid);
		job.getJobDataMap().put("phone", phone);
		job.getJobDataMap().put("content", content);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(date);
		//Date转String	//设置触发器	
		SimpleTrigger trigger = (SimpleTrigger)newTrigger().withIdentity("ActivitySmsTask_"+activityid, "ActivitySmsTaskGroup_"+activityid).startAt(startDate).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();	
	}
	
	public static void removeJob(String activityid) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobKey jobKey = JobKey.jobKey("ActivitySmsTask_"+activityid, "ActivitySmsTaskGroup_"+activityid);
		TriggerKey triggerKey = TriggerKey.triggerKey("ActivitySmsTask_"+activityid, "ActivitySmsTaskGroup_"+activityid);
		if(sched.checkExists(triggerKey)){
			sched.pauseTrigger(triggerKey);// 停止触发器  
	        sched.unscheduleJob(triggerKey);// 移除触发器  
		}
		if(sched.checkExists(jobKey)){
	        sched.deleteJob(jobKey);// 删除任务  
		}
	}
	
		
}