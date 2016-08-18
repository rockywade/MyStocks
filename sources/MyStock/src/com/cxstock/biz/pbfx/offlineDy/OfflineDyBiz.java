package com.cxstock.biz.pbfx.offlineDy;

import com.cxstock.utils.pubutil.Page;


/**
 * 线下答疑的接口
 * @author root
 *
 */
public interface OfflineDyBiz {
	
	/**
	 * 线下答疑列表查询
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName
	 */
	public void  findPageOfflineDy(Page page, String[] property,String[] value, String orderName);

}
