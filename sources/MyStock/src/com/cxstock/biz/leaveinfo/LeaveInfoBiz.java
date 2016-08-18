package com.cxstock.biz.leaveinfo;
import java.util.List;

import com.cxstock.pojo.Classes;
import com.cxstock.pojo.LeaveInfo;
import com.cxstock.pojo.LeaveInfoLog;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

/**
 * 请假信息的接口
 * @ wzx
 * 2016-5-24
 * 
 */
public interface LeaveInfoBiz {

	
	/**
	 * 跳转新增数据或修改数据
	 * @param student
	 * @return
	 */
	public  Student goAddOrUpdateLeaveInfo(String xh);
	
	
	/**
	 * 保存/更新数据
	 * @param dto
	 * @return
	 */
	public boolean saveOrUpdateLeaveInfo(LeaveInfo dto);
	
	
	/**
	 * 执行新增数据的保存
	 * @param dto
	 * @return
	 */
	public  boolean saveLeaveInfo(LeaveInfo qo);
	
   
	/**
	 * 老师审核查询
	 * @param page
	 * 
	 */
	public void findPageLeaveInfo(Page page,String[] property,String[] value);
	
	
	
	/**
	 * 请假信息审核/批量审核保存
	 * @param pojo
	 * @param siteid //审核/批量审核的条件
	 */
	public void saveOrUpdaLeaveInfo(LeaveInfo pojo, String studentnums);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteLeaveInfo(Integer id);
	
	/**
	 * 辅导员分页 ，搜索，排序查询
	 * @param pojo
	 * @param page
	 */
	public void  findPageLeaveInfo1(Page page, String[] property,String[] value, String orderName1,String orderName2);
	
	/**
	 * 院系领导分页 ，搜索，排序查询
	 * @param pojo
	 * @param page
	 */
	public void  findPageLeaveInfo4(Page page, String[] propertyName, Object[] value, String orderName1,String orderName2, String orderName3, 
			String classcode ,List classcodevalue );
   
	/**
	 * 根基院系查询所有班级
	 * @param yx
	 * @return
	 */
   public List<Classes> findClass(String value);
   
   
   
	public void findPageLeaveInfo10(Page page) ;
	
	/**
	 * 查询所有
	 * 班级
	 * @return
	 */
	public List<ComboData> findClassComb();
	
	/**
	 * 根据老师查询相应的班级
	 * @param property
	 * @param value
	 * @return
	 */
	public List<ComboData> findClassComb1(List<Classes> counList);
	
	/**
	 * 计算
	 * @param studentnum
	 * @return
	 */
	public Integer countTotalnum(String studentnum);
	
	
	/**
	 * 查询我的学生预览
	 * @param page
	 */
	public  void findPageLeaveInfoLog(Page page,String[] property,String[] value, String orderName);
    
	/**
	 * 保存记录
	 * @param qo
	 * @return
	 */
	public  boolean saveLeaveInfoLog(LeaveInfoLog qo);
	
	
	/**
	 * 更新LeaveInfoLog表
	 * @return
	 */
	public  int  updateLeaveInfoLog(Integer totalnum,String studentnum);
	
	/**
	 * 根据学号查询记录表的数据
	 * @param studentnum
	 * @return
	 */
	public  LeaveInfoLog findSingleLeaveInfoLog(String studentnum);
	
	/**
	 * 查询学生手机端数据
	 * @param studentnum
	 * @return
	 */
	public void   findLeaveInfo(Page page ,String studentnum,String  orderName);
	
	/**
	 * 查询学生手机端下拉数据（全部数据）
	 * @param studentnum
	 * @param orderName
	 * @return
	 */
	public  List  findListLeaveinfo(String studentnum,String  orderName);
	
}
