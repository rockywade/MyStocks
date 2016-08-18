package com.cxstock.biz.headmaster;

import com.cxstock.pojo.HeadMaster;
import com.cxstock.utils.pubutil.Page;

public interface HeadMasterBiz {

	
	/**
	 * 分页查询列表
	 */
	public void findPageHeadMaster(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改 
	 */
	public boolean saveOrUpdateHeadMaster(HeadMaster headMaster);
	
	/**
	 * 删除 
	 */
	public void deleteHeadMaster(Integer id);
	

}
