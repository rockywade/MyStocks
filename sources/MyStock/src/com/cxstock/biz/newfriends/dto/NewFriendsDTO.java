package com.cxstock.biz.newfriends.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.NewFriends;


public class NewFriendsDTO {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	private String nclass;
	
	public String getNclass() {
		return nclass;
	}


	public NewFriendsDTO() {
		super();
	}
	
	public NewFriendsDTO(Integer id, String zgh, String xm,String xb, String phone) {
		super();
		this.id = id;
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
	}
	
	public static NewFriendsDTO createDto(NewFriends pojo) {
		NewFriendsDTO dto = null;
		if (pojo != null) {
			dto = new NewFriendsDTO(pojo.getId(), pojo.getZgh(),pojo.getXm(),pojo.getXb(), 
					pojo.getPhone());
			dto.setSsyq(pojo.getSsyq());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<NewFriendsDTO> list = new ArrayList<NewFriendsDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				NewFriends NewFriends = (NewFriends)iterator.next();
				NewFriendsDTO dto = createDto(NewFriends);
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

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public void setNclass(String nclass) {
		this.nclass = nclass;
	}

	





}