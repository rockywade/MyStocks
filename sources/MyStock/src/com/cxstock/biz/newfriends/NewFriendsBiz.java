package com.cxstock.biz.newfriends;

import com.cxstock.pojo.NewFriends;
import com.cxstock.utils.pubutil.Page;

public interface NewFriendsBiz {

	
	/**
	 * 分页查询列表
	 */
	public void findPageNewFriends(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改 
	 */
	public boolean saveOrUpdateNewFriends(NewFriends newFriends);
	
	/**
	 * 删除 
	 */
	public void deleteNewFriends(Integer id);
	

}
