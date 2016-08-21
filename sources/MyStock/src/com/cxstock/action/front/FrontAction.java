package com.cxstock.action.front;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.activity.ApplyActivityBiz;
import com.cxstock.biz.activity.dto.ActivityDTO;
import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.expert.ExpertBiz;
import com.cxstock.biz.expertbespeak.ExpertBespeakBiz;
import com.cxstock.biz.expertbespeak.dto.StartExpertInfoDTO;
import com.cxstock.biz.onlineqa.OnlineQABiz;
import com.cxstock.biz.onlineqajudge.OnlineQAJudgeBiz;
import com.cxstock.biz.pbfx.offlineFd.OfflineFdBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.secondbookstore.SecondBookStoreBiz;
import com.cxstock.biz.secondbookstorejudge.SecondBookStoreJudgeBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.pojo.Activity;
import com.cxstock.pojo.IfOpeTimeLog;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.News;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.pojo.SecondBookStore;
import com.cxstock.pojo.SiteInfoDTO;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.DateUtil;

/**
 * 学习资料control层
 * @author root
 */
@SuppressWarnings("serial")
public class FrontAction extends BaseAction{
	
	private OnlineQABiz  onlineQABiz;
	
	private OnlineQAJudgeBiz onlineQAJudgeBiz;
	
	private SecondBookStoreBiz  secondBookStoreBiz;
	
	private SecondBookStoreJudgeBiz secondBookStoreJudgeBiz;
	
	private ApplyActivityBiz applyActivityBiz;
	
	private ExpertBiz expertBiz;
	
	private ExpertBespeakBiz expertBespeakBiz;
	
	//场地
	private SiteInfoBiz siteInfoBiz;
	private String siteid;
	private String ifApproval;
	private String sitename;
	private String sitetype;
	private String park;
	private String counsellor;
	private Integer usetime;
	private Date createtime;
	
	//学习资料
	private DatumInfoBiz datumInfoBiz;
	private String datumname;
	private String shareman ; 
	private Integer sharegrade;
	
	//线下辅学
	private  OfflineFdBiz  offlineFdBiz;
	
	//活动报名
	private ActivityDTO aDto;
	
	//专家预约
	private String yx;
	private String bm;
	private String experttype;
	private String ename;
	
	private String url;
	
	//关键字搜索
	private String key;
	private String serchKey;
	
	private String storeType;
	private String aid;
	private String newsid;
	private Integer id;
	

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OnlineQAJudgeBiz getOnlineQAJudgeBiz() {
		return onlineQAJudgeBiz;
	}

	public void setOnlineQAJudgeBiz(OnlineQAJudgeBiz onlineQAJudgeBiz) {
		this.onlineQAJudgeBiz = onlineQAJudgeBiz;
	}
	
	public OnlineQABiz getOnlineQABiz() {
		return onlineQABiz;
	}

	public void setOnlineQABiz(OnlineQABiz onlineQABiz) {
		this.onlineQABiz = onlineQABiz;
	}

	public SecondBookStoreBiz getSecondBookStoreBiz() {
		return secondBookStoreBiz;
	}

	public void setSecondBookStoreBiz(SecondBookStoreBiz secondBookStoreBiz) {
		this.secondBookStoreBiz = secondBookStoreBiz;
	}

	public SecondBookStoreJudgeBiz getSecondBookStoreJudgeBiz() {
		return secondBookStoreJudgeBiz;
	}

	public void setSecondBookStoreJudgeBiz(
			SecondBookStoreJudgeBiz secondBookStoreJudgeBiz) {
		this.secondBookStoreJudgeBiz = secondBookStoreJudgeBiz;
	}
	
	public ApplyActivityBiz getApplyActivityBiz() {
		return applyActivityBiz;
	}

	public void setApplyActivityBiz(ApplyActivityBiz applyActivityBiz) {
		this.applyActivityBiz = applyActivityBiz;
	}
	
	public ExpertBiz getExpertBiz() {
		return expertBiz;
	}

	public void setExpertBiz(ExpertBiz expertBiz) {
		this.expertBiz = expertBiz;
	}

	public ExpertBespeakBiz getExpertBespeakBiz() {
		return expertBespeakBiz;
	}

	public void setExpertBespeakBiz(ExpertBespeakBiz expertBespeakBiz) {
		this.expertBespeakBiz = expertBespeakBiz;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getSerchKey() {
		return serchKey;
	}

	public void setSerchKey(String serchKey) {
		this.serchKey = serchKey;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	
	
	public SiteInfoBiz getSiteInfoBiz() {
		return siteInfoBiz;
	}

	public void setSiteInfoBiz(SiteInfoBiz siteInfoBiz) {
		this.siteInfoBiz = siteInfoBiz;
	}
   
	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getIfApproval() {
		return ifApproval;
	}

	public void setIfApproval(String ifApproval) {
		this.ifApproval = ifApproval;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSitetype() {
		return sitetype;
	}

	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public Integer getUsetime() {
		return usetime;
	}

	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}
	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	

	public DatumInfoBiz getDatumInfoBiz() {
		return datumInfoBiz;
	}

	public void setDatumInfoBiz(DatumInfoBiz datumInfoBiz) {
		this.datumInfoBiz = datumInfoBiz;
	}

	public String getDatumname() {
		return datumname;
	}

	public void setDatumname(String datumname) {
		this.datumname = datumname;
	}

	public String getShareman() {
		return shareman;
	}

	public void setShareman(String shareman) {
		this.shareman = shareman;
	}

	public Integer getSharegrade() {
		return sharegrade;
	}

	public void setSharegrade(Integer sharegrade) {
		this.sharegrade = sharegrade;
	}

	
	public OfflineFdBiz getOfflineFdBiz() {
		return offlineFdBiz;
	}

	public void setOfflineFdBiz(OfflineFdBiz offlineFdBiz) {
		this.offlineFdBiz = offlineFdBiz;
	}
	
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getExperttype() {
		return experttype;
	}

	public void setExperttype(String experttype) {
		this.experttype = experttype;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public ActivityDTO getaDto() {
		return aDto;
	}

	public void setaDto(ActivityDTO aDto) {
		this.aDto = aDto;
	}

	public String logout(){
		this.getRequest().getSession().removeAttribute(Constants.USERINFO);
		return "login";
	}

	public String goToLogin(){
		UserDTO userInfo = this.getUserDTO();
		if(null!=userInfo){
			this.getRequest().getSession().setAttribute("url", this.getUrl());
			return "success";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * 线上答疑分页查询
	 * @return
	 */
   public  String findPageOnlineQA(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if("".equals(this.key)){
				this.key = null;
			}
			String hql = "from OnlineQA where status = 0 ";
			if(null!=key){
				hql += " and (nickname like '%"+this.key+"%' or content like '%"+this.key+"%' or subject like '%"+this.key+"%')";
			}
			onlineQABiz.findPageOnlineQA(page, hql);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
   }
   
   /**
	 * 线上答疑评论分页查询
	 * @return
	 */
   public String findPageOnlineQAJudge() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"qaid"};
			Object[] value = {id};
			onlineQAJudgeBiz.findPageOnlineQAJudge(page,property,value,null);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
   
   /**
	 * 二手书店分页查询
	 * @return
	 */
   public  String findPageSecondBookStore(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if("".equals(this.key)){
				this.key = null;
			}
			if("".equals(this.storeType)){
				this.storeType = null;
			}
			String hql = "from SecondBookStore where status = 0 ";
			if(null!=key){
				hql += " and (nickname like '%"+this.key+"%' or content like '%"+this.key+"%' or subject like '%"+this.key+"%')";
			}
			if(null!=storeType){
				hql += " and storeType = '"+this.storeType+"'";
			}
			secondBookStoreBiz.findPageSecondBookStore(page, hql);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
  }
   
   /**
	 * 二手书店评论分页查询
	 * 
	 * @return
	 */
	public String findPageSecondBookStoreJudge() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"storeid"};
			Object[] value = {id};
			secondBookStoreJudgeBiz.findPageSecondBookStoreJudge(page,property,value,null);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 更新人气
	 * 
	 * @return
	 */
	public String updateOnlineQAPopularity() {
		try {
			onlineQABiz.updatePopularity(id);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 更新人气
	 * 
	 * @return
	 */
	public String updateSecondBookStorePopularity() {
		try {
			secondBookStoreBiz.updatePopularity(id);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
   
	/**
	 * 场地首页展示
	 * @return
	 */
	public  String findPageSiteInfoBy(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if(null != this.ifApproval && !"".equals(this.ifApproval) && this.ifApproval.equals("3")){
				//获取登录用户的信息去查学生表的辅导员
				Instructor userInfo = this.getUserDTO().getInstructor();
				//根据登录号查询管理老师（辅导员）
				if(null != userInfo){
					counsellor = userInfo.getXm();
				}
			}
		     String[] property = { "sitename", "sitetype",
						"park","counsellor" };
		     String[] value = { this.sitename, this.sitetype, 
						this.park,this.counsellor};
			   siteInfoBiz.findPageSiteInfoBy(page, property, value,"createtime");
			   this.outPageString(page);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 学习资料
	 * 首页展示
	 * @return
	 */
	public  String  findPageDtumInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			String[] property = { "datumname", "shareman" };
			String[] value = { this.datumname, this.shareman};
			datumInfoBiz.findPageDatumInfoOrderBy(page, property, value,
					"toptime","sharetime", "sharegrade");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	
	}
	
	
	/**
     * 首页展示的列表
     * @return
     */
    public  String findPageOfflineFd(){
    	try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			String[] property = {"status","fxxm","fxteacher","xmzise"};
			Object[] value = {this.key,this.key,this.key,this.key};
			offlineFdBiz.findPageOfflineFd(page, property, value);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    
    /**
	 * 查询场地使用
	 * 时间情况  传到前端
	 * @return
	 */
	public  String  findAllData(){
		 //场地预约的集合
		 List<SiteInfoLog> list = new ArrayList<SiteInfoLog>();
		   list = siteInfoBiz.findAllData(this.siteid);
		   
		   //场地不对外开放时间集合
		/* List<IfOpeTimeLog>  list1 = new ArrayList<IfOpeTimeLog>();
		    list1  = siteInfoBiz.findAllIfOpeTimeLog(this.siteid);*/
		    
		    //场地不对外开放时数据
		  IfOpeTimeLog  log =  siteInfoBiz.findSingleIfOpeTimeLog(this.siteid);
		    
		   //接收数据传到前台
		   List<SiteInfoDTO> siteInfoDTOList = new ArrayList<SiteInfoDTO>();
		   if(null != list ){
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			   SiteInfoLog st = null;
			   for(int i=0;i<list.size();i++){
				   st = list.get(i);
				
				   SiteInfoDTO s = new SiteInfoDTO(); 
				   
				   if("待审核".equals(st.getStatus())){
					   s.setCid(4);
				   }else if ("已通过".equals(st.getStatus())){
					   s.setCid(3);
				   }else{
					   s.setCid(5);
				   }
				     String activtyDate = null;
				     if( null !=st.getActivitydate()&& null !=st.getActivitytime()){
				            activtyDate = DateTime.toString(st.getActivitydate());
				          
				     }
				     String activityTime   = st.getActivitytime();
					   //判断不为空才进来
					  if( null != activtyDate && !"".equals(activtyDate) 
							   && null != activityTime && !"".equals(activityTime)  ){
					   //截取开始时间 和结束时间
					   String starTime = activityTime.split("-")[0];
					   String endTime = activityTime.split("-")[1];
					  
				      s.setStart(activtyDate+" "+starTime);
				      s.setEnd(activtyDate+" "+endTime);
				      s.setId("-"+(i+1));
				      s.setNotes("");
				      s.setLogId(st.getLogId());
				      s.setTitle(st.getActivityname());//放活动名称
				      siteInfoDTOList.add(s);
				 }
			   }
			   //不对外开放
			   if ( null !=log){
				    for(int i=0;i<10;i++){
					   SiteInfoDTO s1 = new SiteInfoDTO(); 
					   s1.setCid(2);
					 String activtyDate1 = null;
					 if( null !=log.getActivitydate()){
						   //日期添加天数 隔7天加一次
						  String timess = DateUtil.format(log.getActivitydate());
		                  String activtyDate2 =  DateTime.getNewDay(timess, i*7);
		                  activtyDate1 = activtyDate2.split(" ")[0];
		                  
					     }
					     String activityTime1   = log.getActivitytime();
						   //判断不为空才进来
						if( null != activtyDate1 && !"".equals(activtyDate1) 
								   && null != activityTime1 && !"".equals(activityTime1)  ){
						   //截取开始时间 和结束时间
						  String starTime1 = activityTime1.split("-")[0];
						  String endTime1 = activityTime1.split("-")[1];
						   
						  s1.setStart(activtyDate1+" "+starTime1);
						  s1.setEnd(activtyDate1+" "+endTime1);
					      s1.setId((i+1)+"");
					      s1.setNotes("");
					      s1.setLogId(log.getOpeId());
					      s1.setTitle("场地不对外开放");
					     siteInfoDTOList.add(s1);
					 }
				   }
			     }
			     
			   this.outListStringCalendar(siteInfoDTOList);
		      } else{
			     this.outError(); 
		   }
		return  null;
	}

	
	@SuppressWarnings("unchecked")
	private void outListStringCalendar(List list) {
		try {
			JSONArray jsonArray = new JSONArray();
			if (list.size() > 0) {
				jsonArray = JSONArray.fromObject(list);
			}
			String jsonString = "{evts:"
					+ jsonArray.toString() + "}";
			outString(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新闻专栏
	 */
	public String newsSpecialColumn(){
		
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		
		applyActivityBiz.findPageNews(page);
		this.outPageString(page);
		return null;
	}
	
	/**
	 * 新闻详情
	 */
	public String newsDetail(){
		News news = applyActivityBiz.findNewsDetail(newsid);
		this.outObjectString(news, null);
		return null;
	}
	
	/**
	 * 活动报名
	 */
	public String dataActivityInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			//applyActivityBiz.findPageActivityInfo(page,null);
			String applystyle = "已通过";
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String toDayDate = sf.format(d);
			//applyActivityBiz.findPageActivityPassed(page, null, applystyle, toDayDate,null);
			applyActivityBiz.findPageActivityInfo(page, null);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 活动详情
	 */
	public String activityDetail(){
		try {
			Activity ac = applyActivityBiz.findActivityInfoById(aid);
			this.outObjectString(ac, null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
    /**
     * 活动报名，具体类别活动
     */
	public String activityInfoByGenre(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			applyActivityBiz.findActivityByGenre(page,key,serchKey);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 专家预约
	 */
	public String dataExpertSbInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"yx","unit","expertType","xm"};
			String[] values = {this.yx,this.bm,this.experttype,this.ename};
			expertBespeakBiz.findPageStartExpertInfoById(page, property, values);
			String[] e = {"expert"};
			this.outPageString(page,e);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	/**
	 * 专家信息
	 */
	public String expertInfo(){
		try {
			StartExpertInfoDTO se = expertBespeakBiz.findPageStartExpertInfoById(id);
			this.outObjectString(se,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 线上答疑
	 */
	public String findOnlineQA(){
		try {
			OnlineQA qa = onlineQABiz.loadById(id);
			this.outObjectString(qa,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 二手书店
	 */
	public String findSecondBookStore(){
		try {
			SecondBookStore store = secondBookStoreBiz.loadById(id);
			this.outObjectString(store,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
}
