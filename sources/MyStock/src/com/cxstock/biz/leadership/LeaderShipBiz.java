package com.cxstock.biz.leadership;

import com.cxstock.pojo.LeaderShip;
import com.cxstock.utils.pubutil.Page;

public interface LeaderShipBiz {

	
	/**
	 * 分页查询用户列表
	 */
	public void findPageLeaderShip(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateLeaderShip(LeaderShip leaderShip);
	
	/**
	 * 删除用户
	 */
	public void deleteLeaderShip(Integer id);
	

}
