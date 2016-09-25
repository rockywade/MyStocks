package com.cxstock.biz.headmaster.imp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.biz.headmaster.HeadMasterBiz;
import com.cxstock.biz.headmaster.dto.HeadMasterDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.utils.pubutil.Page;

public class HeadMasterBizImp implements HeadMasterBiz {

	private BaseDAO baseDao;

	private UserBiz userBiz;
	
	private ClassesBiz classesBiz;

	public ClassesBiz getClassesBiz() {
		return classesBiz;
	}


	public void setClassesBiz(ClassesBiz classesBiz) {
		this.classesBiz = classesBiz;
	}
	
	public UserBiz getUserBiz() {
		return userBiz;
	}


	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	public BaseDAO getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}


	/*
	 * 分页查询 
	 */
	@SuppressWarnings("unchecked")
	public void findPageHeadMaster(Page page,String[] property, Object[] value) {
		String hql = "from HeadMaster as model where 1=1 ";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by zgh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = HeadMasterDTO.createDtos(list);
		int total = baseDao.countAll("HeadMaster");
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateHeadMaster(HeadMaster headMaster) {
		HeadMaster h = new HeadMaster();
		if(null != headMaster.getId()) {
			h = (HeadMaster) baseDao.loadById(HeadMaster.class,headMaster.getId());
		}else{
			HeadMaster hs = (HeadMaster) baseDao.loadObject("from HeadMaster where zgh='"
					+ headMaster.getZgh() + "'");
			if (hs != null) {
				headMaster.setId(hs.getId());
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(headMaster.getZgh());
				dto.setPassword(headMaster.getZgh());
				dto.setState(1);
				dto.setSsyq(headMaster.getSsyq());
				dto.setRole("4");//班主任
				userBiz.saveOrUpdateUser(dto);
			}
		} 
		
		if(null!=headMaster.getHclass()){
			h.setHclass(headMaster.getHclass());
		}
		if(null!=headMaster.getXb()&&!"".equals(headMaster.getXb())){
			h.setXb(headMaster.getXb());
		}
		if(null!=headMaster.getSsyq()&&!"".equals(headMaster.getSsyq())){
			h.setSsyq(headMaster.getSsyq());
		}
		
		h.setXm(headMaster.getXm());
		h.setPhone(headMaster.getPhone());
		h.setZgh(headMaster.getZgh());
		baseDao.saveOrUpdate(h);
		headMaster.setId(h.getId());
		return true;
	}

	/*
	 * 删除用户
	 * 
	 */
	public void deleteHeadMaster(Integer id) {
		HeadMaster hs = (HeadMaster)baseDao.loadById(HeadMaster.class, id);
		Set<Classes> classes = hs.getHclass();
		Iterator it = classes.iterator();
		Classes c = null;
		while(it.hasNext()){
			c = (Classes)it.next();
			c.setHeadMaster(null);
			classesBiz.saveOrUpdateClasses(c);
		}
		String sql = "delete from user_role where userid = (select userid from users a, tbl_headmaster b where" +
		" a.logincode=b.zgh and b.id="+id+") and roleid=4";
		baseDao.excuteBySql(sql);
		String stuUpSql = "UPDATE tbl_student SET headmaster_id = null where headmaster_id = " + id;
		baseDao.excuteBySql(stuUpSql);
		
		baseDao.deleteById(HeadMaster.class, id);
	}


	@Override
	public HeadMaster headMasterExist(HeadMaster headMaster) {
		String hql = "from HeadMaster where zgh='"+headMaster.getZgh()+"'";
		List<HeadMaster> studentList = baseDao.findByHql(hql);
		if(studentList.size()==0){
			return null;
		}else{
			return studentList.get(0);
		}
	}
}
