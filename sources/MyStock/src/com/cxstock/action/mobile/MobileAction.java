package com.cxstock.action.mobile;

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
import com.cxstock.biz.expertbespeak.dto.StudentbespeakDTO;
import com.cxstock.biz.leaveinfo.LeaveInfoBiz;
import com.cxstock.biz.onlineqa.OnlineQABiz;
import com.cxstock.biz.onlineqajudge.OnlineQAJudgeBiz;
import com.cxstock.biz.pbfx.offlineFd.OfflineFdBiz;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.secondbookstore.SecondBookStoreBiz;
import com.cxstock.biz.secondbookstorejudge.SecondBookStoreJudgeBiz;
import com.cxstock.biz.siteinfo.SiteInfoBiz;
import com.cxstock.biz.siteinfo.dto.SiteInfoLogDTO;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.biz.student.StudentBiz;
import com.cxstock.pojo.Activity;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.IfOpeTimeLog;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.LeaveInfo;
import com.cxstock.pojo.LeaveInfoLog;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.News;
import com.cxstock.pojo.OfflineFd;
import com.cxstock.pojo.OfflineFdLog;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.pojo.SecondBookStore;
import com.cxstock.pojo.SiteInfo;
import com.cxstock.pojo.SiteInfoDTO;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Studentbespeak;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.ServiceHelper;
import com.cxstock.utils.system.SmsUtil;
import com.cxstock.utils.system.Tools;
import com.cxstock.utils.system.UuidUtil;

/**
 * 学习资料control层
 * @author root
 */
@SuppressWarnings("serial")
public class MobileAction extends BaseAction{
	
	private OnlineQABiz  onlineQABiz;
	
	private OnlineQAJudgeBiz onlineQAJudgeBiz;
	
	private SecondBookStoreBiz  secondBookStoreBiz;
	
	private SecondBookStoreJudgeBiz secondBookStoreJudgeBiz;
	
	private ApplyActivityBiz applyActivityBiz;
	
	private ExpertBiz expertBiz;
	
	private ExpertBespeakBiz expertBespeakBiz;
	
	private StudentBiz studentBiz;
	
	private UserBiz userBiz;
	
	//登录
	private String logincode;
	private String password;
	
	//场地
	private SiteInfoBiz siteInfoBiz;
	private String proposer;
	private String siteid;
	private String relationtel;
	private String counsellor;
	private Date activitydate;
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
   // 按时间查询
	private String startdate;
	private String enddate;
	
	private String ifApproval;
    //申请记录表
    private String logId;
    private String xh;
    private Integer usetime;
    private Date applyTime;
    //不对外开放的id
	private String  opeId;
	
	
	
	//请假
	private LeaveInfoBiz leaveInfoBiz;
	private  String studentnum;
	private  String  leavereason;
	private  Date  leavetime;
	private  Date  backsctime;
	private  String  parentsinfo;
	private  String parentstel;
	private Integer totalnum;
	
	//学习资料
	private DatumInfoBiz datumInfoBiz;
	private String datumname;
	private String shareman ; 
	private Integer sharegrade;
	
	//线下辅学
	private  OfflineFdBiz  offlineFdBiz;
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
    private Integer plnumber;
    private  String  bz;
    private  Date topTime;
    private  Integer  createrid;
    private  String  creatername;
    
    
	 //记录内容
    private  String bmId;
    private  String bmName;
    private  String plnr;
    private  String bmtime;
    private  String tbTag;
    private  String bmTag;
    private  String xmmc;
	
	
	
	//活动申报
	private String checkkey="0";
	private String activityid;
	private String activityname;
	private String applyuser;
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
	private int score;
	private String applystyle;
	//活动报名
	private ActivityDTO aDto;
	private String usernum;
	private String username;
	private String classes;
	private String dorm;
	private String attendstudentphonenum;
	
	//专家预约
	private String yx;
	private String bm;
	private String experttype;
	private String ename;
	private Integer stid;
	private String studentemail;
	private String bespeaktime;
	private String bespeakplace;
	private String applygenre;
	private String detailinfo;
	private String uploadbespeaktime;
	private String bespeakstate;
	private String url;
	
	//关键字搜索
	private String key;
	private String serchKey;
	
	private String storeType;
	private String aid;
	private String newsid;
	private Integer id;
	
	
	
	
	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	//短信接口
	private SmsBiz smsBiz;

	public Integer getStid() {
		return stid;
	}

	public void setStid(Integer stid) {
		this.stid = stid;
	}

	public String getStudentemail() {
		return studentemail;
	}

	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}

	public String getBespeaktime() {
		return bespeaktime;
	}

	public void setBespeaktime(String bespeaktime) {
		this.bespeaktime = bespeaktime;
	}

	public String getBespeakplace() {
		return bespeakplace;
	}

	public void setBespeakplace(String bespeakplace) {
		this.bespeakplace = bespeakplace;
	}

	public String getApplygenre() {
		return applygenre;
	}

	public void setApplygenre(String applygenre) {
		this.applygenre = applygenre;
	}

	public String getDetailinfo() {
		return detailinfo;
	}

	public void setDetailinfo(String detailinfo) {
		this.detailinfo = detailinfo;
	}

	public String getUploadbespeaktime() {
		return uploadbespeaktime;
	}

	public void setUploadbespeaktime(String uploadbespeaktime) {
		this.uploadbespeaktime = uploadbespeaktime;
	}

	public String getBespeakstate() {
		return bespeakstate;
	}

	public void setBespeakstate(String bespeakstate) {
		this.bespeakstate = bespeakstate;
	}

	public StudentBiz getStudentBiz() {
		return studentBiz;
	}

	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
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

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getAttendstudentphonenum() {
		return attendstudentphonenum;
	}

	public void setAttendstudentphonenum(String attendstudentphonenum) {
		this.attendstudentphonenum = attendstudentphonenum;
	}

	public String getCheckkey() {
		return checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

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

	public String getApplystyle() {
		return applystyle;
	}

	public void setApplystyle(String applystyle) {
		this.applystyle = applystyle;
	}

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
   
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getRelationtel() {
		return relationtel;
	}

	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
	}

	public Date getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(Date activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
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

	public String getIfschool() {
		return ifschool;
	}

	public void setIfschool(String ifschool) {
		this.ifschool = ifschool;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getDutystate() {
		return dutystate;
	}

	public void setDutystate(String dutystate) {
		this.dutystate = dutystate;
	}

	public String getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getOpeId() {
		return opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
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
    
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LeaveInfoBiz getLeaveInfoBiz() {
		return leaveInfoBiz;
	}

	public void setLeaveInfoBiz(LeaveInfoBiz leaveInfoBiz) {
		this.leaveInfoBiz = leaveInfoBiz;
	}

	public String getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
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

	public String getParentstel() {
		return parentstel;
	}

	public void setParentstel(String parentstel) {
		this.parentstel = parentstel;
	}
     
	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
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

	
	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
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
	
	/** 登录验证 */
	public String login() {
		try {
			String code = logincode.trim().toLowerCase();
			String pass = password.trim().toLowerCase();
			UserDTO userInfo = userBiz.login(code, pass);
			// System.out.println(userInfo.getRolename());
			if (userInfo != null) {
				if (code.startsWith("3")) {
					Student student = userBiz.getStudent(userInfo
							.getLogincode());
					userInfo.setStudent(student);
					userInfo.setPhone(student.getPhone());
				}
				this.getSession().setAttribute(Constants.USERINFO, userInfo);
				return "gotophoneindex";
			} else {
				this.getSession().setAttribute("error", "用户名或密码错误");
				return "loginerror";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getSession().setAttribute("error", "连接失败");
			return "loginerror";
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
			
			status = "已发布";
			if(null != this.getUserDTO().getLogincode()){
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
     * 线下辅导详情
     * @return
     */
    public String findSingOfflineFd(){
    	try {
			//根据id查询数据
			OfflineFd fds  = offlineFdBiz.findSingOfflineFd(this.xmid);
			if(null != fds){
				this.outObjectString(fds, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
    	return null;
    }
    
    
    
    
    
    /**
     * 手机端场地申请记录
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
	 * 活动申报
	 * @return
	 */
	public String applyActivity(){
		Student std = this.getUserDTO().getStudent();
		if(std!=null){
			applyuser = std.getXm();
			studentnum = std.getXh();
			studentphonenum = std.getPhone();
			activitytime = activitytime+":00";
			signupendtime = signupendtime + ":00";
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
				this.outString("{success:true,message:'保存成功!  请将纸质版申报表交至......'}");
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
		}else{
			this.outString("{success:true,message:'无法获取用户信息！'}");
		}
		return null;
	}
	
	/**
	 * 申报查询
	 **/
	public String listMyActivity(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			String value = activityname;
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
	 * 活动报名，展示可报名的活动
	 **/
	public String couldJoinActivity(){
		try {
			Date d = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String toDayDate = sf.format(d);
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String value = activityname;
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
	 * 活动报名
	 */
	public String attendActivity(){
		Student std = this.getUserDTO().getStudent();
		if(std!=null){
			usernum = std.getXh();
			username = std.getXm();
			classes = std.getClasses().getBjdm();
			counsellor = std.getInstructor() != null ? std.getInstructor().getXm() : "";
			dorm = std.getQsmc();
			attendstudentphonenum = std.getPhone();
			Attend attendIsOrNot = new Attend();
		    attendIsOrNot = applyActivityBiz.findIsOrNotAttend(usernum,activityid);
			if(attendIsOrNot!=null){
				this.outString("{success:true,message:'你已在参与中...'}");
			}else{
				String id = Tools.getUid();
				Attend attend = new Attend(id, usernum, username, classes, counsellor, dorm, attendstudentphonenum, 
						activityid, activityname, activitygenre, activitytime, inschoolterm, activityplace, score);
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
		}else{
			this.outString("{success:true,message:'无法获取用户信息！'}");
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
	 * 专家预约列表
	 */
	public String expertBespeakList(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"ssyq","unit","expertType","xm"};
			String[] values = {this.yx,this.bm,this.experttype,this.ename};
			expertBespeakBiz.findPageStartExpertInfoById(page,property,values);
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
	 * 学生随机or指定预约专家
	 */
	public String bespeakExpert(){
		try {
			Integer stdid = this.getUserDTO().getStudent().getId();
			Expert ex = new Expert(id);
			Student st = new Student(stdid);
			Studentbespeak studentBespeak = new Studentbespeak(stid, studentemail, bespeaktime,
					bespeakplace, applygenre, detailinfo, uploadbespeaktime, bespeakstate, st,ex);
			boolean bn = expertBespeakBiz.saveOrUpdateStudentbespeak(studentBespeak);
			if(bn){
				if(stid==null){
					//指定专家预约，预约成功后给专家发送短信
					if(id!=null && !id.equals("")){
						Expert expert = expertBespeakBiz.findExpertById(id);
						Student std = expertBespeakBiz.findStudentById(stdid);
						String mobile = expert.getPhone();
						SmsBiz smsBiz = ServiceHelper.getSmsBiz();
						Sms sms = smsBiz.findById(9);
						String content = sms.getContent().replace("$(XM)",std.getXm());
						SmsUtil.sendSms(mobile, content);
					}
					this.outString("{success:true,message:'预约成功!'}");
				}else{
					this.outString("{success:true,message:'成功!'}");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
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
	 * 部门下拉菜单
	 */
	public String findUnitComb(){
		 try {
				this.outListString(expertBespeakBiz.findUnitComb());
			   } catch (Exception e) {
				 e.printStackTrace();
				 this.outError();
			   }
		return null;
	}
	
	
	/**
	 * 预约查询
	 */
	public String myBespeak(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			//Student st = new Student(this.getUserDTO().getStudent().getId());
			expertBespeakBiz.findPageStudentBespeakById(page,this.getUserDTO().getStudent().getId());
			//String[] s = {"student"};
			//this.outPageString(page,s);
			String[] ingore = {"expert","student"};
			this.outPageString(page, ingore);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 取消预约
	 */
	public String cancel(){
		try {
			expertBespeakBiz.updateBespeakState(stid);
			this.outString("{success:true,message:'修改成功!'}");
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
	
	

	
	/**
	 * 请假申请数据保存
	 */
	public String saveLeaveInfo(){
		 try {
			 
			//获取登录用户的信息
			Student st = this.getUserDTO().getStudent();
			 //明细数据新增
			LeaveInfo leaveInfo = new LeaveInfo();
				leaveInfo.setId(this.id);
				leaveInfo.setLeavereason(this.leavereason);
				leaveInfo.setStudentname(st.getXm());
				leaveInfo.setStudentnum(st.getXh());
				leaveInfo.setLeavetime(this.leavetime);
				leaveInfo.setBacksctime(this.backsctime);
				leaveInfo.setClassth(st.getHeadmaster().getXm());
				leaveInfo.setBedroom(st.getQsmc());
				leaveInfo.setRelationtel(st.getPhone());
				leaveInfo.setCounsellor(st.getInstructor().getXm() != null ? st.getInstructor().getXm() : "");
				leaveInfo.setClasscode(st.getClasses().getBjdm());
				leaveInfo.setParentsinfo(this.parentsinfo);
				leaveInfo.setRulesstate("on");
				leaveInfo.setParentstel(this.parentstel);
				leaveInfo.setDaysum(0);
				leaveInfo.setTotalnum(this.totalnum);
				leaveInfo.setTutorstatus("待审核");
				leaveInfo.setSchoolstatus("无");
				if(this.id == null){
				 //查询发送内容
				  Sms  sms  = smsBiz.findById(5);
				  String content = sms.getContent().replace("$(XM)", st.getInstructor().getXm() != null ? st.getInstructor().getXm() : "");
				
				   //调用短息接口的方法
			     SmsUtil.sendSms(st.getInstructor().getPhone() != null ? st.getInstructor().getPhone() : "", content);
				}
				
				//获取数据判断是否是第一次新增记录表数据
				LeaveInfoLog lg = leaveInfoBiz.findSingleLeaveInfoLog(st.getXh());
				if(null != lg){
					if(lg.getStudentnum().equals(st.getXh())){
						//记录表数据新增
						LeaveInfoLog  log  =  new LeaveInfoLog();
						log.setStudentname(st.getXm());
						log.setStudentnum(st.getXh());
						log.setClasscode(st.getClasses().getBjdm());
						log.setClassth(st.getHeadmaster().getXm());
						log.setBedroom(st.getQsmc());
						log.setRelationtel(st.getPhone());
						log.setCounsellor(st.getInstructor().getXm() != null ? st.getInstructor().getXm() : "");
						
						log.setOperatTime(lg.getOperatTime());
						log.setTotalnum(lg.getTotalnum());
						//记录表数据
						leaveInfoBiz.saveLeaveInfoLog(log);
						
					}
				}else{
					//记录表数据新增
					LeaveInfoLog  log  =  new LeaveInfoLog();
					log.setStudentname(st.getXm());
					log.setStudentnum(st.getXh());
					log.setClasscode(st.getClasses().getBjdm());
					log.setClassth(st.getHeadmaster().getXm());
					log.setBedroom(st.getQsmc());
					log.setRelationtel(st.getPhone());
					log.setCounsellor(st.getInstructor().getXm() != null ? st.getInstructor().getXm() : "");
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
	 * 请假申请列表
	 */
	public  String  findListLeaveInfo(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			//获取学生的登录号（学号）
			leaveInfoBiz.findLeaveInfo(page,this.getUserDTO().getLogincode(), "leavetime");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	//取消请假申请
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
	 * 场地申请(搜索) 手机端
	 */
	public String  findListSite(){
		try {
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
			List  list = siteInfoBiz.findListSite(propertyName, value, "createtime", "createtime", startdate, enddate);
			if(null != list){
				this.outList1(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
	}
	
	
	
	/**
	 * 我申请的场地
	 */
	public  String   findPageSiteInfoLog(){
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
               
             xh = this.getUserDTO().getLogincode();
			 String[] propertyName = { "xh", "sitetype",
						"park"};
			 String[] value = { this.xh, this.sitetype, 
						this.park};
			
			siteInfoBiz.findPageSiteInfoLog1(page, propertyName, value, "applyTime", "applyTime",  startdate, enddate);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return  null;
	}
	
	
	
	/**
	 * 场地申请详情
	 */
	public  String findSingLog(){
		try {
			SiteInfoLog log = siteInfoBiz.findSingLog(this.logId);
			if(null != log){
				SiteInfoLogDTO dto =  new SiteInfoLogDTO();
			    dto.setLogId(log.getLogId());
			    dto.setProposer(log.getProposer());
			    dto.setXh(log.getXh());
			    dto.setRelationtel(log.getRelationtel());
			    dto.setCounsellor(log.getCounsellor());
			    dto.setActivityname(log.getActivityname());
			    dto.setActivitycontent(log.getActivitycontent());
			    dto.setActivitytype(log.getActivitytype());
			    dto.setActivitydate(DateTime.toString(log.getActivitydate()));
			    dto.setActivitytime(log.getActivitytime());
			    dto.setBeorganize(log.getBeorganize());
			    dto.setGuideth(log.getGuideth());
			    dto.setSitemodle(log.getSitemodle());
			    dto.setSitename(log.getSitename());
			    dto.setStatus(log.getStatus());
			    dto.setIfschool(log.getIfschool());
				this.outObjectString(dto, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
	}
	
	/**
	 * 场地申请取消
	 */
	public  String updateSiteInfoLog(){
		  try {
			 
			  siteInfoBiz.updateSiteInfoLog("已取消", this.logId);
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
	 * 跳转场地申请
	 */
	public  String  findSing(){
		try {
			SiteInfo  st = siteInfoBiz.findSing(this.id);
			if(null != st){
				this.outObjectString(st, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
	}
	
}
