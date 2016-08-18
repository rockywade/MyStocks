package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二手书店
 * @author root
 */
@Entity
@Table(name="tbl_secondbookstore")
public class SecondBookStore implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer  id;
	
	//主题
	private  String subject;
	
	

	//内容
	private String content;
	
	//上传图片
	private  String  image;
	
	//附件
	private  String attachment;
	
	//发布人ID
	private  Integer publisher;
	
	//发布人名称
	private  String nickname;
	
	//发布时间
	private String  publishtime;
	
	//更新时间
	private String  updatetime;
	
	//顶置时间
	private String toptime;
	
	//人气
	private Integer popularity;
	
	//最新回复人名称
	private  String replynickname;
	

	//状态：待审核，展示中，隐藏中 
	private Integer status;
	
	//状态：置顶 取消置顶
	private Integer topflag;
	
	//状态：高亮 取消高亮
	private Integer highlight;
	
	private String storeType;

	

	public  SecondBookStore(){
		
	}

    public  SecondBookStore(Integer id,String subject,String content,String image,String attachmentFileName,String storeType){
    	super();
    	this.id = id;
    	this.subject = subject;
    	this.content = content;
    	this.image = image;
    	this.attachment = attachmentFileName;
    	this.storeType = storeType;
	}
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	
	public String getToptime() {
		return toptime;
	}

	public void setToptime(String toptime) {
		this.toptime = toptime;
	}




	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getPublisher() {
		return publisher;
	}

	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getReplynickname() {
		return replynickname;
	}

	public void setReplynickname(String replynickname) {
		this.replynickname = replynickname;
	}
	
	public Integer getTopflag() {
		return topflag;
	}

	public void setTopflag(Integer topflag) {
		this.topflag = topflag;
	}

	public Integer getHighlight() {
		return highlight;
	}

	public void setHighlight(Integer highlight) {
		this.highlight = highlight;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
