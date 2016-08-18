package com.cxstock.action.onlineqajudge;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.onlineqajudge.OnlineQAJudgeBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.OnlineQAJudge;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;

/**
 * 学习资料control层
 * 
 * @author root
 */
@SuppressWarnings("serial")
public class OnlineQAJudgeAction extends BaseAction {

	private OnlineQAJudgeBiz onlineQAJudgeBiz;

	private Integer id;
	
	private Integer qaid;

	private String content;

	public OnlineQAJudgeBiz getOnlineQAJudgeBiz() {
		return onlineQAJudgeBiz;
	}

	public void setOnlineQAJudgeBiz(OnlineQAJudgeBiz onlineQAJudgeBiz) {
		this.onlineQAJudgeBiz = onlineQAJudgeBiz;
	}

	public Integer getId() {
		return id;
	}

	public void setQaid(Integer qaid) {
		this.qaid = qaid;
	}
	
	public Integer getQaid() {
		return qaid;
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
		onlineQAJudgeBiz.deleteOnlineQAJudge(id);
		this.outString("{success:true,message:'删除成功!'}");
		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String findPageOnlineQAJudge() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"qaid"};
			Object[] value = {qaid};
			onlineQAJudgeBiz.findPageOnlineQAJudge(page,property,value,this.getUserDTO().getUserid());
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
	public String saveOrUpdateOnlineQAJudge() {
		try {
			UserDTO userInfo = this.getUserDTO();
			OnlineQAJudge qa = new OnlineQAJudge(id,qaid, content, userInfo.getUserid(), userInfo.getNickname(),
					DateUtil.format(new Date()));

			onlineQAJudgeBiz.saveOnlineQAJudge(qa);
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
