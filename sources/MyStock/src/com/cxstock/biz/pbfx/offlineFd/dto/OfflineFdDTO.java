package com.cxstock.biz.pbfx.offlineFd.dto;

import com.cxstock.pojo.OfflineFd;

/**
 * 线下辅导接数据
 * 的实体类
 * @author root
 *
 */
public class OfflineFdDTO extends OfflineFd{
    
    //特别关注标示N:未关注，Y：已关注
 	 private String tbTag;
 	
 	 //报名标示1已报名，0未报名
 	 private String bmTag;
 	 //编号
 	 private String number;

	public String getTbTag() {
		return tbTag;
	}

	public void setTbTag(String tbTag) {
		this.tbTag = tbTag;
	}

	public String getBmTag() {
		return bmTag;
	}

	public void setBmTag(String bmTag) {
		this.bmTag = bmTag;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
 	
     
}
