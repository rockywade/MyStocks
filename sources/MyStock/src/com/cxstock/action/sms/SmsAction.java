package com.cxstock.action.sms;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.pojo.Sms;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class SmsAction extends BaseAction  {
	
	private Integer id;
	
	private String smsName;
	
	private String content;
	
	private SmsBiz smsBiz;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSmsName() {
		return smsName;
	}

	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}

	public String findPageSms(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			smsBiz.findPageSms(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改
	 */
	public String saveOrUpdateSms() {
		try {
			Sms p = new Sms(id,smsName,content);
			smsBiz.saveOrUpdateSms(p);
			if(id==null){
				this.outString("{success:true,message:'保存成功!'}");
			}else{
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
    
	/**
	 * 删除
	 */
	public String deleteSms() {
		try {
			smsBiz.deleteSms(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
