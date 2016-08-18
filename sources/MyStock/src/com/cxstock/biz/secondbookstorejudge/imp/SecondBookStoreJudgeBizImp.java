package com.cxstock.biz.secondbookstorejudge.imp;

import java.util.List;

import com.cxstock.biz.secondbookstorejudge.SecondBookStoreJudgeBiz;
import com.cxstock.biz.secondbookstorejudge.dto.SecondBookStoreJudgeDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.SecondBookStoreJudge;
import com.cxstock.utils.pubutil.Page;

public class SecondBookStoreJudgeBizImp implements SecondBookStoreJudgeBiz{
	
	private BaseDAO  baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void deleteSecondBookStoreJudge(Integer id) {
		baseDao.deleteById(SecondBookStoreJudge.class, id);	
	}

	@Override
	public void findPageSecondBookStoreJudge(Page page,String[] property ,Object[] value, Integer userId) {
		String[] orderName = {"judgetime"};
		String[] orderType = {"DESC"};
		List list = baseDao.findByProperty("SecondBookStoreJudge",property,value,orderName,orderType, page.getStart(), page.getLimit());
		int total = baseDao.count("SecondBookStoreJudge",property,value);
		if(null==userId){
			page.setRoot(list);
		}else{
			List dtos = SecondBookStoreJudgeDTO.createDtos(list,userId);
			page.setRoot(dtos);
		}
		page.setTotal(total);
		
	}

	@Override
	public boolean saveSecondBookStoreJudge(SecondBookStoreJudge pojo) {
		baseDao.saveOrUpdate(pojo);
		String hql = "update SecondBookStore set replynickname='"+pojo.getNickname()+"' where id="+pojo.getStoreid();
		baseDao.update(hql);
		return true;
	}

 }
