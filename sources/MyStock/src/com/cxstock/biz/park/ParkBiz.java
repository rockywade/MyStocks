package com.cxstock.biz.park;

import java.util.List;

import com.cxstock.pojo.Park;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface ParkBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdatePark(Park Park);
	
	/**
	 * 删除 
	 */
	public boolean deletePark(Integer id);
	
	/**
	 * 下拉数据
	 */
	public List findPark();
	
	public void findPagePark(Page page);
	
}
