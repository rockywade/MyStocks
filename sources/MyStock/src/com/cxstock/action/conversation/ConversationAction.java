package com.cxstock.action.conversation;

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
import com.cxstock.biz.conversation.ConversationBiz;
import com.cxstock.pojo.Conversation;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.FileUtils;

/**
 * 
 * @author root
 */
@SuppressWarnings("serial")
public class ConversationAction extends BaseAction{
	
	private ConversationBiz  conversationBiz;
	
	private Integer id;
	
	private Integer studentid;
	//谈话时间
	private String conversationtime;
	//地点
	private String conversationpalce;
	//访谈人姓名
	private String conversationname;
	//访谈人类别
	private String conversationtype;
	//主要问题
	private String problem;
	//辅导方案
	private String solution;
	
	private String mimeType;
	
	private String filename;
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}
	
	private InputStream inStream;
	
	

	//文件上传
	private File stuff;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String stuffFileName; 
	

	public ConversationBiz getConversationBiz() {
		return conversationBiz;
	}

	public void setConversationBiz(ConversationBiz conversationBiz) {
		this.conversationBiz = conversationBiz;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public String getConversationtime() {
		return conversationtime;
	}

	public void setConversationtime(String conversationtime) {
		this.conversationtime = conversationtime;
	}

	public String getConversationpalce() {
		return conversationpalce;
	}

	public void setConversationpalce(String conversationpalce) {
		this.conversationpalce = conversationpalce;
	}

	public String getConversationname() {
		return conversationname;
	}

	public void setConversationname(String conversationname) {
		this.conversationname = conversationname;
	}

	public String getConversationtype() {
		return conversationtype;
	}

	public void setConversationtype(String conversationtype) {
		this.conversationtype = conversationtype;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public File getStuff() {
		return stuff;
	}

	public void setStuff(File stuff) {
		this.stuff = stuff;
	}

	public String getStuffFileName() {
		return stuffFileName;
	}

	public void setStuffFileName(String stuffFileName) {
		this.stuffFileName = stuffFileName;
	}

	public  String viewConversation(){
		   Conversation conversation = conversationBiz.loadById(id);
		   HttpServletRequest request = this.getRequest();  
	       request.setAttribute("conversation", conversation);  
		   return  "viewConversation";
	   }
	
	/**
	 * 分页查询
	 * @return
	 */
   public  String findPageConversation(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"specialcare_id"};
			Object[] value = {this.studentid};
			conversationBiz.findPageConversation(page, property, value);
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
	public String saveOrUpdateConversation() {
		try {
			String stuffFilePath = "";
			if (null != this.stuff) {
				Date d = new Date();
				String path =  ServletActionContext.getServletContext().getRealPath(
	            "upload")
	            + File.separator + d.getTime() + getFileExp(this.stuffFileName);
				stuffFilePath = this.getRequest().getContextPath()+"/"+"upload" + "/"+ d.getTime() + getFileExp(this.stuffFileName); // 使用時間戳作為文件名
				
				File toFile = new File(path);
				writeFile(this.stuff, toFile);
			}
			Conversation con = new Conversation(this.conversationtime,this.conversationpalce,this.conversationname,this.conversationtype,this.problem,this.solution,stuffFilePath,this.studentid);
			
			conversationBiz.saveOrUpdateConversation(con);
			this.outString("{success:true,message:'保存成功!'}");
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
