package com.cxstock.biz.groundtype;

import java.util.List;

import com.cxstock.pojo.GroundType;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface GroundTypeBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateGroundType(GroundType GroundType);
	
	/**
	 * 删除 
	 */
	public boolean deleteGroundType(Integer id);
	
	/**
	 * 下拉数据
	 */
	public List findGroundType();
	
	public void findPageGroundType(Page page);
	
}
