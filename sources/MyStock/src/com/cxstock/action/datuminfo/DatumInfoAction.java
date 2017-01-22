package com.cxstock.action.datuminfo;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.datuminfo.DatumInfoBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.DataJiFen;
import com.cxstock.pojo.DatumInfo;
import com.cxstock.pojo.DownLog;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateTime;
import com.cxstock.utils.system.FileUtils;

/**
 * 学习资料control层
 * 
 * @author root
 */
@SuppressWarnings("serial")
public class DatumInfoAction extends BaseAction {

	private DatumInfoBiz datumInfoBiz;

	private Integer id;
	private String datumname;
	private String datumnumber;
	private String datumsize;
	private String datumstyle;
	private String shareman;
	private Date sharetime;
	private Date toptime;
	private String datumcontent;
	private Integer sharegrade;
	private String status;
	private Integer downnum;
	private String downnumber;
	private String transfetag;
	private Date createtime;
	private Integer createrid;
	private String creatername;

	// 按时间查询
	private String startdate;
	private String enddate;

	// 文件上传
	private File upload;
	// 接收文件
	private String paper;
	// fileName 前面必須和uplaod一致,不然找不到文件
	private String uploadFileName;

	// 判断传过来是上传/隐藏（1）或者下载/取消隐藏（0）
	private String ifApproval;

	// 接收前端传过来的参数
	private String datumInfoAll;

	// 下载资料的参数
	private ServletContext context;
	private String filename;
	private String mimeType;
	private InputStream inStream;

	// 下载记录
	private String downid;
	private String downname;
	private String gradeTag;
	private Date dowtime;

	/**
	 * 学习资料分页查询
	 * 
	 * @return
	 */
	public String findPageDatumInfo() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			String[] property = { "datumname", "shareman", "datumstyle",
					"status" };
			String[] value = { this.datumname, this.shareman, this.datumstyle,
					this.status };
			datumInfoBiz.findPageDatumInfo(page, property, value);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

		return null;
	}

	/**
	 * 资料上传
	 * 
	 * @return
	 */
	public String saveDatumInfo() {
		String files = "";
		String style = "";
		if (null != this.upload) {
			Date d = new Date();
			String path = ServletActionContext.getServletContext().getRealPath(
					"upload")
					+ File.separator
					+ d.getTime()
					+ getFileExp(this.uploadFileName);
			files = this.getRequest().getContextPath() + "/" + "upload" + "/"
					+ d.getTime() + getFileExp(this.uploadFileName); // 使用時間戳作為文件名

			File toFile = new File(path);
			style = writeFile(this.upload, toFile);
		} else {
			files = paper;
		}

		// 获取登录用户的信息
		UserDTO userInfo = this.getUserDTO();
		Integer zljf = userInfo.getZljf();
		if (null != zljf) {
			zljf = zljf + 2;
		} else {
			zljf = 2;
		}
		// 更新users表
		datumInfoBiz.updateUsers(zljf, userInfo.getLogincode());

		// 获取文件名截取最后三位 判断文件类型
		String st = this.uploadFileName;
		String sts = null;
		if (null != st || !st.equals("")) {
			sts = st.substring(st.lastIndexOf(".") + 1);
		}

		DatumInfo datumInfo = new DatumInfo();
		datumInfo.setId(this.id);
		// 学习资料编号 对应登录编号 方便查询
		datumInfo.setDatumnumber(userInfo.getLogincode());
		datumInfo.setDatumname(this.datumname);
		datumInfo.setShareman(userInfo.getNickname());
		datumInfo.setSharetime(new Date());
		datumInfo.setDatumstyle(sts);
		datumInfo.setDatumcontent(files);
		datumInfo.setSharegrade(this.sharegrade);
		datumInfo.setDatumsize(style);
		datumInfo.setStatus("展现中");
		datumInfo.setTransfetag("1");
		datumInfo.setToptime(new Date());
		datumInfo.setCreatetime(new Date());
		datumInfo.setCreaterid(this.getUserDTO().getUserid());
		datumInfo.setCreatername(this.getUserDTO().getNickname());
		datumInfoBiz.saveDatumInfo(datumInfo);
		if (id == null) {
			this.outString("{success:true,message:'保存成功!'}");
		} else {
			this.outString("{success:true,message:'修改成功!'}");
		}
		return null;
	}

	/**
	 * 上传资料删除
	 * 
	 * @return
	 */
	public String deleteDatumInfo() {
		try {
			datumInfoBiz.deleteDatumInfo(this.id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 批量刪除
	 * @return
	 */
	public  String  deleteDatumInfoAll(){
		try {
			datumInfoBiz.deleteDatumInfoAll(this.datumInfoAll);
			this.outString("{success:true,message:'刪除成功成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
	}
	
	
	/**
	 * 我历史下载资料/隐藏/取消隐藏
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		try {
			DatumInfo datumInfo = new DatumInfo();
			datumInfo.setId(this.id);
			datumInfo.setDatumnumber(this.datumnumber);
			datumInfo.setDatumname(this.datumname);
			datumInfo.setShareman(this.shareman);
			datumInfo.setSharetime(this.sharetime);
			datumInfo.setDatumstyle(this.datumstyle);
			datumInfo.setDatumcontent(this.datumcontent);
			datumInfo.setSharegrade(this.sharegrade);
			datumInfo.setDatumsize(this.datumsize);
			datumInfo.setStatus(this.status);
			datumInfo.setDownnum(this.downnum);
			datumInfo.setTransfetag(this.transfetag);
			datumInfo.setDownnumber(this.downnumber);
			datumInfo.setCreatetime(this.createtime);
			datumInfo.setCreaterid(this.createrid);
			datumInfo.setCreatername(this.creatername);
			datumInfo.setToptime(this.toptime);
			datumInfo.setUpdaterid(this.getUserDTO().getUserid());
			datumInfo.setUpdatername(this.getUserDTO().getNickname());
			datumInfo.setUpdatetime(new Date());
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("1")) {
				datumInfo.setStatus("隐藏中");
			}
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("0")) {
				datumInfo.setStatus("展现中");
			}
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("2")) {
				datumInfo.setToptime(new Date());
				datumInfo.setStatus("展现中/已置顶");
			}
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("3")) {
				datumInfo.setToptime(this.createtime);
				datumInfo.setStatus("展现中");
			}
			datumInfoBiz.saveDatumInfo(datumInfo);
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
	
    
	/**
	 * 批量操作
	 * @return
	 */
	public String saveOrUpdataDatumInfoAll(){
		
		try {
			DatumInfo datumInfo = new DatumInfo();
			 if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("1")) {
				datumInfo.setToptime(this.createtime);
				datumInfo.setStatus("隐藏中");
			 }
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("0")) {
				datumInfo.setStatus("展现中");
			}
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("2")) {
				datumInfo.setToptime(new Date());
				datumInfo.setStatus("展现中/已置顶");
			}
			if (null != ifApproval && !"".equals(ifApproval)
					&& ifApproval.equals("3")) {
				datumInfo.setToptime(this.createtime);
				datumInfo.setStatus("展现中");
			}
			
			datumInfoBiz.saveOrUpdaDatumInfo(datumInfo, this.datumInfoAll);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return  null;
		
	}
	
	
	/**
	 *管理员端 ：分页，排序，搜素查询
	 * 
	 * @return
	 */
	public String findPageDatumInfoBy() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			String[] property = { "datumname", "shareman", "datumstyle",
					"status" };
			String[] value = { this.datumname, this.shareman, this.datumstyle,
					this.status };
			datumInfoBiz.findPageDatumInfoBy(page, property, value, "toptime",
					"sharetime");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 学习资料 老师端分页列表
	 * 
	 * @return
	 */
	public String findPageDatumInfoOrderBy() {
		status = "展现中";
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			String[] property = { "datumname", "shareman", "datumstyle",
					"status" };
			String[] value = { this.datumname, this.shareman, this.datumstyle,
					this.status };
			datumInfoBiz.findPageDatumInfoOrderBy(page, property, value,
					"sharetime", "downnum", "sharegrade");
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 我资料下载列表
	 * 
	 * @return
	 */
	public String findByHqlAndDate() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			// 获取登录者登录号
			String downid = this.getUserDTO().getLogincode();
			
			//结束时间加1
			String enddate = DateTime.getNewDay1(this.enddate, 1);
			datumInfoBiz.findByHqlAndDate(page, this.startdate, enddate,
					"downid", downid, "dowtime");
			this.outPageString(page);
		} catch (ParseException e) {
			e.printStackTrace();
			this.outError();
		}

		return null;
	}

	/**
	 * 我上传的资料列表
	 * 
	 * @return
	 */
	public String findByHqlAndDate1() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());

			// 获取登录者登录号对应资料编号
			datumnumber = this.getUserDTO().getLogincode();
			//结束时间加1
			String enddate = DateTime.getNewDay1(this.enddate, 1);
			datumInfoBiz.findByHqlAndDate1(page, this.startdate, enddate,
					"datumnumber", datumnumber, "sharetime");
			this.outPageString(page);
		} catch (ParseException e) {
			e.printStackTrace();
			this.outError();
		}

		return null;
	}

	/**
	 * 资料评分
	 * 
	 * @return
	 */
	public String saveOrUpdateDownLog() {
		try {
			DownLog downLog = new DownLog();
			downLog.setDownid(this.downid);
			downLog.setDatumname(this.datumname);
			downLog.setDatumcontent(this.datumcontent);
			downLog.setDatumnumber(this.datumnumber);
			downLog.setDownname(this.downname);
			downLog.setDatumstyle(this.datumstyle);
			downLog.setDatumsize(this.datumsize);
			downLog.setDownnumber(this.downnumber);
			downLog.setGradeTag("Y");
			if (null != this.dowtime) {
				downLog.setDowtime(this.dowtime);
			}
			downLog.setSharegrade(this.sharegrade);
			datumInfoBiz.saveOrUpdataDownLog(downLog);

			if (null != this.downid && !"".equals(this.downid)) {
				// 计算平均评分
				Integer sumSharegrade = datumInfoBiz.sumSharegrade(
						"datumnumber", this.datumnumber, "gradeTag", "Y");

				String id = this.downid.split("_")[1];
				// 获取学习资料数据
				DatumInfo datumInfo = datumInfoBiz.findDatumInfoById(Integer
						.parseInt(id));
				if (null != datumInfo) {
					DatumInfo dt = new DatumInfo();
					dt.setId(datumInfo.getId());
					dt.setDatumnumber(datumInfo.getDatumnumber());
					dt.setDatumname(datumInfo.getDatumname());
					dt.setShareman(datumInfo.getShareman());
					dt.setSharetime(datumInfo.getSharetime());
					dt.setDatumstyle(datumInfo.getDatumstyle());
					dt.setDatumcontent(datumInfo.getDatumcontent());
					// 平均分
					dt.setSharegrade(sumSharegrade);
					dt.setDatumsize(datumInfo.getDatumsize());
					dt.setStatus(datumInfo.getStatus());

					dt.setDownnum(datumInfo.getDownnum());

					// 下载编号 方便我们的历史下载查询
					dt.setDownnumber(datumInfo.getDownnumber());
					dt.setTransfetag(datumInfo.getTransfetag());
					dt.setCreatetime(datumInfo.getCreatetime());
					dt.setCreaterid(datumInfo.getCreaterid());
					dt.setCreatername(datumInfo.getCreatername());
					dt.setToptime(datumInfo.getToptime());
					dt.setUpdaterid(this.getUserDTO().getUserid());
					dt.setUpdatername(this.getUserDTO().getNickname());
					dt.setUpdatetime(new Date());
					datumInfoBiz.saveDatumInfo(dt);
				}

			}
			if (downid == null) {
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

	/**
	 * 查询分享之星
	 * 
	 * @return
	 */
	public String findAllDataJiFen() {
		try {
			List<DataJiFen> list = new ArrayList<DataJiFen>();
			list = datumInfoBiz.findAllDataJiFen();
			List<UserDTO> list1 = new ArrayList<UserDTO>();
			if (null != list) {
				if(list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						UserDTO dto = new UserDTO();
						dto.setNickname(list.get(i).getNickname());
						dto.setZljf(list.get(i).getZljf());
						dto.setOrderName("No." + (i+1));
						list1.add(dto);
					}
				} else {
					UserDTO dto = new UserDTO();
					dto.setNickname("每月1号统计上月分享之星");
					dto.setZljf(null);
					list1.add(dto);
				}
				
				this.outListString(list1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;

	}

	// 下载方法
	public DatumInfoAction() {
		context = ServletActionContext.getServletContext();

	}

	@Override
	public String execute() throws Exception {
		mimeType = context.getMimeType(filename);
		return SUCCESS;
	}

	public InputStream getInStream() {
		// 获取登录用户的信息
		UserDTO userInfo = this.getUserDTO();

		// 获取参数路径
		DatumInfo datumInfo = datumInfoBiz.findDatumInfoById(this.id);
		if (datumInfo != null) {
		   if(null != this.ifApproval && !"".equals(this.ifApproval)&&
	 	             this.ifApproval.equals("1")){
				    // 学习资料
					DatumInfo dt = new DatumInfo();
					dt.setId(this.id);
					dt.setDatumnumber(datumInfo.getDatumnumber());
					dt.setDatumname(datumInfo.getDatumname());
					dt.setShareman(datumInfo.getShareman());
					dt.setSharetime(datumInfo.getSharetime());
					dt.setDatumstyle(datumInfo.getDatumstyle());
					dt.setDatumcontent(datumInfo.getDatumcontent());
					dt.setSharegrade(datumInfo.getSharegrade());
					dt.setDatumsize(datumInfo.getDatumsize());
					dt.setStatus(datumInfo.getStatus());
					// 每下载一次加1
					if (null == datumInfo.getDownnum() || datumInfo.getDownnum() == 0) {
						dt.setDownnum(1);
					} else {
						dt.setDownnum(datumInfo.getDownnum() + 1);
					}
					// 下载编号 方便我们的历史下载查询
					dt.setDownnumber(userInfo.getLogincode());
					dt.setTransfetag("0");
					dt.setCreatetime(datumInfo.getCreatetime());
					dt.setCreaterid(datumInfo.getCreaterid());
					dt.setCreatername(datumInfo.getCreatername());
					dt.setToptime(datumInfo.getToptime());
					dt.setUpdaterid(this.getUserDTO().getUserid());
					dt.setUpdatername(this.getUserDTO().getNickname());
					dt.setUpdatetime(new Date());
		        
				    //判断是否评分过
					DownLog lg = datumInfoBiz.findDownLogById(userInfo.getLogincode() + "_"
									+ datumInfo.getId());
					
					// 下载记录新增或者修改
					DownLog downLog = new DownLog();
					downLog.setDownid(userInfo.getLogincode() + "_"+ datumInfo.getId());
					downLog.setDatumname(datumInfo.getDatumname());
					downLog.setDatumcontent(datumInfo.getDatumcontent());
					downLog.setDatumnumber(datumInfo.getDatumnumber());
					downLog.setDownname(this.getUserDTO().getNickname());
					downLog.setDatumstyle(datumInfo.getDatumstyle());
					downLog.setDatumsize(datumInfo.getDatumsize());
					downLog.setDownnumber(userInfo.getLogincode());
					if(null != lg){
					 if(lg.getGradeTag().equals("Y")){
					   downLog.setGradeTag("Y");
					   downLog.setSharegrade(lg.getSharegrade());
					}
					}else{
						downLog.setGradeTag("N");
					    downLog.setSharegrade(0);
					}
					downLog.setDowtime(datumInfo.getSharetime());
					
					datumInfoBiz.saveOrUpdataDownLog(downLog);

					datumInfoBiz.saveDatumInfo(dt);
			  }
			     // 获取文件名称
				String st = datumInfo.getDatumcontent().substring(
						datumInfo.getDatumcontent().lastIndexOf("/") + 1);
				filename = st;
			
		}

		inStream = context.getResourceAsStream("/upload/" + filename);
		if (inStream == null) {
			inStream = new ByteArrayInputStream("Sorry,File not found !"
					.getBytes());
		}
		return inStream;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setFilename(String filename) {
		try {
			this.filename = new String(filename.getBytes("ISO8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
		}
	}

	public String getFilename() {
		try {
			return new String(filename.getBytes(), "ISO8859-1"); // UTF-8
																	// ISO8859-1
		} catch (UnsupportedEncodingException e) {
			return this.filename;
		}
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public DatumInfoBiz getDatumInfoBiz() {
		return datumInfoBiz;
	}

	public void setDatumInfoBiz(DatumInfoBiz datumInfoBiz) {
		this.datumInfoBiz = datumInfoBiz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatumname() {
		return datumname;
	}

	public void setDatumname(String datumname) {
		this.datumname = datumname;
	}

	public String getDatumnumber() {
		return datumnumber;
	}

	public void setDatumnumber(String datumnumber) {
		this.datumnumber = datumnumber;
	}

	public String getDatumsize() {
		return datumsize;
	}

	public void setDatumsize(String datumsize) {
		this.datumsize = datumsize;
	}

	public String getDatumstyle() {
		return datumstyle;
	}

	public void setDatumstyle(String datumstyle) {
		this.datumstyle = datumstyle;
	}

	public String getShareman() {
		return shareman;
	}

	public void setShareman(String shareman) {
		this.shareman = shareman;
	}

	public Date getSharetime() {
		return sharetime;
	}

	public void setSharetime(Date sharetime) {
		this.sharetime = sharetime;
	}

	public Date getToptime() {
		return toptime;
	}

	public void setToptime(Date toptime) {
		this.toptime = toptime;
	}

	public Integer getSharegrade() {
		return sharegrade;
	}

	public void setSharegrade(Integer sharegrade) {
		this.sharegrade = sharegrade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDownnum() {
		return downnum;
	}

	public void setDownnum(Integer downnum) {
		this.downnum = downnum;
	}

	public String getDownnumber() {
		return downnumber;
	}

	public void setDownnumber(String downnumber) {
		this.downnumber = downnumber;
	}

	public String getTransfetag() {
		return transfetag;
	}

	public String getDatumcontent() {
		return datumcontent;
	}

	public void setDatumcontent(String datumcontent) {
		this.datumcontent = datumcontent;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreaterid() {
		return createrid;
	}

	public void setCreaterid(Integer createrid) {
		this.createrid = createrid;
	}

	public String getCreatername() {
		return creatername;
	}

	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}

	public void setTransfetag(String transfetag) {
		this.transfetag = transfetag;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getIfApproval() {
		return ifApproval;
	}

	public void setIfApproval(String ifApproval) {
		this.ifApproval = ifApproval;
	}

	public String getDatumInfoAll() {
		return datumInfoAll;
	}

	public void setDatumInfoAll(String datumInfoAll) {
		this.datumInfoAll = datumInfoAll;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	// 上传文件的文件名
	private String getFileExp(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos);
	}

	private static final int BUFFER_SIZE = 16 * 1024;

	private static String writeFile(File src, File dst) {
		String style = "";
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				// 获取文件大小
				long size = src.length();
				// 文件格式化
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

	public String getDownid() {
		return downid;
	}

	public void setDownid(String downid) {
		this.downid = downid;
	}

	public String getDownname() {
		return downname;
	}

	public void setDownname(String downname) {
		this.downname = downname;
	}

	public String getGradeTag() {
		return gradeTag;
	}

	public void setGradeTag(String gradeTag) {
		this.gradeTag = gradeTag;
	}

	public Date getDowtime() {
		return dowtime;
	}

	public void setDowtime(Date dowtime) {
		this.dowtime = dowtime;
	}


}
