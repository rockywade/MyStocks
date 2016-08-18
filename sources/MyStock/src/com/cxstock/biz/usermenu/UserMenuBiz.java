package com.cxstock.biz.usermenu;

import java.util.List;

import com.cxstock.utils.pubutil.TreeNodeChecked;

@SuppressWarnings("unchecked")
public interface UserMenuBiz {

	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateUserMenu(String menuids,String zgh);
	
	/**
	 * 删除 
	 */
	public boolean deleteUserMenu(String zgh);
	
	
	public List<TreeNodeChecked> findUserMenu(String zgh);
	
}
