package com.cxstock.action.newfriends;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.newfriends.NewFriendsBiz;
import com.cxstock.pojo.NewFriends;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class NewFriendsAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	
	private String nclass;
	
	
	private NewFriendsBiz newFriendsBiz;
	
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


	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNclass() {
		return nclass;
	}

	public void setNclass(String nclass) {
		this.nclass = nclass;
	}


	public NewFriendsBiz getNewFriendsBiz() {
		return newFriendsBiz;
	}

	public void setNewFriendsBiz(NewFriendsBiz newFriendsBiz) {
		this.newFriendsBiz = newFriendsBiz;
	}

	

	
	/**
	 * 分页查询 
	 */
	public String findPageNewFriends() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"zgh","xm"};
			Object[] value = {this.zgh,this.xm};
			newFriendsBiz.findPageNewFriends(page,property,value);
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
	public String saveOrUpdateNewFriends() {
		try {
			String[] ic = nclass.split(",");
			NewFriends newFriends = new NewFriends(id,zgh,xm,xb,phone);
			newFriends.setSsyq(ssyq);
			boolean b = newFriendsBiz.saveOrUpdateNewFriends(newFriends);
			if (b) {
				if (id == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			}else{
				this.outString("{success:false,errors:'此职工号已存在!'}");
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
	public String deleteInstructor() {
		try {
			newFriendsBiz.deleteNewFriends(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	

}
