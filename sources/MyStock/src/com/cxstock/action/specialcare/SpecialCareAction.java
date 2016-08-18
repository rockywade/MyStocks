package com.cxstock.action.specialcare;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.specialcare.SpecialCareBiz;
import com.cxstock.pojo.SpecialCare;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.FileUtils;
import com.cxstock.utils.system.JxlExcelHelper;

/**
 * 
 * @author root
 */
@SuppressWarnings("serial")
public class SpecialCareAction extends BaseAction{
	
	private SpecialCareBiz  specialCareBiz;
	
	private Integer id;
	//学号
	private String xh;
	//姓名
	private String xm;
	//行政班
	private String administrativeClass;
	//班主任
	private String bzr;
	//辅导员
	private String fdy;
	//经济困难
	private String economic;
	//学业困难
	private String academic;
	//心理困难
	private String mental;
	
	private String mimeType;
	
	private String filename;
	private String qsh;
	private String phone;
	private String fq;
	private String fqphone;
	private String mq;
	private String mqphone;
	private String bzrphone;
	private String fdyphone;
	private String xszy;
	private String xszyphone;
	private String xszylogincode;
	private String bzrlogincode;
	private String fdylogincode;
	
	//导出文件
	private ServletContext context;
	
	//构造，获取context
	public SpecialCareAction(){
		context = ServletActionContext.getServletContext();
	}
	
	
	public ServletContext getContext() {
		return context;
	}


	public Integer getId() {
		return id;
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


	public String getBzrphone() {
		return bzrphone;
	}


	public void setBzrphone(String bzrphone) {
		this.bzrphone = bzrphone;
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


	public void setId(Integer id) {
		this.id = id;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilename() {
		 try {  
	            return new String(filename.getBytes(),"ISO8859-1");  
	        } catch (UnsupportedEncodingException e) {  
	            return this.filename;  
	        }
	}

	public void setFilename(String filename) {
		try {  
           this.filename = new String(filename.getBytes("ISO8859-1"),"GBK");  
       } catch (UnsupportedEncodingException e) {  
       }
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	private InputStream inStream;
	
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
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

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public String getFdy() {
		return fdy;
	}

	public void setFdy(String fdy) {
		this.fdy = fdy;
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

	//文件上传
	private File excel;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String excelFileName; 
	
	public SpecialCareBiz getSpecialCareBiz() {
		return specialCareBiz;
	}

	public void setSpecialCareBiz(SpecialCareBiz specialCareBiz) {
		this.specialCareBiz = specialCareBiz;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public  String getSpecialCare(){
	    try {
			SpecialCare s = specialCareBiz.getSpecialCare(this.xh);
			
			this.outObjString(s);
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
   public  String findPageSpecialCare(){
	    try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			String[] property = {"xh","xm","administrativeClass","bzr","fdy","economic","academic","mental"};
			Object[] value = {this.xh,this.xm,this.administrativeClass,this.bzr,this.fdy,this.economic,this.academic,this.mental};
			UserDTO user = this.getUserDTO();
			specialCareBiz.findPageSpecialCare(page,this.getUserDTO().getLogincode(),null, property, value);
			
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			 this.outError();
		}
	   return  null;
   }
   
 //重写execute方法
	public String execute() throws Exception {
		mimeType = context.getMimeType(filename);
		return SUCCESS;
	}
	
	public String update() throws Exception {
		specialCareBiz.update(id, economic, academic, mental);
		this.outString("{success:true,message:'保存成功!'}");
		return  null; 
	}
   
	public String save(){
		try {
			SpecialCare sc = new SpecialCare(this.xh,this.xm,this.administrativeClass,this.economic,this.academic,this.mental,this.qsh,this.phone,this.fq,this.fqphone,this.mq,this.mqphone,
					this.bzr,this.bzrphone,this.fdy,this.fdyphone,this.xszy,this.xszyphone,
					this.xszylogincode,this.bzrlogincode,this.fdylogincode);
			specialCareBiz.saveOrUpdateSpecialCare(sc);
			this.outString("{success:true,message:'添加成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
   
   public String importExcel(){
		try {
			Date d = new Date();
			String path =  ServletActionContext.getServletContext().getRealPath(
            "upload")
            + File.separator + d.getTime() + getFileExp(this.excelFileName);
			
			File importFile = new File(path);
			writeFile(this.excel, importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[]{"xh","xm","administrativeClass","economic","academic","mental",
    		"qsh","phone","fq","fqphone","mq","mqphone","bzr","bzrlogincode","bzrphone",
    		"fdy","fdylogincode","fdyphone","xszy","xszylogincode","xszyphone"};
			
			List<SpecialCare> importSpecialCareList = exh.readExcel(SpecialCare.class, fieldNames, true);
			for(int i=0;i<importSpecialCareList.size();i++){
				specialCareBiz.saveOrUpdateSpecialCare(importSpecialCareList.get(i));
			}
			this.outString("{success:true,message:'导入成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

    //导出名单(exportExcel.do)
	public InputStream getInStream() {
		filename = new Date().getTime() + ".xls"; 
		try {
			//待参加学生名单，过滤掉了取消参加活动的学生
			String[] property = {"xh","xm","administrativeClass","bzr","fdy","economic","academic","mental"};
			String[] value = {this.xh,this.xm,this.administrativeClass,this.bzr,this.fdy,this.economic,this.academic,this.mental};
			String v = "";
			for(int i=0;i<value.length;i++){
				v = value[i];
				if(null!=v&&!"".equals(v)){
					v = java.net.URLDecoder.decode(v, "UTF-8");
					value[i] = v;
				}
			}
			List<SpecialCare> specialCareList = specialCareBiz.findByProperty("SpecialCare", property, value);
			
	        String[] titles = new String[]{"学号","姓名","行政班","经济困难","学业困难","心理困难",
					"寝室","本人电话","父亲","父亲电话","母亲","母亲电话","班主任","班主任职工号","班主任电话",
					"辅导员","辅导员职工号","辅导员电话","新生之友","新生之友职工号","新生之友电话"};
	        String[] fieldNames = new String[]{"xh","xm","administrativeClass","economic","academic","mental",
	        		"qsh","phone","fq","fqphone","mq","mqphone","bzr","bzrlogincode","bzrphone",
	        		"fdy","fdylogincode","fdyphone","xszy","xszylogincode","xszyphone"};
			
	        String path = ServletActionContext.getServletContext().getRealPath("file")+ File.separator;
	       
	        File exportFile = new File(path+filename);
	        ExcelHelper exh = JxlExcelHelper.getInstance(exportFile);
	        exh.writeExcel(SpecialCare.class, specialCareList, fieldNames, titles);
	       
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		inStream = context.getResourceAsStream("/file/"+filename);
		
		if(inStream==null){
           inStream = new ByteArrayInputStream("Sorry,File not found !"  
                   .getBytes());
       }
		return inStream;
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
