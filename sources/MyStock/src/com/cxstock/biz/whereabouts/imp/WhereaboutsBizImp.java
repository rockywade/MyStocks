package com.cxstock.biz.whereabouts.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.whereabouts.WhereaboutsBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;
import com.cxstock.pojo.Unit;
import com.cxstock.pojo.Wheresaboutscensus;
import com.cxstock.pojo.Wheresaboutslaunch;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

public class WhereaboutsBizImp implements WhereaboutsBiz {

	private BaseDAO baseDao;	
	
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}


	/**
	 * 发起去向统计
	 */
	public int saveLaunch(Wheresaboutslaunch wheres) {
		baseDao.save(wheres);
		return 1;
	}
	
	/**
	 * 发起去向列表
	 */
	@SuppressWarnings("unchecked")
	public void findPageLaunch(Page page,String launchname) {
		String hql = "from Wheresaboutslaunch as model where 1=1 ";
		if(launchname!=null && !launchname.equals("")){
			hql += " and model.launchname like '%"+launchname+"%'";
		}
		String[] orderName = {"launchtime"};
		String[] orderType = {"DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		String hqlc = "select count(*) "+hql;
		int total = baseDao.countQuery(hqlc);
		page.setRoot(list);
		page.setTotal(total);
	}


	/**
	 * 撤销去向统计
	 */
	public int updateLaunchStyle(String launchid , String launchstyle) {
			String hql = "update Wheresaboutslaunch as w set w.launchstyle='"+launchstyle+ "' where w.launchid='"+launchid+"'";
			return baseDao.update(hql);
	}

	/**
	 * 待填写假日去向统计
	 */
	public Wheresaboutslaunch findNeedWriteWheres(String todaytime,String launchstyle, String ssxy) {
		String hql = "from Wheresaboutslaunch as w where w.censusendtime >= '"+todaytime+"' and w.launchstyle = '"+launchstyle+"'" + " and (w.ssxy like'%" +ssxy+"%' or w.ssxy IS NULL or w.ssxy='')";
		return (Wheresaboutslaunch) baseDao.loadObject(hql);
	}

	/**
	 * 提交填写假日去向统计
	 */
	public int saveCensus(Wheresaboutscensus wherecensus) {
		baseDao.save(wherecensus);
		return 1;
	}

	/**
	 * 去向填写更新
	 */
	public void updateObject(Wheresaboutscensus wherecensus) {
		baseDao.saveOrUpdate(wherecensus);
	}

	/**
	 * 更新假日去向发起表登记人数
	 */
	public int updateLaunchStyle(String launchid, int censuspersonnum) {
		String hql = "update Wheresaboutslaunch as w set w.censuspersonnum='"+censuspersonnum+ "' where w.launchid='"+launchid+"'";
		return baseDao.update(hql);
	}

	/**
	 * 个人假日去向统计清单
	 */
	@SuppressWarnings("unchecked")
	public void findPageCensusByUserId(Page page,String censususernum) {
		String hql = "from Wheresaboutscensus as model where model.censususernum = '"+censususernum+"'";
		@SuppressWarnings("unused")
		String hqlc = "select count(*) "+hql;
		String[] orderName = {"holidaytime"};
		String[] orderType = {"DESC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(hqlc);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 假日去向数据统计
	 */
	@SuppressWarnings("unchecked")
	public void findPageCensusById(Page page, String[] porperty,
			String[] values, String launchid) {
		String hql = "from Wheresaboutscensus as model where model.launchid = '"+launchid+"'";
		for(int i=0;i<porperty.length;i++){
			if(values[i]!=null){
				hql += " and model."+porperty[i]+" like '%"+values[i]+"%'";
			}
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"censususernum"};
		String[] orderType = {"ASC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(chql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 教师端去向统计浏览
	 */
	public void findPageCensusByProperty(Page page, String[] property,
			String[] values, String tmajor, String[] tclasses) {
		String hql = "from Wheresaboutscensus as model where model.mojar = '"+tmajor+"' ";
		//查询老师所属班级
		for(int i=0;i<tclasses.length;i++){
			if(i == 0) {
				hql += " and ";
			}
			hql += " model.classes = '" +tclasses[i]+ "'";
			if(tclasses.length>1 && i<tclasses.length-1){
				hql += " or ";
			}
		}
		//获取模糊查询的值
		for(int i=0;i<property.length;i++){
			if(null!=values[i]){
				hql += " and model." + property[i] + " like '%"+values[i]+"%'";
			}
		}
		
		String hqlc = "select count(*) "+hql;
		String[] orderName = {"holidaytime","censususernum"};
		String[] orderType = {"DESC","ASC"};
		List list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		int total = baseDao.countQuery(hqlc);
		page.setRoot(list);
		page.setTotal(total);
	}


	/**
	 * 是否填写去向统计
	 */
	public Wheresaboutscensus findCensus(String logincode, String launchid) {
		String hql = "from Wheresaboutscensus as model where model.censususernum = '"+logincode+"' and model.launchid = '"+launchid+"'";
		return (Wheresaboutscensus) baseDao.loadObject(hql);
	}


	/**
	 * 去向统计详细信息
	 */
	public List<Wheresaboutscensus> findWhereCenById(String censusid) {
		String hql = "from Wheresaboutscensus as model where model.censusid = '"+censusid+"'";
		return baseDao.findByHql(hql);
	}


	/**
	 * 查询所有班级
	 */
	@SuppressWarnings("unchecked")
	public List<ComboData> findClassesComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Classes>  counList = baseDao.listAll("Classes");
		for (Classes c : counList) {
			ComboData comb = new ComboData();
			comb.setValue(c.getBjdm());
			comb.setText(c.getBjdm());
			list.add(comb);
		}
		return list;
	}


	
}
