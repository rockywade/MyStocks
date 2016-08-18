package com.cxstock.biz.groundtype.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.groundtype.GroundTypeBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.GroundType;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class GroundTypeBizImp implements GroundTypeBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPageGroundType(Page page) {
		String hql = "from GroundType order by orderNum";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("GroundType");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateGroundType(GroundType groundType) {
		GroundType g = new GroundType();
		if(groundType.getId()!=null){
			g = (GroundType)baseDao.loadById(GroundType.class,groundType.getId());
		}
		g.setTypeName(groundType.getTypeName());
		g.setOrderNum(groundType.getOrderNum());
		baseDao.saveOrUpdate(g);
	}
	
	/*
	 * 删除
	 */
	public boolean deleteGroundType(Integer id) {
		baseDao.deleteById(GroundType.class, id);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findGroundType() {
		List list = new ArrayList();
		List<GroundType> groundTypeList = baseDao.findByHql("from GroundType order by orderNum");
		for (GroundType groundType : groundTypeList) {
			ComboData dto = new ComboData();
			dto.setValue(groundType.getTypeName());
			dto.setText(groundType.getTypeName());
			list.add(dto);
		}
		return list;
	}

}
