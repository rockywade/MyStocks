package com.cxstock.action.siteinfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.biz.siteinfo.dto.SiteInfoLogDTO;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.pojo.IfOpeTimeLog;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.SiteInfo;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.SmsUtil;
import com.cxstock.utils.system.UuidUtil;

/***
 *场地信息控制层
 * 
 * @author wzx
 * 
 *   2016-5-27
 */
@SuppressWarnings("serial")
public class SiteInfoAction extends BaseAction {

	private SiteInfoBiz siteInfoBiz;
	
	private DatumInfoBiz datumInfoBiz;
	
	private SmsBiz smsBiz;

	// 获取前台转过来的修改字段条件
	private String siteids;

	private Integer id;
	private String proposer;
	private String siteid;
	private String relationtel;
	private String counsellor;
	private String activityname;
	private String activitycontent;
	private Date activitydate;
	private String activitytime;
	private String activitytype;
	private String sitename;
	private String sitetype;
	private String beorganize;
	private String guideth;
	private Integer sitemodle;
	private String park;
	private String sitemanager;
	private String nowstatus;
	private String photo;
	private String sitecondition;
	private String ifschool;
	private String agreement;
	private String dutystate;
	private String status;
	private  String opeTime;
	
	private  Date  createtime;
    private  Integer  createrid;
    private  String  creatername;
  
   // 按时间查询
	private String startdate;
	private String enddate;

    
    //申请记录表
    private String logId;
    private String xh;
    private Integer usetime;
    private Date applyTime;
	
    //不对外开放的id
	private String  opeId;
    
    
	// 上传图片
	private File upload;
	// 照片
	private String srcImg;
	// fileName 前面必須和uplaod一致,不然找不到文件
	private String uploadFileName;

	// 判断传过来是审批通过（0）或者审批不通过（1）
	//其他数字  做相应的其他判断
	private String ifApproval;


	/**
	 * 场地分页 ，排序，搜索 查询
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
						"park", "status","counsellor" };
		     String[] value = { this.sitename, this.sitetype, 
						this.park, this.status,this.counsellor };
			   siteInfoBiz.findPageSiteInfoBy(page, property, value,"createtime");
			   this.outPageString(page);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
    /**
     * 场地申请的分页查询
     * @return
     */
	public  String   findPageApplySiteInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			
			String enddate = "";
			String startdate = "";
            if(null !=this.enddate && !"".equals(this.enddate) &&
            		null != this.startdate && !"".equals(this.startdate)){
            	//结束时间加1
    			 enddate = DateTime.getNewDay1(this.enddate, 1);
    			//时间加
    		    startdate = DateTime.getNewDay1(this.startdate, 0);
            	
            }
			
			 String[] propertyName = { "sitename", "sitetype",
						"park"};
			 String[] value = { this.sitename, this.sitetype, 
						this.park};
			
			siteInfoBiz.findPageApplySiteInfo(page, propertyName, value, "createtime", "createtime",  startdate, enddate);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return  null;
	}
	
	
	
	// 场地审核/批量审核
	public String saveOrUpdaSiteInfo() {
		try {
			SiteInfoLog log = new SiteInfoLog();
			if (ifApproval.equals("0")) {
				log.setStatus("已通过");
				
			}
			if (ifApproval.equals("1")) {
				log.setStatus("未通过");
			}
			siteInfoBiz.saveOrUpdaSiteInfo(log, this.siteids);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 场地申请分页查询 按条件
	 * 
	 * @return
	 */
	public String findPageSiteInfo() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
		     String[] property = { "sitename", "sitetype", 
						"park", "status" };
			  String[] value = { this.sitename, this.sitetype,
						this.park, this.status};
			   siteInfoBiz.findPageSiteInfo(page, property, value);
			   this.outPageString(page);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 创建场地新增/修改数据保存
	 * @return
	 */
	public String saveOrUpdatest() {
		try {
			String img = "";
			if (null != this.upload) {
				Date d = new Date();
				String path = ServletActionContext.getServletContext().getRealPath(
						"upload")
						+ File.separator
						+ d.getTime()
						+ getFileExp(this.uploadFileName);
				img = this.getRequest().getContextPath() + "/" + "upload" + "/"
						+ d.getTime() + getFileExp(this.uploadFileName); // 使用時間戳作為文件名

				File toFile = new File(path);
				writeFile(this.upload, toFile);
			} else {
				img = srcImg;
			}
			SiteInfo siteInfo = new SiteInfo();
			siteInfo.setId(this.id);
			
			//判断是新增还是修改
			if(null == this.id){
				siteInfo.setSiteid(UuidUtil.get32UUID()+this.getUserDTO().getLogincode());// 场地编号
			}else{
				SiteInfo st = siteInfoBiz.findSing(this.id);
			   if(null != st){
				 siteInfo.setSiteid(st.getSiteid());
				}
			}
			
			siteInfo.setRelationtel(this.relationtel);
			siteInfo.setSitename(this.sitename);
			siteInfo.setSitetype(this.sitetype);
			siteInfo.setCounsellor(this.counsellor);
			siteInfo.setPark(this.park);
			siteInfo.setSitemodle(this.sitemodle);
			siteInfo.setAgreement(this.agreement);
			siteInfo.setDutystate(this.dutystate);
			siteInfo.setSitemodle(this.sitemodle);
			siteInfo.setNowstatus("查看预约情况");
			siteInfo.setStatus("待审核");
			//默认场地使用时间 早上9点到晚上21点 12小时
			siteInfo.setUsetime(12);
			
			//乱码转换
			String inputer   = new String( this.activitytime.getBytes("ISO-8859-1") , "utf-8"); 
			
			siteInfo.setSitecondition(this.sitecondition);
			siteInfo.setPhoto(img);
            if(null != ifApproval && !"".equals(ifApproval)){
			   if(ifApproval.equals("0")){ 
				siteInfo.setPhoto(img);
				siteInfo.setStatus("待审核");
			   }
			}
  
             //判断相片不为空时候 是修改
             if(null == img || "".equals(img)){
			  siteInfo.setPhoto(this.photo);
               }
               //创建人不为空时候是修改
              if(null ==this.creatername || "".equals(this.creatername)){
			  siteInfo.setCreatetime(new Date());
			  siteInfo.setCreaterid(this.getUserDTO().getUserid());
			  siteInfo.setCreatername(this.getUserDTO().getNickname());
			  
              }else{
			  siteInfo.setCreatetime(this.createtime);
			  siteInfo.setCreaterid(this.createrid);
			  siteInfo.setCreatername(this.creatername);
			  siteInfo.setUpdaterid(this.getUserDTO().getUserid());
			  siteInfo.setUpdatername(this.getUserDTO().getNickname());
			  siteInfo.setUpdatetime(new Date()); 
              }
              
               if(null == this.id){
            	  //保存场地不对外开放 日期
                   IfOpeTimeLog  ope  = new IfOpeTimeLog();
                   ope.setOpeId(UuidUtil.get32UUID());
                   ope.setSiteid(siteInfo.getSiteid());
                   ope.setActivitydate(this.activitydate);
                   ope.setActivitytime(inputer);
                   //执行不对外开放
                   siteInfoBiz.saveOrUpdateIfOpeTimeLog(ope);
               }else{
            	 if(null !=this.activitydate && null !=this.activitytime &&
  						 !"".equals(this.activitytime)){
  				 //场地修改时修改不对外开放时间
  			     IfOpeTimeLog lg = siteInfoBiz.findSingleIfOpeTimeLog(this.siteid);
  					if(null != lg){
  						IfOpeTimeLog l = new IfOpeTimeLog();
  						  l.setOpeId(lg.getOpeId());
  						  l.setSiteid(lg.getSiteid());
  						  l.setActivitydate(this.activitydate);
  						  l.setActivitytime(this.activitytime);
  						  siteInfoBiz.saveOrUpdateIfOpeTimeLog(l);
  					 }else{
  						 IfOpeTimeLog lo = new IfOpeTimeLog();
  						 lo.setOpeId(UuidUtil.get32UUID());
  						 lo.setSiteid(this.siteid);
  						 lo.setActivitydate(this.activitydate);
  						 lo.setActivitytime(this.activitytime);
						  siteInfoBiz.saveOrUpdateIfOpeTimeLog(lo);
  					 } 
  				   } 
            	   
                }
              
  		    //添加主表数据
			siteInfoBiz.saveSiteInfo(siteInfo);

			if (id == null) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 场地删除
	 * 
	 * @return
	 */
	public String deleteSiteInfo() {
		try {
			siteInfoBiz.deleteSiteInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	
	/**
	 *根据已经预约的logId
	 *查询审人数据
	 * @return
	 */
	public  String  findSingleLog(){
		try {
			SiteInfoLog log  = siteInfoBiz.findSingLog(this.logId);
			SiteInfoLogDTO  dto = new SiteInfoLogDTO();
			if(null != log){
			  String activitydate1 =DateUtil.format(log.getActivitydate());
			  String activitydate = activitydate1.split(" ")[0];
			  dto.setSitename(log.getSitename());
			  dto.setSitecondition(log.getSitecondition());
			  dto.setProposer(log.getProposer());
			  dto.setXh(log.getXh());
			  dto.setRelationtel(log.getRelationtel());
			  dto.setCounsellor(log.getCounsellor());
			  dto.setActivityname(log.getActivityname());
			  dto.setActivitycontent(log.getActivitycontent());
			  dto.setActivitydate(activitydate);
			  dto.setActivitytime(log.getActivitytime());
			  dto.setActivitytype(log.getActivitytype());
			  dto.setBeorganize(log.getBeorganize());
			  dto.setGuideth(log.getGuideth());
			  dto.setSitemodle(log.getSitemodle());
			  if("是"==log.getIfschool()||"是".equals(log.getIfschool())){
				  dto.setIfschool("是");
			  }else{
				  dto.setIfschool("否"); 
			  }
			 
			 }
			if(null != dto){
			  this.outObjectString(dto,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return  null;
	}

	/**
	 * 查询所有的管理老师
	 * （辅导员）
	 * @return
	 */
	public  String findSiteInfoComb(){
		try {
			this.outListString(siteInfoBiz.findSiteInfoComb());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return null;
	}
	
	/**
	 * 查询所有园区
	 * @return
	 */
	public  String findParkComb(){
		try {
			this.outListString(siteInfoBiz.findParkComb());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 查询所有场地类型
	 * @return
	 */
	public  String findGroundTypeComb(){
	 try {
		this.outListString(siteInfoBiz.findGroundTypeComb());
	 } catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
		return  null;
	}
	
	
	/**
	 * 查询所有的组织
	 * @return
	 */
    public  String findOrgaComb(){
       try {
		this.outListString(siteInfoBiz.findOrgaComb());
	   } catch (Exception e) {
		 e.printStackTrace();
		 this.outError();
	   }
		return  null;
	}
    
    /**
     * 场地申请记录
     * 信息保存
     * @return
     */
    public  String  saveOrUpdateSiteInfoLog(){
    	
    	try {
			SiteInfoLog  lg =  new  SiteInfoLog();
			//申请场地id
			lg.setLogId(UuidUtil.get32UUID());
			lg.setSitename(this.sitename);
			lg.setSiteid(this.siteid);
			lg.setSitecondition(this.sitecondition);
			lg.setProposer(this.proposer);
			lg.setXh(this.xh);
			lg.setRelationtel(this.relationtel);
			lg.setCounsellor(this.counsellor);
			lg.setActivityname(this.activityname);
			lg.setActivitycontent(this.activitycontent);
			lg.setActivitytype(this.activitytype);
			lg.setBeorganize(this.beorganize);
			lg.setGuideth(this.guideth);
			lg.setSitemodle(this.sitemodle);
			lg.setIfschool(this.ifschool);
			lg.setPark(this.park);
			lg.setSitetype(this.sitetype);
			lg.setDutystate(this.dutystate);
			lg.setApplyTime(new Date());
			if(this.agreement.equals("true")){
				lg.setAgreement("已阅读");
			}
			lg.setStatus("待审核");
			
			//查询所有场地判断申请时间是否已被使用
			List<SiteInfoLog>  list = siteInfoBiz.findAllData(this.siteid);
			if(list.size()>0 && null != list){
				for(int i=0;i<list.size();i++){
					//截取日期
					String times = DateUtil.format(list.get(i).getActivitydate());
					String times1 =  times.split(" ")[0];
					//截取日期
					String timess = DateUtil.format(this.activitydate);
					String timess1 =  timess.split(" ")[0];
				
				  if(times1.equals(timess1)){
					     //截取开始时间 和结束时间（数据库时间）
					     String starTime = list.get(i).getActivitytime().split("-")[0];
					     String endTime = list.get(i).getActivitytime().split("-")[1];
					 
						 //截取开始时间 和结束时间（前台传时间）
						 String starTime1 = this.activitytime.split("-")[0];
						 String endTime1 = this.activitytime.split("-")[1];
						   //开始时间截取判断是否是一位数
						  
						  
						   //两个日期之间判断
						   int res = endTime1.compareTo(starTime);  //可行
						   
						   int res1 = endTime1.compareTo(endTime);//当结束时间小于数据结束时间  大于数据开始时间  不行
						   
						   int res2  = starTime1.compareTo(endTime); //开始传小于结束数  不可行
						   
						   int res3  = starTime1.compareTo(starTime);
						   
						   
						 if(res > 0 && res1<0 || res2<0 && res1<0){  //message errors
							this.outString("{success:false,message:'该场地这个时间段已被预约!请从新选择时间!'}");
							break;
						} 
				  }else{
						lg.setActivitydate(this.activitydate);
						lg.setActivitytime(this.activitytime);	
					}	 
				}
			}else{
				lg.setActivitydate(this.activitydate);
				lg.setActivitytime(this.activitytime);	
			}
			//计算使用时间
			if(null != this.activitytime || !"".equals(this.activitytime) && 
					null != this.activitydate ){
				  String activitydate1  = DateUtil.format(this.activitydate);
				  String activitydate2 =  activitydate1.split(" ")[0];
				  //截取开始时间 和结束时间
				   String starTime = this.activitytime.split("-")[0];
				   String endTime = this.activitytime.split("-")[1];
				  
				Integer usetTiem =DateUtil.getOffsetHours(DateUtil.parse(activitydate2+" "+starTime), DateUtil.parse(activitydate2+" "+endTime)) ;
				lg.setUsetime(usetTiem);
			}
			lg.setNowstatus("预约情况");
			
			
			this.siteInfoBiz.saveOrUpdateSiteInfoLog(lg);
		    if (this.logId == null || "".equals(this.logId)) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    /**
     * 场地申请列表查询
     * @return
     */
    public String findPageSiteInfoLog(){
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
				
				/*//院系领导
				LeaderShip lp = this.getUserDTO().getLeaderShip();
				if(null != lp){
					park = lp.getSsyq();
				}*/
			}
			if(null != this.ifApproval && !"".equals(this.ifApproval) && this.ifApproval.equals("1")){
				//获取学生登录的登录号
				xh = this.getUserDTO().getLogincode();
			 }
			 String[] property = { "sitename", "sitetype", 
						"park", "status" ,"counsellor","xh"};
			  String[] value = { this.sitename, this.sitetype, 
						this.park, this.status,this.counsellor,this.xh};
			siteInfoBiz.findPageSiteInfoLog(page, property, value,"applyTime");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return  null;
    }
    
    /**
     * 申请场地审批
     * @return
     */
    public  String saveOrUpdateLog(){
    	try {
			SiteInfoLog  lg =  new  SiteInfoLog();
			lg.setLogId(this.logId);
			lg.setSitename(this.sitename);
			lg.setSiteid(this.siteid);
			lg.setSitecondition(this.sitecondition);
			lg.setProposer(this.proposer);
			lg.setXh(this.xh);
			lg.setRelationtel(this.relationtel);
			lg.setCounsellor(this.counsellor);
			lg.setActivityname(this.activityname);
			lg.setActivitycontent(this.activitycontent);
			lg.setActivitydate(this.activitydate);
			lg.setActivitytime(this.activitytime);
			lg.setActivitytype(this.activitytype);
			lg.setBeorganize(this.beorganize);
			lg.setGuideth(this.guideth);
			lg.setSitemodle(this.sitemodle);
			lg.setIfschool(this.ifschool);
			lg.setPark(this.park);
			lg.setSitetype(this.sitetype);
			lg.setUsetime(this.usetime);
			lg.setAgreement(this.agreement);
			lg.setDutystate(this.dutystate);
			lg.setNowstatus("预约情况");
			lg.setApplyTime(this.applyTime);
			//审核时候判断
			if (this.ifApproval.equals("0")) {
				lg.setStatus("已通过");
				//获取学生信息
				Student st = datumInfoBiz.findStudent(this.xh);
				//获取发送信息
				Sms  sms  = smsBiz.findById(3);
				//把场地名称放进去
				String content = sms.getContent().replace("$(CDMC)", this.sitename);
				 //调用短息接口的方法
			    SmsUtil.sendSms(st.getPhone(), content);
				
			}
			if (this.ifApproval.equals("1")) {
				lg.setStatus("未通过");
				//获取学生信息
				Student st = datumInfoBiz.findStudent(this.xh);
				//获取发送信息
				Sms  sms  = smsBiz.findById(4);
				//把场地名称放进去
				String content = sms.getContent().replace("$(CDMC)", this.sitename);
				 //调用短息接口的方法
			   SmsUtil.sendSms(st.getPhone(), content);
			}
			
			 //判断现在使用状态
			if(ifApproval.equals("2")){
				lg.setStatus("已取消");
			}
			if(this.agreement.equals("true")){
				lg.setAgreement("已阅读");
             }
			this.siteInfoBiz.saveOrUpdateSiteInfoLog(lg);
			if (this.id == null) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	
    	return  null;
    }
    
	/**
	 * 删除不对外开放时间
	 * @return
	 */
    public  String deleteIfOpeTimeLog(){
    	try {
			siteInfoBiz.deleteIfOpeTimeLog(this.opeId);
			this.outString("{success:true,message:'删除成功!'}");
		 } catch (Exception e) {
			e.printStackTrace();
			this.outError();
		 }
		return null;
    }
    
    /**
     * 图片预览
     * @return
     */
    public  String viewImg(){
    	SiteInfo qo = siteInfoBiz.loadById(this.id);
    	HttpServletRequest request = this.getRequest();  
        request.setAttribute("qa", qo);  
 	    return  "viewImg";
    }
    
    
	public SiteInfoBiz getSiteInfoBiz() {
		return siteInfoBiz;
	}

	public void setSiteInfoBiz(SiteInfoBiz siteInfoBiz) {
		this.siteInfoBiz = siteInfoBiz;
	}
	

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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteids() {
		return siteids;
	}

	public void setSiteids(String siteids) {
		this.siteids = siteids;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getRelationtel() {
		return relationtel;
	}

	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getActivitycontent() {
		return activitycontent;
	}

	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
	}

	public Date getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(Date activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivitytime() {
		return activitytime;
	}

	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}

	public String getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
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

	public String getBeorganize() {
		return beorganize;
	}

	public void setBeorganize(String beorganize) {
		this.beorganize = beorganize;
	}

	public String getGuideth() {
		return guideth;
	}

	public void setGuideth(String guideth) {
		this.guideth = guideth;
	}

	public Integer getSitemodle() {
		return sitemodle;
	}

	public void setSitemodle(Integer sitemodle) {
		this.sitemodle = sitemodle;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getSitemanager() {
		return sitemanager;
	}

	public void setSitemanager(String sitemanager) {
		this.sitemanager = sitemanager;
	}

	public String getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(String nowstatus) {
		this.nowstatus = nowstatus;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSitecondition() {
		return sitecondition;
	}

	public void setSitecondition(String sitecondition) {
		this.sitecondition = sitecondition;
	}


	public Integer getUsetime() {
		return usetime;
	}

	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}
	

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}



	public String getIfschool() {
		return ifschool;
	}

	public void setIfschool(String ifschool) {
		this.ifschool = ifschool;
	}

	public String getDutystate() {
		return dutystate;
	}

	public void setDutystate(String dutystate) {
		this.dutystate = dutystate;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getOpeTime() {
		return opeTime;
	}



	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}



	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public Integer getCreaterid() {
		return createrid;
	}


	public void setCreaterid(Integer createrid) {
		this.createrid = createrid;
	}


	public String getCreatername() {
		return creatername;
	}


	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}


	public String getIfApproval() {
		return ifApproval;
	}

	public void setIfApproval(String ifApproval) {
		this.ifApproval = ifApproval;
	}
	

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}


	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}



	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getSrcImg() {
		return srcImg;
	}

	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}

	
	
	public String getOpeId() {
		return opeId;
	}



	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

    

	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	// 上传文件的文件名
	private String getFileExp(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos);
	}

	private static final int BUFFER_SIZE = 16 * 1024;

	private static void writeFile(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {

				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
