package com.cxstock.biz.whereabouts;

import java.util.List;

import com.cxstock.pojo.Attend;
import com.cxstock.pojo.Wheresaboutscensus;
import com.cxstock.pojo.Wheresaboutslaunch;
import com.cxstock.utils.pubutil.Page;

public interface WhereaboutsBiz {
	/**
	 * 发起去向统计
	 * @param wheres
	 * @return
	 */
	int saveLaunch(Wheresaboutslaunch wheres);
	/**
	 * 发起去向列表
	 * @param page
	 * @param launchname 
	 */
	void findPageLaunch(Page page, String launchname);
	/**
	 * 撤销去向统计
	 * @param launchid
	 * @param launchstyle
	 * @return
	 */
	int updateLaunchStyle(String launchid, String launchstyle);
	/**
	 * 查找待填写假日去向统计
	 * @param todaytime
	 * @param launchstyle
	 * @return
	 */
	Wheresaboutslaunch findNeedWriteWheres(String todaytime,String launchstyle);
	/**
	 * 提交假日去向统计
	 * @param wherecensus
	 * @return
	 */
	int saveCensus(Wheresaboutscensus wherecensus);
	/**
	 * 跟新假日去向发起表登记人数
	 * @param launchid
	 * @param censuspersonnum
	 * @return
	 */
	int updateLaunchStyle(String launchid, int censuspersonnum);
	/**
	 * 个人假日去向统计清单
	 * @param page
	 * @param censususernum
	 */
	void findPageCensusByUserId(Page page, String censususernum);
	/**
	 * 假日去向数据统计
	 * @param page
	 * @param porperty
	 * @param values
	 * @param id
	 */
	void findPageCensusById(Page page, String[] porperty, String[] values,
			String id);
	/**
	 * 教师端去向统计浏览
	 * @param page
	 * @param property
	 * @param values
	 * @param tmajor
	 * @param tclasses
	 */
	void findPageCensusByProperty(Page page, String[] property,
			String[] values, String tmajor, String[] tclasses);
	/**
	 * 去向填写修改
	 * @param wherecensus
	 */
	void updateObject(Wheresaboutscensus wherecensus);
	/**
	 * 是否填写去向统计
	 * @param logincode
	 * @param launchid
	 * @return
	 */
	Wheresaboutscensus findCensus(String logincode, String launchid);
	/**
	 * 去向统计详细信息
	 * @param censusid
	 * @return
	 */
	List<Wheresaboutscensus> findWhereCenById(String censusid);
	/**
	 * 查询所有班级
	 * @return
	 */
	List findClassesComb();

}
