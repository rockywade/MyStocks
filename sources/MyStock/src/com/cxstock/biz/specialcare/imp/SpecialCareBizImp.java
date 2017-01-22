package com.cxstock.biz.specialcare.imp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.biz.specialcare.SpecialCareBiz;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Conversation;
import com.cxstock.pojo.SpecialCare;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public class SpecialCareBizImp implements SpecialCareBiz{
	
	private BaseDAO  baseDao;

	public SpecialCare getSpecialCare(String xh){
		Student s = (Student) baseDao.loadObject("from Student where xh='"
				+ xh + "'");
		if(null!=s){
			SpecialCare sc = new SpecialCare(); 
			sc.setAcademic("");
			sc.setAdministrativeClass(s.getClasses().getBjdm());
			sc.setEconomic("");
			sc.setBzr(s.getHeadmaster().getXm());
			sc.setBzrlogincode(s.getHeadmaster().getZgh());
			sc.setBzrphone(s.getHeadmaster().getPhone());
			if(s.getInstructor() != null) {
				sc.setFdy(s.getInstructor().getXm());
				sc.setFdylogincode(s.getInstructor().getZgh());
				sc.setFdyphone(s.getInstructor().getPhone());
			}
			sc.setFq("");
			sc.setFqphone(s.getFqphone());
			sc.setMental("");
			sc.setMq("");
			sc.setMqphone(s.getMqphone());
			sc.setQsh(s.getQsmc()+s.getQsh());
			sc.setPhone(s.getPhone());
			sc.setXh(s.getXh());
			sc.setXm(s.getXm());
			sc.setXszy("");
			sc.setXszylogincode("");
			sc.setXszyphone("");
			return sc;
		}
		return null;
	}
	
	
	@Override
	public boolean saveOrUpdateSpecialCare(SpecialCare  pojo) {
		SpecialCare sc = (SpecialCare) baseDao.loadObject("from SpecialCare where xh='"
				+ pojo.getXh() + "'");
		if (sc != null) {
			sc.setAcademic(pojo.getAcademic());
			sc.setAdministrativeClass(pojo.getAdministrativeClass());
			sc.setBzr(pojo.getBzr());
			sc.setBzrlogincode(pojo.getBzrlogincode());
			sc.setBzrphone(pojo.getBzrphone());
			sc.setEconomic(pojo.getEconomic());
			sc.setFdy(pojo.getFdy());
			sc.setFdylogincode(pojo.getFdylogincode());
			sc.setFdyphone(pojo.getFdyphone());
			sc.setFq(pojo.getFq());
			sc.setFqphone(pojo.getFqphone());
			sc.setMental(pojo.getMental());
			sc.setMq(pojo.getMq());
			sc.setMqphone(pojo.getMqphone());
			sc.setQsh(pojo.getQsh());
			sc.setPhone(pojo.getPhone());
			sc.setXh(pojo.getXh());
			sc.setXm(pojo.getXm());
			sc.setXszy(pojo.getXszy());
			sc.setXszylogincode(pojo.getXszylogincode());
			sc.setXszyphone(pojo.getXszyphone());
			
			baseDao.saveOrUpdate(sc);
		}else{
			baseDao.saveOrUpdate(pojo);
		}
		
		
		return true;
	}



	@Override
	public void update(Integer id,String economic,String academic,String mental){
		String hql = "update SpecialCare set economic='"+economic+"',academic='"+academic+"',mental='"+mental+"' where id="+id;
		baseDao.update(hql);
	}



	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}



	/**
	 * 分页查询
	 */
	@Override
	public void findPageSpecialCare(Page page, String logincode,String zydm,String[] property, Object[] value) {
		String[] orderName = {"xh"};
		String[] orderType = {"asc"};
		String s = "";
//		if(null==zydm){
//			s = " and (xszylogincode='"+logincode+"' or bzrlogincode='"+logincode+"' or fdylogincode='"+logincode+"')";
//		}else{
//			s = " and (xszylogincode='"+logincode+"' or bzrlogincode='"+logincode+"' or fdylogincode='"+logincode+"' or fdylogincode in ("+zydm+") )";
//		}
		String hql = "from SpecialCare model where 1=1 ";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]&&!"".equals(value[i])) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}

		}
		List<SpecialCare> list = baseDao.findPageAndOrder(hql,orderName,orderType, page.getStart(), page.getLimit());
		Set<Conversation> conversations = null;
		Conversation conversation = null;
		Iterator it = null;
		int bzr = 0;
		int fdy = 0;
		int xszy = 0;
		int yxld = 0;
		for(SpecialCare sc : list){
			bzr = 0;
			fdy = 0;
			xszy = 0;
			yxld = 0;
			conversations = sc.getConversations();
			if(null!=conversations){
				it = conversations.iterator();
				while(it.hasNext()){
					conversation = (Conversation)it.next();
					if("班主任".equals(conversation.getConversationtype())){
						bzr++;
					}
					if("辅导员".equals(conversation.getConversationtype())){
						fdy++;
					}
					if("新生之友".equals(conversation.getConversationtype())){
						xszy++;
					}
					if("院系领导".equals(conversation.getConversationtype())){
						yxld++;
					}
				}
				sc.setBzrtimes(bzr);
				sc.setFydtimes(fdy);
				sc.setXszytimes(xszy);
				sc.setYxldtimes(yxld);
			}else{
				sc.setBzrtimes(0);
				sc.setFydtimes(0);
				sc.setXszytimes(0);
				sc.setYxldtimes(0);
			}
		}
		int total = baseDao.count("SpecialCare",property,value);
		page.setRoot(list);
		page.setTotal(total);
	}



	@Override
	public List<SpecialCare> findByProperty(String clazz,
			String[] propertyName, Object[] value) {
		String hql="from SpecialCare model where 1=1 ";
		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i] && !"".equals(value[i])) {
				hql += " and model." + propertyName[i] + " like '%" + value[i] + "%'";
			}
		}
		
		return baseDao.findByHql(hql);
	}

	public SpecialCare loadById(Integer id) {
		return (SpecialCare) baseDao.loadById(SpecialCare.class, id);
	}
 }
