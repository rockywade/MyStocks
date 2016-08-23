package com.cxstock.action.expert;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.expert.ExpertBiz;
import com.cxstock.pojo.Expert;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Tools;

@SuppressWarnings("serial")
public class ExpertAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	
	private String photo;
	private String unit;
	private String email;
	private String expertType;
	private String introduce;
	private String ename;
	private String ssyq;
	//文件上传
	private File upload;
	private String uploadFileName;
	
	//文件上传
	private File image;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String imageFileName; 
	
	private ExpertBiz expertBiz;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public ExpertBiz getExpertBiz() {
		return expertBiz;
	}

	public void setExpertBiz(ExpertBiz expertBiz) {
		this.expertBiz = expertBiz;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 分页查询 
	 */
	public String findPageExpert() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"zgh","xm"};
			Object[] value = {this.zgh,this.xm};
			expertBiz.findPageExpert(page,property,value);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	public String saveOrUpdateExpertByAdmin() {
		try {
			Date d = new Date();
			//图片保存路径
			if(null!=this.image){
				String path = ServletActionContext.getServletContext().getRealPath("upload")
				+ File.separator + d.getTime() + Tools.getFileExp(this.imageFileName);
				//获取图片绝对路径
				photo = this.getRequest().getContextPath() + "/upload" + "/"
									+ d.getTime() + Tools.getFileExp(this.imageFileName); 
				File toFile = new File(path);
				Tools.writeFile(this.image,toFile);
			}
			
			Expert expert = new Expert(id,zgh,xm,xb,phone,photo,
					unit,email,expertType,introduce);
			expert.setSsyq(ssyq);
			boolean b = expertBiz.saveOrUpdateExpert(expert);
			if (b) {
				if (id == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			}else{
				this.outString("{success:false,errors:'此职工号已存在!'}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改 
	 */
	public String saveOrUpdateExpert() {
		try {
			Date d = new Date();
			//图片保存路径
			if(null!=this.upload){
				String path = ServletActionContext.getServletContext().getRealPath("upload")
				+ File.separator + d.getTime() + Tools.getFileExp(this.uploadFileName);
				//获取图片绝对路径
				photo = this.getRequest().getContextPath() + "/upload" + "/"
									+ d.getTime() + Tools.getFileExp(this.uploadFileName); 
				File toFile = new File(path);
				Tools.writeFile(this.upload,toFile);
			}
			
			
			
			
			id = this.getUserDTO().getExpert().getId();
			zgh = this.getUserDTO().getExpert().getZgh();
			Expert expert = new Expert(id,zgh,xm,xb,phone,photo,
					unit,email,expertType,introduce);
			boolean b = expertBiz.saveOrUpdateExpert(expert);
			if (b) {
				if (id == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			}else{
				this.outString("{success:false,errors:'此职工号已存在!'}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 删除用户
	 */
	public String deleteExpert() {
		try {
			expertBiz.deleteExpert(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	
	/**
	 * 专家类别下拉菜单
	 */
	public String findEtComb(){
		try {
			List<ComboData> cbList = expertBiz.findEtComb();
			this.outListString(cbList);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 专家下拉菜单
	 */
	public String findEnComb(){
		try {
			System.out.println(expertType);
			List<ComboData> cbList = expertBiz.findEnComb(expertType);
			this.outListString(cbList);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

}
