package com.cxstock.action.instructor;

import java.util.HashSet;
import java.util.Set;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.instructor.InstructorBiz;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.Instructor;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class InstructorAction extends BaseAction {

	private Integer id;
	private String zgh;
	private String xm;
	private String xb;
	private String phone;
	private String ssyq;
	
	private String iclass;
	
	private String ids;
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private InstructorBiz instructorBiz;
	
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

	public String getIclass() {
		return iclass;
	}

	public void setIclass(String iclass) {
		this.iclass = iclass;
	}


	public InstructorBiz getInstructorBiz() {
		return instructorBiz;
	}

	public void setInstructorBiz(InstructorBiz instructorBiz) {
		this.instructorBiz = instructorBiz;
	}

	

	
	/**
	 * 分页查询 
	 */
	public String findPageInstructor() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if(getUserDTO().getLogincode().equals("admin")) {
				String[] property = {"zgh","xm"};
				Object[] value = {this.zgh,this.xm};
				instructorBiz.findPageInstructor(page,property,value);
			} else {
				String[] property = {"zgh","xm","ssyq"};
				Object[] value = {this.zgh,this.xm,getUserDTO().getSsyq()};
				instructorBiz.findPageInstructor(page,property,value);
			}
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
	public String saveOrUpdateInstructor() {
		try {
			Set<Classes> iclassSet = new HashSet<Classes>();
			String[] ic = iclass.split(",");
			for(int i=0;i<ic.length;i++){
				iclassSet.add(new Classes(ic[i]));
			}
			Instructor instructor = new Instructor(id,zgh,xm,xb,phone,iclassSet);
			instructor.setSsyq(ssyq);
			boolean b = instructorBiz.saveOrUpdateInstructor(instructor);
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
	public String deleteInstructor() {
		try {
			instructorBiz.deleteInstructor(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	/**
	 * 删除多个用户
	 */
	public String deleteInstructors() {
		try {
			String[] idArr = ids.split(",");
			for(String id : idArr) {
				
				instructorBiz.deleteInstructor(Integer.valueOf(id));
			}
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	

}
