package com.cxstock.biz.datuminfo.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.DataJiFen;
import com.cxstock.pojo.DatumInfo;
import com.cxstock.pojo.DownLog;
import com.cxstock.pojo.OfflineFd;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.SmsUtil;

public class DatumInfoBizImp implements DatumInfoBiz{
	
	private BaseDAO  baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
    
	
	private SmsBiz smsBiz;
	
	
	public SmsBiz getSmsBiz() {
		return smsBiz;
	}


	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}


	/**
	 * 学习资料的
	 * 分页查询
	 */
	@Override
	public void findPageDatumInfo(Page page, String[] property, String[] value) {
		List list = baseDao.findByPropertyLike("DatumInfo",property,value, page.getStart(), page.getLimit());
		int total = baseDao.count("DatumInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}


    /**
     * 学习资料上传
     * 数据保存
     */
	@Override
	public boolean saveDatumInfo(DatumInfo datumInfo) {
		if(null == datumInfo){
			return false;
		}else{
			baseDao.saveOrUpdate(datumInfo);
			return true;
		}
	}


    /**
     * 我上传资料删除
     */
	@Override
	public void deleteDatumInfo(Integer id) {
		baseDao.deleteById(DatumInfo.class, id);	
		
	}

   /**
    * 批量刪除
    */
	@Override
	public void deleteDatumInfoAll(String datumInfoAll) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(datumInfoAll);
		for (int i = 0; i < jsonArray.size(); i++) {
			   JSONObject jo = (JSONObject) jsonArray.get(i);
			   baseDao.deleteById(DatumInfo.class, jo.getInt("id"));
		}
		
	}

	

    /**
     * 学习资料隐藏/批量隐藏
     */
	@Override
	public void saveOrUpdaDatumInfo(DatumInfo pojo, String datumInfoAll) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(datumInfoAll);
		List<DatumInfo> dtList = new ArrayList<DatumInfo>();
		for (int i = 0; i < jsonArray.size(); i++) {
			   JSONObject jo = (JSONObject) jsonArray.get(i);
			   DatumInfo datumInfo = new DatumInfo();
			   datumInfo.setId(jo.getInt("id"));
			   datumInfo.setDatumnumber(jo.getString("datumnumber"));
			   datumInfo.setDatumname(jo.getString("datumname"));
			   datumInfo.setShareman(jo.getString("shareman"));
			   datumInfo.setSharetime(DateUtil.parse(jo.getString("sharetime")));
			   datumInfo.setDatumstyle(jo.getString("datumstyle"));
			   datumInfo.setDatumcontent(jo.getString("datumcontent"));
			   datumInfo.setSharegrade(jo.getInt("sharegrade"));
			   datumInfo.setDatumsize(jo.getString("datumsize"));
			   datumInfo.setDownnum(jo.getInt("downnum"));
			   datumInfo.setDownnumber(jo.getString("downnumber"));
			   datumInfo.setTransfetag(jo.getString("transfetag"));
			   datumInfo.setCreatetime(DateUtil.parse(jo.getString("createtime")));
			   datumInfo.setCreaterid(jo.getInt("createrid"));
			   datumInfo.setCreatername(jo.getString("creatername"));
			   datumInfo.setStatus(pojo.getStatus());
			   if(null == pojo.getToptime()){
				   datumInfo.setToptime(DateUtil.parse(jo.getString("toptime"))); 
			   }else{
				   datumInfo.setToptime(pojo.getToptime()); 
			   }
			   datumInfo.setUpdaterid(pojo.getUpdaterid());
			   datumInfo.setUpdatername(pojo.getUpdatername());
			   datumInfo.setUpdatetime(new Date());
			   dtList.add(datumInfo);
			
			}
			baseDao.saveOrUpdateAll(dtList);
	}


    /**
     * 查询下载需要的路径
     */
	@Override
	public DatumInfo findDatumInfoById(Integer id) {
		if (id == null) {
			return null;
		}
		DatumInfo datumInfo = (DatumInfo) baseDao
				.loadObject("from DatumInfo where id ='" + id+ "'");
		if (datumInfo != null) {
			return datumInfo;
		}
		return null;
	
	}

     /**
      *判断下载记录是否已经评分过
      */
	@Override
	public DownLog findDownLogById(String downid) {
		if (downid == null) {
			return null;
		}
		DownLog log = (DownLog) baseDao
				.loadObject("from DownLog where downid ='" + downid+ "'");
		if (log != null) {
			return log;
		}
		return null;
	}

	

    /**
     *管理员端  分页，排序，
     * 搜素查询
     */
	@Override
	public void findPageDatumInfoBy(Page page, String[] property,
			String[] value, String orderName1, String orderName2) {
		List list = baseDao.findByProperty("DatumInfo",property,value,orderName1,orderName2,page.getStart(), page.getLimit());
		int total = baseDao.count("DatumInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}


   /**
    * 老师端 分页 排序 搜素
    */
	@Override
	public void findPageDatumInfoOrderBy(Page page, String[] property,
			String[] value, String orderName1, String orderName2,
			String orderName3) {
		List list = baseDao.findByProperty3("DatumInfo",property,value,orderName1,orderName2,orderName3,page.getStart(), page.getLimit());
		int total = baseDao.count("DatumInfo",property,value);
		page.setRoot(list);
		page.setTotal(total);
		
	}


   /**
    * 下载资料记录新增或者修改
    */
    @Override
    public boolean saveOrUpdataDownLog(DownLog downLog) {
    	if(null == downLog){
    		return false;
    	}else{
    		baseDao.saveOrUpdate(downLog);
    		return true;
    	}
	
     }
    
   /**
    * 我下载的资料
    */
    @Override
    public void findByHqlAndDate(Page page, String startDate, String endDate,
    		String propertyname,String downid, String orderName) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate1 = sdf.parse(startDate);
    	Date endDate1 = sdf.parse(endDate);
    	String hql = "from DownLog where downid like '"+downid+"_%' and dowtime between ? and ?  order by  " + orderName + " DESC ";
        List list = baseDao.findByHqlAndDate(hql, startDate1, endDate1, page.getStart(), page.getLimit());
        int total = baseDao.count2("DownLog",propertyname,downid);
        page.setRoot(list);
  	    page.setTotal(total);
    }


   /**
    * 计算平均评分
    */
   @Override
   public Integer sumSharegrade(String propertyname1, String datumnumber,String propertyname2,
	   String gradeTag) {
	   Integer longs = null;
	   String hql = "Select sum(sharegrade) from DownLog where datumnumber='"+datumnumber+"' and gradeTag='"+gradeTag+"'";
	   Integer longttoatl = baseDao.findSum(hql);
	   Integer total = baseDao.count3("DownLog",propertyname1,datumnumber,propertyname2,gradeTag);
	    if(null != longttoatl&& total > 0 ){
	     longs = longttoatl/total;
	    }
	  
	return longs;
  }


  
   /**
    * 我上传的学习资料
    */
   @Override
   public void findByHqlAndDate1(Page page, String startDate, String endDate,
		String propertyname, String datumnumber, String orderName)
		throws ParseException {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   	    Date startDate1 = sdf.parse(startDate);
     	Date endDate1 = sdf.parse(endDate);
      	String hql = "from DatumInfo where datumnumber = '"+datumnumber+"' and sharetime between ? and ?  order by  " + orderName + " DESC ";
        List list = baseDao.findByHqlAndDate(hql, startDate1, endDate1, page.getStart(), page.getLimit());
        int total = baseDao.count2("DatumInfo",propertyname,datumnumber);
        page.setRoot(list);
 	    page.setTotal(total);
	
  }

    /**
     * 更新users表的数据
     * 上传积分
     */
	@Override
	public int updateUsers(Integer zljf, String logincode) {
		String hql = "UPDATE Users SET zljf="+zljf+" WHERE  logincode = '"+logincode+"'";
		Integer users = baseDao.update(hql);
		if(null !=  users){
			return users;
		}else{
			return 0;
		}
	
	}


    /**
     * 查询分享之星
     */
	@Override
	public List<Users> findUsers(Page page,String logincode,String zljf) {
		if(null == zljf){
			return null;
		}
		String  hql =  "from Users where logincode like '"+logincode+"%' order by "+zljf+" desc limit "+page.getStart()+" ,"+page.getLimit()+"";
		List<Users>  list =  baseDao.findByHql(hql);
		if(null != list){
			return list;
		}else{
			return null;
		}
		
	}

	/**
	 * 清空所有user
	 * 积分
	 */
	@Override
	public int updateAllUser() {
		String hql = "UPDATE Users SET zljf='0'";
		Integer ints = baseDao.update(hql);
		if(null !=  ints){
			return ints;
		}else{
			return 0;
		}
	}

    /**
     * 分享之星记录表
     */
	@Override
	public void saveOrUpdateDataJiFen() {
    	   Page page =  new Page();
    	   page.setStart(0); 
		   page.setLimit(10);
		   List<Users> list = findUsers(page,"3","zljf");
		   //user 积分清空
		   updateAllUser();
		   //循环新增资料积分信息
		   if(null !=list){
			  for(int i=0;i<list.size();i++){
					DataJiFen date = new DataJiFen();
					 date.setId(i+1);
					 date.setNickname(list.get(i).getNickname());
					 date.setZljf(list.get(i).getZljf());
					 //保存积分记录信息
					baseDao.saveOrUpdate(date);
					
					//查询学生信息 调短信接口
					Student  st  = findStudent(list.get(i).getLogincode());
					//查询分享之星要发送的信息
				    Sms  sms = smsBiz.findById(7);
				    //调用短息接口的方法
				    SmsUtil.sendSms(st.getPhone(), sms.getContent());
				}
			}
		   
	  }

	/**
	 * 查询学生信息
	 */
	@Override
	public Student findStudent(String xh) {
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
     * 查询分享之星的
     * 所有数据
     */
	@Override
	public List<DataJiFen> findAllDataJiFen() {
		 List<DataJiFen> list =  baseDao.listAll("DataJiFen");
		 if(null !=list){
			 return list;
		 }else{
			 return null; 
		 }
		
	}


	

 }
