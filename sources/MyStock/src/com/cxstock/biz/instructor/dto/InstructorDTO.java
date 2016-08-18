package com.cxstock.biz.instructor.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.pojo.Classes;
import com.cxstock.pojo.Instructor;


public class InstructorDTO {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	private String iclass;
	
	public String getIclass() {
		return iclass;
	}

	public void setIclass(String iclass) {
		this.iclass = iclass;
	}

	public InstructorDTO() {
		super();
	}
	
	public InstructorDTO(Integer id, String zgh, String xm,String xb, String phone,Set<Classes> iclass) {
		super();
		this.id = id;
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		StringBuilder sb = new StringBuilder();
		Iterator it = iclass.iterator();
		while(it.hasNext()){
			sb.append(((Classes)it.next()).getBjdm()).append(",");
		}
		if(sb.length()>0){
			this.iclass = sb.substring(0, sb.length()-1);
		}else{
			this.iclass = sb.toString();
		}
		
	}
	
	public static InstructorDTO createDto(Instructor pojo) {
		InstructorDTO dto = null;
		if (pojo != null) {
			dto = new InstructorDTO(pojo.getId(), pojo.getZgh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone(),pojo.getIclass());
			dto.setSsyq(pojo.getSsyq());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<InstructorDTO> list = new ArrayList<InstructorDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				Instructor instructor = (Instructor)iterator.next();
				InstructorDTO dto = createDto(instructor);
				list.add(dto);
			}
		}
		return list;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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

	





}