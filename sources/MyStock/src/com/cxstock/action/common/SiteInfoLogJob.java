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

import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.utils.system.ServiceHelper;

public class SiteInfoLogJob implements Job {
	
	public SiteInfoLogJob() {
		
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TriggerKey triggerKey = context.getTrigger().getKey();
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		try { 
			//执行删除
		    SiteInfoBiz  siteInfoBiz = ServiceHelper.getSiteInfoBiz();
		    //获取数据判断24小时是否已经审核 ,如未审核删除
		    SiteInfoLog  log =  siteInfoBiz.findSingLog(data.getString("logId"));
		    if(null !=log ){
		      if(log.getStatus()=="待审核" || "待审核".equals(log.getStatus())){
		    	siteInfoBiz.deleteSiteInfoLog(data.getString("logId"));
		     }
		    }
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