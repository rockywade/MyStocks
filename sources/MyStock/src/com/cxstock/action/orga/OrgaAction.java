package com.cxstock.action.orga;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.orga.OrgaBiz;
import com.cxstock.pojo.Orga;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class OrgaAction extends BaseAction  {
	
	private Integer id;
	
	private String orgaName;
	
	private Integer orderNum;
	
	private OrgaBiz orgaBiz;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	public String getOrgaName() {
		return orgaName;
	}



	public void setOrgaName(String orgaName) {
		this.orgaName = orgaName;
	}



	public Integer getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}



	


	public OrgaBiz getOrgaBiz() {
		return orgaBiz;
	}



	public void setOrgaBiz(OrgaBiz orgaBiz) {
		this.orgaBiz = orgaBiz;
	}

	public String findPageOrga(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			orgaBiz.findPageOrga(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 下拉数据
	 */
	public String findOrga() {
		try {
			this.outListString(orgaBiz.findOrga());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 保存/修改
	 */
	public String saveOrUpdateOrga() {
		try {
			Orga o = new Orga(id,orgaName,orderNum);
			orgaBiz.saveOrUpdateOrga(o);
			if(id==null){
				this.outString("{success:true,message:'保存成功!'}");
			}else{
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
    
	/**
	 * 删除
	 */
	public String deleteOrga() {
		try {
			orgaBiz.deleteOrga(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
