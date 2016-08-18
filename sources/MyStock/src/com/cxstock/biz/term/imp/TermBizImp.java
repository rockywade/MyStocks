package com.cxstock.biz.term.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.term.TermBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Term;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class TermBizImp implements TermBiz {
	
	private BaseDAO baseDao;
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPageTerm(Page page) {
		String hql = "from Term order by termName desc ,orderNum asc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Term");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateTerm(Term Term) {
		Term g = new Term();
		if(Term.getId()!=null){
			g = (Term)baseDao.loadById(Term.class,Term.getId());
		}
		g.setTermName(Term.getTermName());
		g.setOrderNum(Term.getOrderNum());
		baseDao.saveOrUpdate(g);
	}
	
	/*
	 * 删除
	 */
	public boolean deleteTerm(Integer id) {
		baseDao.deleteById(Term.class, id);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findTerm() {
		List list = new ArrayList();
		List<Term> termList = baseDao.findByHql("from Term order by termName desc ,orderNum asc");
		for (Term term : termList) {
			ComboData dto = new ComboData();
			dto.setValue(term.getTermName());
			dto.setText(term.getTermName());
			list.add(dto);
		}
		return list;
	}

}
