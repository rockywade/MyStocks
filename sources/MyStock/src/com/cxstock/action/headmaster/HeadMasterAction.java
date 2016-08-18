package com.cxstock.action.headmaster;

import java.util.HashSet;
import java.util.Set;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.headmaster.HeadMasterBiz;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class HeadMasterAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	
	private String hclass;
	
	
	private HeadMasterBiz headMasterBiz;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZgh() {
		return zgh;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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



	public String getHclass() {
		return hclass;
	}

	public void setHclass(String hclass) {
		this.hclass = hclass;
	}

	public HeadMasterBiz getHeadMasterBiz() {
		return headMasterBiz;
	}

	public void setHeadMasterBiz(HeadMasterBiz headMasterBiz) {
		this.headMasterBiz = headMasterBiz;
	}

	/**
	 * 分页查询 
	 */
	public String findPageHeadMaster() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"zgh","xm"};
			Object[] value = {this.zgh,this.xm};
			headMasterBiz.findPageHeadMaster(page,property,value);
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
	public String saveOrUpdateHeadMaster() {
		try {
			Set<Classes> hclassSet = new HashSet<Classes>();
			String[] ic = hclass.split(",");
			for(int i=0;i<ic.length;i++){
				hclassSet.add(new Classes(ic[i]));
			}
			HeadMaster hm = new HeadMaster(id,zgh,xm,xb,phone,hclassSet);
			hm.setSsyq(ssyq);
			boolean b = headMasterBiz.saveOrUpdateHeadMaster(hm);
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
	public String deleteHeadMaster() {
		try {
			headMasterBiz.deleteHeadMaster(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	

}
