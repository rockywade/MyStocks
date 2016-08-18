package com.cxstock.action.leadership;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.leadership.LeaderShipBiz;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class LeaderShipAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String ssyq;
	private String phone;
	
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

	private LeaderShipBiz leaderShipBiz;

	public LeaderShipBiz getLeaderShipBiz() {
		return leaderShipBiz;
	}

	public void setLeaderShipBiz(LeaderShipBiz leaderShipBiz) {
		this.leaderShipBiz = leaderShipBiz;
	}

	
	/**
	 * 分页查询 
	 */
	public String findPageLeaderShip() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"zgh","xm"};
			Object[] value = {this.zgh,this.xm};
			leaderShipBiz.findPageLeaderShip(page,property,value);
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
	public String saveOrUpdateLeaderShip() {
		try {
			LeaderShip leaderShip = new LeaderShip(id,zgh,xm,xb,phone);
			leaderShip.setSsyq(ssyq);
			boolean b = leaderShipBiz.saveOrUpdateLeaderShip(leaderShip);
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
	public String deleteLeaderShip() {
		try {
			leaderShipBiz.deleteLeaderShip(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	

}
