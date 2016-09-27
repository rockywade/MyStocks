package com.cxstock.biz.newfriends.imp;

import java.util.List;

import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.biz.newfriends.NewFriendsBiz;
import com.cxstock.biz.newfriends.dto.NewFriendsDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.NewFriends;
import com.cxstock.utils.pubutil.Page;

public class NewFriendsBizImp implements NewFriendsBiz {

	private BaseDAO baseDao;

	private UserBiz userBiz;
	

	
	public UserBiz getUserBiz() {
		return userBiz;
	}


	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	public BaseDAO getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}


	/*
	 * 分页查询 
	 */
	@SuppressWarnings("unchecked")
	public void findPageNewFriends(Page page,String[] property, Object[] value) {
		String hql = "from NewFriends as model where 1=1 ";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by zgh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = NewFriendsDTO.createDtos(list);
		int total = baseDao.count("NewFriends", property, value);
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateNewFriends(NewFriends newFriends) {
		NewFriends i = new NewFriends();
		if(null != newFriends.getId()) {
			i = (NewFriends) baseDao.loadById(NewFriends.class,newFriends.getId());
		}else{
			NewFriends lt = (NewFriends) baseDao.loadObject("from NewFriends where zgh='"
					+ newFriends.getZgh() + "'");
			if (lt != null) {
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(newFriends.getZgh());
				dto.setPassword(newFriends.getZgh());
				dto.setState(1);
				dto.setSsyq(newFriends.getSsyq());
				dto.setRole("5");//新生之友
				userBiz.saveOrUpdateUser(dto);
			}
		} 
		i.setPhone(newFriends.getPhone());
		i.setXb(newFriends.getXb());
		i.setXm(newFriends.getXm());
		i.setZgh(newFriends.getZgh());
		i.setSsyq(newFriends.getSsyq());
		baseDao.saveOrUpdate(i);
		return true;
	}

	/*
	 * 删除用户
	 * 
	 */
	public void deleteNewFriends(Integer id) {
		//NewFriends NewFriends = (NewFriends)baseDao.loadById(NewFriends.class, id);
		String sql = "delete from user_role where userid = (select userid from users a, tbl_newfriends b where" +
		" a.logincode=b.zgh and b.id="+id+") and roleid=5";
		baseDao.excuteBySql(sql);
		baseDao.deleteById(NewFriends.class, id);
	}
}
