package com.cxstock.action.expertbespeak;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.expert.ExpertBiz;
import com.cxstock.biz.expert.dto.ExpertDTO;
import com.cxstock.biz.expertbespeak.ExpertBespeakBiz;
import com.cxstock.biz.expertbespeak.dto.StartExpertInfoDTO;
import com.cxstock.biz.expertbespeak.dto.StudentbespeakDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.StartExpertInfo;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Studentbespeak;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.ServiceHelper;
import com.cxstock.utils.system.SmsUtil;
import com.cxstock.utils.system.Tools;

public class ExpertbespeakAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	//专家id
	private Integer id;
	//学生id
	private Integer stdid;
	//预约id
	private Integer sbid;
	//预约id(管理员指定专家)
	private Integer stid;
	//空余时间
	private String freetime;
	//办公地址
	private String workaddress;
	//邮箱
	private String studentemail;
	//预约时间
	private String bespeaktime;
	//预约地点
	private String bespeakplace;
	//是否分配老师
	private String haveornotexpert;
	//预约类别
	private String applygenre;
	//详细信息
	private String detailinfo;
	//预约提交时间
	private String uploadbespeaktime;
	//预约状态
	private String bespeakstate;
	//发布专家信息id
	private Integer seifid;
	//预约审核值
	private String checkkey="0";
	//谈话内容
	private String talkcontent;
	//老师反馈状态
	private String expertfeedbackstate;
	//院系
	private String yx;
	//部门
	private String bm;
	//专家类型
	private String experttype;
	//专家名称
	private String ename;
	//提示短信key
	private String msgKey;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	private String photo;
	private String unit;
	private String email;
	private String expertType;
	private String introduce;
	//private String[] idArray;
	private String stidArray;
	//文件上传
	private File upload;
	private String uploadFileName;
	
	//方法接口
	private ExpertBespeakBiz expertBespeakBiz;
	private SmsBiz smsBiz;
	private UserBiz userBiz;
	private ExpertBiz expertBiz;
	/*get set方法*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStdid() {
		return stdid;
	}

	public void setStdid(Integer stdid) {
		this.stdid = stdid;
	}
	
	public Integer getSbid() {
		return sbid;
	}

	public void setSbid(Integer sbid) {
		this.sbid = sbid;
	}
	
	public Integer getStid() {
		return stid;
	}

	public void setStid(Integer stid) {
		this.stid = stid;
	}

	public String getFreetime() {
		return freetime;
	}

	public void setFreetime(String freetime) {
		this.freetime = freetime;
	}

	public String getWorkaddress() {
		return workaddress;
	}

	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
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
	
	public String getHaveornotexpert() {
		return haveornotexpert;
	}

	public void setHaveornotexpert(String haveornotexpert) {
		this.haveornotexpert = haveornotexpert;
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
	
	public String getStidArray() {
		return stidArray;
	}

	public void setStidArray(String stidArray) {
		this.stidArray = stidArray;
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
	
	public String getTalkcontent() {
		return talkcontent;
	}

	public void setTalkcontent(String talkcontent) {
		this.talkcontent = talkcontent;
	}
	
	public String getExpertfeedbackstate() {
		return expertfeedbackstate;
	}

	public void setExpertfeedbackstate(String expertfeedbackstate) {
		this.expertfeedbackstate = expertfeedbackstate;
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
	
	
	
	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getCheckkey() {
		return checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Integer getSeifid() {
		return seifid;
	}

	public void setSeifid(Integer seifid) {
		this.seifid = seifid;
	}

	public ExpertBespeakBiz getExpertBespeakBiz() {
		return expertBespeakBiz;
	}

	public void setExpertBespeakBiz(ExpertBespeakBiz expertBespeakBiz) {
		this.expertBespeakBiz = expertBespeakBiz;
	}

	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	public ExpertBiz getExpertBiz() {
		return expertBiz;
	}

	public void setExpertBiz(ExpertBiz expertBiz) {
		this.expertBiz = expertBiz;
	}

	/**
	 * 获取登录用户信息
	 * @return
	 */
	public String getCurentUserInfo(){
		//辅导员
		Instructor inst = this.getUserDTO().getInstructor();
		//班主任
		HeadMaster hm = this.getUserDTO().getHeadMaster();
		//新生之友
		NewFriends nf = this.getUserDTO().getNewFriends();
		//学生
		Student st = this.getUserDTO().getStudent();
		if(inst!=null){
			JsonConfig	jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[]{ "iclass"}); 
			this.outObjectString(inst, jsonConfig);
		}
		if(hm!=null){
			JsonConfig	jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[]{ "hclass"}); 
			this.outObjectString(hm, jsonConfig);
		}
          if(nf!=null){
			this.outObjectString(nf, null);
		}
         if(st!=null){
			StudentDTO std = new StudentDTO();
			std.setId(st.getId());
			std.setXh(st.getXh());
			std.setXm(st.getXm());
			std.setSsyq(st.getSsyq());
			std.setDalei(st.getClasses().getBjdm());
			std.setXb(st.getXb());
			std.setPhone(st.getPhone());
			this.outObjectString(std, null);
		}
		return null;
	}
	
	/**
	 * 获取专家信息
	 */
	public String getExpertDetailInfo(){
		String exzgh = this.getUserDTO().getLogincode();
		String inzgh = this.getUserDTO().getLogincode();
		if(exzgh!=null && !exzgh.equals("")){
			Expert expert = userBiz.getExpert(exzgh);
			this.outObjectString(expert, null);
		}
		return null;
	}
	
	/**
	 * 获取预约信息
	 */
	public String getStartExpert(){
		String exzgh = this.getUserDTO().getLogincode();
		if(exzgh!=null && !exzgh.equals("")){
			Expert ex = userBiz.getExpert(exzgh);
			if(ex!=null){
				StartExpertInfo sei = expertBespeakBiz.findSEIByStid(ex.getId());
				this.outObjectString(sei, null);
			}
		}
		return null;
	}
	
	
	/**
	 * 专家信息发布
	 * @return
	 */
	public String saveOrUpdateExpertBespeak(){
		try {
			//保存专家信息
			Date d = new Date();
			//图片保存路径
			if(null!=this.upload){
				String path = ServletActionContext.getServletContext().getRealPath("upload")
				+ File.separator + d.getTime() + Tools.getFileExp(this.uploadFileName);
				//获取图片绝对路径
				photo = this.getRequest().getContextPath() + "/upload" + "/"
									+ d.getTime() + Tools.getFileExp(this.uploadFileName); 
				File toFile = new File(path);
				Tools.writeFile(this.upload,toFile);
			}
			Expert ex = this.getUserDTO().getExpert();
			if(ex!=null && !ex.equals("")){
				id = ex.getId();
			}
			zgh = this.getUserDTO().getLogincode();
			Expert expert = new Expert(id,zgh,xm,xb,phone,photo,
					unit,email,expertType,introduce,ssyq);
			boolean b = expertBespeakBiz.saveOrUpdateExpertInfo(expert);
			
			//保存专家预约信息
			if(b){
				//获取专家id
				Expert expe = userBiz.getExpert(zgh);
				Expert e = new Expert(expe.getId());
				//获取该专家已发布的预约信息id
				StartExpertInfoDTO se = expertBespeakBiz.findPageStartExpertInfoById(expe.getId());
				if(se!=null && !se.equals("")){
					seifid = se.getSeifid();
				}
				StartExpertInfo startExpertInfo = new StartExpertInfo(seifid, freetime, workaddress, e);
				boolean bln = expertBespeakBiz.saveOrUpdateExpertBespeak(startExpertInfo);
				if(bln){
					if(seifid==null){
						this.outString("{success:true,message:'发布成功!'}");
					}else{
						this.outString("{success:true,message:'修改成功!'}");
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
	 * （专家端）我的预约
	 */
	public String myBespeakToExpert(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			Expert ex = this.getUserDTO().getExpert();
			if(ex!=null && !ex.equals("")){
				Integer eid = this.getUserDTO().getExpert().getId();
				if(eid!=null){
					expertBespeakBiz.findPageStudentBespeakByEepertId(page,eid);
					String[] ingore = {"expert","student"};
					this.outPageString(page, ingore);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return null;
	}
	
	/**
	 * （管理员端）专家学生预约信息
	 */
	public String expertSB(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			expertBespeakBiz.findPageStudentBespeakByEepertId(page,id);
			String[] ingore = {"expert","student"};
			this.outPageString(page, ingore);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		
		return null;
	}
	
	/**
	 * 管理员端获取待指定学生信息
	 */
	public String sbStudents(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			expertBespeakBiz.findSBStudent(page,experttype,xm);
			String[] ingore = {"student"};
			this.outPageString(page, ingore);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 管理员端，给专家分配多个学生
	 */
	public String updateAllotExpertForStd(){
		try {
			JSONArray jsonArray = new JSONArray();
			jsonArray = JSONArray.fromObject(stidArray);
			if(jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					stid = (Integer) jsonArray.get(i);
					expertBespeakBiz.updateStudentBespeakByIds(stid, id);
				}
				this.outString("{success:true,message:'已完成学生分配!'}");
			}else{
				this.outString("{success:true,message:'不存在分配的学生!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	/**
	 * 预约审核
	 */
	public String checkBespeak(){
		try {
			boolean bn = expertBespeakBiz.updateStudentBespeakById(checkkey,stid);
			if(bn){
				Studentbespeak stdsb = expertBespeakBiz.findSbByStid(stid);
				String mobile = stdsb.getExpert().getPhone();
				SmsBiz smsBiz = ServiceHelper.getSmsBiz();
				Sms sms = smsBiz.findById(11);
				String content = sms.getContent();
				SmsUtil.sendSms(mobile, content);
			}
			this.outString("{success:true,message:'审核成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 添加谈话
	 */
	public String addTalk(){
		try {
			expertBespeakBiz.updateStudentBespeakTalkById(talkcontent, stid);
			this.outString("{success:true,message:'已完成谈话!'}");
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
			stdid = this.getUserDTO().getStudent().getId();
			Expert ex = new Expert(id);
			Student st = new Student(stdid);
			Studentbespeak studentBespeak = new Studentbespeak(sbid, studentemail, bespeaktime,
					bespeakplace, applygenre, detailinfo, uploadbespeaktime, bespeakstate, st,ex);
			boolean bn = expertBespeakBiz.saveOrUpdateStudentbespeak(studentBespeak);
			if(bn){
				if(sbid==null){
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
	 * 学生端专家预约列表
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
	 * （学生端）我的预约
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
			expertBespeakBiz.updateBespeakState(sbid);
			this.outString("{success:true,message:'修改成功!'}");
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
	 * 指定专家
	 */
	public String alreadyenter(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"applygenre","bespeakplace","bespeakstate","expertfeedbackstate","xm"};
			String[] values = {this.applygenre,this.bespeakplace,this.bespeakstate,this.expertfeedbackstate,this.ename};
			expertBespeakBiz.findPageAlreadyEnter(page,property,values);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 待指定专家
	 */
	public String waitEnter(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"haveornotexpert","applygenre","bespeakplace","bespeakstate","expertfeedbackstate","xm"};
			String[] values = {this.haveornotexpert,this.applygenre,this.bespeakplace,this.bespeakstate,this.expertfeedbackstate,this.ename};
			expertBespeakBiz.findPageWaitEnter(page,property,values);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 删除预约
	 */
	public String deleteBespeak(){
		try {
			expertBespeakBiz.deleteExpert(sbid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 给待指定专家学生，分配专家
	 */
	public String updateWaitbespeak(){
		try {
			boolean bn = expertBespeakBiz.updateStudentBespeakByIds(stid,id);
			if(bn){
				Studentbespeak stdsb = expertBespeakBiz.findSbByStid(stid);
				String mobile = stdsb.getExpert().getPhone();
				SmsBiz smsBiz = ServiceHelper.getSmsBiz();
				Sms sms = smsBiz.findById(13);
				String content = sms.getContent();
				SmsUtil.sendSms(mobile, content);
			}
			this.outString("{success:true,message:'已成功指定专家!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 我的预约详情
	 */
	public String mySbDetail(){
		try {
			StudentbespeakDTO sbDto = expertBespeakBiz.findMySbDetailById(sbid);
			this.outObjectString(sbDto, null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 发送专家预约短信
	 * @return
	 */
	public String sendEnterOrFeedBespeakSMS(){
		try {
			Studentbespeak sb = expertBespeakBiz.findSbByStid(stid);
			String xm = sb.getStudent().getXm();
			String ph = sb.getExpert().getPhone();
			Sms sms = null;
			String content = null;
			if(msgKey.equals("enter") && xm!=null && !xm.equals("")){
				sms = smsBiz.findById(10);
				content = sms.getContent().replace("$(XM)", xm);
			}else if(msgKey.equals("feed")){
				sms = smsBiz.findById(12);
				content = sms.getContent();
			}
			if(ph!=null && !ph.equals("")){
				SmsUtil.sendSms(ph, content);
				this.outString("{success:true,message:'短信已发送!'}");
			}else{
				this.outString("{success:true,message:'短信发送失败!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 专家发布的预约详情
	 */
	public String getESBDetailInfo(){
		try {
			StartExpertInfo ste = expertBespeakBiz.findSEIByStid(id);
			this.outObjectString(ste, null);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 办公/单位下拉菜单
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
}
