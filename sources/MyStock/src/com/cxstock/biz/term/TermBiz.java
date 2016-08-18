package com.cxstock.biz.term;

import java.util.List;

import com.cxstock.pojo.Term;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface TermBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateTerm(Term term);
	
	/**
	 * 删除 
	 */
	public boolean deleteTerm(Integer id);
	
	/**
	 * 下拉数据
	 */
	public List findTerm();
	
	public void findPageTerm(Page page);
	
}
