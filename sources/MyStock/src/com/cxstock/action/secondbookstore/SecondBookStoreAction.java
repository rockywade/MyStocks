package com.cxstock.action.secondbookstore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.secondbookstore.SecondBookStoreBiz;
import com.cxstock.pojo.SecondBookStore;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.FileUtils;

/**
 *  
 * @author root
 */
@SuppressWarnings("serial")
public class SecondBookStoreAction extends BaseAction{
	
	private SecondBookStoreBiz  secondBookStoreBiz;
	
	private String key;
	private  Integer type;
	private  Integer value;
	
	private  Integer  id;
	private  String  ids;
	private  String subject;
	private  String content;
	private String storeType;

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	//文件上传
	private File image;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String imageFileName; 
	
	//文件上传
	private File attachment;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String attachmentFileName; 
	

	public SecondBookStoreBiz getSecondBookStoreBiz() {
		return secondBookStoreBiz;
	}

	public void setSecondBookStoreBiz(SecondBookStoreBiz secondBookStoreBiz) {
		this.secondBookStoreBiz = secondBookStoreBiz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	/**
	 * 修改狀態
	 * @return
	 */
   public  String update(){
	   secondBookStoreBiz.update(ids, type, value);
		this.outString("{success:true,message:'修改成功!'}");
		return  null;
   }
   
   public  String deleteSecondBookStore(){
	   secondBookStoreBiz.deleteSecondBookStore(id);
		this.outString("{success:true,message:'删除成功!'}");
		return  null;
  }
   
   public  String delete(){
	   secondBookStoreBiz.delete(ids);
		this.outString("{success:true,message:'删除成功!'}");
		return  null;
  }
	
   public  String viewSecondBookStore(){
	   SecondBookStore store = secondBookStoreBiz.loadById(id);
	   HttpServletRequest request = this.getRequest();  
       request.setAttribute("store", store);  
	   return  "viewSecondBookStore";
   }
	
    
	/**
	 * 分页查询
	 * @return
	 */
   public  String findPageSecondBookStore(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			String hql = "from SecondBookStore where status = 0 ";
			if(null!=key){
				hql += " and (nickname like '%"+this.key+"%' or content like '%"+this.key+"%' or subject like '%"+this.key+"%')";
			}
			secondBookStoreBiz.findPageSecondBookStore(page, hql);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
   }
   
   /**
	 * 分页查询
	 * @return
	 */
  public  String findPageAllSecondBookStore(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			String hql = "from SecondBookStore where 1 = 1 ";
			if(null!=key){
				hql += " and (nickname like '%"+this.key+"%' or content like '%"+this.key+"%' or subject like '%"+this.key+"%')";
			}
			if(null!=storeType){
				hql += " and storeType = '"+this.storeType+"'";
			}
			secondBookStoreBiz.findPageSecondBookStore(page, hql);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
  }
   
   /**
	 * 我的发布   分页查询
	 * @return
	 */
  public  String findPageMySecondBookStore(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String hql = "from SecondBookStore where publisher = "+this.getUserDTO().getUserid();
			secondBookStoreBiz.findPageMySecondBookStore(page, hql);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
  }

   /**
	 * 保存/修改 
	 */
	public String saveOrUpdateSecondBookStore() {
		try {
			String imageFilePath = "";
			String attFilePath = "";
			if (null != this.image) {
				Date d = new Date();
				String path =  ServletActionContext.getServletContext().getRealPath(
	            "upload")
	            + File.separator + d.getTime() + getFileExp(this.imageFileName);
				imageFilePath = this.getRequest().getContextPath()+"/"+"upload" + "/"+ d.getTime() + getFileExp(this.imageFileName); // 使用時間戳作為文件名
				
				File toFile = new File(path);
				writeFile(this.image, toFile);
			}
			if (null != this.attachment) {
				Date d = new Date();
				String path =  ServletActionContext.getServletContext().getRealPath(
	            "upload")
	            + File.separator + d.getTime() + getFileExp(this.attachmentFileName);
				attFilePath = this.getRequest().getContextPath()+"/"+"upload" + "/"+ d.getTime() + getFileExp(this.attachmentFileName); // 使用時間戳作為文件名
				
				File toFile = new File(path);
				writeFile(this.attachment, toFile);
			}
			SecondBookStore store = new SecondBookStore(id,subject,content,imageFilePath,attFilePath,storeType);
			UserDTO userInfo = this.getUserDTO();
			store.setPublisher(userInfo.getUserid());
			store.setNickname(userInfo.getNickname());
			
			secondBookStoreBiz.saveOrUpdaSecondBookStore(store);
			if (id == null) {
				this.outString("{success:true,message:'保存成功!',id:"+store.getId()+"}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
   
	
	private String getFileExp(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos);
	}

	private final int BUFFER_SIZE = 16 * 1024;
	
	private String writeFile(File src, File dst) {
		String style = "";
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//获取文件大小
				long   size =  src.length();
				//文件格式化
			    style = FileUtils.getFormatSize(size);
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return style;
	}
}
