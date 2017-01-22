package com.cxstock.action.whereabouts;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.whereabouts.WhereaboutsBiz;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.Wheresaboutscensus;
import com.cxstock.pojo.Wheresaboutslaunch;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.cxstock.utils.system.DateUtil;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.JxlExcelHelper;
import com.cxstock.utils.system.Tools;

public class WhereaboutsAction extends BaseAction {
	//发起
	private String launchid;		//去向发起id
	private String launchname;		//发起名称
	private String launchstarttime;	//发起开始时间
	private String launchendtime;	//发起结束时间
	private String launchtime;		//发起时间
	private String censusendtime;	//统计截止时间
	private int censuspersonnum;	//登计人数
	private String major;			//专业
	private String classnum;		//班级
	private String rolename;		//角色
	private String launchstyle;		//假日去向发起状态
	
	//登记
	private String censusid;			//登记id
	private String censususernum;		//登记人编号
	private String censususername;		//登记人名称
	private String holidaytime;			//假日时间
	private String classes;				//班级
	private String dorm;				//寝室
	private String userphone;			//联系方式
	private String teacher;				//班主任
	private String counsellor;			//辅导员
	private String wheresfact;			//去向情况
	private String termini;				//目的地
	private String urgentphone;			//紧急联系人电话
	private String leaveschooltime;		//离校时间
	private String returnschooltime;	//返校时间
	private String daysnum;				//天数
	private String parentsknows;		//父母是否知情
	private String parentsphone;		//父母电话
	private String enteragreement;		//同意学校安全协议
	
	private int enterKey;			//是
	private WhereaboutsBiz whereAboutsBiz;

	//导出文件
	private ServletContext context;
	private String filename;
	private String mimeType;
	private InputStream inStream;
	
	//构造，获取context
	public  WhereaboutsAction() {
		context = ServletActionContext.getServletContext();
	}
	
	//重写execute方法
	public String execute() throws Exception {
		mimeType = context.getMimeType(filename);
		return SUCCESS;
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
	//发起
	public String getLaunchid() {
		return launchid;
	}

	public void setLaunchid(String launchid) {
		this.launchid = launchid;
	}

	public String getLaunchname() {
		return launchname;
	}

	public void setLaunchname(String launchname) {
		this.launchname = launchname;
	}

	public String getLaunchstarttime() {
		return launchstarttime;
	}

	public void setLaunchstarttime(String launchstarttime) {
		this.launchstarttime = launchstarttime;
	}

	public String getLaunchendtime() {
		return launchendtime;
	}

	public void setLaunchendtime(String launchendtime) {
		this.launchendtime = launchendtime;
	}

	public String getLaunchtime() {
		return launchtime;
	}

	public void setLaunchtime(String launchtime) {
		this.launchtime = launchtime;
	}

	public String getCensusendtime() {
		return censusendtime;
	}

	public void setCensusendtime(String censusendtime) {
		this.censusendtime = censusendtime;
	}

	public int getCensuspersonnum() {
		return censuspersonnum;
	}

	public void setCensuspersonnum(int censuspersonnum) {
		this.censuspersonnum = censuspersonnum;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getLaunchstyle() {
		return launchstyle;
	}

	public void setLaunchstyle(String launchstyle) {
		this.launchstyle = launchstyle;
	}

	//登记
	public String getCensusid() {
		return censusid;
	}

	public void setCensusid(String censusid) {
		this.censusid = censusid;
	}

	public String getCensususernum() {
		return censususernum;
	}

	public void setCensususernum(String censususernum) {
		this.censususernum = censususernum;
	}

	public String getCensususername() {
		return censususername;
	}

	public void setCensususername(String censususername) {
		this.censususername = censususername;
	}

	public String getHolidaytime() {
		return holidaytime;
	}

	public void setHolidaytime(String holidaytime) {
		this.holidaytime = holidaytime;
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

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public String getWheresfact() {
		return wheresfact;
	}

	public void setWheresfact(String wheresfact) {
		this.wheresfact = wheresfact;
	}

	public String getTermini() {
		return termini;
	}

	public void setTermini(String termini) {
		this.termini = termini;
	}

	public String getUrgentphone() {
		return urgentphone;
	}

	public void setUrgentphone(String urgentphone) {
		this.urgentphone = urgentphone;
	}

	public String getLeaveschooltime() {
		return leaveschooltime;
	}

	public void setLeaveschooltime(String leaveschooltime) {
		this.leaveschooltime = leaveschooltime;
	}

	public String getReturnschooltime() {
		return returnschooltime;
	}

	public void setReturnschooltime(String returnschooltime) {
		this.returnschooltime = returnschooltime;
	}

	public String getDaysnum() {
		return daysnum;
	}
	public void setDaysnum(String daysnum) {
		this.daysnum = daysnum;
	}
	
	public String getParentsknows() {
		return parentsknows;
	}

	public void setParentsknows(String parentsknows) {
		this.parentsknows = parentsknows;
	}

	public String getParentsphone() {
		return parentsphone;
	}
	public void setParentsphone(String parentsphone) {
		this.parentsphone = parentsphone;
	}
	
	public String getEnteragreement() {
		return enteragreement;
	}

	public void setEnteragreement(String enteragreement) {
		this.enteragreement = enteragreement;
	}
	
	public int getEnterKey() {
		return enterKey;
	}

	public void setEnterKey(int enterKey) {
		this.enterKey = enterKey;
	}

	public WhereaboutsBiz getWhereAboutsBiz() {
		return whereAboutsBiz;
	}

	public void setWhereAboutsBiz(WhereaboutsBiz whereAboutsBiz) {
		this.whereAboutsBiz = whereAboutsBiz;
	}

	/**
	 * 发起去向统计
	 * @return
	 */
	public String whereaboutsLaunch(){
		try {
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			Wheresaboutslaunch wheres = new Wheresaboutslaunch();
			
			wheres.setLaunchid(Tools.getUid());
			wheres.setLaunchname(launchname);
			wheres.setLaunchstarttime(launchstarttime);
			wheres.setLaunchendtime(launchendtime);
			wheres.setLaunchtime(launchtime);
			wheres.setCensusendtime(censusendtime);
			wheres.setLaunchstyle("已发布");
			wheres.setSsxy(userInfo.getSsyq());
			
			whereAboutsBiz.saveLaunch(wheres);
			this.outString("{success:true,message:'添加成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 发起假日统计列表，按发起时间倒序排列
	 * @return
	 */
	public String launchByPageList() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			whereAboutsBiz.findPageLaunch(page,launchname);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 撤销去向统计
	 * @return
	 */
	public String cancelWhereLaunch(){
		System.out.println(launchstyle);
		System.out.println(launchid);
		if(!launchstyle.equals("已撤销")){
			launchstyle = "已撤销";
			int flag = whereAboutsBiz.updateLaunchStyle(launchid,launchstyle);
			this.outString("{success:true,message:'已成功撤销!'}");
		}else{
			this.outString("{success:true,message:'不能重复撤销'}");
		}
		return null;
	}
	
	/**
	 * 待填写假日去向统计查询
	 * @return
	 */
	public String findNeedWriteWheres(){
		try {
			Date time = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todaytime = sdf.format(time);
			launchstyle = "已发布";
			String ssxy = getUserDTO().getSsyq();
			if(ssxy.length() > 2) {
				ssxy = ssxy.substring(0, 2);
			}
			Wheresaboutslaunch whereslaun = whereAboutsBiz.findNeedWriteWheres(todaytime,launchstyle, ssxy);
			if(whereslaun!=null){
				Wheresaboutscensus census = whereAboutsBiz.findCensus(this.getUserDTO().getLogincode(),whereslaun.getLaunchid());
				if(census==null){
					this.outObjectString(whereslaun,null);
				}else{
					this.outString("{success:true,message:'nothing'}");
				}
			}else{
				this.outString("{success:true,message:'nothing'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 登记、修改去向统计
	 * @return
	 */
	public String whereAboutsCensus(){
		try {
			//string类型日期转date类型
			SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = null;
			java.util.Date date2 = null;
			try {
				if(StringUtils.isNotBlank(leaveschooltime) && StringUtils.isNotBlank(returnschooltime)) {
					
					date1 = smd.parse(leaveschooltime);
					date2 = smd.parse(returnschooltime);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(date1 == null || date2 == null) {
				Date date = new Date();
				date1 = date;
				date2 = date;
			}
			daysnum = String.valueOf(DateUtil.getOffsetDays(date1, date2));
			Wheresaboutscensus wherecensus = new Wheresaboutscensus();
			wherecensus.setCensususername(censususername);
			wherecensus.setCensususernum(censususernum);
			wherecensus.setLaunchid(launchid);
			wherecensus.setLaunchname(launchname);
			wherecensus.setHolidaytime(holidaytime);
			wherecensus.setClasses(classes);
			//wherecensus.setMojar(major);
			wherecensus.setDorm(dorm);
			wherecensus.setUserphone(userphone);
			wherecensus.setTeacher(teacher);
			wherecensus.setCounsellor(counsellor);
			wherecensus.setWheresfact(wheresfact);
			wherecensus.setTermini(termini);
			wherecensus.setUrgentphone(urgentphone);
			wherecensus.setLeaveschooltime(leaveschooltime);
			wherecensus.setReturnschooltime(returnschooltime);
			wherecensus.setDaysnum(daysnum);
			wherecensus.setParentsknows(parentsknows);
			wherecensus.setParentsphone(parentsphone);
			wherecensus.setEnteragreement("yes");
			wherecensus.setCensusendtime(censusendtime);
			if(censusid==null || censusid.equals("")){
				wherecensus.setCensusid(Tools.getUid());
				int flag = whereAboutsBiz.saveCensus(wherecensus);
				if(flag>0){
					censuspersonnum += 1;
					@SuppressWarnings("unused")
					int uflag = whereAboutsBiz.updateLaunchStyle(launchid, censuspersonnum);
					this.outString("{success:true,message:'提交成功!'}");
				}
			}else{
				wherecensus.setCensusid(censusid);
				whereAboutsBiz.updateObject(wherecensus);
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	/**
	 * 个人假日去向统计清单
	 * @return
	 */
	public String personalWheresCensusList(){
		UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
		censususernum = userInfo.getLogincode();
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			whereAboutsBiz.findPageCensusByUserId(page,censususernum);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 页面传值
	 * @return
	 */
	public String wheresLaunchProperty(){
		String webtitlename = launchname+"假日去向统计";
		getSession().setAttribute("titlename", webtitlename);
		getSession().setAttribute("launchid", launchid);
		return null;
	}
	
	/**
	 * 假日去向数据统计
	 * @return
	 */
	public String wheresCount(){
		try {
			//获取去向发起id
			launchid = (String) getSession().getAttribute("launchid");
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] porperty = {"classes","teacher","counsellor","wheresfact"};
			String[] values = {this.classes,this.teacher,this.counsellor, this.wheresfact};
			whereAboutsBiz.findPageCensusById(page, porperty, values,launchid);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 教师端去向统计浏览
	 * @return
	 */
	public String allCensusList(){
		try {
			//获取教师的专业和班级
			UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
			HeadMaster master = userInfo.getHeadMaster();
			Instructor instructor = userInfo.getInstructor();
			Set<Classes> classes = null;
			if(master!=null){
				classes = master.getHclass();
			}else if(instructor!=null){
				classes = instructor.getIclass();
			} else {
				classes = new HashSet<Classes>();
			}
			
			String[] tclasses = new String[classes.size()];
			int i = 0;
			for(Classes v:classes){
				tclasses[i] =  v.getBjdm();
				i++;
			}
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"censususername","censususernum","classes","teacher","counsellor"};
			String[] values = {this.censususername,this.censususernum,this.classes,this.teacher,this.counsellor};
			whereAboutsBiz.findPageCensusByProperty(page,property,values,"",tclasses);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 去向统计信息导出文档
	 */
	public InputStream getInStream() {
		try {
			List<Wheresaboutscensus> attendList = null;
			attendList = whereAboutsBiz.findWhereCenById(censusid);
	        String[] titles = new String[]{"姓名", "学号", "班级","班主任","去向","目的地","父母是否知情","父母联系方式"};
	        String[] fieldNames = new String[]{"censususername", "censususernum", "classes","teacher","wheresfact","termini","parentsknows","parentsphone"};
	        String path = ServletActionContext.getServletContext().getRealPath("file")+ File.separator;
	        File exportFile = new File(path+launchname+".xls");
	        ExcelHelper exh = JxlExcelHelper.getInstance(exportFile);
	        exh.writeExcel(Wheresaboutscensus.class, attendList, fieldNames, titles);
	        filename = launchname + ".xls";
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
	 * 班级下拉菜单
	 */
	public String findClassesComb(){
		 try {
				this.outListString(whereAboutsBiz.findClassesComb());
			   } catch (Exception e) {
				 e.printStackTrace();
				 this.outError();
			   }
		return null;
	}

}
