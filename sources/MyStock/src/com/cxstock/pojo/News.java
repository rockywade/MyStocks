package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_news")
public class News implements Serializable {
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
	
	public News() {
		
	}
	
	public News(String newsid, String newstitle, String website, String writer,
			String newsdate, String property, String content,
			String newscheckstyle, String highlight, String totop,
			String activityid) {
		this.newsid = newsid;
		this.newstitle = newstitle;
		this.website = website;
		this.writer = writer;
		this.newsdate = newsdate;
		this.property = property;
		this.content = content;
		this.newscheckstyle = newscheckstyle;
		this.highlight = highlight;
		this.totop = totop;
		this.activityid = activityid;
	}
	@Id
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
	
}
