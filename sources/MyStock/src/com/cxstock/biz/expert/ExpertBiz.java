package com.cxstock.biz.expert;

import java.util.List;

import com.cxstock.pojo.Expert;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

public interface ExpertBiz {

	
	/**
	 * 分页查询列表
	 */
	public void findPageExpert(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改 
	 */
	public boolean saveOrUpdateExpert(Expert expert);
	
	/**
	 * 删除 
	 */
	public void deleteExpert(Integer id);


	/**
	 * 删除多个 
	 */
	public void deleteExperts(List<Integer> ids);
	

	/**
	 * 专家类别下拉菜单
	 * @return
	 */
	public List<ComboData> findEtComb();

	/**
	 * 专家下拉菜单
	 * @param experttype 
	 * @return
	 */
	public List<ComboData> findEnComb(String experttype);
	

}
