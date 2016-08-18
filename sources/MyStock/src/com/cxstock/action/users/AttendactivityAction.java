package com.cxstock.action.users;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;

public class AttendactivityAction extends BaseAction {

	//private 
	
	public String attendActivity(){
		/*分页*/
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		
		UserDTO userInfo = (UserDTO) getSession().getAttribute(Constants.USERINFO);
		String usernum = userInfo.getLogincode();
		
		
		
		return null;
	}
}
