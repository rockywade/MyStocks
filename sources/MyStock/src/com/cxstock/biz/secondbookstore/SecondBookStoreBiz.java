package com.cxstock.biz.secondbookstore;

import com.cxstock.pojo.SecondBookStore;
import com.cxstock.utils.pubutil.Page;

/**
 * 线上答疑接口
 * @author root
 *
 */
public interface SecondBookStoreBiz {
	
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
	 * 删除
	 * @param id
	 */
	public void deleteSecondBookStore(Integer id);
	
	/**
	 * 保存、修改
	 * @param pojo
	 */
	public boolean saveOrUpdaSecondBookStore(SecondBookStore  pojo);
	
	
	
	/**
	 * 分页，排序，搜索
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 */
	public void findPageSecondBookStore(Page page, String hql);
	
	public void findPageMySecondBookStore(Page page, String hql);
	
	public SecondBookStore loadById(Integer id);
	
	public void delete(String ids);
}
