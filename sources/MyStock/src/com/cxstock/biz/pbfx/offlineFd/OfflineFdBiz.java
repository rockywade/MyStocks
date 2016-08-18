package com.cxstock.biz.pbfx.offlineFd;

import java.util.List;

import com.cxstock.pojo.DatumInfo;
import com.cxstock.pojo.OfflineFd;
import com.cxstock.pojo.OfflineFdLog;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

/**
 * 线下辅导的接口
 * 
 * @author root
 * 
 */
public interface OfflineFdBiz {

	/**
	 * 管理员 分页，排序，搜索 查询
	 * 
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName
	 */
	public void findPageOfflineFb(Page page, String[] property, String[] value,
			String bmId,String orderName1,String orderName2);
	
	/**
	 * 线下辅导管理新增数据保存
	 * @param fb
	 */
	public Boolean saveOrUpdateOfflineFd(OfflineFd fb);
	
	
	/**
	 * 线下辅导项目删除
	 * 管理员端
	 * @param xmid
	 */
	public void deleteOfflineFd(String xmid);
	
	/**
	 * 批量刪除
	 * @param offlineFdAll
	 */
	public void  deleteOfflinedAll(String offlineFdAll);
	
	
	/**
	 * 线下辅导
	 * 我的报名
	 * @param fb
	 * @param st
	 */
	public Boolean  saveOrUpdate(OfflineFd fb);
	
	/**
	 * 
	 * @param xmid
	 * @return
	 */
	public OfflineFdLog findSingle(String bmId,String xmid);
	
	
	/**
	 * 报名记录新增/
	 * 修改
	 * @param log
	 * @return
	 */
	public Boolean  saveOrUpdateLog(OfflineFdLog log);

	
	/**
	 * 修改线下辅导报名
	 * @param bmnumber
	 * @param xmid
	 * @param bmstatus
	 * @return
	 */
    public  int  updateOfflineFd(Integer bmnumber, String bmstatus,String xmid);
    
    
    /**
     * 评论的修改
     * @param plnumber
     * @param xmid
     * @return
     */
    public int updatePl(Integer plnumber,String xmid);
    
    
    /**
     * 我特别关心
     * @param page
     */
    public  void  findPageTbORCy(Page page, String[] property, String[] value,
			String orderName);
    
    /**
     * 项目管理
     * @param page
     * @param xmid
     */
   public  void findMangeProject (Page page,String xmid,String orderName);
   
   
   /**
    * 根据项目id
    * 查询项目内容
    * @param xmid
    * @return
    */
   public   OfflineFd findSingOfflineFd(String xmid);
   
   /**
    * 查询参与辅学项目
    * 的学生
    * @param xmid
    * @param bmTag
    * @return
    */
   public  List<OfflineFdLog> findAllOfflineFdLog(String xmid,String bmTag);
   
   /**
    * 首页展示列表
    * @param page
    * @param property
    * @param value
    */
   public void findPageOfflineFd(Page page, String[] property, Object[] value);
   
   /**
    * 线下辅学
    * 的批量操作
    * @param pojo
    * @param offlineFdAll
    */
   public void saveOrUpdaOfflineFdAll(OfflineFd  pojo, String offlineFdAll);
   
   /**
    * 统计人气
    * @param xmid
    * @param bmTag
    * @param tbTag
    * @return
    */
   public  int  sumRenQi(String xmid);
   
   
   /**
	 * 线下辅导详情
	 * @param xmid
	 * @return
	 */
	public OfflineFdLog findSingleBy(String xmid);
}
