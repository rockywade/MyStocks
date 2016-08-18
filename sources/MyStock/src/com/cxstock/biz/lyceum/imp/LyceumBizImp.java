package com.cxstock.biz.lyceum.imp;

import java.util.List;

import com.cxstock.biz.lyceum.LyceumBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Lyceum;
import com.cxstock.pojo.Park;
import com.cxstock.utils.pubutil.Page;
/**
 * 学园管理的接口实现
 * @author root
 *
 */
public class LyceumBizImp implements LyceumBiz{
	
	private BaseDAO baseDao;
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 学园列表
	 */
	@Override
	public void findPageLyceum(Page page) {
		String hql = "from Lyceum order by id";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Lyceum");
		page.setRoot(list);
		page.setTotal(total);
		
	}
   
	/**
	 *删除学园	 
	 */
	@Override
	public boolean deleteLyceum(Integer id) {
		baseDao.deleteById(Lyceum.class, id);
		return true;
	}


	/**
	 *新增或修改学园
	 */
	@Override
	public void saveOrUpdateLyceum(Lyceum lyceum) {
		Lyceum ly = new Lyceum();
		if(lyceum.getId()!=null){
			ly = (Lyceum)baseDao.loadById(Lyceum.class,lyceum.getId());
		}
		ly.setLyceumName(lyceum.getLyceumName());
		baseDao.saveOrUpdate(ly);
	}

}
