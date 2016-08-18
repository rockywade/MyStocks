package com.cxstock.action.pbfx.offlineFd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.pbfx.offlineFd.OfflineFdBiz;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.pojo.OfflineFd;
import com.cxstock.pojo.OfflineFdLog;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.JxlExcelHelper;
import com.cxstock.utils.system.SmsUtil;
import com.cxstock.utils.system.UuidUtil;

/**
 * 线下辅导控制层
 * @author root
 *
 */
@SuppressWarnings("serial")
public class OfflineFdAction extends BaseAction{
    
	private  OfflineFdBiz  offlineFdBiz;
	
	private SmsBiz smsBiz;
	
	
	
	private  String  xmid;
    private  String  xmxh;
    private  String  fxxm;
    private  String  fxtime;
    private  String  fxaddress;
    private  String  fxteacher;
    private  String  teachertel;
    private  String  xmintro;
    private  Integer bmnumber;
    private  Integer  xmzise;
   //报名状态：可报名，已报名，已满员，
    private  String  bmstatus;
    //状态：已发布，已发布/置顶/高亮，已发布/置顶，已发布/高亮，隐藏中
    private  String  status;
    private Integer plnumber;
    private  String  bz;
    private  Date topTime;
    private  Date  createtime;
    private  Integer  createrid;
    private  String  creatername;
    
    //批量接收的数据
    private String offlineFdAll;
    
    //前台传过来作判断用的
    private String ifApproval;
    
    //首页展示接收数据
    private String key;
    
    //记录内容
    private  String bmId;
    private  String bmName;
    private  String plnr;
    private  String bmtime;
    private  String tbTag;
    private  String bmTag;
    private  String xmmc;
    
    
    //导出文件的属性
	private ServletContext context;
	private String filename;
	private String mimeType;
	private InputStream inStream;
    
	
	
    /**
     * 线下辅学管理员
     * 列表查询
     * @return
     */
    public  String  findPageOfflineFb(){
    	try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
		
			//判断是否是访问是学生端（0）还是管理员端
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("0")){
				status = "已发布";
				bmId = this.getUserDTO().getLogincode();
			}
			
			String[] property = { "fxxm" , "status" ,"bmstatus"};
			String[] value = { this.fxxm , this.status, this.bmstatus};
			offlineFdBiz.findPageOfflineFb(page, property, value,bmId, "topTime","createtime");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return  null;
    }
    
   
    
    /**
     * 线下辅导项目
     * 新增数据保存
     * @return
     */
    public String saveOrUpdateOfflineFd(){
    	try {
			OfflineFd fd = new OfflineFd();
			//项目id（uuid）
			fd.setXmid(UuidUtil.get32UUID());
			fd.setXmxh(this.xmxh);
			fd.setFxxm(this.fxxm);
			fd.setFxtime(this.fxtime);
			fd.setFxaddress(this.fxaddress);
			fd.setFxteacher(this.fxteacher);
			fd.setTeachertel(this.teachertel);
			fd.setXmintro(this.xmintro);
			fd.setXmzise(this.xmzise);
			fd.setBmnumber(this.bmnumber);
			fd.setBmstatus(this.bmstatus);
			fd.setStatus("已发布");
			fd.setBz(this.bz);
			fd.setCreatetime(new Date());
			fd.setPlnumber(this.plnumber);
			if(this.topTime == null){
				fd.setTopTime(new Date());
			}else{
				fd.setTopTime(this.topTime);
			}
			//新增时候给辅导老师发短信通知
			if(null == this.xmid || "".equals(this.xmid)){
				//获取发送信息
				Sms  sms  = smsBiz.findById(8);
				//把场地名称放进去
				String content1 = sms.getContent().replace("$(XXFDMC)", this.fxxm);
				String content2 = content1.replace("$(FDSJ)", this.fxtime);
				String content = content2.replace("$(FDDD)", this.fxaddress);
				 //调用短息接口的方法
			    SmsUtil.sendSms(this.teachertel, content);
			}
			fd.setTopTime(new Date());
			fd.setCreatetime(new Date());
			fd.setCreaterid(this.getUserDTO().getUserid());
			fd.setCreatername(this.getUserDTO().getNickname());
			
			if(null !=this.status && !"".equals(this.status)&&this.status.equals("on")){
				fd.setStatus("隐藏中");
			}
			if(bmstatus == null || bmstatus.equals("")){
				fd.setBmstatus("可报名");
			}
			//隐藏
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("0")){
				fd.setXmid(this.xmid);
				fd.setStatus("隐藏中");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消隐藏
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("1")){
				fd.setXmid(this.xmid);
				fd.setStatus("已发布");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//顶置
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("2")){
				fd.setXmid(this.xmid);
				fd.setStatus(this.status+"/置顶");
				fd.setTopTime(new Date());
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消顶置
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("3")){
				fd.setXmid(this.xmid);
				int st = this.status.length();
				if(0< st && st<=6){
					fd.setStatus("已发布");
				}
				if(6< st && st<=9){
					String str1= this.status.split("/")[0];
					String str2= this.status.substring(7,9); 
					fd.setStatus(str1+"/"+str2);
				}
				fd.setTopTime(this.createtime);
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//高亮
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("4")){
				fd.setXmid(this.xmid);
				fd.setStatus(this.status+"/高亮");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消高亮
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("5")){
				fd.setXmid(this.xmid);
				int st = this.status.length();
				if(0< st && st<=6){
					fd.setStatus("已发布");
				}
				if(6< st && st<=9){
					String str1= this.status.split("/")[0];
				    String str2 =this.status.split("/")[1];
					fd.setStatus(str1+"/"+str2);
				}
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			offlineFdBiz.saveOrUpdateOfflineFd(fd);
			if (this.xmid == null || this.xmid.equals("")) {
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
     * 线下辅学
     * 批量操作
     * @return
     */
    public  String  saveOrUpdaOfflineFdAll(){
    	
    	try {
			OfflineFd fd = new OfflineFd();
			//隐藏
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("0")){
				fd.setXmid(this.xmid);
				fd.setStatus("隐藏中");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消隐藏
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("1")){
				fd.setXmid(this.xmid);
				fd.setStatus("已发布");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//顶置
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("2")){
				fd.setXmid(this.xmid);
				fd.setStatus(this.status+"/置顶");
				fd.setTopTime(new Date());
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消顶置
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("3")){
				fd.setXmid(this.xmid);
				int st = this.status.length();
				if(0< st && st<=6){
					fd.setStatus("已发布");
				}
				String str1= "";
				String str2= "";
				if(6< st && st<=9){
					if("已发布/置顶/高亮" == this.status){
		        	        str1= this.status.split("/")[0];
		        	        str2= this.status.substring(7,9); 
		        	        fd.setStatus(str1+"/"+str2);
		        		 
		        	}else{
		        		 str1= this.status.split("/")[0];
		        		 str2= this.status.substring(4,6); 
		        		 fd.setStatus(str1+"/"+str2);
		        	}
				}
				fd.setTopTime(this.createtime);
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//高亮
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("4")){
				fd.setXmid(this.xmid);
				fd.setStatus(this.status+"/高亮");
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			//取消高亮
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("5")){
				fd.setXmid(this.xmid);
				int st = this.status.length();
				if(0< st && st<=6){
					fd.setStatus("已发布");
				}
				String str1= "";
				String str2= "";
				if(6< st && st<=9){
					if("已发布/高亮/置顶" == this.status){
	        	        str1= this.status.split("/")[0];
	        	        str2= this.status.substring(7,9); 
	        	        fd.setStatus(str1+"/"+str2);
	        		 
	        	}else{
	        		 str1= this.status.split("/")[0];
	        		 str2= this.status.substring(4,6); 
	        		 fd.setStatus(str1+"/"+str2);
	        	}
					
				}
				fd.setCreatetime(this.createtime);
				fd.setCreaterid(this.createrid);
				fd.setCreatername(this.creatername);
				fd.setUpdatetime(new Date());
				fd.setUpdaterid(this.getUserDTO().getUserid());
				fd.setUpdatername(this.getUserDTO().getNickname());
			}
			offlineFdBiz.saveOrUpdaOfflineFdAll(fd, this.offlineFdAll);
			this.outString("{success:true,message:'修改成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    
    /**
     * 线下辅导
     * 项目修改
     * @return
     */
    public String  updateOfflineFd(){
    	
    	try {
    		//根据id查询数据
    		OfflineFd fds  = offlineFdBiz.findSingOfflineFd(this.xmid);
    		if(null != fds){
			OfflineFd fd = new OfflineFd();
			//项目id
			fd.setXmid(this.xmid);
			fd.setXmxh(this.xmxh);
			fd.setFxxm(this.fxxm);
			fd.setFxtime(this.fxtime);
			fd.setFxaddress(this.fxaddress);
			fd.setFxteacher(this.fxteacher);
			fd.setTeachertel(this.teachertel);
			fd.setXmintro(this.xmintro);
			fd.setXmzise(this.xmzise);
			fd.setBmnumber(fds.getBmnumber());
			fd.setBmstatus(fds.getBmstatus());
			fd.setStatus(fds.getStatus());
			fd.setPlnumber(fds.getPlnumber());
			if(this.topTime == null){
				fd.setTopTime(new Date());
			}else{
				fd.setTopTime(fds.getTopTime());
			}
			//判断修改老师和电话不一样烦短息通知
			if(!this.fxteacher.equals(fds.getFxteacher()) && 
					!this.teachertel.equals(fds.getTeachertel())){
				//调用短息接口
				//获取发送信息
				Sms  sms  = smsBiz.findById(8);
				//把场地名称放进去
				String content1 = sms.getContent().replace("$(XXFDMC)", this.fxxm);
				String content2 = content1.replace("$(FDSJ)", this.fxtime);
				String content = content2.replace("$(FDDD)", this.fxaddress);
				 //调用短息接口的方法
			    SmsUtil.sendSms(this.teachertel, content);
			}
			offlineFdBiz.saveOrUpdateOfflineFd(fd);
    	  }
			this.outString("{success:true,message:'修改成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    /**
     * 线下辅导
     * 项目删除
     * @return
     */
    public  String deleteOfflineFd(){
    	try {
			this.offlineFdBiz.deleteOfflineFd(this.xmid);
			this.outString("{success:true,message:'刪除成功成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    /**
     * 批量刪除
     * @return
     */
    public String  deleteOfflinedAll(){
    	try {
			this.offlineFdBiz.deleteOfflinedAll(this.offlineFdAll);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    
    /**
     * 我的报名/关注/取消报名
     * 线下辅导项目
     * @return
     */
    public String saveOrUpdate(){
		try {
			 
			//报名
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("0")){
			   if(null != this.bmnumber && 0 != this.bmnumber){
				   if(this.bmnumber+1 == this.xmzise){
					   bmstatus = "已满员";
				   }else{
					   bmstatus = "可报名"; 
				    }
				    bmnumber = this.bmnumber+1;
				 }else{
					bmnumber = 1;
				  }
				//报名记录数据新增
			    OfflineFdLog  log = new  OfflineFdLog();
				
				//报名修改报名次数和报名状态
				offlineFdBiz.updateOfflineFd(bmnumber, bmstatus,this.xmid);
				
				//获取学生登录数据
			 Student  st = this.getUserDTO().getStudent();
			  if(null != st){
				 log.setBmId(st.getXh());
				 log.setBmName(st.getXm());
				 log.setDalei(st.getDalei());
				 log.setClasses(st.getClasses().getBjdm());
				 log.setPhone(st.getPhone());
				 
				 log.setXmid(this.xmid+this.getUserDTO().getLogincode());
				 log.setXmmc(this.fxxm);
				 if(null != this.fxtime){
				     log.setBmtime(DateTime.toDate(this.fxtime));
				 }
				 log.setFxaddress(this.fxaddress);
				 log.setFxteacher(this.fxteacher);
				 log.setXmzise(this.xmzise);
				 log.setCreatername(this.creatername);
				 log.setBmnumber(bmnumber);
				 log.setBmstatus(this.bmstatus);
				 log.setPlnumber(this.plnumber);
				 log.setXmxh(this.xmxh);
				 log.setXmintro(this.xmintro);
				 log.setBmTag("1");
				 if(null == this.tbTag || "".equals(this.tbTag)){
					 log.setTbTag("N");
				 }else{
					 log.setTbTag(this.tbTag);
				 }
			
				 log.setPlnr(this.plnr);
				 offlineFdBiz.saveOrUpdateLog(log);
			  }
		    }
			
			//评论
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("1")){
				
				OfflineFdLog lg = offlineFdBiz.findSingle(this.getUserDTO().getLogincode(),this.xmid);
				if(null != lg.getPlnr() && !"".equals(lg.getPlnr())){
					offlineFdBiz.updatePl(plnumber, this.xmid);
					//报名记录数据新增
					 OfflineFdLog  log = new  OfflineFdLog();
					 log.setBmId(lg.getBmId());
					 log.setBmName(lg.getBmName());
					 log.setDalei(lg.getDalei());
					 log.setClasses(lg.getClasses());
					 log.setPhone(lg.getPhone());
					 
					 log.setXmid(this.xmid+this.getUserDTO().getLogincode());
					 log.setXmmc(this.fxxm);
					 log.setFxaddress(lg.getFxaddress());
					 log.setBmtime(lg.getBmtime());
					 log.setFxteacher(lg.getFxteacher());
					 log.setXmzise(lg.getXmzise());
					 log.setCreatername(lg.getCreatername());
					 log.setBmnumber(lg.getBmnumber());
					 log.setBmstatus(lg.getBmstatus());
					 if(null == lg.getPlnumber()){
						 log.setPlnumber(1);
					 }else{
						 log.setPlnumber(lg.getPlnumber()); 
					 }
					 log.setXmintro(lg.getXmintro());
					 log.setBmTag(lg.getBmTag());
					 log.setTbTag(lg.getTbTag());
					 log.setXmxh(lg.getXmxh());
					 log.setPlnr(this.plnr);
					 log.setPlnrTime(new Date());
					 log.setPlTag("Y");
					 offlineFdBiz.saveOrUpdateLog(log);
				}else{
					if(null != this.plnumber){
						plnumber = plnumber+1;
					}else{
						plnumber = 1;
					}
					//评论更新f
					offlineFdBiz.updatePl(plnumber, this.xmid);
					
					//报名记录数据新增
					 OfflineFdLog  log = new  OfflineFdLog();
					 log.setBmId(lg.getBmId());
					 log.setBmName(lg.getBmName());
					 log.setDalei(lg.getDalei());
					 log.setClasses(lg.getClasses());
					 log.setPhone(lg.getPhone());
					 
					 log.setXmid(this.xmid+this.getUserDTO().getLogincode());
					 log.setXmmc(this.fxxm);
					 log.setFxaddress(lg.getFxaddress());
					 log.setBmtime(lg.getBmtime());
					 log.setFxteacher(lg.getFxteacher());
					 log.setXmzise(lg.getXmzise());
					 log.setCreatername(lg.getCreatername());
					 log.setBmnumber(lg.getBmnumber());
					 log.setBmstatus(lg.getBmstatus());
					 if(null == lg.getPlnumber()){
						 log.setPlnumber(1);
					 }else{
						 log.setPlnumber(lg.getPlnumber()+1); 
					 }
					 log.setXmintro(lg.getXmintro());
					 log.setBmTag(lg.getBmTag());
					 log.setTbTag(lg.getTbTag());
					 log.setXmxh(lg.getXmxh());
					 log.setPlnr(this.plnr);
					 log.setPlnrTime(new Date());
					 log.setPlTag("Y");
					 offlineFdBiz.saveOrUpdateLog(log);
				}
			}
			 //特别关注
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("2")){
				//查询原有的数据
				OfflineFdLog lg = offlineFdBiz.findSingle(this.getUserDTO().getLogincode(),this.xmid);
				if(null != lg){
				//报名记录数据新增
				 OfflineFdLog  log = new  OfflineFdLog();
				 log.setBmId(lg.getBmId());
				 log.setBmName(lg.getBmName());
				 log.setDalei(lg.getDalei());
				 log.setClasses(lg.getClasses());
				 log.setPhone(lg.getPhone());
				 
				 log.setXmid(this.xmid+this.getUserDTO().getLogincode());
				 log.setXmmc(lg.getXmmc());
				 log.setFxaddress(lg.getFxaddress());
				 log.setBmtime(lg.getBmtime());
				 log.setFxteacher(lg.getFxteacher());
				 log.setXmzise(lg.getXmzise());
				 log.setCreatername(lg.getCreatername());
				 log.setBmnumber(lg.getBmnumber());
				 log.setBmstatus(lg.getBmstatus());
				 log.setPlnumber(lg.getPlnumber());
				 log.setXmintro(lg.getXmintro());
				 log.setBmTag(lg.getBmTag());
				 log.setXmxh(lg.getXmxh());
				 log.setTbTag("Y");
				 log.setPlnr(lg.getPlnr());
				 log.setPlnrTime(lg.getPlnrTime());
				 log.setPlTag(lg.getPlTag());
				 offlineFdBiz.saveOrUpdateLog(log);
				}else{
					//获取学生登录数据
					Student  st = this.getUserDTO().getStudent();
					 if(null != st){
							//报名记录数据新增
						    OfflineFdLog  log = new  OfflineFdLog();
						    log.setBmId(st.getXh());
						    log.setBmName(st.getXm());
						    log.setDalei(st.getDalei());
							log.setClasses(st.getClasses().getBjdm());
							log.setPhone(st.getPhone());
							 
							log.setXmid(this.xmid+this.getUserDTO().getLogincode());
						    log.setXmmc(this.fxxm);
							if(null != this.fxtime){
							     log.setBmtime(DateTime.toDate(this.fxtime));
							 }
							log.setFxaddress(this.fxaddress);
							log.setFxteacher(this.fxteacher);
							log.setXmzise(this.xmzise);
							log.setCreatername(this.creatername);
							log.setBmnumber(bmnumber);
							log.setBmstatus(this.bmstatus);
							log.setPlnumber(this.plnumber);
							log.setXmxh(this.xmxh);
							log.setXmintro(this.xmintro);
							log.setBmTag("0");
							log.setTbTag("Y");
							log.setPlnr(this.plnr);
						    log.setPlTag("Y");
						offlineFdBiz.saveOrUpdateLog(log);
					}
				}
			}
			 //取消报名
			if(null !=ifApproval && !"".equals(ifApproval) && ifApproval.equals("3")){
				 if(null != this.bmnumber && 0 != this.bmnumber){
					bmnumber = this.bmnumber-1;
				  }
				  bmstatus = "可报名";
				 //取消报名更新主表
			     offlineFdBiz.updateOfflineFd(bmnumber, bmstatus,this.xmid);
			    
			   //查询原有的数据
			   OfflineFdLog lg = offlineFdBiz.findSingle(this.getUserDTO().getLogincode(),this.xmid);
			   if(null != lg){
			     //报名记录数据新增
				 OfflineFdLog  log = new  OfflineFdLog();
				 log.setBmId(lg.getBmId());
				 log.setBmName(lg.getBmName());
				 log.setDalei(lg.getDalei());
				 log.setClasses(lg.getClasses());
				 log.setPhone(lg.getPhone());
				 
				 log.setXmid(this.xmid+this.getUserDTO().getLogincode());
				 log.setXmmc(lg.getXmmc());
				 log.setFxaddress(lg.getFxaddress());
				 log.setBmtime(lg.getBmtime());
				 log.setFxteacher(lg.getFxteacher());
				 log.setXmzise(lg.getXmzise());
				 log.setCreatername(lg.getCreatername());
				 log.setBmnumber(lg.getBmnumber()-1);
				 log.setBmstatus(lg.getBmstatus());
				 log.setPlnumber(lg.getPlnumber());
				 log.setXmintro(lg.getXmintro());
				 log.setXmxh(lg.getXmxh());
				 log.setBmTag("0");
				 log.setTbTag(lg.getTbTag());
				 log.setPlnr(lg.getPlnr());
				 log.setPlnrTime(lg.getPlnrTime());
				 log.setPlTag(lg.getPlTag());
				 offlineFdBiz.saveOrUpdateLog(log);
			   }
			}
			
			
			 if (this.bmId== null || this.bmId.equals("")) {
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
     * 我关注的项目
     * @return
     */
    public  String findPageTb(){
    	  try {
			Page  page = new  Page();
		    page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
		    String[]   property = {"bmId" ,"tbTag"};
			String[]   value = { this.getUserDTO().getLogincode(),"Y" };
			
		  offlineFdBiz.findPageTbORCy(page, property,value, "bmtime");
		   this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
	
    
    /**
     * 我参与的项目
     * @return
     */
    public  String  findPageCy(){
    	try {
			Page  page = new  Page();
		    page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
		    String[]   property = {"bmId" ,"bmTag"};
			String[]   value = { this.getUserDTO().getLogincode(),"1" };
			
			 offlineFdBiz.findPageTbORCy(page, property,value, "bmtime");
		   this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	
    	return null;
    }
    
    /**
     * 评论列表
     * @return
     */
    public  String findPl(){
    	try {
			Page  page = new  Page();
		    page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
		    String[]   property = {"xmid" ,"bmTag","plTag"};
			String[]   value = { this.xmid,"1" ,"Y"};
			offlineFdBiz.findPageTbORCy(page, property,value, "plnrTime");
		   this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    /**
     * 项目管理
     * @return
     */
    public String  findMangeProject(){
    	try {
			Page  page = new  Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			offlineFdBiz.findMangeProject(page, this.xmid, "xmxh");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return  null;
    }
    
    
    /**
     * 根据项目id
     * 查询项目数据
     * 跳转项目管理
     * @return
     */
    public  String  findSingOfflineFd(){
    	try {
			OfflineFd fd  = offlineFdBiz.findSingOfflineFd(this.xmid);
			if(null != fd){
			 this.outObjectString(fd,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	   return null; 
    }
    
    
    
    //下载方法
    public  OfflineFdAction(){
      	context= ServletActionContext.getServletContext();
    	
    }
    
    @Override  
    public String execute() throws Exception {  
        mimeType = context.getMimeType(filename);  
        return SUCCESS;  
    }  
    
    public String getFilename() {
		 try {  
	           return new String(filename.getBytes(),"ISO8859-1");  //UTF-8  ISO8859-1
	        } catch (UnsupportedEncodingException e) {  
	            return this.filename;  
	        }
	}

	public void setFilename(String filename) {
		try {  
			  this.filename = new String(filename.getBytes("ISO8859-1"),"GBK");  
       } catch (UnsupportedEncodingException e) {  
       }
	}

    
    //导出辅学报名名单(exportstudent.do)
	public InputStream getInStream() {
		try {
		
			//查询参与辅学项目的学生
			List<OfflineFdLog> list = offlineFdBiz.findAllOfflineFdLog(this.xmid, "1");
			
	        String[] titles = new String[]{"序号","姓名", "学号", "大类", "班级", "联系电话"};
	        String[] fieldNames = new String[]{"xmxh","bmName", "bmId", "dalei","classes","phone"};
			
	        String path = ServletActionContext.getServletContext().getRealPath("file")+ File.separator;
	        
	        //根据项目id查项目名
	        OfflineFd  fd =  offlineFdBiz.findSingOfflineFd(this.xmid);
	        if(null != fd){
	            File exportFile = new File(path+fd.getFxxm()+".xls");
	 	        ExcelHelper exh = JxlExcelHelper.getInstance(exportFile);
	 	        //判断导出是报名表模板还是已经报名的学生
	 	        if(null != this.ifApproval && !"".equals(this.ifApproval)&&
	 	             this.ifApproval.equals("1")){
	 	             File exportFile1 = new File(path+"报名表"+".xls");
	 	 	         ExcelHelper exh1 = JxlExcelHelper.getInstance(exportFile1);
	 	        	 List<OfflineFdLog> list1 = new ArrayList<OfflineFdLog>();
	 	        	 exh1.writeExcel(OfflineFdLog.class, list1, fieldNames, titles);
	 	        	 filename = "报名表" + ".xls";
	 	        }else{
	 	        	 exh.writeExcel(OfflineFdLog.class, list, fieldNames, titles);
	 	        	 filename = "报名名单" + ".xls";
	 	        }
	 	      
	        }
	       
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		inStream = context.getResourceAsStream("/file/"+filename);
		if(inStream==null){
            inStream = new ByteArrayInputStream("Sorry,File not found !"  
                    .getBytes());
        }
		return inStream;
	}
    
	public String getMimeType() {
		return mimeType;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
    
	
	public OfflineFdBiz getOfflineFdBiz() {
		return offlineFdBiz;
	}

	public void setOfflineFdBiz(OfflineFdBiz offlineFdBiz) {
		this.offlineFdBiz = offlineFdBiz;
	}
	

	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}



	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getXmxh() {
		return xmxh;
	}
	public void setXmxh(String xmxh) {
		this.xmxh = xmxh;
	}
	public String getFxxm() {
		return fxxm;
	}
	public void setFxxm(String fxxm) {
		this.fxxm = fxxm;
	}
	public String getFxtime() {
		return fxtime;
	}
	public void setFxtime(String fxtime) {
		this.fxtime = fxtime;
	}
	public String getFxaddress() {
		return fxaddress;
	}
	public void setFxaddress(String fxaddress) {
		this.fxaddress = fxaddress;
	}
	public String getFxteacher() {
		return fxteacher;
	}
	public void setFxteacher(String fxteacher) {
		this.fxteacher = fxteacher;
	}
	public String getTeachertel() {
		return teachertel;
	}
	public void setTeachertel(String teachertel) {
		this.teachertel = teachertel;
	}
	public String getXmintro() {
		return xmintro;
	}
	public void setXmintro(String xmintro) {
		this.xmintro = xmintro;
	}
	
	public Integer getBmnumber() {
		return bmnumber;
	}
	public void setBmnumber(Integer bmnumber) {
		this.bmnumber = bmnumber;
	}

	public Integer getXmzise() {
		return xmzise;
	}
	public void setXmzise(Integer xmzise) {
		this.xmzise = xmzise;
	}
	public String getBmstatus() {
		return bmstatus;
	}
	public void setBmstatus(String bmstatus) {
		this.bmstatus = bmstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
   
	public Integer getPlnumber() {
		return plnumber;
	}


	public void setPlnumber(Integer plnumber) {
		this.plnumber = plnumber;
	}


	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	public Date getTopTime() {
		return topTime;
	}


	public void setTopTime(Date topTime) {
		this.topTime = topTime;
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
	

	public String getOfflineFdAll() {
		return offlineFdAll;
	}



	public void setOfflineFdAll(String offlineFdAll) {
		this.offlineFdAll = offlineFdAll;
	}



	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBmId() {
		return bmId;
	}


	public void setBmId(String bmId) {
		this.bmId = bmId;
	}


	public String getBmName() {
		return bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	public String getPlnr() {
		return plnr;
	}
	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}
	public String getBmtime() {
		return bmtime;
	}

	public void setBmtime(String bmtime) {
		this.bmtime = bmtime;
	}

	public String getTbTag() {
		return tbTag;
	}

	public void setTbTag(String tbTag) {
		this.tbTag = tbTag;
	}


	public String getBmTag() {
		return bmTag;
	}


	public void setBmTag(String bmTag) {
		this.bmTag = bmTag;
	}


	public String getXmmc() {
		return xmmc;
	}


	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
    
}
