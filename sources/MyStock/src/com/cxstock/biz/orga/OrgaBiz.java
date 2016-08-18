package com.cxstock.biz.orga;

import java.util.List;

import com.cxstock.pojo.Orga;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface OrgaBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateOrga(Orga orga);
	
	/**
	 * 删除 
	 */
	public boolean deleteOrga(Integer id);
	
	/**
	 * 下拉数据
	 */
	public List findOrga();
	
	
	public void findPageOrga(Page page);
}
