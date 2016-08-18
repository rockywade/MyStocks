package com.cxstock.biz.leaveinfo.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.cxstock.biz.leaveinfo.LeaveInfoBiz;
import com.cxstock.biz.leaveinfo.dto.LeaveInfoDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.GroundType;
import com.cxstock.pojo.LeaveInfo;
import com.cxstock.pojo.LeaveInfoLog;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.DateUtil;

/**
 * 请假信息实现接口 @ wzx 2016-5-24
 * 
 */
public class LeaveInfoBizImp implements LeaveInfoBiz {

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}


	/**
	 * 请假信息数据保存/更新
	 * 
	 */
	@Override
	public boolean saveOrUpdateLeaveInfo(LeaveInfo qo) {
		int days = DateUtil.getOffsetDays(qo.getLeavetime(), qo.getBacksctime());
         if(null == qo){
        	 return false;
         }else{
        	 baseDao.saveOrUpdate(qo);
        	 return true;
         }
		
	}

	/**
	 * 根据登录用户的logincode去查询学生信息
	 * 请假跳转新增或修改数据modle
	 * 
	 */
	@Override
	public Student goAddOrUpdateLeaveInfo(String xh) {
		if (xh == null || xh == "") {
			return null;
		}
		Student dto = (Student) baseDao
				.loadObject("from Student where xh ='" + xh
						+ "'");
		if (dto != null) {
			return dto;
		}
		return null;
	}

	/**
	 * 执行请假新增数据保存
	 */
	@Override
	public boolean saveLeaveInfo(LeaveInfo qo) {
	    int days = DateUtil.getOffsetDays(qo.getLeavetime(), qo.getBacksctime());
		if (qo == null) {
			return false;
		} else {
			if(qo.getRulesstate().equals("on")){
				qo.setRulesstate("已阅读");
			}
			qo.setDaysum(days);
		
			if(days >= 30){
				qo.setSchoolstatus("待审核");
			}
			qo.setStatus("待审核");
			baseDao.save(qo);
			return true;
		}

	}

	
     
	/**
	 * 老师审核列表查询
	 */
	@Override
	public void findPageLeaveInfo(Page page, String[] property,String[] value) {
		List list = baseDao.findByPropertyLike("LeaveInfo",property,value, page.getStart(), page.getLimit());
		int total = baseDao.count("LeaveInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
		
	}

	/**
	 * 请假信息批量审核
	 */
	@Override
	public void  saveOrUpdaLeaveInfo(LeaveInfo pojo, String studentnums){
		//baseDao.saveOrUpdate(pojo);
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(studentnums);
		List<LeaveInfo> lfList = new ArrayList<LeaveInfo>();
		for (int i = 0; i < jsonArray.size(); i++) {
		   //JSONObject jo = (JSONObject) jsonArray.get(i);
			LeaveInfo leaveInfo = new LeaveInfo();
			leaveInfo.setLeavereason(pojo.getLeavereason());
			leaveInfo.setStudentname(pojo.getStudentname());
			leaveInfo.setStudentnum(pojo.getStudentnum());
			leaveInfo.setStatus(pojo.getStatus());
			leaveInfo.setLeavetime(pojo.getLeavetime());
			leaveInfo.setBacksctime(pojo.getBacksctime());
			leaveInfo.setClassth(pojo.getClassth());
			leaveInfo.setBedroom(pojo.getBedroom());
			leaveInfo.setRelationtel(pojo.getRelationtel());
			leaveInfo.setCounsellor(pojo.getCounsellor());
			leaveInfo.setLeavereason(pojo.getLeavereason());
			leaveInfo.setMajor(pojo.getMajor());
			leaveInfo.setClasscode(pojo.getClasscode());
			leaveInfo.setParentsinfo(pojo.getParentsinfo());
			leaveInfo.setRulesstate(pojo.getRulesstate());
			leaveInfo.setParentstel(pojo.getParentstel());
			leaveInfo.setChecksopinion(pojo.getChecksopinion());
			lfList.add(leaveInfo);
		
		}
		baseDao.saveOrUpdateAll(lfList);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteLeaveInfo(Integer id) {
		baseDao.deleteById(LeaveInfo.class, id);	
		
	}
    
	/**
	 *辅导员 分页，搜索，排序查询
	 */
	@Override
	public void findPageLeaveInfo1(Page page, String[] property,String[] value, String orderName1,String orderName2) {
		List list = baseDao.findByProperty("LeaveInfo",property,value,orderName1,orderName2,page.getStart(), page.getLimit());
		int total = baseDao.count("LeaveInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}

    /**
     * 院系领导
     * 分页，搜索，排序查询
     */
	@Override
	public void findPageLeaveInfo4(Page page,String[] propertyName,
			Object[] value, String orderName1, String orderName2,String orderName3,
			String classcode ,List classcodevalue) {
		List list = baseDao.findByProperty4("LeaveInfo",propertyName,value,orderName1,orderName2,orderName3,
				classcode,classcodevalue,page.getStart(), page.getLimit());
		int total = baseDao.count1("LeaveInfo",propertyName,value,orderName3,classcode,classcodevalue);
		page.setRoot(list);
		page.setTotal(total);
		  
		
	}
	
	@Override
	public void findPageLeaveInfo10(Page page) {
		String hql = "from Student s left join fetch s.classes c where c.bjdm='bj001'";
		List list = baseDao.findByHql(hql);
		StringBuilder sb = new StringBuilder();
		String xh = "";
		for(int i=0;i<list.size();i++){
			sb.append("'").append(((Student)list.get(i)).getXh()).append("'").append(",");
		}
		if(sb.length()>0){
			xh = sb.substring(0,sb.length()-1); 
		}
		hql = "from LeaveInfo where daysum>30 and studentnum in ("+xh+") ";
		list = baseDao.findByHql(hql, 0, 10);
		int total = 10;
		page.setRoot(list);
		page.setTotal(total);
		  
		
	}
	
	
	
	

    /**
     * 根据所属园区查所有班级
     */
	@Override
	public List<Classes> findClass(String  value) {
		String sql = "from Classes as c where c.ssyq = '"+value+"'";
		List<Classes> list = baseDao.findByHql(sql);
		return list;
	}

    /**
     * 查询所有班级
     * 下拉菜单
     */
	@Override
	public List<ComboData> findClassComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Classes>  counList = baseDao.listAll("Classes");
		for (Classes gt : counList) {
			ComboData comb = new ComboData();
			comb.setValue(gt.getBjdm());
			comb.setText(gt.getBjdm());
			list.add(comb);
		}
		return list;
	}
	
	/**
	 * 根据老师查询
	 * 相应的班级
	 * 下拉菜单
	 */
	@Override
	public List<ComboData> findClassComb1(List<Classes> counList) {
		List<ComboData> list = new ArrayList<ComboData>();
		for (Classes gt : counList) {
			 ComboData comb = new ComboData();
			 comb.setValue(gt.getBjdm());
			 comb.setText(gt.getBjdm());
			 list.add(comb);
		}
		return list;
	}

	
	

    /**
     * 计算请假次数
     */
	@Override
	public Integer countTotalnum(String studentnum) {
		String hql = "Select sum(totalnum) from LeaveInfo where studentnum='"+studentnum+"' ";
		 Integer total = baseDao.findSum(hql);
		  if(total >0){
			  return total;
		  }
		return 0;
	}

	
	
     /**
      * 我的学生预览列表
      */
	@Override
	public void findPageLeaveInfoLog(Page page, String[] property,
			String[] value, String orderName) {
		List list = baseDao.findByProperty1("LeaveInfoLog",property,value,orderName,page.getStart(), page.getLimit());
		int total = baseDao.count("LeaveInfoLog",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}

    /**
     * 根据学号查询
     * 记录表的数据
     */
	@Override
	public LeaveInfoLog findSingleLeaveInfoLog(String studentnum) {
		if (studentnum == null || studentnum == "") {
			return null;
		}
		LeaveInfoLog dto = (LeaveInfoLog) baseDao
				.loadObject("from LeaveInfoLog where studentnum ='" + studentnum
						+ "'");
		if (dto != null) {
			return dto;
		}
		return  null;
	}

    
	/**
	 * 新增保存记录表数据
	 */
	@Override
	public boolean saveLeaveInfoLog(LeaveInfoLog qo) {
		if(null == qo){
			return false;
		}else{
			baseDao.saveOrUpdate(qo);
        	return true;
		}
		
	}

    /**
     * 更新记录表的
     * 统计次数
     */
	@Override
	public int updateLeaveInfoLog(Integer totalnum, String studentnum) {
		String hql = "UPDATE LeaveInfoLog SET totalnum="+totalnum+" WHERE  studentnum = '"+studentnum+"'";
		Integer s = baseDao.update(hql);
		if(null !=  s){
			return s;
		}else{
			return 0;
		}
		
	}

    /**
     * 查询学生手机端列表
     */
	@Override
	public void findLeaveInfo(Page page ,String studentnum, String orderName) {
		String hql = "from LeaveInfo where studentnum = '"+studentnum+"'    order by  " + orderName + " DESC ";
		List<LeaveInfo> list = baseDao.findByHql(hql,page.getStart(),page.getLimit());
		String hql1 = "select count(*)  from  LeaveInfo where studentnum = '"+studentnum+"'  ";
		int total = baseDao.countQuery(hql1);
		List<LeaveInfoDTO> list1 =  new ArrayList<LeaveInfoDTO>();
		if(null != list ){
			for(int i=0 ;i<list.size();i++){
			  LeaveInfoDTO  dto  = new LeaveInfoDTO();
			  dto.setId(list.get(i).getId());
			  dto.setLeavereason(list.get(i).getLeavereason());
			  dto.setLeavetime(DateTime.toString(list.get(i).getLeavetime()));
			  dto.setBacksctime(DateTime.toString(list.get(i).getBacksctime()));
			  dto.setDaysum(list.get(i).getDaysum());
			  dto.setTutorstatus(list.get(i).getTutorstatus());
			  dto.setSchoolstatus(list.get(i).getSchoolstatus());
			  dto.setParentsinfo(list.get(i).getParentsinfo());
			  list1.add(dto);
			 }
			page.setRoot(list1);
			page.setTotal(total);
		}
	
	}


	@Override
	public List findListLeaveinfo(String studentnum, String orderName) {
		String hql = "from LeaveInfo where studentnum = '"+studentnum+"'    order by  " + orderName + " DESC ";
		List<LeaveInfo> list = baseDao.findByHql(hql);
		List<LeaveInfoDTO> list1 =  new ArrayList<LeaveInfoDTO>();
		if(null != list ){
			for(int i=0 ;i<list.size();i++){
			  LeaveInfoDTO  dto  = new LeaveInfoDTO();
			  dto.setId(list.get(i).getId());
			  dto.setLeavereason(list.get(i).getLeavereason());
			  dto.setLeavetime(DateTime.toString(list.get(i).getLeavetime()));
			  dto.setBacksctime(DateTime.toString(list.get(i).getBacksctime()));
			  dto.setDaysum(list.get(i).getDaysum());
			  dto.setTutorstatus(list.get(i).getTutorstatus());
			  dto.setSchoolstatus(list.get(i).getSchoolstatus());
			  dto.setParentsinfo(list.get(i).getParentsinfo());
			  list1.add(dto);
			 }
		    return  list1;
	     }else{
	    	 return null;
	     }
	}
}
