package com.cxstock.biz.power.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.dao.BaseDAO;
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
import com.cxstock.utils.pubutil.Page;

public class UserBizImpl implements UserBiz {

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * 登录验证
	 * 
	 * @see com.cxstock.biz.power.UserBiz#login(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public UserDTO login(String code, String pass) {
		String hql = "from Users as t where t.logincode='" + code
				+ "' and t.password='" + pass + "'";
		Users user = (Users) baseDao.loadObject(hql);
		if (user != null) {
			// user.getUserid();
			UserDTO dto = UserDTO.createDto(user);// 初始化user
			hql = "from Vusermenu as t where t.userid=" + user.getUserid();
			List<Vusermenu> list = baseDao.findByHql(hql);
			Map<Integer,String> map = new HashMap<Integer,String>();
			Iterator itV = list.iterator();
			Vusermenu vusermenu = null;
			while(itV.hasNext()){
				vusermenu = (Vusermenu)itV.next();
				if(null!=map.get(vusermenu.getMenuid())){
					itV.remove();
				}else{
					map.put(vusermenu.getMenuid(), "");
				}
			}
			hql = "from UserMenu where zgh='" + code + "' order by ordernum";
			List<UserMenu> userMenuList = baseDao.findByHql(hql);
			Iterator itU = userMenuList.iterator();
			UserMenu userMenu = null;
			boolean exist = false;
			while(itU.hasNext()){
				exist = false;
				userMenu = (UserMenu)itU.next();
				for(int i=0;i<list.size();i++){
					if(userMenu.getMenuid()==list.get(i).getMenuid()){
						exist = true;
						break;
					}
				}
				if(exist){
					itU.remove();
				}
			}
			StringBuilder menus = new StringBuilder();
			menus.append("<div class='backW'><div class='backAside'><div class='backAside_top'><img src='../../admin/img/backAsideTop.png' /></div><ul class='backAside_ul'>");
			vusermenu = null;
			
			for(int i=0;i<userMenuList.size();i++){
				userMenu = userMenuList.get(i);
				menus.append("<li").append("><a href='#'").
					append(" onclick='toUrl(\"").append(userMenu.getMenuurl()).append("\")'><img src='../../admin/img/").
					append(userMenu.getIcon()).append("'>").append(userMenu.getMenuname()).append("</a><div class='nav_div'></div></li>");
			}
			for(int i=0;i<list.size();i++){
				vusermenu = list.get(i);
				menus.append("<li").append("><a href='#'").
					append(" onclick='toUrl(\"").append(vusermenu.getMenuurl()).append("\")'><img src='../../admin/img/").
					append(vusermenu.getIcon()).append("'>").append(vusermenu.getMenuname()).append("</a><div class='nav_div'></div></li>");
			}
			menus.append("</ul><div class='backAside_bottom'><img src='../../admin/img/backAsideBottom.png' /></div></div></div>");
			Set<Role> roles = user.getRoles();
			StringBuilder sb = new StringBuilder("");
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				sb.append(((Role)it.next()).getRolename()).append(",");
			}
			if(sb.length()>0){
				dto.setRolename(sb.substring(0, sb.length()-1));
			}else{
				dto.setRolename("");
			}
			
			dto.setUsermenu(menus.toString());
			return dto;
		}
		return null;
	}

	/*
	 * 分页查询用户列表
	 * 
	 * @see
	 * com.cxstock.biz.power.UserBiz#findPageUser(com.cxstock.utils.system.Page)
	 */
	@SuppressWarnings("unchecked")
	public void findPageUser(Page page) {
		String hql = "from Users as t left join fetch t.roles a where a.rolename='管理员' order by t.userid";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtoList = UserDTO.createDtos(list);
		String countSql = "select count(*) " + hql;
		int total = baseDao.countQuery(countSql);
		page.setRoot(dtoList);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 * 
	 * @see
	 * com.cxstock.biz.power.UserBiz#saveOrUpdateUser(com.cxstock.biz.power.
	 * dto.UserDTO)
	 */
	public boolean saveOrUpdateUser(UserDTO dto) {
		Users user = new Users();
		if (dto.getUserid() != null) {
			user = (Users) baseDao.loadById(Users.class, dto.getUserid());
		} else {
			Users u = (Users) baseDao.loadObject("from Users where logincode='"
					+ dto.getLogincode() + "'");
			if (u != null) {
				if(null!=dto.getRole()&&!"".equals(dto.getRole())){
					Set<Role> roles = u.getRoles();
					if(null==roles){
						roles = new HashSet<Role>();
					}
					String[] role = dto.getRole().split(",");
					for(int i=0;i<role.length;i++){
						boolean f = false;
						for(Role r : roles){
							if(r.getRoleid()==Integer.parseInt(role[i])){
								f = true;
								break;
							}
						}
						if(!f){
							roles.add(new Role(Integer.parseInt(role[i])));
						}
					}
					u.setRoles(roles);
					baseDao.saveOrUpdate(u);
				}
				return false;
			}
			user.setLogincode(dto.getLogincode());
			user.setState(1);
		}
		user.setPassword(dto.getPassword());
		Set<Role> roles = user.getRoles();
		if(null==roles){
			roles = new HashSet<Role>();
		}
		String[] role = dto.getRole().split(",");
		for(int i=0;i<role.length;i++){
			roles.add(new Role(Integer.parseInt(role[i])));
		}
		user.setRoles(roles);
		user.setSsyq(dto.getSsyq());
		baseDao.saveOrUpdate(user);
		return true;
	}
	
	public boolean updatePwd(String pwd,Integer userid) {
		String hql = "update Users set password = '"+pwd+"' where userid="+userid;
		baseDao.update(hql);
		return true;
	}
	
	public boolean updateNickName(String nickname,Integer userid) {
		String hql = "update Users set nickname = '"+nickname+"' where userid="+userid;
		baseDao.update(hql);
		return true;
	}
	
	public boolean updatePhone(UserDTO dto,String phone) {
		String hql = "";
		if(null!=dto.getStudent()){
			hql = "update Student set phone = '"+phone+"' where id="+dto.getStudent().getId();
			baseDao.update(hql);
		}
		if(null!=dto.getInstructor()){
			hql = "update Instructor set phone = '"+phone+"' where id="+dto.getInstructor().getId();
			baseDao.update(hql);
		}
		if(null!=dto.getLeaderShip()){
			hql = "update LeaderShip set phone = '"+phone+"' where id="+dto.getLeaderShip().getId();
			baseDao.update(hql);
		}
		if(null!=dto.getExpert()){
			hql = "update Expert set phone = '"+phone+"' where id="+dto.getExpert().getId();
			baseDao.update(hql);
		}
		if(null!=dto.getHeadMaster()){
			hql = "update HeadMaster set phone = '"+phone+"' where id="+dto.getHeadMaster().getId();
			baseDao.update(hql);
		}
		if(null!=dto.getNewFriends()){
			hql = "update NewFriends set phone = '"+phone+"' where id="+dto.getNewFriends().getId();
			baseDao.update(hql);
		}
		return true;
	}

	/*
	 * 删除用户
	 * 
	 * @see com.cxstock.biz.power.UserBiz#deleteUser(java.lang.String)
	 */
	public void deleteUser(Integer userid) {
		baseDao.deleteById(Users.class, userid);
	}

	/* 查询学生对象 */
	public Student getStudent(String logincode) {
		String hql = "from Student as t where t.xh='" + logincode + "'";
		return (Student) baseDao.loadObject(hql);
	}

	//班主任查询
	public HeadMaster getHeadMaster(String logincode) {
		String hql = "from HeadMaster as h where h.zgh = '"+logincode+"'";
		return (HeadMaster) baseDao.loadObject(hql);
	}

	//辅导员查询
	public Instructor getInstructor(String logincode) {
		String hql = "from Instructor as i where i.zgh = '"+logincode+"'";
		return (Instructor) baseDao.loadObject(hql);
	}

	//院系领导查询
	public LeaderShip getLeaderShip(String logincode) {
		String hql = "from LeaderShip as l where l.zgh = '"+logincode+"'";
		return (LeaderShip) baseDao.loadObject(hql);
	}

	//新生之友查询
	public NewFriends getNewFriends(String logincode) {
		String hql = "from NewFriends as n where n.zgh = '"+logincode+"'";
		return (NewFriends) baseDao.loadObject(hql);
	}

	//专家查询
	public Expert getExpert(String logincode) {
		String hql = "from Expert as n where n.zgh = '"+logincode+"'";
		return (Expert) baseDao.loadObject(hql);
	}

	
	
	
	/**
	 * 根据姓名查询专家电话
	 */
	@Override
	public Expert findExpert(String xm) {
		if (xm == null || xm == "") {
			return null;
		}
		Expert expert = (Expert) baseDao
				.loadObject("from Expert where xm ='" + xm
						+ "'");
		if (expert != null) {
			return expert;
		}
		
		return null;
	}

	 /**
	  * 根据姓名查询班主电话
	  */
	@Override
	public HeadMaster findHeadMaster(String xm) {
		if (xm == null || xm == "") {
			return null;
		}
		HeadMaster headMaster = (HeadMaster) baseDao
				.loadObject("from HeadMaster where xm ='" + xm
						+ "'");
		if (headMaster != null) {
			return headMaster;
		}
		
		return null;
	}

	/**
	 * 根据姓名查询辅导员电话
	 */
	@Override
	public Instructor findInstructor(String xm) {
		if (xm == null || xm == "") {
			return null;
		}
		Instructor instructor = (Instructor) baseDao
				.loadObject("from Instructor where xm ='" + xm
						+ "'");
		if (instructor != null) {
			return instructor;
		}
		
		return null;
	}
    
	/**
	 * 根据姓名查询院系领导死暗花
	 */
	@Override
	public LeaderShip findLeaderShip(String xm) {
		if (xm == null || xm == "") {
			return null;
		}
		LeaderShip leaderShip = (LeaderShip) baseDao
				.loadObject("from LeaderShip where xm ='" + xm
						+ "'");
		if (leaderShip != null) {
			return leaderShip;
		}
		return null;
	}
	
	public void updatePwd(String logincode){
		String hql = "update Users set password = '"+logincode+"' where logincode='"+logincode+"'";
		baseDao.update(hql);
	}

}
