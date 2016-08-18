package com.cxstock.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.cxstock.action.common.ActivitySmsQuartz;
import com.cxstock.action.common.SiteInfoLogQuartz;
import com.cxstock.action.common.StarOfSharingQuartz;
import com.cxstock.biz.activity.ApplyActivityBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.pojo.Activity;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.pojo.Sms;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.ServiceHelper;


public class ScheduleStartListener implements ServletContextListener{	
	
	public void contextDestroyed(ServletContextEvent sce){
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.shutdown();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void contextInitialized(ServletContextEvent sce){		
		try {
			recovery();
		}catch (Exception e){
		}
	}
	
	
	public void recovery() throws Exception{
		try {
			 //分享之星定时任务
			 StarOfSharingQuartz.addJob();
			 recoveryActivitySms();
			 
             SiteInfoBiz  siteInfoBiz = ServiceHelper.getSiteInfoBiz();
             List<SiteInfoLog>  varList = siteInfoBiz.findAllDate1();
             if(null != varList){
              for(int i=0;i<varList.size();i++){
            	 if("待审核"==varList.get(i).getStatus() ||
            	    "待审核".equals(varList.get(i).getStatus())){
            		 String date1 =DateTime.toString1(varList.get(i).getApplyTime()); 
            		 String date = DateTime.getNewDay(date1, 1);
            		 SiteInfoLogQuartz.addJob(date,varList.get(i).getLogId());
            	  }
              }
             }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  recoveryActivitySms() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ApplyActivityBiz applyActivityBiz = ServiceHelper.getApplyActivityBiz();
		List<Activity> varList = applyActivityBiz.findAllActivity();
		if(varList!=null){
			for(Activity ac:varList){
				//获取未导入名单的活动
				int isOrNotImportFile = applyActivityBiz.findAllNotImportFileOfActivity(ac.getActivityid());
				if(isOrNotImportFile>0){
					//活动时间
					String acTime = ac.getActivitytime();
					//时长
					String acTimeLong = ac.getTimeofduration();
					//转数值类型
					String subTimeLong = acTimeLong.substring(0, acTimeLong.indexOf("小")).trim();
					double dnum = Double.parseDouble(subTimeLong);
					//转日期类型
					try {
						Date acDateTime = sdf.parse(acTime);
						//活动结束时间
						long times = (long) (acDateTime.getTime()+dnum*60*60*1000);
						//活动结束3天后时间
						long endTDLTimes = times+3*24*60*60*1000;
						Date d = new Date(endTDLTimes);
						String date = sdf.format(d);
						//获取短信内容
						SmsBiz smsBiz = ServiceHelper.getSmsBiz();
						Sms sms = smsBiz.findById(2);
						String content = sms.getContent().replace("$(HDMC)",ac.getActivityname());
						ActivitySmsQuartz.addJob(date, ac.getActivityid(), ac.getPhonenum(), content);
					}catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

