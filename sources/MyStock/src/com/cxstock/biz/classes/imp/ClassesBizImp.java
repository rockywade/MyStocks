package com.cxstock.biz.classes.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public class ClassesBizImp implements ClassesBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 保存/修改 
	 */
	public void saveOrUpdateClasses(Classes classes) {
		Classes c = new Classes();
		if(classes.getBjdm()!=null){
			c = (Classes)baseDao.loadById(Classes.class,classes.getBjdm());
			if(null!=c){
				if(null!=classes.getInstructor()){
					c.setInstructor(classes.getInstructor());
				}
				if(null!=classes.getHeadMaster()){
					c.setHeadMaster(classes.getHeadMaster());	
				}
				if(null!=classes.getStudents()){
					c.setStudents(classes.getStudents());
				}
				if(null!=classes.getSsyq()&&!"".equals(classes.getSsyq())){
					c.setSsyq(classes.getSsyq());
				}
				baseDao.saveOrUpdate(c);
				classes.setBjdm(c.getBjdm());
			}else{
				baseDao.saveOrUpdate(classes);
			}
		}
		
		
	}
	
	/*
	 * 删除
	 */
	public boolean deleteClasses(String bjdm) {
		baseDao.deleteById(Classes.class, bjdm);
		return true;
	}
	
	/*
	 * 下拉数据
	 */
	public List findClasses() {
		List list = new ArrayList();
		List<Classes> classesList = baseDao.listAll("Classes");
		for (Classes classes : classesList) {
			ComboData dto = new ComboData();
			dto.setValue(classes.getBjdm());
			dto.setText(classes.getBjdm());
			list.add(dto);
		}
		return list;
	}
	
	public void findPageClasses(Page page) {
		String hql = "from Classes order by bjdm desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countAll("Classes");
		page.setRoot(list);
		page.setTotal(total);
	}
	
	public Classes loadById(String bjdm) {
		return (Classes) baseDao.loadById(Classes.class,bjdm);
	}

	@Override
	public List findClassesBySsyq(String ssyq) {
		String hql = " from Classes c where ssyq='"+ssyq+"'";
		List list = new ArrayList();
		List<Classes> classesList = baseDao.findByHql(hql);
		for (Classes classes : classesList) {
			ComboData dto = new ComboData();
			dto.setValue(classes.getBjdm());
			dto.setText(classes.getBjdm());
			list.add(dto);
		}
		return list;
	}
}
