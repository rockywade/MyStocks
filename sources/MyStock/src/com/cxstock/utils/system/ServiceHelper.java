package com.cxstock.utils.system;

import com.cxstock.biz.activity.ApplyActivityBiz;
import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.biz.sms.SmsBiz;



/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return Constants.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static SiteInfoBiz getSiteInfoBiz(){
		return (SiteInfoBiz) getService("siteInfoBiz");
	}
	
	public static SmsBiz getSmsBiz(){
		return (SmsBiz) getService("smsBiz");
	}
	
	public static DatumInfoBiz getDatumInfoBiz(){
		return (DatumInfoBiz) getService("datumInfoBiz");
	}
	
	public static ApplyActivityBiz getApplyActivityBiz(){
		return (ApplyActivityBiz) getService("applyActivityBiz");
	}
	
}
