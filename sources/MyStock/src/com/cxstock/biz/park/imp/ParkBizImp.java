package com.cxstock.biz.park.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.park.ParkBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Park;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class ParkBizImp implements ParkBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	public void findPagePark(Page page) {
		String hql = "from Park order by orderNum";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Park");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdatePark(Park park) {
		Park p = new Park();
		if(park.getId()!=null){
			p = (Park)baseDao.loadById(Park.class,park.getId());
		}
		p.setOrderNum(park.getOrderNum());
		p.setParkName(park.getParkName());
		baseDao.saveOrUpdate(p);
	}
	
	/*
	 * 删除
	 */
	public boolean deletePark(Integer id) {
		baseDao.deleteById(Park.class, id);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findPark() {
		List list = new ArrayList();
		List<Park> ParkList = baseDao.findByHql("from Park order by orderNum");
		for (Park park : ParkList) {
			ComboData dto = new ComboData();
			dto.setValue(park.getParkName());
			dto.setText(park.getParkName());
			list.add(dto);
		}
		return list;
	}
}
