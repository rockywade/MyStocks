package com.cxstock.biz.sms;

import com.cxstock.pojo.Sms;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface SmsBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateSms(Sms sms);
	
	/**
	 * 删除 
	 */
	public boolean deleteSms(Integer id);
	
	public void findPageSms(Page page);
	
	public Sms findById(Integer id);
}
