package com.cxstock.biz.expert.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.expert.ExpertBiz;
import com.cxstock.biz.expert.dto.ExpertDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.StartExpertInfo;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

public class ExpertBizImp implements ExpertBiz {

	private BaseDAO baseDao;

	private UserBiz userBiz;
	
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
	public void findPageExpert(Page page,String[] property, Object[] value) {
		String hql = "from Expert as model where 1=1";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by zgh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = ExpertDTO.createDtos(list);
		int total = baseDao.count("Expert", property, value);
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateExpert(Expert expert) {
		Expert e = new Expert();
		StartExpertInfo startExpertInfo = null;
		if(null != expert.getId()) {
			e = (Expert) baseDao.loadById(Expert.class,expert.getId());
		}else{
			Expert ex = (Expert) baseDao.loadObject("from Expert where zgh='"
					+ expert.getZgh() + "'");
			if (ex != null) {
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(expert.getZgh());
				dto.setPassword(expert.getZgh());
				dto.setState(1);
				dto.setSsyq(expert.getSsyq());
				dto.setRole("7");//专家
				userBiz.saveOrUpdateUser(dto);
			}
			startExpertInfo = new StartExpertInfo();
		} 
		e.setPhone(expert.getPhone());
		e.setXb(expert.getXb());
		e.setXm(expert.getXm());
		e.setZgh(expert.getZgh());
		e.setEmail(expert.getEmail());
		e.setExpertType(expert.getExpertType());
		e.setIntroduce(expert.getIntroduce());
		e.setUnit(expert.getUnit());
		e.setPhoto(expert.getPhoto());
		e.setSsyq(expert.getSsyq());
		baseDao.saveOrUpdate(e);
		if(startExpertInfo != null) {
			startExpertInfo.setExpert(e);
			baseDao.saveOrUpdate(startExpertInfo);
		}
		return true;
	}

	/*
	 * 删除用户
	 * 
	 */
	public void deleteExpert(Integer id) {
		//删除关联的用户
		String sql = "delete from user_role where userid = (select userid from users a, tbl_expert b where" +
		" a.logincode=b.zgh and b.id="+id+") and roleid=7";
		baseDao.excuteBySql(sql);
		
		//删除tbl_studentbespeak
		String sbsSql = "delete from tbl_studentbespeak where expert_id= " + id;
		//删除tbl_startexpertinfo
		String seiSql = "delete from tbl_startexpertinfo where expert_id= " + id;
		
		baseDao.excuteBySql(sbsSql);
		baseDao.excuteBySql(seiSql);
		
		baseDao.deleteById(Expert.class, id);
	}

	
	/**
	 * 删除多个 
	 */
	public void deleteExperts(List<Integer> ids) {
		for(Integer id : ids) {
			deleteExpert(id);
		}
	}
	


	/**
	 * 专家下拉菜单
	 */
	@SuppressWarnings("unchecked")
	public List<ComboData> findEnComb(String experttype) {
		List<ComboData> list = new ArrayList<ComboData>();
		String hql = "from Expert as ex where 1=1 and ex.expertType = '"+experttype+"'";
		/*if(experttype!=null && !experttype.equals("")){
			hql += " and ex.expertType = '"+experttype+"'";
		}*/
		List<Expert> exList = baseDao.findByHql(hql);
		if(exList!=null){
			for(Expert v:exList){
				ComboData ex = new ComboData();
				ex.setValue(v.getId().toString());
				ex.setText(v.getXm());
				list.add(ex);
			}
		}
		return list;
	}


	/**
	 * 专家类别下拉菜单
	 */
	public List<ComboData> findEtComb() {
		return null;
	}
	
}
