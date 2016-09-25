package com.cxstock.action.student;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.action.student.vo.StudentExcel;
import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.biz.headmaster.HeadMasterBiz;
import com.cxstock.biz.instructor.InstructorBiz;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.student.StudentBiz;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.FileUtils;
import com.cxstock.utils.system.JxlExcelHelper;

@SuppressWarnings("serial")
public class StudentAction extends BaseAction {

	private Integer id;
	private String xh;
	private String xm;
	private String xb;
	private String phone;
	
	private String ssyq;
	private String bjdm;
	
	
	private String qsh;
	private String qsmc;
	
	private Integer instructorid;
	//班主任
	private Integer headmasterid;
	//新生之友
	private Integer newfriendsid;
	
	//文件上传
	private File excel;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String excelFileName; 
	
	private String ids;
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	private StudentBiz studentBiz;
	
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}


	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getQsmc() {
		return qsmc;
	}

	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
	}

	public Integer getInstructorid() {
		return instructorid;
	}

	public void setInstructorid(Integer instructorid) {
		this.instructorid = instructorid;
	}

	public Integer getHeadmasterid() {
		return headmasterid;
	}

	public void setHeadmasterid(Integer headmasterid) {
		this.headmasterid = headmasterid;
	}

	public Integer getNewfriendsid() {
		return newfriendsid;
	}

	public void setNewfriendsid(Integer newfriendsid) {
		this.newfriendsid = newfriendsid;
	}
	
	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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



	public StudentBiz getStudentBiz() {
		return studentBiz;
	}

	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}

	
	public ClassesBiz getClassesBiz() {
		return classesBiz;
	}

	public void setClassesBiz(ClassesBiz classesBiz) {
		this.classesBiz = classesBiz;
	}

	public InstructorBiz getInstructorBiz() {
		return instructorBiz;
	}

	public void setInstructorBiz(InstructorBiz instructorBiz) {
		this.instructorBiz = instructorBiz;
	}

	public HeadMasterBiz getHeadMasterBiz() {
		return headMasterBiz;
	}

	public void setHeadMasterBiz(HeadMasterBiz headMasterBiz) {
		this.headMasterBiz = headMasterBiz;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	private UserBiz userBiz;

	private ClassesBiz classesBiz;
	
	private InstructorBiz instructorBiz;
	
	private HeadMasterBiz headMasterBiz;
	
	
	/**
	 * 分页查询 
	 */
	public String findPageStudent() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if(getUserDTO().getLogincode().equals("admin")) {
				String[] property = {"xh","xm"};
				Object[] value = {this.xh,this.xm};
				studentBiz.findPageStudent(page,property,value);
			} else {
				String[] property = {"xh","xm","ssyq"};
				Object[] value = {this.xh,this.xm,getUserDTO().getSsyq()};
				studentBiz.findPageStudent(page,property,value);
			}
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改 
	 */
	public String saveOrUpdateStudent() {
		try {
			Classes classes = new Classes(bjdm);
			Instructor instructor = new Instructor(instructorid);
			HeadMaster headmaster= new HeadMaster(headmasterid);
			NewFriends newfriends = new NewFriends(newfriendsid);
			Student student = new Student(id,xh,xm,xb,phone,qsh,qsmc,classes,instructor,headmaster,newfriends);
			student.setSsyq(ssyq);
			boolean b = studentBiz.saveOrUpdateStudent(student);
			if (b) {
				if (id == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			}else{
				this.outString("{success:false,errors:'此学号已存在!'}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 删除用户
	 */
	public String deleteStudent() {
		try {
			studentBiz.deleteStudent(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	/**
	 * 删除多个用户
	 */
	public String deleteStudents() {
		try {
			String[] idArr = ids.split(",");
			for(String id : idArr) {
				studentBiz.deleteStudent(Integer.valueOf(id));
			}
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	

	public String importExcel(){
		try {
			Date d = new Date();
			String path =  ServletActionContext.getServletContext().getRealPath(
            "upload")
            + File.separator + d.getTime() + getFileExp(this.excelFileName);
			
			File importFile = new File(path);
			writeFile(this.excel, importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[]{"xh","xm","xb","zymc","classes","qsmc","qsh","phone",
    		"fqphone","instructor","instructorName","instructorPhone","headmaster","headmasterName","headmasterPhone","ssyq"};
			
			List<StudentExcel> importStudentList = exh.readExcel(StudentExcel.class, fieldNames, true);
			StudentExcel studentExcel = null;
			for(int i=0;i<importStudentList.size();i++){
				studentExcel = importStudentList.get(i);
				if(null==studentExcel.getInstructor()||"".equals(studentExcel.getInstructor())||null==studentExcel.getHeadmaster()||"".equals(studentExcel.getHeadmaster())){
					continue;
				}else{
					Student s = (Student) studentBiz.studentExist(studentExcel);
					if(null!=s){
						//保存到用户表
						saveUser(studentExcel);
						//检查辅导员是否存在
						Instructor in = saveInstructor(studentExcel);
						//检查班主任是否存在
						HeadMaster hm = saveHeadMaster(studentExcel);
						//检查班级是否存在
						Classes c = saveClasses(studentExcel,in,hm);
						updateStudent(s,studentExcel,c);
					}else{
						//保存到用户表
						saveUser(studentExcel);
						//检查辅导员是否存在
						Instructor in = saveInstructor(studentExcel);
						//检查班主任是否存在
						HeadMaster hm = saveHeadMaster(studentExcel);
						//检查班级是否存在
						Classes c = saveClasses(studentExcel,in,hm);
						saveStudent(studentExcel,c);
					}
				}
			}
			this.outString("{success:true,message:'导入成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	private String getFileExp(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos);
	}

	private final int BUFFER_SIZE = 16 * 1024;
	
	private String writeFile(File src, File dst) {
		String style = "";
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//获取文件大小
				long   size =  src.length();
				//文件格式化
			    style = FileUtils.getFormatSize(size);
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
		return style;
	}
	
	private void saveUser(StudentExcel studentExcel){
		UserDTO dto = new UserDTO();
		dto.setLogincode(studentExcel.getXh());
		dto.setPassword(studentExcel.getXh());
		dto.setState(1);
		dto.setSsyq(studentExcel.getSsyq());
		dto.setRole("2");//学生
		userBiz.saveOrUpdateUser(dto);
	}
	
	private Classes saveClasses(StudentExcel studentExcel,Instructor i ,HeadMaster hm){
		Classes c = new Classes();
		c.setBjdm(studentExcel.getClasses());
		c.setInstructor(i);
		c.setHeadMaster(hm);
		c.setSsyq(studentExcel.getSsyq());
		classesBiz.saveOrUpdateClasses(c);
		return c;
	}
	
	private Instructor saveInstructor(StudentExcel studentExcel){
		Instructor i = new Instructor();
		i.setPhone(studentExcel.getInstructorPhone());
		i.setZgh(studentExcel.getInstructor());
		i.setXm(studentExcel.getInstructorName());
		i.setSsyq(studentExcel.getSsyq());
		instructorBiz.saveOrUpdateInstructor(i);
		return i;
	}
	
	private HeadMaster saveHeadMaster(StudentExcel studentExcel){
		HeadMaster hm = new HeadMaster();
		hm.setPhone(studentExcel.getHeadmasterPhone());
		hm.setZgh(studentExcel.getHeadmaster());
		hm.setXm(studentExcel.getHeadmasterName());
		hm.setSsyq(studentExcel.getSsyq());
		headMasterBiz.saveOrUpdateHeadMaster(hm);
		return hm;
	}
	
	private boolean saveStudent(StudentExcel student,Classes c) {
		Student s = new Student();
		if(null!=c){
			s.setClasses(c);
			s.setHeadmaster(c.getHeadMaster());
			s.setInstructor(c.getInstructor());	
		}
		
		s.setXh(student.getXh());	
		s.setXm(student.getXm());
		s.setXb(student.getXb());
		s.setZymc(student.getZymc());
		
		s.setQsh(student.getQsh());
		s.setQsmc(student.getQsmc());
		String phones = student.getFqphone().trim();
		if(phones.indexOf("/")!=-1){
			if(phones.split("/")[0].length()==11){
				s.setFqphone(phones.split("/")[0]);
			}
			if(phones.split("/")[1].length()==11){
				s.setMqphone(phones.split("/")[1]);
			}
		}else{
			if(phones.length()==11){
				s.setFqphone(phones);
			}
		}
		String sphone = student.getPhone().trim();
		if(sphone.indexOf("/")!=-1){
			if(sphone.split("/")[0].length()==11){
				s.setPhone(sphone.split("/")[0]);
			}
		}else{
			if(sphone.length()==11){
				s.setPhone(sphone);
			}
		}
		s.setSsyq(student.getSsyq());
		studentBiz.saveOrUpdateStudent(s);
		return true;
	}
	
	public boolean updateStudent(Student student,StudentExcel studentExcel,Classes c) {
		student.setClasses(c);
		student.setHeadmaster(student.getHeadmaster());
		student.setInstructor(student.getInstructor());		
		student.setXh(studentExcel.getXh());	
		student.setXm(studentExcel.getXm());
		student.setXb(studentExcel.getXb());
		student.setZymc(studentExcel.getZymc());
		
		student.setQsh(studentExcel.getQsh());
		student.setQsmc(studentExcel.getQsmc());
		String phones = studentExcel.getFqphone();
		if(phones.indexOf("/")!=-1){
			student.setFqphone(phones.split("/")[0]);
			student.setMqphone(phones.split("/")[1]);
		}
		if(studentExcel.getPhone().indexOf("/")!=-1){
			student.setPhone(studentExcel.getPhone().split("/")[0]);
		}else{
			student.setPhone(studentExcel.getPhone());
		}
		student.setSsyq(studentExcel.getSsyq());
		
		studentBiz.saveOrUpdateStudent(student);
		return true;
	}
}
