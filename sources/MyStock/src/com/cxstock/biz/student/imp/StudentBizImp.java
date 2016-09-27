package com.cxstock.biz.student.imp;

import java.util.List;

import com.cxstock.action.student.vo.StudentExcel;
import com.cxstock.biz.classes.ClassesBiz;
import com.cxstock.biz.headmaster.HeadMasterBiz;
import com.cxstock.biz.instructor.InstructorBiz;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.student.StudentBiz;
import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public class StudentBizImp implements StudentBiz {

	private BaseDAO baseDao;

	private UserBiz userBiz;
	
	private ClassesBiz classesBiz;



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
	public void findPageStudent(Page page,String[] property, Object[] value) {
		String hql = "from Student as model where 1=1 ";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql+=" order by xh desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtos = StudentDTO.createDtos(list);
		int total = baseDao.count("Student", property, value);
		
		page.setRoot(dtos);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateStudent(Student student) {
		Student s = new Student();
		if(null != student.getId()) {
			s = (Student) baseDao.loadById(Student.class,student.getId());
		}else{
			Student lt = (Student) baseDao.loadObject("from Student where xh='"
					+ student.getXh() + "'");
			if (lt != null) {
				return false;
			}else{
				UserDTO dto = new UserDTO();
				dto.setLogincode(student.getXh());
				dto.setPassword(student.getXh());
				dto.setState(1);
				dto.setSsyq(student.getSsyq());
				dto.setRole("2");//学生
				userBiz.saveOrUpdateUser(dto);
			}
		} 
		if(null!=student.getClasses()){
			s.setClasses(student.getClasses());
			Classes c = classesBiz.loadById(student.getClasses().getBjdm());
			s.setHeadmaster(c.getHeadMaster());
			s.setInstructor(c.getInstructor());	
		}else{
			if(null!=student.getHeadmaster()){
				s.setHeadmaster(student.getHeadmaster());
			}
			if(null!=student.getInstructor()){
				s.setInstructor(student.getInstructor());			
			}
		}
		
		if(null!=student.getAttend()){
			s.setAttend(student.getAttend());
		}
		s.setPhone(student.getPhone());
		s.setQsh(student.getQsh());
		s.setQsmc(student.getQsmc());
		s.setXb(student.getXb());
		s.setXm(student.getXm());
		s.setXh(student.getXh());
		if(null!=student.getFq()&&!"".equals(student.getFq())){
			s.setFq(student.getFq());
		}
		if(null!=student.getMq()&&!"".equals(student.getMq())){
			s.setMq(student.getMq());
		}
		
		s.setFqphone(student.getFqphone());
		s.setMqphone(student.getMqphone());
		s.setSsyq(student.getSsyq());
		baseDao.saveOrUpdate(s);
		return true;
	}

	public Student studentExist(StudentExcel studentExcel) {
		String hql = "from Student where xh='"+studentExcel.getXh()+"'";
		List<Student> studentList = baseDao.findByHql(hql);
		if(studentList.size()==0){
			return null;
		}else{
			return studentList.get(0);
		}
		
	}
	
	
	/*
	 * 删除用户
	 * 
	 */
	public void deleteStudent(Integer id) {
//		Student student = (Student)baseDao.loadById(Student.class, id);
//		Classes  classes = student.getClasses();
//		Set students = classes.getStudents();
//		students.remove(student);
//		classesBiz.saveOrUpdateClasses(c);
//		
		Student s = (Student) baseDao.loadById(Student.class, id);
		String sql = "delete from user_role where userid = (select userid from users a, tbl_student b where" +
				" a.logincode=b.xh and b.id="+id+")";
		baseDao.excuteBySql(sql);
		String hql = "delete from Users where logincode = '"+s.getXh()+"'";
		baseDao.update(hql);
		
		baseDao.deleteById(Student.class, id);
		
	}
}
