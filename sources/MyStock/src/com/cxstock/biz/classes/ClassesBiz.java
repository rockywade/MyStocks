package com.cxstock.biz.classes;

import java.util.List;

import com.cxstock.pojo.Classes;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface ClassesBiz {

	
	/**
	 * 保存/修改院系
	 */
	public void saveOrUpdateClasses(Classes classes);
	
	/**
	 * 删除院系
	 */
	public boolean deleteClasses(String bjdm);
	
	/**
	 * 院系下拉数据
	 */
	public List findClasses();
	
	public void findPageClasses(Page page);
	
	public Classes loadById(String bjdm);
	
	public List findClassesBySsyq(String ssyq);
	
}
