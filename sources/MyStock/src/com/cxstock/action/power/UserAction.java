package com.cxstock.action.power;

import java.util.List;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.sms.SmsBiz;
import com.cxstock.biz.usermenu.UserMenuBiz;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.Instructor;
import com.cxstock.pojo.LeaderShip;
import com.cxstock.pojo.NewFriends;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.pubutil.TreeNodeChecked;
import com.cxstock.utils.system.Constants;
import com.cxstock.utils.system.SmsUtil;

@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	private UserBiz userBiz;
	
	private SmsBiz smsBiz;

	private Integer userid;
	private String logincode;
	private String password;
	private String nickName;
	private String phone;
	private List<Integer> roleids;
	private String role;
	private Integer roleid;
	

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SmsBiz getSmsBiz() {
		return smsBiz;
	}

	public void setSmsBiz(SmsBiz smsBiz) {
		this.smsBiz = smsBiz;
	}

	private Integer state;

	/** 登录验证 */
	public String login() {
		try {
			String code = logincode.trim().toLowerCase();
			String pass = password.trim().toLowerCase();
			UserDTO userInfo = userBiz.login(code, pass);
			// System.out.println(userInfo.getRolename());
			if (userInfo != null) {
				if (code.startsWith("3")) {
					Student student = userBiz.getStudent(userInfo
							.getLogincode());
					userInfo.setStudent(student);
					userInfo.setPhone(student.getPhone());
				}else if(code.startsWith("00")){
					HeadMaster headMaster = userBiz.getHeadMaster(userInfo.getLogincode());
					Instructor instructor = userBiz.getInstructor(userInfo.getLogincode());
					LeaderShip leaderShip = userBiz.getLeaderShip(userInfo.getLogincode());
					NewFriends newFriends = userBiz.getNewFriends(userInfo.getLogincode());
					Expert expert = userBiz.getExpert(userInfo.getLogincode());
					
					userInfo.setHeadMaster(headMaster);
					userInfo.setInstructor(instructor);
					userInfo.setLeaderShip(leaderShip);
					userInfo.setNewFriends(newFriends);
					userInfo.setExpert(expert);
					if(null!=headMaster){
						userInfo.setPhone(headMaster.getPhone());
					}
					if(null!=leaderShip){
						userInfo.setPhone(leaderShip.getPhone());
					}
					if(null!=newFriends){
						userInfo.setPhone(newFriends.getPhone());
					}
					if(null!=expert){
						userInfo.setPhone(expert.getPhone());
					}
					if(null!=instructor){
						userInfo.setPhone(instructor.getPhone());
					}
				}else{
					
				}
				this.getSession().setAttribute(Constants.USERINFO, userInfo);
				return "success";
			} else {
				this.getRequest().setAttribute("error", "用户名或密码错误");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("error", "连接失败");
			return "login";
		}
	}

	/** 用户权限菜单 */
	public String getMenuBuf() {
		UserDTO userInfo = this.getUserDTO();
		try {
			if (userInfo != null) {
				this.outString(userInfo.getUsermenu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 分页查询用户列表
	 */
	public String findPageUser() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			userBiz.findPageUser(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改用户
	 */
	public String saveOrUpdateUser() {
		try {
			UserDTO dto = new UserDTO(userid, logincode, password, role,
					null, state, null);
			boolean bool = userBiz.saveOrUpdateUser(dto);
			if (bool) {
				if (userid == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			} else {
				this.outString("{success:false,errors:'用户账号已存在!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/**
	 * 修改密码
	 */
	public String updatePwd() {
		userBiz.updatePwd(password,this.getUserDTO().getUserid());
		Sms sms = smsBiz.findById(14);
	    SmsUtil.sendSms(this.getUserDTO().getPhone(), sms.getContent());
		this.outString("{success:true,message:'修改成功!'}");
		return null;
	}
	
	/**
	 * 修改昵称
	 */
	public String updateNickName() {
		
		userBiz.updateNickName(nickName,this.getUserDTO().getUserid());
		this.getUserDTO().setNickname(nickName);
		this.outString("{success:true,message:'修改成功!'}");
		return null;
	}
	
	/**
	 * 修改手机号
	 */
	public String updatePhone() {
		userBiz.updatePhone(this.getUserDTO(),phone);
		this.getUserDTO().setPhone(phone);
		this.outString("{success:true,message:'修改成功!'}");
		return null;
	}
	
	/**
	 * 重置密码
	 */
	public String resetPwd() {
		userBiz.updatePwd(logincode);
		this.outString("{success:true,message:'修改成功!'}");
		return null;
	}

	/**
	 * 删除用户
	 */
	public String deleteUser() {
		try {
			userBiz.deleteUser(userid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<Integer> roleids) {
		this.roleids = roleids;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public Integer getUserid() {
		return userid;
	}

	public String getLogincode() {
		return logincode;
	}

	public String getPassword() {
		return password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
