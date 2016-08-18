package com.cxstock.biz.siteinfo.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.GroundType;
import com.cxstock.pojo.IfOpeTimeLog;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.Orga;
import com.cxstock.pojo.Park;
import com.cxstock.pojo.SiteInfo;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.SmsUtil;


/**
 * 场地信息接口实现类
 * @author wzx
 * 2016-5-27
 */
public class SiteInfoBizImp implements SiteInfoBiz{
	
	private BaseDAO baseDao;
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
    private DatumInfoBiz datumInfoBiz;
	
	private SmsBiz smsBiz;
    
	
	
    
	public DatumInfoBiz getDatumInfoBiz() {
		return datumInfoBiz;
	}

	public void setDatumInfoBiz(DatumInfoBiz datumInfoBiz) {
		this.datumInfoBiz = datumInfoBiz;
	}

	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}

	
	/**
	 * 场地信息申请保存
	 */
	@Override
	public boolean saveSiteInfo(SiteInfo stinfo) {
		if(null == stinfo){
			return false;
		}else{
			baseDao.saveOrUpdate(stinfo);
			return true;
		}
	}
	
	/**
	 * 根据场地id
	 * 查询场地编号
	 */
	@Override
	public SiteInfo findSing(Integer id) {
		 if(null == id ){
			 return null;
		 }else{
			 SiteInfo dto = (SiteInfo) baseDao
				.loadObject("from SiteInfo where id =" + id+ "");
		if (dto != null) {
			return dto;
		}
	   }
		return null;
	}
     
	/**
	 * 场地审核/批量审核
	 */
	@Override
	public void saveOrUpdaSiteInfo(SiteInfoLog pojo, String siteids) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(siteids);
		List<SiteInfoLog> stList = new ArrayList<SiteInfoLog>();
		for (int i = 0; i < jsonArray.size(); i++) {
		   JSONObject jo = (JSONObject) jsonArray.get(i);
		   SiteInfoLog st = new SiteInfoLog();
			st.setLogId(jo.getString("logId"));
			st.setXh(jo.getString("xh"));
			st.setProposer(jo.getString("proposer"));
			st.setSiteid(jo.getString("siteid"));//学号
			st.setRelationtel(jo.getString("relationtel"));
			st.setSitename(jo.getString("sitename"));
			st.setSitetype(jo.getString("sitetype"));
			st.setActivityname(jo.getString("activityname"));
			st.setActivitytype(jo.getString("activitytype"));
			st.setActivitycontent(jo.getString("activitycontent"));
			st.setBeorganize(jo.getString("beorganize"));
			st.setCounsellor(jo.getString("counsellor"));
			st.setActivitydate(DateUtil.parse(jo.getString("activitydate")));
			st.setActivitytime(jo.getString("activitytime"));
			st.setGuideth(jo.getString("guideth"));
			st.setIfschool(jo.getString("ifschool"));
			st.setSitemodle(jo.getInt("sitemodle"));
			st.setAgreement(jo.getString("agreement"));
			st.setPark(jo.getString("park"));
			st.setNowstatus(jo.getString("nowstatus"));
			st.setUsetime(jo.getInt("usetime"));
			st.setDutystate(jo.getString("dutystate"));
			st.setApplyTime(DateUtil.parse(jo.getString("applyTime")));
			st.setSitecondition(jo.getString("sitecondition"));
			st.setStatus(pojo.getStatus());
			stList.add(st);
			
			//短信接口
			if("已通过" ==pojo.getStatus()){
				//获取学生信息
				Student st1 = datumInfoBiz.findStudent(jo.getString("xh"));
				//获取发送信息
				Sms  sms  = smsBiz.findById(3);
				//把场地名称放进去
				String content = sms.getContent().replace("$(CDMC)", jo.getString("sitename"));
				 //调用短息接口的方法
			    SmsUtil.sendSms(st1.getPhone(), content);
			}else{
				//获取学生信息
				Student st1 = datumInfoBiz.findStudent(jo.getString("xh"));
				//获取发送信息
				Sms  sms  = smsBiz.findById(4);
				//把场地名称放进去
				String content = sms.getContent().replace("$(CDMC)", jo.getString("sitename"));
				 //调用短息接口的方法
			    SmsUtil.sendSms(st1.getPhone(), content);
			}
		
		}
		baseDao.saveOrUpdateAll(stList);
	}

	/**
	 * 场地列表分页按条件查询
	 */
	@Override
	public void findPageSiteInfo(Page page, String[] property, String[] value) {
		List list = baseDao.findByPropertyLike("SiteInfo",property,value, page.getStart(), page.getLimit());
		int total = baseDao.count("SiteInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}
    
	/**
	 * 删除场地
	 */
	@Override
	public void deleteSiteInfo(Integer id) {
		baseDao.deleteById(SiteInfo.class, id);	
		
	}
    
	/**
	 * 查询所有场地
	 * 使用时间情况
	 */
	@Override
	public List<SiteInfoLog> findAllData(String  siteid) {
		List<SiteInfoLog>  list= new ArrayList<SiteInfoLog>();
		if(null == siteid){
			return null;
		}else{
			 String hql = "from SiteInfoLog where siteid ='" + siteid+ "'";
			  list=  baseDao.findByHql(hql);
	        if(null != list) {
		       return list;
	        }
		}
	    return  null;
	}

	
	 /**
	  * 场地分页，搜索
	  * 排序查询
	  */
	@Override
	public void findPageSiteInfoBy(Page page, String[] property,
			String[] value, String orderName) {
		List list = baseDao.findByProperty1("SiteInfo",property,value,orderName,page.getStart(), page.getLimit());
		int total = baseDao.count("SiteInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
	}
   
	/**
	 * 场地申请分页查询
	 */
	@Override
	public void findPageApplySiteInfo(Page page, String[] propertyName,
			Object[] value, String timeName, String orderName1, String startTime,
			String endTime) {
		List list = baseDao.findByProperty5("SiteInfo", propertyName, value, timeName, orderName1, startTime, endTime, page.getStart(), page.getLimit());
		int total = baseDao.count5("SiteInfo", propertyName, value, timeName, startTime, endTime);
	    page.setRoot(list);
		page.setTotal(total);
	}
	
	/**
	 * 手机端  我申请的场地
	 */
	 public void findPageSiteInfoLog1(Page page, String[] propertyName,
			Object[] value, String timeName, String orderName1, String startTime,
			String endTime) {
		  List list = baseDao.findByProperty5("SiteInfoLog", propertyName, value, timeName, orderName1, startTime, endTime, page.getStart(), page.getLimit());
		  int total = baseDao.count5("SiteInfoLog", propertyName, value, timeName, startTime, endTime);
	      page.setRoot(list);
		  page.setTotal(total);
	}

    /**
     * 查询所有
     * 管理老师（辅导员）
     */
	@Override 
	public List<ComboData> findSiteInfoComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		//String hql = "from Instructor as a where a.roleid="+roleid+"";//findByHql(hql);
		List<Instructor>  counList = baseDao.listAll("Instructor");
		for (Instructor cf : counList) {
			ComboData comb = new ComboData();
			comb.setValue(cf.getXm());
			comb.setText(cf.getXm());
			list.add(comb);
		}
		return list;
	 }




    /**
     * 查询所有园区
     */
	@Override
	public List<ComboData> findParkComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Park>  counList = baseDao.listAll("Park");
		for (Park pk : counList) {
			ComboData comb = new ComboData();
			comb.setValue(pk.getParkName());
			comb.setText(pk.getParkName());
			list.add(comb);
		}
		return list;
	}


    /**
     * 查询所有组织
     */
	@Override
	public List<ComboData>    findOrgaComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Orga>  counList = baseDao.listAll("Orga");
		for (Orga og : counList) {
			ComboData comb = new ComboData();
			comb.setValue(og.getOrgaName());
			comb.setText(og.getOrgaName());
			list.add(comb);
		}
		return list;
	}


   /**
    * 查询所有场地类型
    */
	@Override
	public List<ComboData> findGroundTypeComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<GroundType>  counList = baseDao.listAll("GroundType");
		for (GroundType gt : counList) {
			ComboData comb = new ComboData();
			comb.setValue(gt.getTypeName());
			comb.setText(gt.getTypeName());
			list.add(comb);
		}
		return list;
	}


    /**
     * 场地申请记录
     * 保存
     */
    @Override
    public Boolean saveOrUpdateSiteInfoLog(SiteInfoLog log) {
	 if(null == log){
		 return false;
	 }else{
		 baseDao.saveOrUpdate(log);
		 return true;
	 }
  }


    /**
     * 场地申请查询
     */
	@Override
	public void findPageSiteInfoLog(Page page, String[] property,
			String[] value, String orderName) {
		List list = baseDao.findByProperty1("SiteInfoLog",property,value,orderName,page.getStart(), page.getLimit());
		int total = baseDao.count("SiteInfoLog",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}

    
	/**
	 * 查询所有
	 * 场地信息
	 */
	public  List<SiteInfoLog> findAllDate1(){
		List<SiteInfoLog>  list =  baseDao.listAll("SiteInfoLog");
		if(null != list){
		    return list;
		}else{
			return  null;
		}
		
	}
    
	
	/**
	 * 删除
	 * 申请的场地
	 */
	public  void deleteSiteInfoLog(String logId){
		baseDao.deleteById(SiteInfoLog.class, logId);	
	}


     /**
      * 根据id
      * 查询场地
      * 是否在24小时之内审核
      */
	@Override
	public SiteInfoLog findSingLog(String logId) {
		if(null == logId){
			return null;
		}else{
			SiteInfoLog lg = (SiteInfoLog) baseDao.loadObject("from SiteInfoLog where logId ='" + logId
					+ "'");
	    if (lg != null) {
		   return lg;
	       }
		}
		return null;
	  }


    /**
     * 记录场地不对外
     * 开放时间
     */
	@Override
	public Boolean saveOrUpdateIfOpeTimeLog(IfOpeTimeLog ifOpeTimeLog) {
		if(null == ifOpeTimeLog){
			return  false;
		}else{
			baseDao.saveOrUpdate(ifOpeTimeLog);
			return  true;
		}
	}


     /**
      * 查询所有场地不对外
      * 开放的时间
      */
	@Override
	public List<IfOpeTimeLog> findAllIfOpeTimeLog(String siteid) {
		List<IfOpeTimeLog>  list= new ArrayList<IfOpeTimeLog>();
		if(null == siteid){
			return null;
		}else{
			 String hql = "from IfOpeTimeLog where siteid ='" + siteid+ "'";
			  list=  baseDao.findByHql(hql);
	        if(null != list) {
		       return list;
	        }
		}
	    return  null;
	}

	
	/**
	 * 查询场地不对外开放时间
	 */
	@Override
	public IfOpeTimeLog findSingleIfOpeTimeLog(String siteid) {
	   if(null == siteid){
		   return null;
	   }else{
		  IfOpeTimeLog lg = (IfOpeTimeLog) baseDao.loadObject("from IfOpeTimeLog where siteid ='" + siteid
				+ "'");
       if (lg != null) {
	   return lg;
        }
	  }
	  return null;
	}

	/**
	 * 删除场地不对外
	 * 开放的时间
	 */
	@Override
	public void deleteIfOpeTimeLog(String opeId) {
		baseDao.deleteById(IfOpeTimeLog.class, opeId);	
		
	}

	
	/**
	 * 创建场地图片预览
	 */
	@Override
	public SiteInfo loadById(Integer id) {
		return (SiteInfo) baseDao.loadById(SiteInfo.class, id);
	}
     
	/**
	 * 场地申请  手机端
	 */
	@Override
	public List findListSite(String[] propertyName, Object[] value,
			String timeName, String orderName1, String startTime, String endTime) {
		List list = baseDao.findByProperty6("SiteInfo", propertyName, value, timeName, orderName1, startTime, endTime);
		if(null != list){
			return list;
		}else{
		  return null;
		}
	
	}
  
	/**
	 * 手机端 申请场地取消
	 */
	@Override
	public int updateSiteInfoLog(String status,String logId) {
		String hql = "UPDATE SiteInfoLog SET status='"+status+"' WHERE  logId = '"+logId+"'";
		Integer lg = baseDao.update(hql);
		if(null !=  lg){
			return lg;
		}else{
			return 0;
		}
		
	}

	

	
	

}
