package com.cxstock.action.secondbookstorejudge;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.secondbookstorejudge.SecondBookStoreJudgeBiz;
import com.cxstock.pojo.SecondBookStoreJudge;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;

/**
 * 学习资料control层
 * 
 * @author root
 */
@SuppressWarnings("serial")
public class SecondBookStoreJudgeAction extends BaseAction {

	private SecondBookStoreJudgeBiz secondBookStoreJudgeBiz;

	private Integer id;
	
	private Integer storeid;

	private String content;


	public SecondBookStoreJudgeBiz getSecondBookStoreJudgeBiz() {
		return secondBookStoreJudgeBiz;
	}

	public void setSecondBookStoreJudgeBiz(
			SecondBookStoreJudgeBiz secondBookStoreJudgeBiz) {
		this.secondBookStoreJudgeBiz = secondBookStoreJudgeBiz;
	}

	public Integer getId() {
		return id;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String delete() {
		secondBookStoreJudgeBiz.deleteSecondBookStoreJudge(id);
		this.outString("{success:true,message:'删除成功!'}");
		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String findPageSecondBookStoreJudge() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"storeid"};
			Object[] value = {storeid};
			secondBookStoreJudgeBiz.findPageSecondBookStoreJudge(page,property,value,this.getUserDTO().getUserid());
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
	public String saveOrUpdateSecondBookStoreJudge() {
		try {
			UserDTO userInfo = this.getUserDTO();
			SecondBookStoreJudge judge = new SecondBookStoreJudge(id,storeid, content, userInfo.getUserid(), userInfo.getNickname(),
					DateUtil.format(new Date()));

			secondBookStoreJudgeBiz.saveSecondBookStoreJudge(judge);
			if (id == null) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

}
