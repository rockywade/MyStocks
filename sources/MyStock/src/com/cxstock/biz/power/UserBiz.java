package com.cxstock.biz.power;

import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public interface UserBiz {

	/**
	 * 登录验证
	 */
	public UserDTO login(String code, String pass);
	
	/**
	 * 分页查询用户列表
	 */
	public void findPageUser(Page page);
	
	/**
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateUser(UserDTO dto);
	
	/**
	 * 删除用户
	 */
	public void deleteUser(Integer userid);
	
	/*查询学生对象*/
	public Student getStudent(String logincode);
	
	//查询班主任
	public HeadMaster getHeadMaster(String logincode);

	//查询辅导员
	public Instructor getInstructor(String logincode);
	
	//查询院系领导
	public LeaderShip getLeaderShip(String logincode);
	
	//查询新生之友
	public NewFriends getNewFriends(String logincode);
	
	//查询专家
	public Expert getExpert(String logincode);
	
	public boolean updatePwd(String pwd,Integer userid);
	
	public boolean updateNickName(String nickname,Integer userid);
	
	public boolean updatePhone(UserDTO dto,String phone);
	
	
	//根据姓名查询 班主电话
	public HeadMaster findHeadMaster(String xm);
	
	//根据姓名查询辅导员电话
	public Instructor findInstructor(String xm);
	
	//根据姓名查询院系领导电话
	public LeaderShip findLeaderShip(String xm);
	
	//根据姓名查询专家电话
	public  Expert  findExpert(String xm);
	
	public void updatePwd(String logincode);
}
