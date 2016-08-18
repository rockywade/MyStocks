package com.cxstock.biz.headmaster.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;


public class HeadMasterDTO {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	private String hclass;
	
	public String getHclass() {
		return hclass;
	}

	public void setHclass(String hclass) {
		this.hclass = hclass;
	}

	public HeadMasterDTO() {
		super();
	}
	
	public HeadMasterDTO(Integer id, String zgh, String xm,String xb, String phone,Set<Classes> hclass) {
		super();
		this.id = id;
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		StringBuilder sb = new StringBuilder();
		Iterator it = hclass.iterator();
		while(it.hasNext()){
			sb.append(((Classes)it.next()).getBjdm()).append(",");
		}
		if(sb.length()>0){
			this.hclass = sb.substring(0, sb.length()-1);
		}else{
			this.hclass = sb.toString();
		}
		
	}
	
	public static HeadMasterDTO createDto(HeadMaster pojo) {
		HeadMasterDTO dto = null;
		if (pojo != null) {
			dto = new HeadMasterDTO(pojo.getId(), pojo.getZgh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone(),pojo.getHclass());
			dto.setSsyq(pojo.getSsyq());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<HeadMasterDTO> list = new ArrayList<HeadMasterDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				HeadMaster hm = (HeadMaster)iterator.next();
				HeadMasterDTO dto = createDto(hm);
				list.add(dto);
			}
		}
		return list;
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


}