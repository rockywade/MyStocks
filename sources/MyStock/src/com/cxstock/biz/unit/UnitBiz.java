package com.cxstock.biz.unit;

import java.util.List;

import com.cxstock.pojo.Unit;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface UnitBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateUnit(Unit unit);
	
	/**
	 * 删除 
	 */
	public boolean deleteUnit(Integer id);
	
	/**
	 * 下拉数据
	 */
	public List findUnit();
	
	/**
	 * 下拉数据
	 */
	public List findUnitAll();
	
	public void findPageUnit(Page page);
	
}
