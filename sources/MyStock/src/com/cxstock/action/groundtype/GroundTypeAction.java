package com.cxstock.action.groundtype;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.groundtype.GroundTypeBiz;
import com.cxstock.pojo.GroundType;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class GroundTypeAction extends BaseAction  {
	
	private Integer id;
	
	private String typeName;
	
	private Integer orderNum;
	
	private GroundTypeBiz groundTypeBiz;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	public Integer getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}



	





	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public GroundTypeBiz getGroundTypeBiz() {
		return groundTypeBiz;
	}



	public void setGroundTypeBiz(GroundTypeBiz groundTypeBiz) {
		this.groundTypeBiz = groundTypeBiz;
	}

	public String findPageGroundType(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			groundTypeBiz.findPageGroundType(page);
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
	public String findGroundType() {
		try {
			this.outListString(groundTypeBiz.findGroundType());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 保存/修改
	 */
	public String saveOrUpdateGroundType() {
		try {
			GroundType g = new GroundType(id,typeName,orderNum);
			groundTypeBiz.saveOrUpdateGroundType(g);
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
	public String deleteGroundType() {
		try {
			groundTypeBiz.deleteGroundType(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
