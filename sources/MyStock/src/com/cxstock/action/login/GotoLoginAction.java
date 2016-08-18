package com.cxstock.action.login;

import javax.servlet.http.HttpSession;

import com.cxstock.action.BaseAction;
import com.cxstock.utils.system.Constants;

public class GotoLoginAction extends BaseAction {

	/*跳转登录页面*/
	public String goToLogin(){
		HttpSession session = getSession();
		session.setAttribute("gotologin", "login");
		session.removeAttribute(Constants.USERINFO);
		return "login";
	}
}
