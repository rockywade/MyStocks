package com.cxstock.biz.conversation.imp;

import java.util.List;

import com.cxstock.biz.conversation.ConversationBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Conversation;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.utils.pubutil.Page;

public class ConversationBizImp implements ConversationBiz{
	
	private BaseDAO  baseDao;

	@Override
	public boolean saveOrUpdateConversation(Conversation  pojo) {
		baseDao.saveOrUpdate(pojo);
		return true;
	}






	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Conversation loadById(Integer id){
		return (Conversation) baseDao.loadById(Conversation.class, id);
	}

	/**
	 * 分页查询
	 */
	@Override
	public void findPageConversation(Page page, String[] property, Object[] value) {
		String[] orderName = {"conversationtime"};
		String[] orderType = {"desc"};
		List list = baseDao.findByProperty("Conversation",property,value,orderName,orderType, page.getStart(), page.getLimit());
		int total = baseDao.count("Conversation",property,value);
		page.setRoot(list);
		page.setTotal(total);
	}

 }
