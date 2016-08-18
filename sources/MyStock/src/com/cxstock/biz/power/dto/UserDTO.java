package com.cxstock.biz.power.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Role;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.UserMenu;
import com.cxstock.pojo.Users;
import com.cxstock.pojo.Vusermenu;

public class UserDTO {

	private Integer userid;
	private String logincode;
	private String password;
	private List<Integer> roleids;
	private Integer roleid;
	private String role;
	private String rolename;
	private Integer state;
	private String usermenu;
	private Student student;
	private String ssyq;
	private HeadMaster headMaster;
	private Instructor instructor;
	private LeaderShip leaderShip;
	private NewFriends newFriends;
	private Expert expert;
	private String student_id;
	private String phone;
	
	
	public String getPhone() {
		if(null==phone){
			return "";
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	//昵称
	private String nickname;
	//上传资料积分
	private Integer zljf;
	//分享之星排序名字
	private String orderName;
	

	public HeadMaster getHeadMaster() {
		return headMaster;
	}

	public void setHeadMaster(HeadMaster headMaster) {
		this.headMaster = headMaster;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public LeaderShip getLeaderShip() {
		return leaderShip;
	}

	public void setLeaderShip(LeaderShip leaderShip) {
		this.leaderShip = leaderShip;
	}

	public NewFriends getNewFriends() {
		return newFriends;
	}

	public void setNewFriends(NewFriends newFriends) {
		this.newFriends = newFriends;
	}

	public UserDTO() {
		super();
	}

	public UserDTO(Integer userid, String logincode, String password,
			  Integer state) {
		super();
		this.userid = userid;
		this.logincode = logincode;
		this.password = password;
		this.state = state;
		
	}

	public UserDTO(Integer userid, String logincode, String password,String role, String rolename, Integer state,
			 Object obj) {
		super();
		this.userid = userid;
		this.logincode = logincode;
		this.password = password;
		this.role = role;
		this.rolename = rolename;
		this.state = state;
	}

	public static UserDTO createDto(Users pojo) {
		UserDTO dto = null;
		if (pojo != null) {
			dto = new UserDTO(pojo.getUserid(), pojo.getLogincode(),pojo.getPassword(), 
					pojo.getState());
			if(null==pojo.getNickname()||"".equals(pojo.getNickname())){
				dto.setNickname(pojo.getLogincode());
			}else{
				dto.setNickname(pojo.getNickname());
			}
			
			dto.setSsyq(pojo.getSsyq());
			dto.setZljf(pojo.getZljf());
		}
		
		return dto;
	}

	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				Users user = (Users)iterator.next();
				UserDTO dto = createDto(user);
				Set<Role> roles = user.getRoles();
				StringBuilder sb = new StringBuilder("");
				StringBuilder roleid = new StringBuilder("");
				Iterator it = roles.iterator();
				Role r = null;
				while (it.hasNext()) {
					r = (Role)it.next();
					sb.append(r.getRolename()).append(",");
					roleid.append(r.getRoleid()).append(",");
				}
				if(sb.length()>0){
					dto.setRolename(sb.substring(0, sb.length()-1));
					dto.setRole(roleid.substring(0, roleid.length()-1));
					
				}else{
					dto.setRolename("");
					dto.setRole("");
				}
				list.add(dto);
			}
		}
		return list;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public List<Integer> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<Integer> roleids) {
		this.roleids = roleids;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public String getUsermenu() {
		return usermenu;
	}

	public void setUsermenu(String usermenu) {
		this.usermenu = usermenu;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String studentId) {
		student_id = studentId;
	}

	public String getNickname() {
		if(null==nickname){
			return "";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}	

	public Integer getZljf() {
		return zljf;
	}

	public void setZljf(Integer zljf) {
		this.zljf = zljf;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

   
}
   