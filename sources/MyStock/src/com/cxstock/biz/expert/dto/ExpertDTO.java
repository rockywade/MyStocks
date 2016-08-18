package com.cxstock.biz.expert.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.Expert;


public class ExpertDTO {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String ssyq;
	private String phone;
	
	private String photo;
	private String unit;
	private String email;
	private String expertType;
	private String introduce;
	

	public ExpertDTO() {
		super();
	}
	
	public ExpertDTO(Integer id, String zgh, String xm,String xb, String phone,
			String photo,
			String unit,
			String email,
			String expertType,
			String introduce) {
		super();
		this.id = id;
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.photo = photo;
		this.unit = unit;
		this.email = email;
		this.expertType = expertType;
		this.introduce = introduce;
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

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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

	public static ExpertDTO createDto(Expert pojo) {
		ExpertDTO dto = null;
		if (pojo != null) {
			dto = new ExpertDTO(pojo.getId(), pojo.getZgh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone(),pojo.getPhoto(),pojo.getUnit(),pojo.getEmail(),
					pojo.getExpertType(),pojo.getIntroduce());
			dto.setSsyq(pojo.getSsyq());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<ExpertDTO> list = new ArrayList<ExpertDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				Expert expert = (Expert)iterator.next();
				ExpertDTO dto = createDto(expert);
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