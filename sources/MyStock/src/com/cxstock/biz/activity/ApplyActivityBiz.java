package com.cxstock.biz.activity;

import java.util.List;

import com.cxstock.pojo.Activity;
import com.cxstock.pojo.Attend;
import com.cxstock.pojo.HeadMaster;
import com.cxstock.pojo.News;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

public interface ApplyActivityBiz {

	void saveApplyActivity(Activity activity);

	List<?> selectCheckActivity();

	Activity findActivityInfoById(String activityid);
	
	int updateyById(String applystyle,String activityid);

	List<Activity> findAllActivity(String usernum);

	void findPageApply(Page page, String[] property, String[] value, String ssyq);

	void findPageApply(Page page,String value,String usernum);

	void findMyAttendActivity(Page page,String[] property,String[] value,String usernum);

	int deleteStateById(String state, String activityid, Integer stdid);

	int updateStateActivityById(String state, String activityid);

	int saveAttendActivity(Attend attend);
	
	public Student findStudentById(Integer id) ;

	List<Attend> findAttendById(String activityid);

	void updateActivityById(Activity activity);

	void updateClassById(String clazz, String setproperty, String applystyle,
			String byid, String activityid);

	int saveClazz(News news);

	News findClazzById(String clazz, String findproperty, String idValue);

	void findPageActivity(Page page, String[] property, String[] values,
			String findstyle);

	void updateNews(News news);

	int updateClassById(String clazz, String setproperty, String state,
			String[] byid, String[] valuesId);
	/**
	 * 分页，查询字段，
	 * @param page
	 * @param value
	 * @param applystyle
	 * @param toDayDate
	 * @param activitygenre 
	 */
	void findPageActivityPassed(Page page, String value, String applystyle,String toDayDate, String activitygenre);
	/**
	 * 判断数据是否存在
	 * @param usernum
	 * @param activityid
	 * @return
	 */
	Attend findIsOrNotAttend(String usernum, String activityid);
	/**
	 * 纪实考评
	 * @param page
	 * @param property
	 * @param values
	 * @param master
	 * @param tid
	 * @param inschoolterm 
	 * @return
	 */
	void findPageAttendByProperty(Page page, String[] property,
			String[] values, HeadMaster master, int tid, String inschoolterm);
	/**
	 * 获取学生信息
	 * @param logincode
	 * @return
	 */
	Student findStudentById(String logincode);
	/**
	 * 根据id更新数组中的属性
	 * @param clazz
	 * @param setproperty
	 * @param updatevalues
	 * @param byid
	 * @param activityid
	 */
	void updateClassPropertiesById(String clazz, String[] setproperty,
			String[] updatevalues, String byid, String activityid);
	/**
	 * 更新参加活动得分
	 * @param clazz
	 * @param setproperty
	 * @param score
	 * @param byid
	 * @param valuesId
	 * @return
	 */
	int updateScoreById(String clazz, String setproperty, int score,
			String[] byid, String[] valuesId);
	/**
	 * 更新参加活动的总得分
	 * @param clazz
	 * @param setproperty
	 * @param sum
	 * @param setbyid
	 * @param updatevaluesId
	 */
	void updateGrossById(String clazz, String setproperty, int sum,
			String setbyid, String updatevaluesId);
	/**
	 * 获取总得分
	 * @param clazz
	 * @param value
	 * @param setbyid
	 * @param updatevaluesId
	 * @return
	 */
	int countGross(String clazz, String value, String setbyid,String updatevaluesId);
	/**
	 * 公示
	 * @param page
	 * @param propety
	 * @param values
	 */
	void findPagePublicity(Page page, String[] propety, String[] values);
	/**
	 * 新闻专栏
	 * @param page
	 */
	void findPageNews(Page page);
	/**
	 * 首页活动报名
	 * @param page
	 * @param object
	 */
	void findPageActivityInfo(Page page, Object object);

	/**
	 * 具体类型活动
	 * @param page 
	 * @param key
	 * @param serchKey 
	 */
	void findActivityByGenre(Page page, String key, String serchKey);

	/**
	 * 公示参加活动信息
	 * @param page 
	 * @param activityid
	 */
	void findAttendActivityInfoById(Page page, String activityid);

	/**
	 * 新闻详情
	 * @param newsid
	 * @return
	 */
	News findNewsDetail(String newsid);

	/**
	 * 获取已结束活动还未发布新闻和公示的活动
	 * @return
	 */
	List<Activity> findAllActivity();

	/**
	 * 没有导入参加活动名单的活动
	 * @param activityid 
	 * @return
	 */
	int findAllNotImportFileOfActivity(String activityid);
	
	/**
	 * 公示审核
	 * @param pulishkey
	 * @param activityid
	 */
	void updatePublicityCheck(String pulishkey, String activityid);

	/**
	 * 所在学期
	 * @return
	 */
	List findSzxqComb();

	/**
	 * 查询活动
	 * @param usernum
	 * @param activityid
	 * @return
	 */
	Attend findAttendByIds(String usernum, String activityid);


	void deleteActivity(String activityid);
}
