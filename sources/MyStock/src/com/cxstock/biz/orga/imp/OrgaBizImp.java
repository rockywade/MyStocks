package com.cxstock.biz.orga.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.orga.OrgaBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Orga;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class OrgaBizImp implements OrgaBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPageOrga(Page page) {
		String hql = "from Orga order by orderNum";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Orga");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateOrga(Orga orga) {
		Orga o = new Orga();
		if(orga.getId()!=null){
			o = (Orga)baseDao.loadById(Orga.class,orga.getId());
		}
		o.setOrderNum(orga.getOrderNum());
		o.setOrgaName(orga.getOrgaName());
		baseDao.saveOrUpdate(o);
	}
	
	/*
	 * 删除
	 */
	public boolean deleteOrga(Integer id) {
		baseDao.deleteById(Orga.class, id);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findOrga() {
		List list = new ArrayList();
		List<Orga> OrgaList = baseDao.findByHql("from Orga order by orderNum");
		for (Orga Orga : OrgaList) {
			ComboData dto = new ComboData();
			dto.setValue(Orga.getOrgaName());
			dto.setText(Orga.getOrgaName());
			list.add(dto);
		}
		return list;
	}
}
