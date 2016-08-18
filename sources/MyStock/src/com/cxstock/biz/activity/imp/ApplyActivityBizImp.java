package com.cxstock.biz.activity.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cxstock.biz.activity.ApplyActivityBiz;
import com.cxstock.biz.activity.dto.ActivityDTO;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Activity;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.News;
import com.cxstock.pojo.Orga;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Term;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

public class ApplyActivityBizImp implements ApplyActivityBiz {

	private BaseDAO baseDao;		//dao层接口参数
	
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	
	
	/**
	 * 申请活动
	 */
	public void saveApplyActivity(Activity activity) {
		baseDao.save(activity);
	}
	
	/**
	 * 显示待审活动信息
	 */
	@Override
	public List<?> selectCheckActivity() {
		String clazz = "Activity";
		return baseDao.listAll(clazz);
	}
	
	/**
	 * 通过id获取活动详情
	 */
	@Override
	public Activity findActivityInfoById(String activityid) {
		String hql = "from Activity a where a.activityid='"+activityid+"'";
		return (Activity) baseDao.loadObject(hql);
	}
	
	/**
	 * 审核待审活动，修改申请活动申请状态
	 * */
	@Override
	public int updateyById(String applystyle,String activityid) {
		String hql = "update Activity as a set a.applystyle='"+applystyle+"' where a.activityid='"+activityid+"'";
		System.out.println(hql);
		return baseDao.update(hql);
	}
	/**
	 * 个人活动列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findAllActivity(String usernum) {
		String hql = "from Activity as a where a.studentnum='"+usernum+"'";
		return baseDao.findByHql(hql);
	}

	/**
	 * 我报名的活动
	 */
	@SuppressWarnings("unchecked")
	public void findMyAttendActivity(Page page,String[] property,String[] value,String usernum) {
		String hql = "from Attend as model where model.usernum = '"+usernum+"' ";
		for(int i=0; i<property.length;i++){
			if(value[i]!=null){
				hql +=" and model."+property[i]+" like '%"+value[i]+"%'";
			}
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"activitytime","activityname"};
		String[] orderType = {"DESC","DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 活动管理-活动审批列表分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void findPageApply(Page page, String[] property, String[] value, String ssyq) {
		String sthql;
		if(ssyq!=null && !ssyq.equals("")){
			sthql = "from Student as model where model.ssyq = '"+ssyq+"'";
		}else{
			sthql = "from Student as model where 1=1";
		}
		List<Student> stList = baseDao.findByHql(sthql);
		List comenYqSt = new ArrayList();
		for(Student st: stList){
			comenYqSt.add("'"+st.getXh()+"'");
		}
		String idstr = comenYqSt.toString();
		String strList = idstr.substring(idstr.indexOf("[")+1, idstr.indexOf("]"));
		List list = new ArrayList();
		if(comenYqSt.size()>0){
			String hql = "from Activity as model where model.applystyle = '待审核' and model.studentnum in ("+strList+")";
			for (int i = 0; i < property.length; i++) {
				if (value[i]!=null) {
					hql += " and model." + property[i] + " like '%" + value[i]
							+ "%'";
				}
			}
			String chql = "select count(*) "+hql;
			String[] orderName = {"activitytime","signupendtime"};
			String[] orderType = {"DESC","DESC"};
			list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
			int total = baseDao.countQuery(chql);
			page.setRoot(list);
			page.setTotal(total);
		}else{
			page.setRoot(list);
			page.setTotal(0);
		}
	}

	/**
	 * 根据用户num查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void findPageApply(Page page,String value, String usernum) {
		String hql = "from Activity as model where model.studentnum = '"+usernum+"' ";
		if(value!=null){
			hql +=" and model.activityname like '%" + value+ "%'";
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"activitytime","signupendtime"};
		String[] orderType = {"DESC","DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 取消报名
	 */
	public int deleteStateById(String state, String activityid,Integer stdid) {
		
		if(activityid!=null && !activityid.equals("") && stdid!=null &&!stdid.equals("")){
			String sql = "delete from student_attend where studentid = "+stdid+" and attendid = '"+activityid+"'";
			int i = baseDao.excuteBySql(sql);
			if(i>0){
				baseDao.deleteById(Attend.class, activityid);
				return 1;
			}
		}
		return 0;
		
	}

	/**
	 * 报名人数更新
	 */
	public int updateStateActivityById(String state,String activityid) {
		String hql;
		if(state!=null){
			hql = "update Activity as a set a.attendnum = a.attendnum-1 where a.activityid='"+activityid+"'";
		}else{
			hql = "update Activity as a set a.attendnum = a.attendnum+1 where a.activityid='"+activityid+"'";
		}
		return baseDao.update(hql);
	}
	
	/**
	 * 活动报名
	 */
	public int saveAttendActivity(Attend attend) {
		try {
			baseDao.save(attend);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 发布活动个人活动管理
	 */
	@SuppressWarnings("unchecked")
	public List<Attend> findAttendById(String activityid) {
		String hql = "from Attend as att where att.activityid='"+activityid+"' and att.state!='已取消'";
		return baseDao.findByHql(hql);
	}

	/**
	 * 活动修改
	 */
	public void updateActivityById(Activity activity) {
		baseDao.saveOrUpdate(activity);
	}

	/**
	 * 更新
	 */
	public void updateClassById(String clazz, String setproperty,
			String applystyle, String byid, String activityid) {
		String hql = "update "+clazz+" as a set a."+setproperty+" = '"+applystyle+"' where a."+byid+"='"+activityid+"'";
		baseDao.update(hql);
	}

	/**
	 * 添加新闻
	 */
	public int saveClazz(News news) {
		try {
			baseDao.save(news);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	/**
	 * 更新新闻
	 */
	public void updateNews(News news) {
		baseDao.saveOrUpdate(news);
	}

	/**
	 * 获取新闻稿
	 */
	public News findClazzById(String clazz, String findproperty,
			String idValue) {
		String hql = "from "+clazz+" as n where n."+findproperty+" = '"+idValue+"'";
		return (News) baseDao.loadObject(hql);
	}

	/**
	 * 待新闻，公示审核的活动
	 */
	@SuppressWarnings("unchecked")
	public void findPageActivity(Page page, String[] property, String[] values,
			String findstyle) {
		String hql = "from Activity as model where 1=1";
		for (int i = 0; i < property.length; i++) {
			if(null!=values[i]){
				hql += " and model." + property[i] + " like '%"+values[i]+"%'";
			}
		}
		hql +=" and model."+findstyle+"='未公示待审核'";
		String hqlc = "select count(*) " + hql;
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(hqlc);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 文件导入更新数据
	 */
	public int updateClassById(String clazz, String setproperty, String state,
			String[] byid, String[] valuesId) {
		String hql = "update "+clazz+" as a set a."+setproperty+" = '"+state+"'";
		String where = " where 1=1 ";
		for(int i=0;i<byid.length;i++){
			if(valuesId[i]!=null){
				where +=" and a."+ byid[i]+" = '"+valuesId[i]+"' ";
			}
		}
		return baseDao.update(hql+where);
	}

	/**
	 * 查询可以报名的活动
	 */
	@SuppressWarnings("unchecked")
	public void findPageActivityPassed(Page page, String value,
			String applystyle,String toDayDate, String activitygenre) {
		String hql = "from Activity as model where model.applystyle = '"+applystyle+"' and model.signupendtime >= '"+toDayDate+"'";
		if(activitygenre!=null && !activitygenre.equals("")){
			hql += " and model.activitygenre = '"+activitygenre+"'";
		}
		if (null != value) {
			hql += " and model.activityname" + " like '%" + value + "%'";
		}
		String[] orderName = {"activitytime","signupendtime"};
		String[] orderType = {"DESC","DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName,orderType,page.getStart(), page.getLimit());
		String chql = "select count(*) "+hql;
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 判断是否报过名
	 */
	public Attend findIsOrNotAttend(String usernum, String activityid) {
		String hql = "from Attend as a where a.usernum = '"+usernum+"' and a.activityid = '"+activityid+"'";
		return (Attend) baseDao.loadObject(hql);
	}

	/**
	 * 纪实考评
	 */
	@SuppressWarnings("unchecked")
	public void findPageAttendByProperty(Page page, String[] property,
			String[] values, HeadMaster master,int tid,String inschoolterm) {
		String hql;
		if(master!=null){
			hql = "from Student as model  where  model.headmaster = "+tid+" ";
		}else{
			hql = "from Student as model where model.instructor = "+tid+" ";
		}
		for(int i=0;i<property.length;i++){
			if(null!=values[i]){
				hql += " and model." + property[i] + " like '%"+values[i]+"%'";
			}
		}
		String chql = "select count(*)"+hql;
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List stdDto = StudentDTO.createRecordDto(list,inschoolterm);
		int total = baseDao.countQuery(chql);
		page.setRoot(stdDto);
		page.setTotal(total);
	}
	
	/**
	 * 获取学生信息
	 */
	public Student findStudentById(Integer id) {
		String hql = "from Student as s where s.id = '"+id+"'";
		return (Student) baseDao.loadObject(hql);
	}

	/**
	 * 获取学生信息
	 */
	public Student findStudentById(String logincode) {
		String hql = "from Student as s where s.xh = '"+logincode+"'";
		return (Student) baseDao.loadObject(hql);
	}

	/**
	 * 更具id更新数组中的属性
	 */
	public void updateClassPropertiesById(String clazz, String[] setproperty,
			String[] updatevalues, String byid, String activityid) {
		String hql = "update "+clazz+" as cl set ";
		for(int i=0;i<setproperty.length;i++){
			hql += "cl."+setproperty[i]+"= '"+updatevalues[i]+"' ";
			if(setproperty.length>1 && i<setproperty.length-1){
				hql +=" , ";
			}
		}
		hql += " where cl."+byid+"= '"+activityid+"'";
		baseDao.update(hql);
	}

	/**
	 * 公示审核
	 */
	@SuppressWarnings("unchecked")
	public void updatePublicityCheck(String pulishkey, String activityid) {
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String activitypublicitytime = sf.format(d);
		String adhql = "from Attend as model where model.activityid = '"+activityid+"'";
		List<Attend> adList = baseDao.findByHql(adhql);
		if(pulishkey.equals("1")){//允许公示
			String hql = "update Activity as model set model.publicitycheckstyle = '公示中' , model.activitypublicitytime = '"+activitypublicitytime+"' where model.activityid = '"+activityid+"'";
			int m = baseDao.update(hql);
			if(m>0 && adList!=null && adList.size()>0){
				String achql = "from Activity as model where model.publicitycheckstyle = '公示中'";
				List<Activity> aclist = baseDao.findByHql(achql);
				List acidList = new ArrayList();
				for(Activity ac:aclist){
					acidList.add("'"+ac.getActivityid()+"'");
				}
				if(acidList.size()>0){
					String idstr = acidList.toString();
					String subidstr = idstr.substring(idstr.indexOf("[")+1, idstr.indexOf("]"));
					for(Attend ad: adList){
						String sumhql = "select SUM(gotscore) from Attend as model where model.usernum = '"+ad.getUsernum()+"' and model.activityid in ("+subidstr+")";
						int sum = baseDao.findSum(sumhql);
						String grosshql = "update Attend as model set model.gross = "+sum+" where model.usernum = '"+ad.getUsernum()+"'";
						baseDao.update(grosshql);
					}
				}
			}
		}else if(pulishkey.equals("2")){//不允许公示
			String hql = "update Activity as model set model.publicitycheckstyle = '公示不通过' , model.activitypublicitytime = '"+activitypublicitytime+"' where model.activityid = '"+activityid+"'";
			int n = baseDao.update(hql);
			if(n>0 && adList!=null && adList.size()>0){
				for(Attend ad: adList){
					String gotscorehql = "update Attend as model set model.gotscore = "+0+" where model.usernum = '"+ad.getUsernum()+"' and model.activityid = '"+activityid+"'";
					int p = baseDao.update(gotscorehql);
				}
			}
		}
	}
	
	/**
	 * 更新参加活动得分
	 */
	public int updateScoreById(String clazz, String setproperty, int score,
			String[] byid, String[] valuesId) {
		String hql = "update "+clazz+" as a set a."+setproperty+" = "+score+"";
		String where = " where 1=1 ";
		for(int i=0;i<byid.length;i++){
			if(valuesId[i]!=null){
				where +=" and a."+ byid[i]+" = '"+valuesId[i]+"' ";
			}
		}
		return baseDao.update(hql+where);
	}

	/**
	 * 统计更新学生的总得分
	 */
	public void updateGrossById(String clazz, String setproperty, int sum,
			String setbyid, String updatevaluesId) {
		String hql = "update "+clazz+" as a set a."+setproperty+" = "+sum+ " where "+setbyid+" = '"+updatevaluesId+"'";
		baseDao.update(hql);
	}

	/**
	 * 获取总得分
	 */
	public int countGross(String clazz, String value, String setbyid,String updatevaluesId) {
		String hql ="select "+value+" from "+clazz+" as a where a."+setbyid+" = '"+updatevaluesId+"'";
		return baseDao.findSum(hql);
	}


	/**
	 * 公示
	 */
	@SuppressWarnings("unchecked")
	public void findPagePublicity(Page page, String[] propety, String[] values) {
		String hql = "from Activity as model where model.publicitycheckstyle = '公示中' ";
		
		for(int i=0;i<propety.length;i++){
			if(values[i]!=null){
				hql +=" and model."+propety[i]+" like '%"+values[i]+"%' ";
			}
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"publicitycheckstyle"}; 
		String[] orderType ={"DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}


	/**
	 * 新闻专栏
	 */
	@SuppressWarnings("unchecked")
	public void findPageNews(Page page) {
		String hql = "from News as model where model.newscheckstyle = '已发布'";
		String chql = "select count(*) "+hql;
		String[] orderName = {"totop","newsdate"};
		String[] orderType = {"ASC","DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}


	/**
	 * 首页活动报名
	 */
	@SuppressWarnings("unchecked")
	public void findPageActivityInfo(Page page, Object object) {
		List listAllActivity = new ArrayList();
		String[] agenre= {"学科讲座","生涯规划","心理健康","党团建设","文体活动","其他"};
		for(int i=0;i<agenre.length;i++){
			String hql = "from Activity as model where model.activitygenre='"+agenre[i]+"' and model.applystyle='已通过'";
			String[] orderName = {"activitytime"};
			String[] orderType = {"DESC"};
			
			List<Activity> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
			if(list!=null && list.size()>0){
				for(Activity ac:list){
					ActivityDTO aDto = new ActivityDTO();
					aDto.setAid(ac.getActivityid());
					aDto.setAname(ac.getActivityname());
					aDto.setAgenre(ac.getActivitygenre());
					aDto.setAttendnum(ac.getAttendnum());
					aDto.setCapacity(ac.getCapacity());
					listAllActivity.add(aDto);
				}
			}
		}
		page.setRoot(listAllActivity);
	}

	/**
	 *具体类型活动
	 */
	@SuppressWarnings("unchecked")
	public void findActivityByGenre(Page page,String key,String serchKey) {
		String hql ="from Activity as model where 1=1 and model.applystyle='已通过' ";
		if(serchKey!=null && !serchKey.equals("")){
			hql +=" and model.activityname like '%"+serchKey+"%' ";
		}
		if(key.equals("kxjz")){
			hql +=" and model.activitygenre = '学科讲座'";
		}else if(key.equals("sygh")){
			hql +=" and model.activitygenre = '生涯规划'";
		}else if(key.equals("xljk")){
			hql +=" and model.activitygenre = '心理健康'";
		}else if(key.equals("dtjs")){
			hql +=" and model.activitygenre = '党团建设'";
		}else if(key.equals("wthd")){
			hql +=" and model.activitygenre = '文体活动'";
		}else if(key.equals("qt")){
			hql +=" and model.activitygenre = '其他'";
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"activitytime","checktime"};
		String[] orderType = {"DESC","ASC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 公示参加活动信息
	 */
	@SuppressWarnings("unchecked")
	public void findAttendActivityInfoById(Page page,String activityid) {
		String hql = "from Attend as model where model.activityid = '"+activityid+"'";
		String chql = "select count(*) "+hql;
		String[] orderName = {"usernum"};
		String[] orderType = {"ASC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 新闻详情
	 */
	public News findNewsDetail(String newsid) {
		String hql = "from News as model where model.newsid = '"+newsid+"'";
		return (News) baseDao.loadObject(hql);
	}


	/**
	 *  获取已结束活动还未发布新闻和公示的活动
	 */
	@SuppressWarnings("unchecked")
	public List<Activity> findAllActivity() {
		Date d = new Date();
		long endTime = d.getTime() - 3*24*60*60*1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curentTime = sdf.format(endTime);
		String hql ="from Activity as model where model.applystyle='已通过' and model.signupendtime >= '"+curentTime+"'";
		List<Activity> acList = baseDao.findByHql(hql);
		if(acList!=null && acList.size()>0){
			return acList;
		}
		return null;
	}



	/**
	 * 没有导入参加活动名单的活动
	 */
	public int findAllNotImportFileOfActivity(String activityid) {
		String hql = "select count(*) from Attend as model where model.state = '待参加' and model.activityid = '"+activityid+"'";
		return (int) baseDao.findSum(hql);
	}



	/**
	 * 所在学期
	 */
	@SuppressWarnings("unchecked")
	public List<ComboData> findSzxqComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Term>  counList = baseDao.listAll("Term");
		for (Term ter : counList) {
			ComboData comb = new ComboData();
			comb.setValue(ter.getTermName());
			comb.setText(ter.getTermName());
			list.add(comb);
		}
		return list;
	}

	/**
	 * 查询活动
	 */
	public Attend findAttendByIds(String usernum, String activityid) {
		String hql = "from Attend as model where model.usernum = '"+usernum+"' and model.activityid = '"+activityid+"'";
		return (Attend) baseDao.loadObject(hql);
	}

}
