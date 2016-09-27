package com.cxstock.biz.instructor.imp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.biz.instructor.InstructorBiz;
import com.cxstock.biz.instructor.dto.InstructorDTO;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.student.StudentBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public class InstructorBizImp implements InstructorBiz {

	private BaseDAO baseDao;

	private UserBiz userBiz;
	
	private ClassesBiz classesBiz;
	
	private StudentBiz studentBiz;

	public StudentBiz getStudentBiz() {
		return studentBiz;
	}


	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}


	public ClassesBiz getClassesBiz() {
		return classesBiz;
	}


	public void setClassesBiz(ClassesBiz classesBiz) {
		this.classesBiz = classesBiz;
	}
	
	public UserBiz getUserBiz() {
		return userBiz;
	}


	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	public BaseDAO getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}


	/*
	 * 分页查询 
	 */
	@SuppressWarnings("unchecked")
	public void findPageInstructor(Page page,String[] property, Object[] value) {
		String hql = "from Instructor as model where 1=1";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by zgh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = InstructorDTO.createDtos(list);
		int total = baseDao.count("Instructor", property, value);
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateInstructor(Instructor instructor) {
		Instructor i = new Instructor();
		if(null != instructor.getId()) {
			i = (Instructor) baseDao.loadById(Instructor.class,instructor.getId());
		}else{
			Instructor lt = (Instructor) baseDao.loadObject("from Instructor where zgh='"
					+ instructor.getZgh() + "'");
			if (lt != null) {
				instructor.setId(lt.getId());
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(instructor.getZgh());
				dto.setPassword(instructor.getZgh());
				dto.setState(1);
				dto.setSsyq(instructor.getSsyq());
				dto.setRole("3");//辅导员
				userBiz.saveOrUpdateUser(dto);
			}
		} 
		if(null!=instructor.getIclass()){
			i.setIclass(instructor.getIclass());
		}
		
		if(null!=instructor.getXb()&&!"".equals(instructor.getXb())){
			i.setXb(instructor.getXb());
		}
		if(null!=instructor.getSsyq()&&!"".equals(instructor.getSsyq())){
			i.setSsyq(instructor.getSsyq());
		}
		i.setPhone(instructor.getPhone());
		i.setXm(instructor.getXm());
		i.setZgh(instructor.getZgh());
		baseDao.saveOrUpdate(i);
		instructor.setId(i.getId());
		return true;
	}

	/*
	 * 删除用户
	 * 
	 */
	public void deleteInstructor(Integer id) {
		Instructor instructor = (Instructor)baseDao.loadById(Instructor.class, id);
		Set<Classes> classes = instructor.getIclass();
		Iterator it = classes.iterator();
		Classes c = null;
		while(it.hasNext()){
			c = (Classes)it.next();
			c.setInstructor(null);
			classesBiz.saveOrUpdateClasses(c);
		}
		String sql = "delete from user_role where userid = (select userid from users a, tbl_instructor b where" +
		" a.logincode=b.zgh and b.id="+id+") and roleid=3";
		baseDao.excuteBySql(sql);
		String stuUpSql = "UPDATE tbl_student SET instructor_id = null where instructor_id = " + id;
		baseDao.excuteBySql(stuUpSql);
		baseDao.deleteById(Instructor.class, id);
	}
}
