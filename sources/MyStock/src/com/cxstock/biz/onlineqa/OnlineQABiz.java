package com.cxstock.biz.onlineqa;

import com.cxstock.pojo.OnlineQA;
import com.cxstock.utils.pubutil.Page;

/**
 * 线上答疑接口
 * @author root
 *
 */
public interface OnlineQABiz {
	
	/**
	 * 修改人气
	 * @param id
	 */
	public void updatePopularity(Integer id);
	
	/**
	 * 修改最新回复人
	 * @param id
	 */
	public void updateReplynickname(Integer id,String nickname);
	
	
	/**
	 * 修改状态
	 * @param id
	 */
	public void update(String ids,Integer type,Integer value);
	
	/**
	 * 我上传资料删除
	 * @param id
	 */
	public void deleteOnlineQA(Integer id);
	
	/**
	 * 保存、修改
	 * @param pojo
	 */
	public boolean saveOrUpdaOnlineQA(OnlineQA  pojo);
	
	
	
	/**
	 * 分页，排序，搜索
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 */
	public void findPageOnlineQA(Page page, String hql);
	
	public void findPageMyOnlineQA(Page page, String hql);
	
	public OnlineQA loadById(Integer id);
	
	public void delete(String ids);
}
