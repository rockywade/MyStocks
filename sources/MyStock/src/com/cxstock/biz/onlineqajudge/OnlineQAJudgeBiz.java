package com.cxstock.biz.onlineqajudge;

import com.cxstock.pojo.OnlineQAJudge;
import com.cxstock.utils.pubutil.Page;

/**
 * 学习资料接口
 * @author root
 *
 */
public interface OnlineQAJudgeBiz {
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param property
	 * @param value
	 */
	public void findPageOnlineQAJudge(Page page,String[] property ,Object[] value,Integer userId);
	
	/**
	 * 保存
	 * @param OnlineQAJudgeDTO
	 * @return
	 */
	public boolean saveOnlineQAJudge(OnlineQAJudge onlineQAJudge);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteOnlineQAJudge(Integer id);
	
	
	
	
	
}
