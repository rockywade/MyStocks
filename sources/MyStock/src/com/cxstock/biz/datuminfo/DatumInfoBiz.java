package com.cxstock.biz.datuminfo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cxstock.pojo.DataJiFen;
import com.cxstock.pojo.DatumInfo;
import com.cxstock.pojo.DownLog;
import com.cxstock.pojo.LeaveInfo;
import com.cxstock.pojo.Sms;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;

/**
 * 学习资料接口
 * @author root
 *
 */
public interface DatumInfoBiz {
	
	
	/**
	 * 管理端的学习资料分页查询
	 * @param page
	 * @param property
	 * @param value
	 */
	public void findPageDatumInfo(Page page,String[] property,String[] value);
	
	/**
	 * 资料上传数据保存
	 * @param datumInfo
	 * @return
	 */
	public boolean saveDatumInfo(DatumInfo datumInfo);
	
	/**
	 * 我上传资料删除
	 * @param id
	 */
	public void deleteDatumInfo(Integer id);
	
	
	/**
	 * 批量刪除
	 * @param datumInfoAll
	 */
	public void  deleteDatumInfoAll(String datumInfoAll);
	
	
	
	/**
	 * 学习资料隐藏/批量隐藏
	 * @param pojo
	 * @param studentnums
	 */
	public void saveOrUpdaDatumInfo(DatumInfo  pojo, String datumInfoAll);
	
	
	/**
	 * 查询下载需要的路径
	 * @param id
	 * @return
	 */
	public  DatumInfo  findDatumInfoById(Integer id);
	
	
	/**
	 * 管理员端  分页，排序，搜索
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 */
	public void findPageDatumInfoBy(Page page, String[] property,String[] value, String orderName1,String orderName2);
	
    
	/**
	 * 老师端 分页 排序，搜素
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 * @param orderName3
	 */
	public  void  findPageDatumInfoOrderBy(Page page, String[] property,String[] value, String orderName1,String orderName2,String orderName3);
   
	/**
	 * 下载资料记录新增或修改
	 * @param downLog
	 */
	public  boolean  saveOrUpdataDownLog(DownLog downLog);
	
	
	
	/**
	 * 学习资料下载
	 * @param page
	 * @param startDate
	 * @param endDate
	 * @param downid
	 */
	public void  findByHqlAndDate(Page page, String startDate, String endDate,String propertyname,String downid,String orderName)throws ParseException;
  
	
	/**
	 * 学习资料上传
	 * @param page
	 * @param startDate
	 * @param endDate
	 * @param propertyname
	 * @param datumnumber
	 * @param orderName
	 * @throws ParseException
	 */
	public void  findByHqlAndDate1(Page page, String startDate, String endDate,String propertyname,String datumnumber,String orderName)throws ParseException;
	
	
	
	/**
	 * 统计所有下载资料评分
	 * @param propertyname
	 * @param downid
	 * @param gradeTag
	 * @return
	 */
	public  Integer  sumSharegrade(String propertyname1,String datumnumber,String propertyname2,String gradeTag);
	
	
	/**
	 * 更新uesr表
	 * @return
	 */
	public  int  updateUsers(Integer zljf,String logincode);
	
	
	/**
	 * 查询user表的
	 * 资料积分最大的前
	 * 10条
	 * @param zljf
	 * @return
	 */
	public List<Users>  findUsers(Page page,String logincode,String zljf);
	
	/**
	 * 资料的分享之星记录表
	 * @param date
	 * @return
	 */
	public  void saveOrUpdateDataJiFen();
	
	/**
	 * 清空user
	 * 的积分
	 * @return
	 */
	public  int  updateAllUser();
	
	/**
	 * 查询学生信息
	 * @param xh
	 * @return
	 */
	public  Student findStudent(String xh);
	
	/**查询分享之星的
	 * 所有数据
	 * @return
	 */
	public List<DataJiFen> findAllDataJiFen();
	
	/**
	 * 查询判断是否已经下载过
	 * @param downid
	 * @return
	 */
	public  DownLog  findDownLogById(String  downid);
	
}
