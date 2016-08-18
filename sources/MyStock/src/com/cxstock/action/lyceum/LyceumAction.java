package com.cxstock.action.lyceum;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.lyceum.LyceumBiz;
import com.cxstock.pojo.Lyceum;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class LyceumAction extends BaseAction{
	
	private LyceumBiz  lyceumBiz; 
	
	private Integer  id;
	
	private String lyceumName;
	
	
   /**
    * 学园列表
    * @return
    */
	public String findPageLyceum(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			lyceumBiz.findPageLyceum(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 新增或者修改数据
	 * 保存
	 * @return
	 */
	public  String  saveOrUpdateLyceum(){
		try {
			Lyceum lyceum = new Lyceum();
			lyceum.setId(this.id);
			lyceum.setLyceumName(this.lyceumName);
			lyceumBiz.saveOrUpdateLyceum(lyceum);
			if(id==null){
				this.outString("{success:true,message:'保存成功!'}");
			}else{
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
	}
	
	
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteLyceum(){
		try {
			lyceumBiz.deleteLyceum(this.id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

	public LyceumBiz getLyceumBiz() {
		return lyceumBiz;
	}

	public void setLyceumBiz(LyceumBiz lyceumBiz) {
		this.lyceumBiz = lyceumBiz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLyceumName() {
		return lyceumName;
	}

	public void setLyceumName(String lyceumName) {
		this.lyceumName = lyceumName;
	}
	
	
	
	

}
