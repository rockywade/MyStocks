package com.cxstock.biz.student.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.pojo.Attend;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Student;


public class StudentDTO {

	private Integer id;
	private String xh;
	private String xm;
	private String xb;
	private String phone;
	
	private String bjdm;
	
	private Integer instructorid;
	private Integer headmasterid;
	private Integer newfriendsid;
	
	private String instructor;
	private String headmaster;
	private String newfriends;
	
	private String inschoolterm;
	
	private String mq;
	
	private String fq;
	
	private String fqphone;
	
	private String mqphone;
	
	//寝室号
	private String qsh;
	//寝室名称
	private String qsmc;
	//大类
	private String dalei;
	
	private String ssyq;
	
	//考评总分
	private int gross;

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getInschoolterm() {
		return inschoolterm;
	}

	public void setInschoolterm(String inschoolterm) {
		this.inschoolterm = inschoolterm;
	}

	public String getQsmc() {
		return qsmc;
	}

	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
	}

	public StudentDTO() {
		super();
	}
	
	public StudentDTO(Integer id, String xh, String xm,String xb, String phone,String qsh,String qsmc,Classes cl,Instructor instructor,HeadMaster headmaster) {
		super();
		this.id = id;
		this.xh = xh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.bjdm = cl.getBjdm();
		
		if(null!=instructor){
			this.instructorid = instructor.getId();
			this.instructor = instructor.getXm();
		}
		
		if(null!=headmaster){
			this.headmasterid = headmaster.getId();
			this.headmaster = headmaster.getXm();
		}
		
		this.qsh = qsh;
		this.qsmc = qsmc;
	}
	
	public static StudentDTO createDto(Student pojo) {
		StudentDTO dto = null;
		if (pojo != null) {
			dto = new StudentDTO(pojo.getId(), pojo.getXh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone(),pojo.getQsh(),pojo.getQsmc(),pojo.getClasses(),pojo.getInstructor(),pojo.getHeadmaster());
		      dto.setDalei(pojo.getDalei()); 
		      dto.setSsyq(pojo.getSsyq());
		}
		
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				Student Student = (Student)iterator.next();
				StudentDTO dto = createDto(Student);
				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * 纪实考评
	 * @param list
	 * @param inschoolterm2 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List createRecordDto(Collection list, String inschoolterm) {
		List<StudentDTO> listDto = new ArrayList<StudentDTO>();
		//List<StudentDTO> listDto = new ArrayList();
		Set<Attend> attends = null;
		Attend at = null;
		if (list != null) {
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				Student stu = (Student) iter.next();
				attends = stu.getAttend();
				int sum = 0;
				if(null!=attends&&attends.size()>0){
					Iterator it = attends.iterator();
					while(it.hasNext()){
						at = (Attend)it.next();
						if(null==inschoolterm){
							sum += at.getGotscore();
						}else{
							if(at.getInschoolterm().equals(inschoolterm)){
								sum += at.getGotscore();
							}
						}
					}
				}
				StudentDTO stdto = new StudentDTO();
				stdto.setId(stu.getId());
				stdto.setXh(stu.getXh());
				stdto.setXm(stu.getXm());
				stdto.setXb(stu.getXb());
				stdto.setInschoolterm(inschoolterm);
				stdto.setBjdm(stu.getClasses().getBjdm());
				//if(master!=null){
				stdto.setInstructor(stu.getInstructor().getXm());
				//}else{
				stdto.setHeadmaster(stu.getHeadmaster().getXm());
				//	}
				stdto.setPhone(stu.getPhone());
				stdto.setQsmc(stu.getQsmc());
				stu.getAttend();
				stdto.setGross(sum);
				listDto.add(stdto);
			}
		}
		return listDto;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}



	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getHeadmaster() {
		return headmaster;
	}

	public void setHeadmaster(String headmaster) {
		this.headmaster = headmaster;
	}

	public String getNewfriends() {
		return newfriends;
	}

	public void setNewfriends(String newfriends) {
		this.newfriends = newfriends;
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

	public String getDalei() {
		return dalei;
	}

	public void setDalei(String dalei) {
		this.dalei = dalei;
	}
	

	public int getGross() {
		return gross;
	}

	public void setGross(int gross) {
		this.gross = gross;
	}

	public String getMq() {
		return mq;
	}

	public void setMq(String mq) {
		this.mq = mq;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
	}

	public String getFqphone() {
		return fqphone;
	}

	public void setFqphone(String fqphone) {
		this.fqphone = fqphone;
	}

	public String getMqphone() {
		return mqphone;
	}

	public void setMqphone(String mqphone) {
		this.mqphone = mqphone;
	}

	
}