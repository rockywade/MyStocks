package com.cxstock.biz.expertbespeak.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cxstock.biz.expertbespeak.ExpertBespeakBiz;
import com.cxstock.biz.expertbespeak.dto.StartExpertInfoDTO;
import com.cxstock.biz.expertbespeak.dto.StudentbespeakDTO;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.StartExpertInfo;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Studentbespeak;
import com.cxstock.pojo.Term;
import com.cxstock.pojo.Unit;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;

public class ExpertBespeakBizImp implements ExpertBespeakBiz {
	
	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 专家信息发布
	 */
	public boolean saveOrUpdateExpertBespeak(StartExpertInfo startExpertInfo) {
		baseDao.saveOrUpdate(startExpertInfo);
		return true;
	}

	/**
	 * 学生预约
	 */
	public boolean saveOrUpdateStudentbespeak(Studentbespeak studentBespeak) {
		if(studentBespeak.getExpert().getId()==0){
			studentBespeak.setExpert(null);
			studentBespeak.setBespeakstyle("随机");
			studentBespeak.setHaveornotexpert("未分配");
		}else{
			studentBespeak.setBespeakstyle("指定");
			studentBespeak.setHaveornotexpert("已分配");
		}
		Date date = new Date();
		String d = DateUtil.format(date);
		studentBespeak.setBespeakstate("待接受");
		studentBespeak.setExpertfeedbackstate("无反馈");
		studentBespeak.setUploadbespeaktime(d);
		baseDao.saveOrUpdate(studentBespeak);
		return true;
	}
	
	/**
	 * (student)我的预约
	 */
	@SuppressWarnings("unchecked")
	public void findPageStudentBespeakById(Page page, Integer id) {
		List listDto = new ArrayList();
		String hql = "from Studentbespeak as model where model.student = "+id+"";
		String chql = "select count(*) "+hql;
		String[] orderName = {"uploadbespeaktime"};
		String[] orderType = {"DESC"};
		List<Studentbespeak> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		if(list!=null){
			for(Studentbespeak sb:list){
				StudentbespeakDTO sbDto = new StudentbespeakDTO();
				if(sb.getExpert()!=null){
					sbDto.setExpertName(sb.getExpert().getXm());
				}else{
					sbDto.setExpertName("未指定");
				}
				sbDto.setStid(sb.getStid());
				sbDto.setBespeaktime(sb.getBespeaktime());
				sbDto.setDetailinfo(sb.getDetailinfo());
				sbDto.setBespeakstate(sb.getBespeakstate());
				listDto.add(sbDto);
			}
		}
		page.setRoot(listDto);
		page.setTotal(total);
	}

	/**
	 * (Expert)我的预约
	 */
	@SuppressWarnings("unchecked")
	public void findPageStudentBespeakByEepertId(Page page, Integer id) {
		List listDto = new ArrayList();
		String hql = "from Studentbespeak as model where model.expert = "+id+"";
		String chql = "select count(*) "+hql;
		String[] orderName = {"uploadbespeaktime"};
		String[] orderType = {"DESC"};
		List<Studentbespeak> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		if(list!=null){
			for(Studentbespeak sb:list){
				StudentbespeakDTO sbDto = new StudentbespeakDTO();
				sbDto.setStid(sb.getStid());
				sbDto.setStdid(sb.getStudent().getId());
				sbDto.setStudentXh(sb.getStudent().getXh());
				sbDto.setStudentName(sb.getStudent().getXm());
				sbDto.setStudentXb(sb.getStudent().getXb());
				sbDto.setStudentBjmc(sb.getStudent().getClasses().getBjdm());
				sbDto.setStudentEmail(sb.getStudentemail());
				sbDto.setStudentphone(sb.getStudent().getPhone());
				sbDto.setBespeaktime(sb.getBespeaktime());
				sbDto.setUploadbespeaktime(sb.getUploadbespeaktime());
				sbDto.setBespeakstate(sb.getBespeakstate());
				sbDto.setBespeakplace(sb.getBespeakplace());
				sbDto.setApplygenre(sb.getApplygenre());
				sbDto.setDetailinfo(sb.getDetailinfo());
				sbDto.setTalkcontent(sb.getTalkcontent());
				listDto.add(sbDto);
			}
		}
		page.setRoot(listDto);
		page.setTotal(total);
	}

	/**
	 * 预约审核
	 */
	public boolean updateStudentBespeakById(String checkkey,Integer stid) {
		String hql = null;
		if("1".equals(checkkey)){	//拒绝
			hql = "update Studentbespeak as model set model.bespeakstate = '已拒绝' " +
					"where model.stid = "+stid+"";
		}else if("2".equals(checkkey)){//接受
			hql = "update Studentbespeak as model set model.bespeakstate = '已接受' " +
			"where model.stid = "+stid+"";				
		}
		if(hql!=null && !hql.equals("")){
			baseDao.update(hql);
			return true;
		}
		return false;
	}

	/**
	 * 添加谈话
	 */
	public void updateStudentBespeakTalkById(String talkcontent,Integer stid) {
		String hql = null;
		if(talkcontent!=null && !talkcontent.equals("")){
			hql = "update Studentbespeak as model set model.talkcontent = '"+talkcontent+"' , model.bespeakstate = '已完成' , model.expertfeedbackstate = '已反馈' where model.stid = "+stid+"";
		}else{
			hql = "update Studentbespeak as model set model.bespeakstate = '已完成　待反馈' " +
			" where model.stid = "+stid+"";
		}
		baseDao.update(hql);
	}

	/**
	 * 专家列表
	 */
	@SuppressWarnings("unchecked")
	public void findPageStartExpertInfoById(Page page,String[] property,String[] values) {
		List listDto = new ArrayList();
		String hql = "from StartExpertInfo as model where 1=1 ";
		String ehql = "from Expert as ex where 1=1 ";
		for(int i = 0;i<property.length;i++){
			if(values[i]!=null && !values[i].equals("") && i<property.length-1){
				ehql += " and ex."+property[i]+" like '%"+values[i]+"%'";
			}else if(values[i]!=null && !values[i].equals("")){
				ehql +=" and ex."+property[i]+" like '%"+values[i]+"%'";
			}
		}
		List<Expert> expert = baseDao.findByHql(ehql);
		if(expert!=null && expert.size()>0){
			for(int i=0;i<expert.size();i++){
				if(i>0){
					hql +=" or model.expert="+expert.get(i).getId()+"";
				}else{
					hql +=" and model.expert="+expert.get(i).getId()+"";
				}
			}
		}else{
			hql += " and model.expert="+-1+"";
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"freetime"};
		String[] orderType = {"DESC"};
		List<StartExpertInfo> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		if(list!=null){
			for(StartExpertInfo ex:list){
				StartExpertInfoDTO seiDto = new StartExpertInfoDTO();
				seiDto.setId(ex.getExpert().getId());
				seiDto.setExpertPhoto(ex.getExpert().getPhoto());
				seiDto.setExpertZgh(ex.getExpert().getZgh());
				seiDto.setExpertName(ex.getExpert().getXm());
				seiDto.setExpertXb(ex.getExpert().getXb());
				seiDto.setExpertphone(ex.getExpert().getPhone());
				seiDto.setExpertEmail(ex.getExpert().getEmail());
				seiDto.setExpertUnit(ex.getExpert().getUnit());
				seiDto.setWorkaddress(ex.getWorkaddress());
				seiDto.setPersonalInfro(ex.getExpert().getIntroduce());
				seiDto.setExpertGenre(ex.getExpert().getExpertType());
				listDto.add(seiDto);
			}
		}
		page.setRoot(listDto);
		page.setTotal(total);
	}

	/**
	 * 专家详情
	 */
	public StartExpertInfoDTO findPageStartExpertInfoById(Integer id) {
		StartExpertInfoDTO seifDto = new StartExpertInfoDTO();
		if(id!=null && !id.equals("")){
			String hql = "from StartExpertInfo as model where expert = "+id+"";
			StartExpertInfo se = (StartExpertInfo) baseDao.loadObject(hql);
			if(se!=null && !se.equals("")){
				seifDto.setId(se.getExpert().getId());
				seifDto.setExpertName(se.getExpert().getXm());
				seifDto.setExpertUnit(se.getExpert().getUnit());
				seifDto.setExpertGenre(se.getExpert().getExpertType());
				seifDto.setExpertEmail(se.getExpert().getEmail());
				seifDto.setExpertphone(se.getExpert().getPhone());
				seifDto.setFreetime(se.getFreetime());
				seifDto.setWorkaddress(se.getWorkaddress());
				seifDto.setPersonalInfro(se.getExpert().getIntroduce());
				seifDto.setExpertPhoto(se.getExpert().getPhoto());
				seifDto.setSeifid(se.getSeifid());
			}
		}
		return seifDto;
	}

	/**
	 * 取消预约
	 */
	public void updateBespeakState(Integer sbid) {
		if(sbid!=null){
			String hql = "update Studentbespeak as sb set sb.bespeakstate = '已取消' where sb.stid="+sbid+"";
			baseDao.update(hql);
		}
	}

	/**
	 * 指定专家
	 */
	@SuppressWarnings("unchecked")
	public void findPageAlreadyEnter(Page page, String[] property, String[] values) {
		List listsbDto = new ArrayList();
		String hql = "from Studentbespeak as model where model.bespeakstyle='指定' and model.bespeakstate !='已取消'";
		/**模糊查询——————————start————————*/
		String ehql = "from Expert as ex where 1=1 ";
		if(values[property.length-1]!=null){
			ehql += " and ex."+property[property.length-1]+" like '%"+values[property.length-1]+"%'";
		}
		//获取以姓名查询的专家
		List<Expert> exlist = baseDao.findByHql(ehql);
		for(int m=0;m<property.length;m++){
			if(values[m]!=null && m<property.length-1){
				hql += " and model."+property[m]+" like '%"+values[m]+"%'";
			}else if(values[m]!=null && !values[m].equals("")){				//模糊查询预约老师属性
				if(exlist!=null && exlist.size()>0){
					for(int i=0;i<exlist.size();i++){
						if(i>0){
						 	hql +=" or model.expert = "+exlist.get(i).getId()+"";
						}else{
							hql +=" and model.expert = "+exlist.get(i).getId()+"";
						}
					}
				}else{
					hql +=" and model.expert = '"+null+"'";
				}
			}
		}
		/**模糊查询————————end————————*/
		String chql = "select count(*) "+hql;
		String[] orderName = {"uploadbespeaktime"};
		String[] orderType = {"ASC"};
		List<Studentbespeak> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		for(Studentbespeak sb:list){
			StudentbespeakDTO sbDto = new StudentbespeakDTO();
			sbDto.setStid(sb.getStid());
			sbDto.setApplygenre(sb.getApplygenre());
			sbDto.setStudentName(sb.getStudent().getXm());
			sbDto.setUploadbespeaktime(sb.getUploadbespeaktime());
			sbDto.setBespeaktime(sb.getBespeaktime());
			sbDto.setBespeakplace(sb.getBespeakplace());
			sbDto.setHaveornotexpert(sb.getHaveornotexpert());
			sbDto.setExpertName(sb.getExpert().getXm());
			sbDto.setBespeakstate(sb.getBespeakstate());
			sbDto.setExpertfeedbackstate(sb.getExpertfeedbackstate());
			listsbDto.add(sbDto);
		}
		page.setRoot(listsbDto);
		page.setTotal(total);
		
	}

	/**
	 * 删除预约
	 */
	public void deleteExpert(Integer sbid) {
		baseDao.deleteById(Studentbespeak.class, sbid);
	}

	/**
	 * 待指定专家
	 */
	@SuppressWarnings("unchecked")
	public void findPageWaitEnter(Page page, String[] property, String[] values) {
		List listsbDto = new ArrayList();
		String hql = "from Studentbespeak as model where model.bespeakstyle='随机' and model.bespeakstate !='已取消'";
		/**模糊查询——————————start————————*/
		String ehql = "from Expert as ex where 1=1 ";
		if(values[property.length-1]!=null){
			ehql += " and ex."+property[property.length-1]+" like '%"+values[property.length-1]+"%'";
		}
		//获取以姓名查询的专家
		List<Expert> exlist = baseDao.findByHql(ehql);
		for(int m=0;m<property.length;m++){
			if(values[m]!=null && m<property.length-1){
				hql += " and model."+property[m]+" like '%"+values[m]+"%'";
			}else if(values[m]!=null && !values[m].equals("")){				//模糊查询预约老师属性
				if(exlist!=null && exlist.size()>0){
					for(int i=0;i<exlist.size();i++){
						if(i>0){
						 	hql +=" or model.expert = "+exlist.get(i).getId()+"";
						}else{
							hql +=" and model.expert = "+exlist.get(i).getId()+"";
						}
					}
				}else{
					hql +=" and model.expert = '"+null+"'";
				}
			}
		}
		/**模糊查询————————end————————*/
		String chql = "select count(*) "+hql;
		String[] orderName = {"uploadbespeaktime"};
		String[] orderType = {"ASC"};
		List<Studentbespeak> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		for(Studentbespeak sb:list){
			StudentbespeakDTO sbDto = new StudentbespeakDTO();
			sbDto.setStdid(sb.getStudent().getId());
			sbDto.setStid(sb.getStid());
			sbDto.setApplygenre(sb.getApplygenre());
			sbDto.setStudentName(sb.getStudent().getXm());
			sbDto.setUploadbespeaktime(sb.getUploadbespeaktime());
			sbDto.setBespeaktime(sb.getBespeaktime());
			sbDto.setBespeakplace(sb.getBespeakplace());
			sbDto.setHaveornotexpert(sb.getHaveornotexpert());
			//sbDto.setExpertName(sb.getExpert().getXm());
			sbDto.setBespeakstate(sb.getBespeakstate());
			sbDto.setExpertfeedbackstate(sb.getExpertfeedbackstate());
			listsbDto.add(sbDto);
		}
		page.setRoot(listsbDto);
		page.setTotal(total);
	}

	/**
	 * 给待指定专家学生，分配专家
	 */
	public boolean updateStudentBespeakByIds(Integer stid, Integer id) {
		if(stid!=null && id!=null){
			String hql = "update Studentbespeak as sb set sb.expert = "+id+" , sb.haveornotexpert = '已分配' where sb.stid = "+stid+"";
			baseDao.update(hql);
			return true;
		}
		return false;
	}

	/**
	 * 我的预约详情
	 */
	public StudentbespeakDTO findMySbDetailById(Integer sbid) {
		String hql = "from Studentbespeak as sb where sb.stid="+sbid+"";
		Studentbespeak sb = (Studentbespeak) baseDao.loadObject(hql);
		StudentbespeakDTO sbDto = new StudentbespeakDTO();
		sbDto.setBespeakstyle(sb.getBespeakstyle());
		sbDto.setBespeakstate(sb.getBespeakstate());
		if(sb.getExpert()!=null){
			sbDto.setExpertPhoto(sb.getExpert().getPhoto());
			sbDto.setExpertName(sb.getExpert().getXm());
			sbDto.setExpertPhone(sb.getExpert().getPhone());
			sbDto.setExpertEmail(sb.getExpert().getEmail());
		}
		sbDto.setApplygenre(sb.getApplygenre());
		sbDto.setBespeaktime(sb.getBespeaktime());
		sbDto.setBespeakplace(sb.getBespeakplace());
		sbDto.setDetailinfo(sb.getDetailinfo());
		sbDto.setTalkcontent(sb.getTalkcontent());
		return sbDto;
	}

	/**
	 * 发送确认预约提示短信
	 */
	public Studentbespeak findSbByStid(Integer stid) {
		String hql = "from Studentbespeak as model where model.stid = "+stid+"";
		return (Studentbespeak) baseDao.loadObject(hql);
	}

	/**
	 * 单位/办公下拉菜单
	 */
	@SuppressWarnings("unchecked")
	public List<ComboData> findUnitComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Unit>  counList = baseDao.listAll("Unit");
		for (Unit un : counList) {
			ComboData comb = new ComboData();
			comb.setValue(un.getUnitName());
			comb.setText(un.getUnitName());
			list.add(comb);
		}
		return list;
	}

	/**
	 * 专家发布的预约信息
	 */
	public StartExpertInfo findSEIByStid(Integer id) {
		String hql = "from StartExpertInfo as model where model.expert = "+id+"";
		return (StartExpertInfo) baseDao.loadObject(hql);
	}

	/**
	 * 管理员端预约学生信息
	 */
	@SuppressWarnings("unchecked")
	public void findSBStudent(Page page, String experttype, String xm) {
		
		String types = "";
		if(StringUtils.isNotBlank(experttype)) {
			String[] expertTypes = experttype.split(",");
			for(int i = 0; i < expertTypes.length -1; i++) {
				if(i == 0) {
					types += "'" + expertTypes[i] +"'";
				} else {
					types += ",'" + expertTypes[i] +"'";
				}
			}
		}
		
		String hql = "from Studentbespeak as model where model.haveornotexpert = '未分配' and model.applygenre in ("+types+")";
		//模糊查询
		if(xm!=null && !xm.equals("")){
			String stdhql = "from Student as model where model.xm like '%"+xm+"%'";
			List<Student> stdList = baseDao.findByHql(stdhql);
			List stdIdList = new ArrayList();
			if(stdList.size()>0){
				for(Student std:stdList){
					stdIdList.add("'"+std.getId()+"'");
				}
				String idstr = stdIdList.toString();
				String strList = idstr.substring(idstr.indexOf("[")+1, idstr.indexOf("]"));
				hql += " and model.student in ("+strList+")";
			}else{
				hql += " and model.student = -1";
			}
		}
		String chql = "select count(*)"+hql;
		String[] orderName = {"uploadbespeaktime"};
		String[] orderType = {"DESC"};
		List<Studentbespeak> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		List listDto = new ArrayList(); 
		if(list.size()>0){
			for(Studentbespeak stdbs:list){
				StudentbespeakDTO stdDto = new StudentbespeakDTO();
				stdDto.setStid(stdbs.getStid());
				stdDto.setStdid(stdbs.getStudent().getId());
				stdDto.setStudentName(stdbs.getStudent().getXm());
				stdDto.setUploadbespeaktime(stdbs.getUploadbespeaktime());
				listDto.add(stdDto);
			}
		}
		page.setRoot(listDto);
		page.setTotal(total);
	}

	/**
	 * 获取专家信息
	 */
	public Expert findExpertById(Integer id) {
		String hql = "from Expert as model where model.id = "+id+"";
		return (Expert) baseDao.loadObject(hql);
	}

	/**
	 * 获取学生信息
	 */
	public Student findStudentById(Integer stdid) {
		String hql = "from Student as model where model.id = "+stdid+"";
		return (Student) baseDao.loadObject(hql);
	}

	public boolean saveOrUpdateExpertInfo(Expert expert) {
		Expert e = new Expert();
		if(null != expert.getId()) {
			e = (Expert) baseDao.loadById(Expert.class,expert.getId());
		}else{
			Expert ex = (Expert) baseDao.loadObject("from Expert where zgh='"
					+ expert.getZgh() + "'");
			if (ex != null) {
				return false;
			}
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
		return true;
	}

}
