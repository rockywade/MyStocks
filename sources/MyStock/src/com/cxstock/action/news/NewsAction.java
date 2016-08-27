package com.cxstock.action.news;

import org.apache.commons.lang.StringUtils;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.news.NewsBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.News;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Tools;

@SuppressWarnings("serial")
public class NewsAction extends BaseAction {

	private NewsBiz newsBiz;

	private String newsid;
	private String newstitle;
	private String website;
	private String writer;
	private String newsdate;
	private String property;
	private String content;
	private String newscheckstyle;
	private String highlight;
	private String totop;
	private String activityid;

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getNewsdate() {
		return newsdate;
	}

	public void setNewsdate(String newsdate) {
		this.newsdate = newsdate;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewscheckstyle() {
		return newscheckstyle;
	}

	public void setNewscheckstyle(String newscheckstyle) {
		this.newscheckstyle = newscheckstyle;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getTotop() {
		return totop;
	}

	public void setTotop(String totop) {
		this.totop = totop;
	}

	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}

	public String findNews() {
		try {
			UserDTO user = getUserDTO();
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			HeadMaster headMaster = user.getHeadMaster();
			Instructor instructor = user.getInstructor();
			LeaderShip leaderShip = user.getLeaderShip();
			NewFriends newFriends = user.getNewFriends();
			Expert expert = user.getExpert();
			String name ="";
			
			if(null!=headMaster){
				name = headMaster.getXm();
			}
			if(null!=leaderShip){
				name = leaderShip.getXm();
			}
			if(null!=newFriends){
				name = newFriends.getXm();
			}
			if(null!=expert){
				name = expert.getXm();
			}
			if(null!=instructor){
				name = instructor.getXm();
			}
			if(user.getLogincode().contains("admin")) {
				name = user.getLogincode();
			}
			String[] propertes = { "newstitle", "newsdate", "writer" };
			String[] value = { this.newstitle, this.newsdate, user.getLogincode()};
			newsBiz.findMyNews(page, propertes, value, name);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public String saveOrUpdateNews() {

		try {
			
			if(StringUtils.isBlank(newsid)) {
				newsid = Tools.getUid();
			}
			//管理员端状态改为：已发布
			newscheckstyle = "已发布";
			writer = getUserDTO().getLogincode();
			if("http://www.abc.mnp.com(网址输入格式)".equals(website)) {
				setWebsite("");
			}
			
			News news = new News(newsid, newstitle, website, writer, newsdate,
					property, content, newscheckstyle, highlight, totop, activityid);
			newsBiz.updateNews(news);
			
			if(newsid == null){
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
	
	public String deleteNews() {
		
		try {
			UserDTO user = getUserDTO();
			newsBiz.deleteByIdAndWriter(newsid, user.getLogincode());
			
			this.outString("{success:true,message:删除成功!'}");
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
}
