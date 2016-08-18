package com.cxstock.biz.unit.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.unit.UnitBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Unit;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class UnitBizImp implements UnitBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPageUnit(Page page) {
		String hql = "from Unit order by orderNum";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Unit");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateUnit(Unit unit) {
		Unit p = new Unit();
		if(unit.getId()!=null){
			p = (Unit)baseDao.loadById(Unit.class,unit.getId());
		}
		p.setOrderNum(unit.getOrderNum());
		p.setUnitName(unit.getUnitName());
		baseDao.saveOrUpdate(p);
	}
	
	/*
	 * 删除
	 */
	public boolean deleteUnit(Integer id) {
		baseDao.deleteById(Unit.class, id);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findUnit() {
		List list = new ArrayList();
		List<Unit> UnitList = baseDao.findByHql("from Unit order by orderNum");
		for (Unit Unit : UnitList) {
			ComboData dto = new ComboData();
			dto.setValue(Unit.getUnitName());
			dto.setText(Unit.getUnitName());
			list.add(dto);
		}
		return list;
	}
	

	/*
	 * 下拉数据
	 */
	public List findUnitAll() {
		List list = new ArrayList();
		ComboData dto = new ComboData();
		dto.setValue("");
		dto.setText("全部");
		list.add(dto);
		list.addAll(findUnit());
		return list;
	}
}
