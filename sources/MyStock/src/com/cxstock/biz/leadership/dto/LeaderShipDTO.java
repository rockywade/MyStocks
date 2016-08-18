package com.cxstock.biz.leadership.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.LeaderShip;


public class LeaderShipDTO {

	private Integer id;
	//职工号
	private String zgh;
	//姓名
	private String xm;
	//性别
	private String xb;
	
	private String ssyq;
	
	//教师手机号码
	private String phone;
	
	public LeaderShipDTO() {
		super();
	}
	
	public LeaderShipDTO(Integer id, String zgh, String xm,String xb, String phone) {
		super();
		this.id = id;
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
	}
	
	public static LeaderShipDTO createDto(LeaderShip pojo) {
		LeaderShipDTO dto = null;
		if (pojo != null) {
			dto = new LeaderShipDTO(pojo.getId(), pojo.getZgh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone());
			dto.setSsyq(pojo.getSsyq());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<LeaderShipDTO> list = new ArrayList<LeaderShipDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				LeaderShip leaderShip = (LeaderShip)iterator.next();
				LeaderShipDTO dto = createDto(leaderShip);
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

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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