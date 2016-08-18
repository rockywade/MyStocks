package com.cxstock.action.common;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.utils.system.ServiceHelper;

public class StarOfSharingJob implements Job {
	
	public StarOfSharingJob() {
		
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TriggerKey triggerKey = context.getTrigger().getKey();
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		try { 
			//获取上下下文数据
			 DatumInfoBiz  datumInfoBiz = ServiceHelper.getDatumInfoBiz();
			  datumInfoBiz.saveOrUpdateDataJiFen();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				removeJob(jobKey,triggerKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void removeJob(JobKey jobKey, TriggerKey tiKey) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(tiKey);
		// 停止触发器
		sched.unscheduleJob(tiKey);
		// 删除任务
		sched.deleteJob(jobKey);
	}
	
}