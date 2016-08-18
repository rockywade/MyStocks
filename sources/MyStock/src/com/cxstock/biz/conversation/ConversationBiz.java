package com.cxstock.biz.conversation;

import com.cxstock.pojo.Conversation;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.utils.pubutil.Page;

/**
 * 线上答疑接口
 * @author root
 *
 */
public interface ConversationBiz {
	
	
	/**
	 * 保存、修改
	 * @param pojo
	 */
	public boolean saveOrUpdateConversation(Conversation  pojo);
	
	
	/**
	 * 分页，排序，搜索
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 */
	public void findPageConversation(Page page, String[] property,Object[] value);
	
	public Conversation loadById(Integer id);
}
