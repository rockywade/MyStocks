package com.cxstock.action.activity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.action.common.ActivitySmsQuartz;
import com.cxstock.action.student.vo.StudentExcel;
import com.cxstock.biz.activity.ApplyActivityBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.biz.student.StudentBiz;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.pojo.Activity;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.ChangToUser;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.News;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.JxlExcelHelper;
import com.cxstock.utils.system.ServiceHelper;
import com.cxstock.utils.system.SmsUtil;
import com.cxstock.utils.system.Tools;

@SuppressWarnings("serial")
public class ApplyactivityAction extends BaseAction {
	
	private Integer studentid;
	//活动
	private String activityname;
	private String applyuser;
	private String studentnum;
	private String studentphonenum;
	private String organizename;
	private String teacher;
	private String phonenum;
	private String activitygenre;
	private String activitytime;
	private String inschoolterm;
	private String faceobj;
	private int capacity;
	private String activityplace;
	private String timeofduration;
	private String activitycontent;
	private String signupendtime;
	private String applystyle;
	//活动审核时间：活动未审核时该字段为空；用以存储活动给出审核时的时间
	private String checktime;						
	private String actionName;						
	private String activityid;						//活动id
	private String checkkey="0";					//审核值，通过or不通过
	private String pulishkey="0";					//活动公示审核值，允许or不允许
	private ApplyActivityBiz applyActivityBiz; 		//Biz层接口参数
	private StudentBiz studentBiz;
	private String newscheckstyle;					//标示新闻是否发布
	private String publicitycheckstyle;				/*公示审核状态:未提交公示申请，该字段为空；
														提交公示申请之后，状态修改为未公示待审核；
														公示审批后，状态修改为公示中状态or不允许公示*/
	private String fileDownKey;
	private String activitypublicitytime;
	private String uploadnewstime;
	
	//活动报名
	private String usernum;
	private String username;
	private String major;
	private String classes;
	private String counsellor;
	private String attendstudentphonenum;
	private String dorm;
	private int score;
	private String sscore;
	private String state;						//报名状态
	private int gotscore;						//所得分数
	private int gross;							//总分数
	
	//新闻
	private String newsid;
	private String newstitle;
	private String website;
	private String writer;
	private String newsdate;
	private String property;
	private String content;
	private String highlight;
	private String totop;
	
	//导入文件
	private File upload;
	private String uploadFileName;
	
	//导出文件
	private ServletContext context;
	private String filename;
	private String mimeType;
	private InputStream inStream;
	
	//构造，获取context
	public ApplyactivityAction(){
		context = ServletActionContext.getServletContext();
	}
	
	//重写execute方法
	public String execute() throws Exception {
		mimeType = context.getMimeType(filename);
		return SUCCESS;
	}



	public StudentBiz getStudentBiz() {
		return studentBiz;
	}

	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public String getFilename() {
		 try {  
	            return new String(filename.getBytes(),"ISO8859-1");  
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

	public String getMimeType() {
		return mimeType;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public int getGross() {
		return gross;
	}
	public void setGross(int gross) {
		this.gross = gross;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	//活动
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getApplyuser() {
		return applyuser;
	}
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
	public String getStudentnum() {
		return studentnum;
	}
	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}
	public String getStudentphonenum() {
		return studentphonenum;
	}
	public void setStudentphonenum(String studentphonenum) {
		this.studentphonenum = studentphonenum;
	}
	public String getOrganizename() {
		return organizename;
	}
	public void setOrganizename(String organizename) {
		this.organizename = organizename;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getActivitygenre() {
		return activitygenre;
	}
	public void setActivitygenre(String activitygenre) {
		this.activitygenre = activitygenre;
	}
	public String getActivitytime() {
		return activitytime;
	}
	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}
	public String getInschoolterm() {
		return inschoolterm;
	}
	public void setInschoolterm(String inschoolterm) {
		this.inschoolterm = inschoolterm;
	}
	public String getFaceobj() {
		return faceobj;
	}
	public void setFaceobj(String faceobj) {
		this.faceobj = faceobj;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getActivityplace() {
		return activityplace;
	}
	public void setActivityplace(String activityplace) {
		this.activityplace = activityplace;
	}
	public String getTimeofduration() {
		return timeofduration;
	}
	public void setTimeofduration(String timeofduration) {
		this.timeofduration = timeofduration;
	}
	public String getActivitycontent() {
		return activitycontent;
	}
	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
	}
	public String getSignupendtime() {
		return signupendtime;
	}
	public void setSignupendtime(String signupendtime) {
		this.signupendtime = signupendtime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getSscore() {
		return sscore;
	}

	public void setSscore(String sscore) {
		this.sscore = sscore;
	}

	public String getApplystyle() {
		return applystyle;
	}
	public void setApplystyle(String applystyle) {
		this.applystyle = applystyle;
	}
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getCheckkey() {
		return checkkey;
	}
	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}
	public String getPulishkey() {
		return pulishkey;
	}
	public void setPulishkey(String pulishkey) {
		this.pulishkey = pulishkey;
	}
	public void setApplyActivityBiz(ApplyActivityBiz applyActivityBiz) {
		this.applyActivityBiz = applyActivityBiz;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNewscheckstyle() {
		return newscheckstyle;
	}
	public void setNewscheckstyle(String newscheckstyle) {
		this.newscheckstyle = newscheckstyle;
	}
	public String getPublicitycheckstyle() {
		return publicitycheckstyle;
	}
	public void setPublicitycheckstyle(String publicitycheckstyle) {
		this.publicitycheckstyle = publicitycheckstyle;
	}
	
	public String getFileDownKey() {
		return fileDownKey;
	}

	public void setFileDownKey(String fileDownKey) {
		this.fileDownKey = fileDownKey;
	}

	public String getActivitypublicitytime() {
		return activitypublicitytime;
	}

	public void setActivitypublicitytime(String activitypublicitytime) {
		this.activitypublicitytime = activitypublicitytime;
	}

	public String getUploadnewstime() {
		return uploadnewstime;
	}

	public void setUploadnewstime(String uploadnewstime) {
		this.uploadnewstime = uploadnewstime;
	}

	//活动报名
	public String getClasses() {
		return classes;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getGotscore() {
		return gotscore;
	}
	public void setGotscore(int gotscore) {
		this.gotscore = gotscore;
	}
	public ApplyActivityBiz getApplyActivityBiz() {
		return applyActivityBiz;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}
	public String getAttendstudentphonenum() {
		return attendstudentphonenum;
	}
	public void setAttendstudentphonenum(String attendstudentphonenum) {
		this.attendstudentphonenum = attendstudentphonenum;
	}
	public String getDorm() {
		return dorm;
	}
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	
	//新闻
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNewsdate() {
		return newsdate;
	}
	public void setNewsdate(String newsdate) {
		this.newsdate = newsdate;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	public String getTotop() {
		return totop;
	}
	public void setTotop(String totop) {
		this.totop = totop;
	}

	/**
	 * 申报活动
	 **/
	public String applyActivity(){
		Activity activity = new Activity(activityname, applyuser, 
				studentnum, studentphonenum, organizename, teacher, 
				phonenum, activitygenre, activitytime, 
				inschoolterm, faceobj, capacity, activityplace, 
				timeofduration, activitycontent, signupendtime, score);
		activity.setApplystyle("待审核");
		if(activityid==null || activityid.equals("")){						//新增
			activityid = Tools.getUid();
			activity.setActivityid(activityid);
			activity.setNewscheckstyle("no");
			applyActivityBiz.saveApplyActivity(activity);
			StudentExcel studentExcel = new StudentExcel();
			studentExcel.setXh(studentnum);
			Student student = studentBiz.studentExist(studentExcel);
			if(student.getSsyq() != null && !student.getSsyq().equals("")) {
				if(student.getSsyq().contains("蓝田")) {
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至蓝田六舍209室，蒋老师,88206718'}");
				} else if(student.getSsyq().contains("丹青")) {
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至青溪一舍131室，王老师，22806841'}");
				} else if(student.getSsyq().contains("云峰")){
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至碧峰连廊131室，田老师，88206505'}");
				} else {
					this.outString("{success:true,message:'申报成功!  请将纸质版申报表交至......'}");
				}
			} else {
				this.outString("{success:true,message:'申报成功!  请将纸质版申报表交至......'}");
			}
			
				
		}else{												
			activity.setActivityid(activityid);
			if(checkkey.equals("1")){										//取消申请
				String clazz = "Activity";
				String setproperty = "applystyle";
				String byid = "activityid";
				applystyle="已取消";
				applyActivityBiz.updateClassById(clazz,setproperty,applystyle,byid,activityid);
				this.outString("{success:true,message:'取消成功!'}");
			}else{															//更新
					applyActivityBiz.updateActivityById(activity);
					this.outString("{success:true,message:'修改成功!'}");
			}
		}
		return null;
	}
	
	/**
	 * 参与公示
	 **/
	public String joinPublish(){
		try {
			if(pulishkey.equals("1")){
				publicitycheckstyle = "未公示待审核";
				String clazz = "Activity";
				String setproperty = "publicitycheckstyle";
				String byid = "activityid";
				applyActivityBiz.updateClassById(clazz, setproperty, publicitycheckstyle, byid, activityid);
				this.outString("{success:true,message:'参与公示成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return null;
	}
	
	/**
	 * 公示审核
	 **/
	public String publishActivity(){
		try {
			applyActivityBiz.updatePublicityCheck(pulishkey,activityid);
			if(pulishkey.equals("1")){
				this.outString("{success:true,message:'审核成功！      已在公示中!'}");
			}else if(pulishkey.equals("2")){
				this.outString("{success:true,message:'审核成功！      该活动不允许公示!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 获取当前用户信息
	 **/
	public String getCurrentUserInfo(){
		try {
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			Student student = userInfo.getStudent();
			Instructor instructor = userInfo.getInstructor();
			HeadMaster headma = userInfo.getHeadMaster();
			NewFriends nf = userInfo.getNewFriends();
			ChangToUser user = new ChangToUser();
			if(student!=null){
				user.setUserNum(student.getXh());
				user.setUserName(student.getXm());
				user.setUserSex(student.getXb());
				user.setUserPhone(student.getPhone());
				user.setUserClasses(student.getClasses().getBjdm());
				user.setUserDorm(student.getQsmc());
				user.setUserYq(student.getSsyq());
				user.setUserInstructor(student.getInstructor().getXm());
				user.setUserHeadmaster(student.getHeadmaster().getXm());
			}else if(instructor!=null){
				user.setUserNum(instructor.getZgh());
				user.setUserName(instructor.getXm());
				user.setUserSex(instructor.getXb());
				user.setUserPhone(instructor.getPhone());
			}else if(headma!=null){
				user.setUserNum(headma.getZgh());
				user.setUserName(headma.getXm());
				user.setUserSex(headma.getXb());
				user.setUserPhone(headma.getPhone());
			}else if(nf!=null){
				user.setUserNum(nf.getZgh());
				user.setUserName(nf.getXm());
				user.setUserSex(nf.getXb());
				user.setUserPhone(nf.getPhone());
			}
			if(userInfo.getLogincode().contains("admin")) {
				user.setUserNum(userInfo.getLogincode());
				user.setUserName(userInfo.getLogincode());
				user.setUserPhone("");
			}
			this.outObjectString(user,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 个人申请活动列表
	 **/
	public String listMyActivity(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			String value = actionName;
			String usernum = userInfo.getLogincode();
			applyActivityBiz.findPageApply(page,value,usernum);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 可以参加的活动
	 **/
	public String couldJoinActivity(){
		try {
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String toDayDate = sf.format(d);
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String value = actionName;
			applystyle = "已通过";
			applyActivityBiz.findPageActivityPassed(page, value, applystyle,toDayDate,activitygenre);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 个人参与活动列表
	 **/
	public String myJoinedActivity() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = { "activityname", "activitygenre" };
			String[] value = { this.actionName, this.activitygenre };
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			String usernum = userInfo.getLogincode();
			applyActivityBiz.findMyAttendActivity(page, property, value,usernum);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 取消参加活动
	 **/
	public String cancelAttend(){
		try {
			if(state.equals("待参加")&&checkkey.equals("1")){
				state = "已取消";
				Attend attend = applyActivityBiz.findAttendByIds(usernum, activityid);
				if(attend!=null && !attend.equals("")){
					Integer stdid = this.getUserDTO().getStudent().getId();
					int flage = applyActivityBiz.deleteStateById(state,attend.getId(),stdid);
					if(flage!=0){
						applyActivityBiz.updateStateActivityById(state,activityid); 
						this.outString("{success:true,message:'已取消报名!'}");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 活动报名
	 **/
	public String attendActivity(){
		Attend attendIsOrNot = new Attend();
	    attendIsOrNot = applyActivityBiz.findIsOrNotAttend(usernum,activityid);
		if(attendIsOrNot!=null){
			this.outString("{success:true,message:'你已在参与中...'}");
		}else{
			Attend attend = new Attend();
			attend.setId(Tools.getUid());
			attend.setUsernum(usernum);
			attend.setUsername(username);
			//attend.setMajor(major);
			attend.setClasses(classes);
			attend.setCounsellor(counsellor);
			attend.setDorm(dorm);
			attend.setAttendstudentphonenum(attendstudentphonenum);
			attend.setActivityid(activityid);
			attend.setActivityname(activityname);
			attend.setActivitygenre(activitygenre);
			attend.setActivitytime(activitytime);
			attend.setInschoolterm(inschoolterm);
			attend.setActivityplace(activityplace);
			attend.setScore(score);
			attend.setState("待参加");
			attend.setGotscore(0);
			this.getUserDTO().getStudent().getAttend().add(attend);
			int flag = applyActivityBiz.saveAttendActivity(attend);
			studentBiz.saveOrUpdateStudent(this.getUserDTO().getStudent());
			if(flag==1){
				@SuppressWarnings("unused")
				int cflage = applyActivityBiz.updateStateActivityById(null,activityid);
			}
			this.outString("{success:true,message:'报名成功!'}");
		}
		return null;
	}
	
	/**
	 * 页面传值，活动id
	 * @return
	 **/
	public String activityGetId(){
		getSession().setAttribute("activityid", activityid);
		return null;
	}
	
	/**
	 * 活动管理-活动详情
	 **/
	public String activityDetail(){
		try {
			activityid = (String) getSession().getAttribute("activityid");
			Activity activity = new Activity();
			activity = applyActivityBiz.findActivityInfoById(activityid);
			this.outObjectString(activity,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 报名列表
	 **/
	public String activityManageAttendList(){
		try {
			activityid = (String) getSession().getAttribute("activityid");
			List<Attend> attendlist = null;
			attendlist = applyActivityBiz.findAttendById(activityid);
			this.outListString(attendlist);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 活动审批列表
	 **/
	public String findPageApply() {
		try {
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			String ssyq = userInfo.getSsyq();
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = { "activityname" , "activitygenre" ,"applystyle"};
			Activity activity = new Activity();
			activity.setApplystyle(applystyle);
			String[] value = { this.actionName , this.activitygenre, this.applystyle};
			applyActivityBiz.findPageApply(page, property, value,ssyq);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 活动审批
	 **/
	public String activityCheck(){
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		checktime = sf.format(d);
		try {
			if(checkkey.equals("1")){
				applystyle="已通过";
			}else{
				applystyle="不通过";
			}
			String clazz = "Activity";
			String[] setproperty = {"applystyle","checktime"};
			String[] updatevalues = {applystyle,checktime};
			String byid = "activityid";
			applyActivityBiz.updateClassPropertiesById(clazz, setproperty, updatevalues, byid, activityid);
			//审核通过，把该活动加入到短信监听池中
			if(applystyle=="已通过"){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Activity ac = applyActivityBiz.findActivityInfoById(activityid);
				//活动时间
				String acTime = ac.getActivitytime();
				//时长
				String acTimeLong = ac.getTimeofduration();
				//转数值类型
				String subTimeLong = acTimeLong.substring(0, acTimeLong.indexOf("小")).trim();
				double dnum = Double.parseDouble(subTimeLong);
				//转日期类型
				try {
					Date acDateTime = sdf.parse(acTime);
					//活动结束时间
					long times = (long) (acDateTime.getTime()+dnum*60*60*1000);
					//活动结束3天后时间
					long endTDLTimes = times+3*24*60*60*1000;
					Date dt = new Date(endTDLTimes);
					String date = sdf.format(dt);
					//获取短信内容
					SmsBiz smsBiz = ServiceHelper.getSmsBiz();
					Sms sms = smsBiz.findById(2);
					String content = sms.getContent().replace("$(HDMC)",ac.getActivityname());
					ActivitySmsQuartz.addJob(date, ac.getActivityid(), ac.getPhonenum(), content);
				}catch (ParseException e) {
					e.printStackTrace();
					this.outError();
				}
			}
			this.outString("{success:true,message:'审核成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 提交新闻
	 **/
	public String submitNews(){
		try {
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String sfd = sf.format(d);
			activityid = (String) getSession().getAttribute("activityid");
			News news = new News();
			news.setNewstitle(newstitle);
			if(website.equals("http://www.abc.mnp.com(网址输入格式)")){
				website = null;
			}else{
				news.setWebsite(website);
			}
			news.setNewsdate(newsdate);
			writer = getUserDTO().getLogincode();
			news.setWriter(writer);
			news.setProperty(property);
			news.setContent(content);
			news.setActivityid(activityid);
			if(newsid!=null && !newsid.equals("")){
				news.setNewsid(newsid);
				news.setNewscheckstyle("已发布");
				if(totop!=null && !totop.equals("") && totop.equals("on")){
					news.setTotop("1");
				}else{
					news.setTotop("100");
				}
				if(highlight!=null && !highlight.equals("") && highlight.equals("on")){
					news.setHighlight("yes");
				}else{
					news.setHighlight("no");
				}
				applyActivityBiz.updateNews(news);
				this.outString("{success:true,message:'发布成功!'}");
			}else{
				news.setNewsid(Tools.getUid());
				news.setNewscheckstyle("待审核");
				news.setTotop("100");
				news.setHighlight("no");
				int flag = applyActivityBiz.saveClazz(news);
				if(flag==1){
					uploadnewstime = sfd;
					String clazz = "Activity";
					String[] setproperty = {"newscheckstyle","uploadnewstime"};
					String[] updatevalues = {"yes",uploadnewstime};
					String byid = "activityid";
					applyActivityBiz.updateClassPropertiesById(clazz, setproperty, updatevalues, byid, activityid);
				}
				this.outString("{success:true,message:'发布成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 获取新闻稿
	 **/
	public String newsDeatail(){
		try {
			News news = new News();
			String clazz = "News";
			String findproperty = "activityid";
			String idValue = activityid;
			news = applyActivityBiz.findClazzById(clazz,findproperty,idValue);
			if(news.getNewsid()!=null && !news.getNewsid().equals("")){
				UserDTO user = getUserDTO();
				HeadMaster headMaster = user.getHeadMaster();
				Instructor instructor = user.getInstructor();
				LeaderShip leaderShip = user.getLeaderShip();
				NewFriends newFriends = user.getNewFriends();
				Expert expert = user.getExpert();
				String name ="";
				
				if(null!=headMaster){
					name = headMaster.getXm();
				}
				if(null!=leaderShip){
					name = leaderShip.getXm();
				}
				if(null!=newFriends){
					name = newFriends.getXm();
				}
				if(null!=expert){
					name = expert.getXm();
				}
				if(null!=instructor){
					name = instructor.getXm();
				}
				if(user.getLogincode().contains("admin")) {
					name = user.getLogincode();
				}
				if(StringUtils.isNotBlank(name)) {
					news.setWriter(name);
				}
				
				this.outObjectString(news,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 *活动新闻、公示审核列表
	 **/
	public String publicitylist(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"activityname","activitygenre","publicitycheckstyle"};
			String[] values = {this.activityname,this.activitygenre,this.publicitycheckstyle};
			String findstyle = "publicitycheckstyle";
			applyActivityBiz.findPageActivity(page,property,values,findstyle);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 新闻审核，展示活动详情
	 **/
	public String newsDetail(){
		try {
			News news = new News();
			String clazz = "News";
			String findproperty = "activityid";
			String idValue = activityid;
			news = applyActivityBiz.findClazzById(clazz, findproperty, idValue);
			this.outObjectString(news,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 导出名单、报名细则文件下载(exportExcel.do)
	 * @return
	 */
	public InputStream getInStream() {
		
		try {
			if(fileDownKey.equals("AttendFileDeatil")){
				filename = "求是学院记实考评工作实施办法.docx";
			}else{
				Activity activity = applyActivityBiz.findActivityInfoById(activityid);
				String fileName = activity.getActivityname() + new Date().getTime();
				
				List<Attend> attendList = null;
				attendList = applyActivityBiz.findAttendById(activityid);
		        String[] titles = new String[]{"姓名", "学号", "签到"};
		        String[] fieldNames = new String[]{"username", "usernum", "state"};
		        String path = ServletActionContext.getServletContext().getRealPath("file")+ File.separator;
		        File exportFile = new File(path+fileName+".xls");
		        ExcelHelper exh = JxlExcelHelper.getInstance(exportFile);
		        exh.writeExcel(Attend.class, attendList, fieldNames, titles);
		        filename = fileName + ".xls";
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
	
	/**
	 * 导入名单(Applyactivity_importExcel.do)
	 * @return
	 */
	public String importExcel(){
		try {
			String path = ServletActionContext.getServletContext().getRealPath("upload")
			+ File.separator + this.uploadFileName;
			File importFile = new File(path);
			Tools.writeFile(this.upload, importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[]{"username", "usernum", "state"};
			List<Attend> importAttendList = exh.readExcel(Attend.class, fieldNames, true);
			for(Attend a :importAttendList){
				if(a.getState().equals("1")){
					state = "已参加";
					String clazz = "Attend";
					String setproperty = "state";
					String[] byid = {"usernum","activityid"};
					String[] valuesId = {a.getUsernum(),this.activityid};
					int upflag = applyActivityBiz.updateClassById(clazz, setproperty, state, byid, valuesId);
					if(upflag>0){
						setproperty = "gotscore";
						gotscore = Integer.parseInt(sscore);
						int UpGotscoreFlag = applyActivityBiz.updateScoreById(clazz, setproperty, gotscore, byid, valuesId);
						//获取参加活动人员的手机号
						Attend attend = applyActivityBiz.findAttendByIds(a.getUsernum(),this.activityid);
						String mobile = attend.getAttendstudentphonenum();
						SmsBiz smsBiz = ServiceHelper.getSmsBiz();
						Sms sms = smsBiz.findById(1);
						String content = sms.getContent().replace("$(HDMC)",attend.getActivityname());
						content = sms.getContent().replace("$(HDSJ)",attend.getActivitytime());
						content = sms.getContent().replace("$(HDDD)",attend.getActivityplace());
						SmsUtil.sendSms(mobile, content);
						/*if (UpGotscoreFlag > 0) {
							setproperty = "gross";
							String value = "SUM(gotscore)";
							String setbyid="usernum";
							String updatevaluesId = a.getUsernum();
							int sum = applyActivityBiz.countGross(clazz,value,setbyid,updatevaluesId);
							applyActivityBiz.updateGrossById(clazz,	setproperty, sum, setbyid, updatevaluesId);
						}*/
					}
				}else{
					state = "未参加";
					String clazz = "Attend";
					String setproperty = "state";
					String[] byid = {"usernum","activityid"};
					String[] valuesId = {a.getUsernum(),this.activityid};
					int upflag = applyActivityBiz.updateClassById(clazz, setproperty, state, byid, valuesId);
					if(upflag>0){
						setproperty = "gotscore";
						gotscore = -1;
						int UpGotscoreFlag = applyActivityBiz.updateScoreById(clazz, setproperty, gotscore, byid, valuesId);
						/*if (UpGotscoreFlag > 0) {
							setproperty = "gross";
							String value = "SUM(gotscore)";
							String setbyid="usernum";
							String updatevaluesId = a.getUsernum();
							int sum = applyActivityBiz.countGross(clazz,value,setbyid,updatevaluesId);
							applyActivityBiz.updateGrossById(clazz,	setproperty, sum, setbyid, updatevaluesId);
						}*/
					}
				}
			}
			this.outString("{success:true,message:'导入成功!'}");
			//文件导入成功后从短信监听池中移除监听
			ActivitySmsQuartz.removeJob(this.activityid);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 纪实考评(Applyactivity_recordcheck.do)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String recordcheck(){
		try {
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			HeadMaster master = userInfo.getHeadMaster();
			Instructor instructor = userInfo.getInstructor();
			int tid;
			if(master!=null){
				tid = master.getId();
			}else{
				tid = instructor.getId();
			}
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"xm","xh","classes"};
			String[] values = {this.username, this.usernum, this.classes};
			applyActivityBiz.findPageAttendByProperty(page,property,values,master,tid,this.inschoolterm);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 纪实考评查看详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String recordcheckDetail(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			Student stu = applyActivityBiz.findStudentById(this.studentid);
			if(stu!=null){
				Set<Attend> attends =  stu.getAttend();
				Attend attend = null;
				if(null!=this.inschoolterm&&!"".equals(this.inschoolterm)){
					if(null==attends){
						attends = new HashSet<Attend>();
					}else{
						Iterator<Attend> it = attends.iterator();  
						while (it.hasNext()) {  
							attend = it.next();  
						    if (!this.inschoolterm.equals(attend.getInschoolterm())) {  
						        it.remove();  
						    }  
						}
					}
				}
				List list = new ArrayList(attends);
				page.setRoot(list);
				page.setTotal(attends.size());
				this.outPageString(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 公示
	 */
	public String publicity(){
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		
		String[] propety = {"activityname","activitygenre"};
		String[] values = {this.activityname,this.activitygenre};
		
		applyActivityBiz.findPagePublicity(page,propety,values);
		
		this.outPageString(page);
		return null;
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
	 * 公示参加活动信息
	 */
	public String attendActivityPublicityDetail(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			applyActivityBiz.findAttendActivityInfoById(page,activityid);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 所在学期下拉菜单
	 */
	public String findSzxqComb(){
		 try {
				this.outListString(applyActivityBiz.findSzxqComb());
			   } catch (Exception e) {
				 e.printStackTrace();
				 this.outError();
			   }
		return null;
	}
	
	/**
	 * 保存活动 活动状态为已通过
	 **/
	public String saveActivity(){
		Activity activity = new Activity(activityname, applyuser, 
				studentnum, studentphonenum, organizename, teacher, 
				phonenum, activitygenre, activitytime, 
				inschoolterm, faceobj, capacity, activityplace, 
				timeofduration, activitycontent, signupendtime, score);
		activity.setApplystyle("已通过");
		if(activityid==null || activityid.equals("")){						//新增
			activityid = Tools.getUid();
			activity.setActivityid(activityid);
			activity.setNewscheckstyle("no");
			applyActivityBiz.saveApplyActivity(activity);
			UserDTO user = getUserDTO();
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			HeadMaster headMaster = user.getHeadMaster();
			Instructor instructor = user.getInstructor();
			LeaderShip leaderShip = user.getLeaderShip();
			NewFriends newFriends = user.getNewFriends();
			Expert expert = user.getExpert();
			String ssyq ="";
			
			if(null!=headMaster){
				ssyq = headMaster.getSsyq();
			}
			if(null!=leaderShip){
				ssyq = leaderShip.getSsyq();
			}
			if(null!=newFriends){
				ssyq = newFriends.getSsyq();
			}
			if(null!=expert){
				ssyq = expert.getSsyq();
			}
			if(null!=instructor){
				ssyq = instructor.getSsyq();
			}
			if(ssyq != null && !ssyq.equals("")) {
				if(ssyq.contains("蓝田")) {
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至蓝田六舍209室，蒋老师,88206718'}");
				} else if(ssyq.contains("丹青")) {
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至青溪一舍131室，王老师，22806841'}");
				} else if(ssyq.contains("云峰")){
					this.outString("{success:true,message:'申报成功！请将纸质版报名表递交至碧峰连廊131室，田老师，88206505'}");
				} else {
					this.outString("{success:true,message:'申报成功!  请将纸质版申报表交至......'}");
				}
			} else {
				this.outString("{success:true,message:'申报成功!  请将纸质版申报表交至......'}");
			}
			
				
		}else{												
			activity.setActivityid(activityid);
			if(checkkey.equals("1")){										//取消申请
				String clazz = "Activity";
				String setproperty = "applystyle";
				String byid = "activityid";
				applystyle="已取消";
				applyActivityBiz.updateClassById(clazz,setproperty,applystyle,byid,activityid);
				this.outString("{success:true,message:'取消成功!'}");
			}else{															//更新
					applyActivityBiz.updateActivityById(activity);
					this.outString("{success:true,message:'修改成功!'}");
			}
		}
		return null;
	}
	
	/**
	 * 删除活动
	 * @return
	 */
	public String deleteActivity() {
		try {
			applyActivityBiz.deleteActivity(activityid);;
			this.outString("{success:true,message:'删除成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outString("{success:true,message:'删除失败!'}");
		}
		return null;
	}
}
