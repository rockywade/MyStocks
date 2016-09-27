package com.cxstock.biz.leadership.imp;

import java.util.List;

import com.cxstock.biz.leadership.LeaderShipBiz;
import com.cxstock.biz.leadership.dto.LeaderShipDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.utils.pubutil.Page;

public class LeaderShipBizImp implements LeaderShipBiz {

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
	public void findPageLeaderShip(Page page,String[] property, Object[] value) {
		String hql = "from LeaderShip as model where 1=1";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by zgh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = LeaderShipDTO.createDtos(list);
		int total = baseDao.count("LeaderShip", property, value);
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateLeaderShip(LeaderShip leaderShip) {
		LeaderShip l = new LeaderShip();
		if(null != leaderShip.getId()) {
			l = (LeaderShip) baseDao.loadById(LeaderShip.class,leaderShip.getId());
		}else{
			LeaderShip ls = (LeaderShip) baseDao.loadObject("from LeaderShip where zgh='"
					+ leaderShip.getZgh() + "'");
			if (ls != null) {
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(leaderShip.getZgh());
				dto.setPassword(leaderShip.getZgh());
				dto.setState(1);
				dto.setSsyq(leaderShip.getSsyq());
				dto.setRole("6");//院系领导
				userBiz.saveOrUpdateUser(dto);
			}
		} 
		l.setZgh(leaderShip.getZgh());
		l.setPhone(leaderShip.getPhone());
		l.setXb(leaderShip.getXb());
		l.setXm(leaderShip.getXm());
		l.setSsyq(leaderShip.getSsyq());
		baseDao.saveOrUpdate(l);
		
		return true;
	}

	/*
	 * 删除用户
	 * 
	 */
	public void deleteLeaderShip(Integer id) {
		String sql = "delete from user_role where userid = (select userid from users a, tbl_leadership b where" +
		" a.logincode=b.zgh and b.id="+id+") and roleid=6";
		baseDao.excuteBySql(sql);
		baseDao.deleteById(LeaderShip.class, id);
	}
}
