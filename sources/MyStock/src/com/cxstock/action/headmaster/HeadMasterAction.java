package com.cxstock.action.headmaster;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.action.student.vo.StudentExcel;
import com.cxstock.biz.headmaster.HeadMasterBiz;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.ExcelHelper;
import com.cxstock.utils.system.FileUtils;
import com.cxstock.utils.system.JxlExcelHelper;

@SuppressWarnings("serial")
public class HeadMasterAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	
	private String hclass;
	
	
	private HeadMasterBiz headMasterBiz;
	
	//文件上传
	private File excel;
	//fileName 前面必須和uplaod一致,不然找不到文件
	private String excelFileName; 
	
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZgh() {
		return zgh;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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



	public String getHclass() {
		return hclass;
	}

	public void setHclass(String hclass) {
		this.hclass = hclass;
	}

	public HeadMasterBiz getHeadMasterBiz() {
		return headMasterBiz;
	}

	public void setHeadMasterBiz(HeadMasterBiz headMasterBiz) {
		this.headMasterBiz = headMasterBiz;
	}

	/**
	 * 分页查询 
	 */
	public String findPageHeadMaster() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			String[] property = {"zgh","xm"};
			Object[] value = {this.zgh,this.xm};
			headMasterBiz.findPageHeadMaster(page,property,value);
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
	public String saveOrUpdateHeadMaster() {
		try {
			Set<Classes> hclassSet = new HashSet<Classes>();
			String[] ic = hclass.split(",");
			for(int i=0;i<ic.length;i++){
				hclassSet.add(new Classes(ic[i]));
			}
			HeadMaster hm = new HeadMaster(id,zgh,xm,xb,phone,hclassSet);
			hm.setSsyq(ssyq);
			boolean b = headMasterBiz.saveOrUpdateHeadMaster(hm);
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
	public String deleteHeadMaster() {
		try {
			headMasterBiz.deleteHeadMaster(id);
			this.outString("{success:true}");
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
			String[] fieldNames = new String[]{"zgh","xm","xb","phone","ssyq", "headClass"};
			
			List<HeadMaster> importHeadMasterList = exh.readExcel(HeadMaster.class, fieldNames, true);
			HeadMaster headMaster = null;
			for(int i=0;i<importHeadMasterList.size();i++){
				headMaster = importHeadMasterList.get(i);
				HeadMaster s = headMasterBiz.headMasterExist(headMaster);
				if(s != null) {
					headMaster.setId(s.getId());
				}
				Set<Classes> hclassSet = new HashSet<Classes>();
				String[] ic = headMaster.getHeadClass().split(",");
				for(int j=0;j<ic.length;j++){
					hclassSet.add(new Classes(ic[j]));
				}
				headMasterBiz.saveOrUpdateHeadMaster(headMaster);
			}
			importFile.delete();
			this.outString("{success:true,message:'导入成功!'}");
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
