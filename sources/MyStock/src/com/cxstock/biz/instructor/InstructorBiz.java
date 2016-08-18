package com.cxstock.biz.instructor;

import com.cxstock.pojo.Instructor;
import com.cxstock.utils.pubutil.Page;

public interface InstructorBiz {

	
	/**
	 * 分页查询列表
	 */
	public void findPageInstructor(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改 
	 */
	public boolean saveOrUpdateInstructor(Instructor instructor);
	
	/**
	 * 删除 
	 */
	public void deleteInstructor(Integer id);
	

}
