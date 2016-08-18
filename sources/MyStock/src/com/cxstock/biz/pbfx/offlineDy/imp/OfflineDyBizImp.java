package com.cxstock.biz.pbfx.offlineDy.imp;

import com.cxstock.biz.pbfx.offlineDy.OfflineDyBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.utils.pubutil.Page;

/**
 * 线下答疑接口实现
 * @author root
 *
 */
public class OfflineDyBizImp implements OfflineDyBiz{

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 线下答疑列表查询
	 */
	@Override
	public void findPageOfflineDy(Page page, String[] property, String[] value,
			String orderName) {
		// TODO Auto-generated method stub
		
	}

}
