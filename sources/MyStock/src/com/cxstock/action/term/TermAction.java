package com.cxstock.action.term;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.term.TermBiz;
import com.cxstock.pojo.Term;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class TermAction extends BaseAction  {
	
	private Integer id;
	
	private String termName;
	
	private Integer orderNum;
	
	private TermBiz termBiz;
	
	public TermBiz getTermBiz() {
		return termBiz;
	}

	public void setTermBiz(TermBiz termBiz) {
		this.termBiz = termBiz;
	}

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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}
	
	public String findPageTerm(){
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			termBiz.findPageTerm(page);
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
	public String findTerm() {
		try {
			this.outListString(termBiz.findTerm());
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
	
	/**
	 * 保存/修改
	 */
	public String saveOrUpdateTerm() {
		try {
			Term t = new Term(id,termName,orderNum);
			termBiz.saveOrUpdateTerm(t);
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
	public String deleteTerm() {
		try {
			termBiz.deleteTerm(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			 this.outString("{success:false,error:'删除出错'}");
		}
		return null;
	}
	
	
}
