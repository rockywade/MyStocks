package com.cxstock.biz.siteinfo;

import java.util.Date;
import java.util.List;

import com.cxstock.pojo.IfOpeTimeLog;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.pojo.SiteInfo;
import com.cxstock.pojo.SiteInfoLog;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

/**
 * 场地信息接口
 * @author wzx
 * 2016-5-27
 */
public interface SiteInfoBiz {
	
	
	/**
	 * 场地信息申请保存
	 * @param stinfo
	 * @return
	 */
	public boolean saveSiteInfo(SiteInfo stinfo);
	
	/**
	 * 根据场地id查询
	 * 场地编号
	 * @param id
	 * @return
	 */
	public SiteInfo findSing(Integer id);
	
	/**
	 * 根据场地siteid查询
	 * 场地编号
	 * @param siteid
	 * @return
	 */
	public SiteInfo findBySiteid(String siteid);
	
	/**
	 * 场地信息批量修改保存
	 * @param pojo
	 * @param siteid //审核/批量审核的条件
	 */
	public void saveOrUpdaSiteInfo(SiteInfoLog pojo, String siteids);
	
	
	/**
	 * 场地申请分页列表查询
	 * 按条件查询
	 * @param page
	 * @param property
	 * @param value
	 */
	public void findPageSiteInfo(Page page, String[] property,String[] value);
	
	/**
	 * 删除场地
	 * @param id
	 */
	public void deleteSiteInfo(Integer id);
	
	/**
	 * 查询所有场地
	 * 使用时间情况
	 * @param clazz
	 * @return
	 */
	public List<SiteInfoLog>  findAllData(String  siteid);
	
	/**
	 * 场地分页，搜索
	 * 排序查询
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName
	 */
	public void  findPageSiteInfoBy(Page page, String[] property,String[] value, String orderName);
	
	
	/**
	 * 场地申请分页查询
	 * @param page
	 * @param propertyName
	 * @param value
	 * @param timeName
	 * @param orderName1
	 * @param startTime
	 * @param endTime
	 */
	public void  findPageApplySiteInfo(Page page,String[] propertyName, Object[] value, 
			String timeName,String orderName1,final String startTime,final String endTime);
	
	
	/**
	 * 查询所有辅导
	 * 员（管理老师）
	 * @return
	 */
	public List<ComboData> findSiteInfoComb();
	
	
	/**
	 * 查询所有园区
	 * @return
	 */
	public List<ComboData> findParkComb();
	
	/**
	 * 查询所有组织
	 * @return
	 */
	public List<ComboData> findOrgaComb();
	
	/**
	 * 查询所有场地类型
	 * @return
	 */
	public List<ComboData> findGroundTypeComb();
	
	

	
	/**
	 * 场地申请记录保存
	 * @param log
	 * @return
	 */
	public  Boolean  saveOrUpdateSiteInfoLog(SiteInfoLog log);
	
	/**
	 * 申请场地列表查询
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName
	 */
	public void  findPageSiteInfoLog(Page page, String[] property,String[] value, String orderName);
	
	
	/**
	 * 查询场地所有
	 * 数据
	 * @return
	 */
	public  List<SiteInfoLog> findAllDate1();
	
	/**
	 * 24小时后不审批场地申请
	 * 自动释然
	 * @param logId
	 */
	public  void deleteSiteInfoLog(String logId);
	
	
	/**
	 * 根据id查询场地是否
	 * 再24小时之内已审核
	 * @param logId
	 * @return
	 */
	public SiteInfoLog  findSingLog(String logId);
	
	/**
	 * 场地不对外开放的时间
	 * 记录
	 * @param ifOpeTimeLog
	 */
	public  Boolean  saveOrUpdateIfOpeTimeLog(IfOpeTimeLog  ifOpeTimeLog);
	
	
	/**
	 * 根据id查询所有
	 * 场地不对外开放的时间
	 * @param siteid
	 * @return
	 */
	public List<IfOpeTimeLog>  findAllIfOpeTimeLog(String  siteid);
	
	/**
	 * 查询场地不对外开放时间
	 * @param siteid
	 * @return
	 */
	public IfOpeTimeLog findSingleIfOpeTimeLog(String  siteid);
	
	
	/**
	 * 删除场地不对外开放时间
	 * @param siteid
	 */
	public void deleteIfOpeTimeLog(String  opeId);
	
	
	/**
	 * 图片预览
	 * @param id
	 * @return
	 */
	public SiteInfo loadById(Integer id);
	
	/**
	 * 学生场地申请 手机端
	 * @param propertyName
	 * @param value
	 * @param timeName
	 * @param orderName1
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public  List findListSite( String[] propertyName,
			Object[] value, String timeName, String orderName1, String startTime,
			String endTime);
	
	
	public  void  findPageSiteInfoLog1(Page page, String[] propertyName,
			Object[] value, String timeName, String orderName1, String startTime,
			String endTime);
	
	/**
	 * 手机端 申请场地取消
	 * @param logId
	 * @return
	 */
	public int updateSiteInfoLog(String status,String logId);
}
