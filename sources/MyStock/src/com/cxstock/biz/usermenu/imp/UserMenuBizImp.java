package com.cxstock.biz.usermenu.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxstock.biz.usermenu.UserMenuBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Menu;
import com.cxstock.pojo.UserMenu;
import com.cxstock.utils.pubutil.TreeNodeChecked;

@SuppressWarnings("unchecked")
public class UserMenuBizImp implements UserMenuBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateUserMenu(String menuids,String zgh) {
		deleteUserMenu(zgh);
		if(menuids.length()==0){
			return;
		}
		String [] mids = menuids.split(",");
		String hql = "";
		UserMenu userMenu = new UserMenu();
		List<Menu> list = null;
		Menu menu = null;
		for(String id : mids){
			userMenu = new UserMenu();
			hql = "from Menu where menuid ="+id;
			list = baseDao.findByHql(hql);
			if(list.size()>0){
				menu = list.get(0);
				userMenu.setIcon(menu.getIcon());
				userMenu.setMenuid(menu.getMenuid());
				userMenu.setMenuname(menu.getMenuname());
				userMenu.setMenuurl(menu.getMenuurl());
				userMenu.setOrdernum(menu.getOrdernum());
				userMenu.setZgh(zgh);
				baseDao.saveOrUpdate(userMenu);
			}
			
		}
	}
	
	/*
	 * 删除
	 */
	public boolean deleteUserMenu(String zgh) {
		String hql = "delete from UserMenu where zgh='"+zgh+"'";
		baseDao.update(hql);
		return true;
	}
	


	@Override
	public List<TreeNodeChecked> findUserMenu(String zgh) {
		String hql = "from Menu order by ordernum";
		List menuList = baseDao.findByHql(hql);
		hql = "from UserMenu where zgh='"+zgh+"'";
		List<UserMenu> userMenuList = baseDao.findByHql(hql);
		List treeNodeList = this.getTreeNode(-1,menuList,userMenuList);
		return treeNodeList;
	}
	
	//功能树
	private List getTreeNode(Integer menuid,List listFunc,List<UserMenu> listRoleFunc){
		List resultList = new ArrayList();
		Map<Integer,String> m = new HashMap<Integer,String>();
		for(UserMenu userMenu : listRoleFunc){
			m.put(userMenu.getMenuid(),"");
		}
		//当前级菜单集合
		List list = this.getChildrens(listFunc, menuid);
		for (Object obj : list) {
			Menu menu = (Menu) obj;
			TreeNodeChecked treeNodeChecked = new TreeNodeChecked();
			treeNodeChecked.setText(menu.getMenuname());
			treeNodeChecked.setId(menu.getMenuid().toString());
			treeNodeChecked.setIconCls(menu.getIcon()==null?"":menu.getIcon());
			if(null!=m.get(menu.getMenuid())){
				treeNodeChecked.setChecked(true);
			}else{
				treeNodeChecked.setChecked(false);
			}
			treeNodeChecked.setChildren(getTreeNode(menu.getMenuid(),listFunc,listRoleFunc));//递归
			resultList.add(treeNodeChecked);
		}
		return resultList;
	}
	
	//子集合
	private List getChildrens(List funcs, Integer menuid) {
		List resultList = new ArrayList();
		Menu func = null;
		for (Object obj : funcs) {
			func = (Menu) obj;
			if (func.getPid().equals(menuid)) {//父节点id
				resultList.add(func);
			}
		}
		return resultList;
	}


}
