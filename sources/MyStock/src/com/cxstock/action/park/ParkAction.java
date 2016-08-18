package com.cxstock.action.park;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.park.ParkBiz;
import com.cxstock.pojo.Park;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class ParkAction extends BaseAction  {
	
	private Integer id;
	
	private String parkName;
	
	private Integer orderNum;
	
	private ParkBiz parkBiz;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getParkName() {
		return parkName;
	}



	public void setParkName(String parkName) {
		this.parkName = parkName;
	}



	public Integer getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}



	


	public ParkBiz getParkBiz() {
		return parkBiz;
	}



	public void setParkBiz(ParkBiz parkBiz) {
		this.parkBiz = parkBiz;
	}

	public String findPagePark(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			parkBiz.findPagePark(page);
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
	public String findPark() {
		try {
			this.outListString(parkBiz.findPark());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 保存/修改
	 */
	public String saveOrUpdatePark() {
		try {
			Park p = new Park(id,parkName,orderNum);
			parkBiz.saveOrUpdatePark(p);
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
	public String deletePark() {
		try {
			parkBiz.deletePark(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
