package com.cxstock.action.unit;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.unit.UnitBiz;
import com.cxstock.pojo.Unit;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class UnitAction extends BaseAction  {
	
	private Integer id;
	
	private String unitName;
	
	private Integer orderNum;
	
	private UnitBiz unitBiz;
	
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


	public String getUnitName() {
		return unitName;
	}



	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}



	public UnitBiz getUnitBiz() {
		return unitBiz;
	}



	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}



	public String findPageUnit(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			unitBiz.findPageUnit(page);
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
	public String findUnit() {
		try {
			this.outListString(unitBiz.findUnit());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 下拉数据
	 */
	public String findUnitAll() {
		try {
			this.outListString(unitBiz.findUnitAll());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 保存/修改
	 */
	public String saveOrUpdateUnit() {
		try {
			Unit p = new Unit(id,unitName,orderNum);
			unitBiz.saveOrUpdateUnit(p);
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
	public String deleteUnit() {
		try {
			unitBiz.deleteUnit(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
