package com.cxstock.biz.lyceum;

import com.cxstock.pojo.Lyceum;
import com.cxstock.pojo.Park;
import com.cxstock.utils.pubutil.Page;

/**
 * 学园管理
 * @author root
 */
public interface LyceumBiz {
	
	/**
	 * 列表查询
	 * @param page
	 */
	public void findPageLyceum(Page page);

	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateLyceum(Lyceum lyceum);
	
	/**
	 * 删除 
	 */
	public boolean deleteLyceum(Integer id);
}
