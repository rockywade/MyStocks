package com.cxstock.biz.sms.imp;

import java.util.List;

import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Sms;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class SmsBizImp implements SmsBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPageSms(Page page) {
		String hql = "from Sms order by id";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Sms");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateSms(Sms sms) {
		Sms s = new Sms();
		if(sms.getId()!=null){
			s = (Sms)baseDao.loadById(Sms.class,sms.getId());
		}
		s.setContent(sms.getContent());
		s.setSmsName(sms.getSmsName());
		baseDao.saveOrUpdate(s);
	}
	
	/*
	 * 删除
	 */
	public boolean deleteSms(Integer id) {
		baseDao.deleteById(Sms.class, id);
		return true;
	}
	
	public Sms findById(Integer id){
		return (Sms) baseDao.loadById(Sms.class, id);
	}
	
}
