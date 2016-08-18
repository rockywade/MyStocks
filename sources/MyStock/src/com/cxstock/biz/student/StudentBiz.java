package com.cxstock.biz.student;

import com.cxstock.action.student.vo.StudentExcel;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public interface StudentBiz {

	
	/**
	 * 分页查询列表
	 */
	public void findPageStudent(Page page,String[] property, Object[] value);
	
	/**
	 * 保存/修改 
	 */
	public boolean saveOrUpdateStudent(Student Student);
	
	/**
	 * 删除 
	 */
	public void deleteStudent(Integer id);
	
	public Student studentExist(StudentExcel studentExcel);

}
