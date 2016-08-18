package com.cxstock.biz.onlineqajudge.imp;

import java.util.List;

import com.cxstock.biz.onlineqajudge.OnlineQAJudgeBiz;
import com.cxstock.biz.onlineqajudge.dto.OnlineQAJudgeDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.OnlineQAJudge;
import com.cxstock.utils.pubutil.Page;

public class OnlineQAJudgeBizImp implements OnlineQAJudgeBiz{
	
	private BaseDAO  baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void deleteOnlineQAJudge(Integer id) {
		baseDao.deleteById(OnlineQAJudge.class, id);	
	}

	@Override
	public void findPageOnlineQAJudge(Page page,String[] property ,Object[] value, Integer userId) {
		String[] orderName = {"judgetime"};
		String[] orderType = {"DESC"};
		List list = baseDao.findByProperty("OnlineQAJudge",property,value,orderName,orderType, page.getStart(), page.getLimit());
		int total = baseDao.count("OnlineQAJudge",property,value);
		if(null==userId){
			page.setRoot(list);
		}else{
			List dtos = OnlineQAJudgeDTO.createDtos(list,userId);
			page.setRoot(dtos);
		}
		page.setTotal(total);
	}

	@Override
	public boolean saveOnlineQAJudge(OnlineQAJudge pojo) {
		baseDao.saveOrUpdate(pojo);
		String hql = "update OnlineQA set replynickname='"+pojo.getNickname()+"' where id="+pojo.getQaid();
		baseDao.update(hql);
		return true;
	}

 }
