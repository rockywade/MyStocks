package com.cxstock.action.usermenu;


import java.util.List;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.usermenu.UserMenuBiz;
import com.cxstock.utils.pubutil.TreeNodeChecked;

@SuppressWarnings("serial")
public class UserMenuAction extends BaseAction  {
	
	private UserMenuBiz userMenuBiz;
	private String zgh;
	private String menuids;


    
	
	
	public UserMenuBiz getUserMenuBiz() {
		return userMenuBiz;
	}

	public void setUserMenuBiz(UserMenuBiz userMenuBiz) {
		this.userMenuBiz = userMenuBiz;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getMenuids() {
		return menuids;
	}

	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}

	/** 获取用户权限菜单 */
	public String findUserMenu() {
		List<TreeNodeChecked> userMenu = userMenuBiz.findUserMenu(zgh);
		try {
			this.outTreeJsonList(userMenu);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 保存权限
	 */
	public String saveUserMenu() {
		try {
			userMenuBiz.saveOrUpdateUserMenu(menuids,zgh);
			this.outString("保存成功!");
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
}
