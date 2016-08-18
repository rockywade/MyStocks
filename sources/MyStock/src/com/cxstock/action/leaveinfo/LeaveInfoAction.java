
package com.cxstock.action.leaveinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.leaveinfo.LeaveInfoBiz;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.LeaveInfo;
import com.cxstock.pojo.LeaveInfoLog;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.SmsUtil;

/**
 * 请假信息的control
 * @ wzx
 * 2016-5-24
 * 
 */

@SuppressWarnings("serial")
public class LeaveInfoAction extends BaseAction{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private LeaveInfoBiz leaveInfoBiz;
	
	private SmsBiz smsBiz;
	
	//标示
	private Integer id;
	//请假学生名称
	private String studentname;
	//学生学号
	private  String studentnum;
	//行政班
	private String classcode; 
	//专业
	private String major;
	//寝室
	private String bedroom;   
	//联系电话
	private String relationtel;
	//班主任
	private String classth;
	//辅导员
	private String counsellor;
	//请假原因
	private  String  leavereason;
	//离校时间
	private  Date  leavetime;
	//请假天数
	private  Integer  daysum;
	//返校时间
	private  Date  backsctime;
	//父母知情
	private  String  parentsinfo;
	//请假累计次数
	private  Integer totalnum;
	//辅导员意见
	private  String tutorstatus;
	//辅导员审核内容
	private  String checksopinion;
	//院系领导状态
	private  String schoolstatus;
	//院系领导审核意见
	private String schoolsopinion;
	//状态：待审核，同意，不同意
	private  String status;
	//父母电话
	private  String parentstel;
	//学生守则说明标示：
	private String rulesstate;
	
	//接收审核传的条件
	private String  studentnums;
	
	//判断传过来是同意（0）或者不同意（1）;
	//其他做别判断
	private String ifApproval;
	
	
	/**
	 *我的学生预览分页，搜索，排序查询
	 * @return
	 */
	public String findPageLeaveInfo1(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if(null != this.ifApproval && !"".equals(this.ifApproval) && this.ifApproval.equals("3")){
				//获取辅导员登录信息
				Instructor userInfo = this.getUserDTO().getInstructor();
				if(null != userInfo ){
					counsellor = userInfo.getXm();
				}
				//获取辅导员登录信息
				HeadMaster  hm = this.getUserDTO().getHeadMaster();
				if(null != hm){
					classth = hm.getXm();
				}
			}
			if(null != this.ifApproval && !"".equals(this.ifApproval) && this.ifApproval.equals("1")){
				//获取学生员登录者的登录号
			     studentnum = this.getUserDTO().getLogincode();
			}
			
			String[] property = { "studentname" , "classcode" ,"studentnum","status","counsellor","classth"};
			String[] value = { this.studentname , this.classcode, this.studentnum,this.status,this.counsellor,this.classth};
			
			leaveInfoBiz.findPageLeaveInfo1(page, property,value,"leavetime","backsctime");
			this.outPageString(page);
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	
	/**
	 * 我的学生预览分页，搜索，排序查询2
	 * @return
	 */
	public String  findPageLeaveInfoLog(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
		
		    //获取辅导员登录信息
			Instructor userInfo = this.getUserDTO().getInstructor();
			if(null != userInfo ){
					counsellor = userInfo.getXm();
			  }
				//获取辅导员登录信息
			HeadMaster  hm = this.getUserDTO().getHeadMaster();
			if(null != hm){
				classth = hm.getXm();
			  }
			String[] property = { "studentname" , "classcode" ,"studentnum","counsellor","classth"};
			String[] value = { this.studentname , this.classcode, this.studentnum,this.counsellor,this.classth};
			
			leaveInfoBiz.findPageLeaveInfoLog(page, property,value,"operatTime");
			this.outPageString(page);
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	
	
	/**
	 * 院系领导查询列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findPageLeaveInfo4(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			   //传值
			   List list1 = new ArrayList();
			 
				//获取院系领导登录信息
				LeaderShip userInfo = this.getUserDTO().getLeaderShip();
				List<Classes> list  = leaveInfoBiz.findClass(userInfo.getSsyq());
				if(null != list){
				   for(Classes cls :list){
					   list1.add("'"+cls.getBjdm()+"'");
				   }
				}
			tutorstatus ="已同意";
			
			String[] property = { "studentname" , "classcode" ,"studentnum","status","tutorstatus"};
			String[] value = { this.studentname , this.classcode, this.studentnum,this.status,this.tutorstatus};
			leaveInfoBiz.findPageLeaveInfo4(page, property, value,"leavetime","backsctime", "daysum", "classcode", list1); 
			//leaveInfoBiz.findPageLeaveInfo10(page); 
			this.outPageString(page);
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	
	
	/**
	 * 请假信息审核/批量审核
	 * @return
	 */
	public  String  saveOrUpdaLeaveInfo(){
		try {
			LeaveInfo leaveInfo = new LeaveInfo();
			leaveInfo.setId(this.id);
			leaveInfo.setLeavereason(this.leavereason);
			leaveInfo.setStudentname(this.studentname);
			leaveInfo.setStudentnum(this.studentnum);
			leaveInfo.setLeavetime(this.leavetime);
			leaveInfo.setBacksctime(this.backsctime);
			leaveInfo.setClassth(this.classth);
			leaveInfo.setBedroom(this.bedroom);
			leaveInfo.setRelationtel(this.relationtel);
			leaveInfo.setCounsellor(this.counsellor);
			leaveInfo.setMajor(this.major);
			leaveInfo.setClasscode(this.classcode);
			leaveInfo.setParentsinfo(this.parentsinfo);
			leaveInfo.setRulesstate(this.rulesstate);
			leaveInfo.setParentstel(this.parentstel);
			if(this.ifApproval.equals("0")){
				leaveInfo.setStatus("已同意");
			}
			if(this.ifApproval.equals("1")){
				leaveInfo.setStatus("不同意");
			}
			leaveInfoBiz.saveOrUpdaLeaveInfo(leaveInfo, this.studentnums);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 
	 * 请假新增或修改跳转url
	 * @return
	 */
	public String  goAddOrUpateLeaveInfo(){
		try {
			//获取登录用户的信息
			Student st = this.getUserDTO().getStudent();
				if(st != null){
					//接收数据返回出去
					StudentDTO dto = new StudentDTO();
					dto.setXm(st.getXm());
					dto.setXh(st.getXh());
					dto.setBjdm(st.getClasses().getBjdm());
					dto.setQsmc(st.getQsmc());
					dto.setPhone(st.getPhone());
					dto.setHeadmaster(st.getHeadmaster().getXm());
					dto.setInstructor(st.getInstructor().getXm());
					//System.out.println(st.getInstructor().getXm()+st.getHeadmaster().getXm()+st.getXm()+st.getInstructor().getXm()+st.getClasses().getMajor().getZymc()+st.getClasses().getBjmc()+st.getInstructor().getXm());
					this.outObjectString(dto,null);
				}else{
					this.outError();
					
				}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		
		return null;
	}
    
	/**
	 * 执行请假新增数据保存
	 * @return
	 */
	public String saveLeaveInfo(){
		 try {
			 //明细数据新增
			LeaveInfo leaveInfo = new LeaveInfo();
				leaveInfo.setId(this.id);
				leaveInfo.setLeavereason(this.leavereason);
				leaveInfo.setStudentname(this.studentname);
				leaveInfo.setStudentnum(this.studentnum);
				leaveInfo.setLeavetime(this.leavetime);
				leaveInfo.setBacksctime(this.backsctime);
				leaveInfo.setClassth(this.classth);
				leaveInfo.setBedroom(this.bedroom);
				leaveInfo.setRelationtel(this.relationtel);
				leaveInfo.setCounsellor(this.counsellor);
				leaveInfo.setMajor(this.major);
				leaveInfo.setClasscode(this.classcode);
				leaveInfo.setParentsinfo(this.parentsinfo);
				leaveInfo.setRulesstate(this.rulesstate);
				leaveInfo.setParentstel(this.parentstel);
				leaveInfo.setDaysum(this.daysum);
				leaveInfo.setTotalnum(this.totalnum);
				leaveInfo.setSchoolsopinion(this.schoolsopinion);
				leaveInfo.setChecksopinion(this.checksopinion);
				leaveInfo.setTutorstatus("待审核");
				leaveInfo.setSchoolstatus("无");
				if(this.id == null){
				 //获取登录者信息
				 Student  student = this.getUserDTO().getStudent();
				 //查询发送内容
				  Sms  sms  = smsBiz.findById(5);
				  String content = sms.getContent().replace("$(XM)", this.counsellor);
				
				   //调用短息接口的方法
			     SmsUtil.sendSms(student.getInstructor().getPhone(), content);
				}
				
				//获取数据判断是否是第一次新增记录表数据
				LeaveInfoLog lg = leaveInfoBiz.findSingleLeaveInfoLog(this.studentnum);
				if(null != lg){
					if(lg.getStudentnum().equals(this.studentnum)){
						//记录表数据新增
						LeaveInfoLog  log  =  new LeaveInfoLog();
						log.setStudentname(this.studentname);
						log.setStudentnum(this.studentnum);
						log.setClasscode(this.classcode);
						log.setClassth(this.classth);
						log.setBedroom(this.bedroom);
						log.setRelationtel(this.relationtel);
						log.setCounsellor(this.counsellor);
						log.setMajor(this.major);
						log.setOperatTime(lg.getOperatTime());
						log.setTotalnum(lg.getTotalnum());
						//记录表数据
						leaveInfoBiz.saveLeaveInfoLog(log);
						
					}
				}else{
					//记录表数据新增
					LeaveInfoLog  log  =  new LeaveInfoLog();
					log.setStudentname(this.studentname);
					log.setStudentnum(this.studentnum);
					log.setClasscode(this.classcode);
					log.setClassth(this.classth);
					log.setBedroom(this.bedroom);
					log.setRelationtel(this.relationtel);
					log.setCounsellor(this.counsellor);
					log.setMajor(this.major);
					log.setOperatTime(new Date());
					log.setTotalnum(this.totalnum);
					//记录表数据
					leaveInfoBiz.saveLeaveInfoLog(log);
				}
				
				//明细表数据保存
				this.leaveInfoBiz.saveLeaveInfo(leaveInfo);
				if(this.id==null){
					this.outString("{success:true,message:'保存成功!'}");
				}else{
					this.outString("{success:true,message:'修改成功!'}");
				}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	/**
	 * 请假老师审批分页查询
	 * @return
	 */
	public String  findPageLeaveInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = { "studentname" , "classcode" ,"studentnum","status"};
			String[] value = { this.studentname , this.classcode, this.studentnum,this.status};
			leaveInfoBiz.findPageLeaveInfo(page, property, value);
			this.outPageString(page);
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		
		return  null;
	}
	
	
	//保存/更新数据的保存
	public String saveOrUpdate(){
		try {
			LeaveInfo leaveInfo = new LeaveInfo();
			leaveInfo.setId(this.id);
			leaveInfo.setLeavereason(this.leavereason);
			leaveInfo.setStudentname(this.studentname);
			leaveInfo.setStudentnum(this.studentnum);
			leaveInfo.setLeavetime(this.leavetime);
			leaveInfo.setBacksctime(this.backsctime);
			leaveInfo.setClassth(this.classth);
			leaveInfo.setBedroom(this.bedroom);
			leaveInfo.setRelationtel(this.relationtel);
			leaveInfo.setCounsellor(this.counsellor);
			leaveInfo.setMajor(this.major);
			leaveInfo.setClasscode(this.classcode);
			leaveInfo.setParentsinfo(this.parentsinfo);
			leaveInfo.setRulesstate(this.rulesstate);
			leaveInfo.setParentstel(this.parentstel);
			leaveInfo.setDaysum(this.daysum);
			leaveInfo.setSchoolsopinion(this.schoolsopinion);
			leaveInfo.setChecksopinion(this.checksopinion);
			leaveInfo.setTutorstatus(this.tutorstatus);
			leaveInfo.setSchoolstatus(this.schoolstatus);
			if(null != this.ifApproval && !"".equals(this.ifApproval) && "0".equals(this.ifApproval)){
				if(this.daysum >= 30){
					leaveInfo.setStatus("已同意");
					leaveInfo.setTotalnum(this.totalnum);
					leaveInfo.setTutorstatus("已同意");
				}else{
					
				leaveInfo.setStatus("已同意");
				leaveInfo.setTotalnum(this.totalnum+1);
				leaveInfo.setTutorstatus("已同意");
				
				//记录表数据修改
				LeaveInfoLog lg = leaveInfoBiz.findSingleLeaveInfoLog(this.studentnum);
				if(null != lg){
					if(null != lg.getTotalnum()){
						leaveInfoBiz.updateLeaveInfoLog(lg.getTotalnum()+1, this.studentnum);
					}else{
						leaveInfoBiz.updateLeaveInfoLog(1, this.studentnum);
					}
				}
			
				//获取学生信息
				Student st = leaveInfoBiz.goAddOrUpdateLeaveInfo(this.studentnum);
				//获取发送信息
				Sms  sms  = smsBiz.findById(6);
				 //调用短息接口的方法
			   SmsUtil.sendSms(st.getPhone(), sms.getContent());
				}
			}
			if(null != this.ifApproval && !"".equals(this.ifApproval) && "1".equals(this.ifApproval)){
				leaveInfo.setStatus("未同意");
				leaveInfo.setTutorstatus("未同意");
				leaveInfo.setTotalnum(this.totalnum);
			}
			if(null != this.ifApproval && !"".equals(this.ifApproval) && "3".equals(this.ifApproval)){
				leaveInfo.setSchoolstatus("已同意");
				leaveInfo.setStatus("已同意");
				leaveInfo.setTotalnum(this.totalnum+1);
				
				//记录表数据修改
				LeaveInfoLog lg = leaveInfoBiz.findSingleLeaveInfoLog(this.studentnum);
				if(null != lg){
					if(null != lg.getTotalnum()){
						leaveInfoBiz.updateLeaveInfoLog(lg.getTotalnum()+1, this.studentnum);
					}else{
						leaveInfoBiz.updateLeaveInfoLog(1, this.studentnum);
					}
					
				}
				//获取学生信息
				Student st = leaveInfoBiz.goAddOrUpdateLeaveInfo(this.studentnum);
				//获取发送信息
				Sms  sms  = smsBiz.findById(6);
				 //调用短息接口的方法
			    SmsUtil.sendSms(st.getPhone(), sms.getContent());
			}if(null != this.ifApproval && !"".equals(this.ifApproval) && "4".equals(this.ifApproval)){
				leaveInfo.setSchoolstatus("未同意");
				leaveInfo.setTotalnum(this.totalnum);
			}
			this.leaveInfoBiz.saveOrUpdateLeaveInfo(leaveInfo);
			if(this.id==null){
				this.outString("{success:true,message:'保存成功!'}");
			}else{
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 删除的方法
	 * @return
	 */
	public String deleteLeaveInfo() {
		try {
			leaveInfoBiz.deleteLeaveInfo(this.id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 查询所有班级
	 * @return
	 */
	public  String  findClassComb(){
		try {
			this.outListString(leaveInfoBiz.findClassComb());
		   } catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		   }
	    return null;
	}
	
	
	/**
	 * 查询所有班级
	 * @return
	 */
	public String findClassComb1(){
	  try {
		//获取登录者信息
		LeaderShip lp =   this.getUserDTO().getLeaderShip();
		HeadMaster hm =  this.getUserDTO().getHeadMaster();
		Instructor  it= this.getUserDTO().getInstructor();
		//接收数据
        List<Classes> counList =  new ArrayList<Classes>();
		if(null !=lp){
			counList = leaveInfoBiz.findClass(lp.getSsyq());
		 }
		 if(null != hm ){
			 Set cl = hm.getHclass();
			 counList = new ArrayList<Classes>(cl);
		 }
		 if(null != it ){
		   Set  st = it.getIclass();
		   counList = new ArrayList<Classes>(st);
		 }
		 List  lists = leaveInfoBiz.findClassComb1(counList);
		 if(null != lists){
			this.outList1(lists);
		  }
	    } catch (Exception e) {
		   e.printStackTrace();
		   this.outError();
	   }
	    return null;
	}
	
	
	
	public LeaveInfoBiz getLeaveInfoBiz() {
		return leaveInfoBiz;
	}

	public void setLeaveInfoBiz(LeaveInfoBiz leaveInfoBiz) {
		this.leaveInfoBiz = leaveInfoBiz;
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

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getBedroom() {
		return bedroom;
	}

	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}

	public String getRelationtel() {
		return relationtel;
	}

	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
	}

	public String getClassth() {
		return classth;
	}

	public void setClassth(String classth) {
		this.classth = classth;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public String getLeavereason() {
		return leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}

	public Integer getDaysum() {
		return daysum;
	}

	public void setDaysum(Integer daysum) {
		this.daysum = daysum;
	}

	public Date getBacksctime() {
		return backsctime;
	}

	public void setBacksctime(Date backsctime) {
		this.backsctime = backsctime;
	}

	public String getParentsinfo() {
		return parentsinfo;
	}

	public void setParentsinfo(String parentsinfo) {
		this.parentsinfo = parentsinfo;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public String getTutorstatus() {
		return tutorstatus;
	}

	public void setTutorstatus(String tutorstatus) {
		this.tutorstatus = tutorstatus;
	}

	public String getSchoolstatus() {
		return schoolstatus;
	}

	public void setSchoolstatus(String schoolstatus) {
		this.schoolstatus = schoolstatus;
	}

	public String getSchoolsopinion() {
		return schoolsopinion;
	}

	public void setSchoolsopinion(String schoolsopinion) {
		this.schoolsopinion = schoolsopinion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentstel() {
		return parentstel;
	}

	public void setParentstel(String parentstel) {
		this.parentstel = parentstel;
	}

	public String getRulesstate() {
		return rulesstate;
	}

	public void setRulesstate(String rulesstate) {
		this.rulesstate = rulesstate;
	}

	public String getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}

	public String getChecksopinion() {
		return checksopinion;
	}

	public void setChecksopinion(String checksopinion) {
		this.checksopinion = checksopinion;
	}

	public String getStudentnums() {
		return studentnums;
	}

	public void setStudentnums(String studentnums) {
		this.studentnums = studentnums;
	}

	public String getIfApproval() {
		return ifApproval;
	}

	public void setIfApproval(String ifApproval) {
		this.ifApproval = ifApproval;
	}

	
	
}
