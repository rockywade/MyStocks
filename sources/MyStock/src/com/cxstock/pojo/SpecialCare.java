package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tbl_specialcare")
public class SpecialCare implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//学号
	private String xh;
	//姓名
	private String xm;
	//行政班
	private String administrativeClass;
	//经济困难
	private String economic;
	//学业困难
	private String academic;
	//心理困难
	private String mental;
	//寝室
	private String qsh;
	//寝室
	private String phone;
	//父亲
	private String fq;
	//父亲电话
	private String fqphone;
	//母亲
	private String mq;
	//母亲电话
	private String mqphone;
	//班主任
	private String bzr;
	//班主任电话
	private String bzrphone;
	//辅导员
	private String fdy;
	//辅导员电话
	private String fdyphone;
	//新生之友
	private String xszy;
	//新生之友电话
	private String xszyphone;
	//新生之友logincode
	private String xszylogincode;
	//班主任logincode
	private String bzrlogincode;
	//辅导员logincode
	private String fdylogincode;
	 
	//领导谈话次数
	@Transient
	private Integer yxldtimes;
	//辅导员谈话次数
	 @Transient
	private Integer fydtimes;
	//班主任谈话次数
	 @Transient
	private Integer bzrtimes;
	//新生之友谈话次数
	 @Transient
	private Integer xszytimes;
	 
	private Set<Conversation>  conversations;
	
	public SpecialCare(){
	}
	
	public SpecialCare(String xh,String xm,String administrativeClass,String economic,String academic,String mental,
			String qsh,String phone,String fq,String fqphone,String mq,String mqphone,
			String bzr,String bzrphone,String fdy,String fdyphone,String xszy,String xszyphone,
			String xszylogincode,String bzrlogincode,String fdylogincode){
		this.xh=xh;
		this.xm=xm;
		this.administrativeClass = administrativeClass;
		this.economic=economic;
		this.academic=academic;
		this.mental=mental;
		this.qsh=qsh;
		this.phone=phone;
		this.fq=fq;
		this.fqphone=fqphone;
		this.mq=mq;
		this.mqphone=mqphone;
		this.bzr=bzr;
		this.bzrphone=bzrphone;
		this.fdy=fdy;
		this.fdyphone=fdyphone;
		this.xszy=xszy;
		this.xszyphone=xszyphone;
		this.xszylogincode=xszylogincode;
		this.bzrlogincode=bzrlogincode;
		this.fdylogincode=fdylogincode;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	 @OneToMany(fetch=FetchType.EAGER)
	 @JoinColumn(name="specialcare_id") 
	public Set<Conversation> getConversations() {
		return conversations;
	}


	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}


	public void setXm(String xm) {
		this.xm = xm;
	}


	public String getAdministrativeClass() {
		return administrativeClass;
	}


	public void setAdministrativeClass(String administrativeClass) {
		this.administrativeClass = administrativeClass;
	}


	public String getEconomic() {
		return economic;
	}


	public void setEconomic(String economic) {
		this.economic = economic;
	}


	public String getAcademic() {
		return academic;
	}


	public void setAcademic(String academic) {
		this.academic = academic;
	}


	public String getMental() {
		return mental;
	}


	public void setMental(String mental) {
		this.mental = mental;
	}

	public String getQsh() {
		return qsh;
	}


	public void setQsh(String qsh) {
		this.qsh = qsh;
	}




	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFq() {
		return fq;
	}


	public void setFq(String fq) {
		this.fq = fq;
	}


	public String getFqphone() {
		return fqphone;
	}


	public void setFqphone(String fqphone) {
		this.fqphone = fqphone;
	}


	public String getMq() {
		return mq;
	}


	public void setMq(String mq) {
		this.mq = mq;
	}


	public String getMqphone() {
		return mqphone;
	}


	public void setMqphone(String mqphone) {
		this.mqphone = mqphone;
	}


	public String getBzr() {
		return bzr;
	}


	public void setBzr(String bzr) {
		this.bzr = bzr;
	}


	public String getBzrphone() {
		return bzrphone;
	}


	public void setBzrphone(String bzrphone) {
		this.bzrphone = bzrphone;
	}


	public String getFdy() {
		return fdy;
	}


	public void setFdy(String fdy) {
		this.fdy = fdy;
	}


	public String getFdyphone() {
		return fdyphone;
	}


	public void setFdyphone(String fdyphone) {
		this.fdyphone = fdyphone;
	}


	public String getXszy() {
		return xszy;
	}


	public void setXszy(String xszy) {
		this.xszy = xszy;
	}


	public String getXszyphone() {
		return xszyphone;
	}


	public void setXszyphone(String xszyphone) {
		this.xszyphone = xszyphone;
	}


	public String getXszylogincode() {
		return xszylogincode;
	}


	public void setXszylogincode(String xszylogincode) {
		this.xszylogincode = xszylogincode;
	}


	public String getBzrlogincode() {
		return bzrlogincode;
	}


	public void setBzrlogincode(String bzrlogincode) {
		this.bzrlogincode = bzrlogincode;
	}


	public String getFdylogincode() {
		return fdylogincode;
	}


	public void setFdylogincode(String fdylogincode) {
		this.fdylogincode = fdylogincode;
	}


	public Integer getYxldtimes() {
		return yxldtimes;
	}


	public void setYxldtimes(Integer yxldtimes) {
		this.yxldtimes = yxldtimes;
	}


	public Integer getFydtimes() {
		return fydtimes;
	}


	public void setFydtimes(Integer fydtimes) {
		this.fydtimes = fydtimes;
	}


	public Integer getBzrtimes() {
		return bzrtimes;
	}


	public void setBzrtimes(Integer bzrtimes) {
		this.bzrtimes = bzrtimes;
	}


	public Integer getXszytimes() {
		return xszytimes;
	}


	public void setXszytimes(Integer xszytimes) {
		this.xszytimes = xszytimes;
	}
}

