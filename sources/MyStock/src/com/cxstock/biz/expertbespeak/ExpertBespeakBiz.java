package com.cxstock.biz.expertbespeak;

import java.util.List;

import com.cxstock.biz.expertbespeak.dto.StartExpertInfoDTO;
import com.cxstock.biz.expertbespeak.dto.StudentbespeakDTO;
import com.cxstock.pojo.Expert;
import com.cxstock.pojo.StartExpertInfo;
import com.cxstock.pojo.Student;
import com.cxstock.pojo.Studentbespeak;
import com.cxstock.utils.pubutil.Page;

public interface ExpertBespeakBiz {
	
	/**
	 * 专家信息发布
	 * @param startExpertInfo
	 * @return
	 */
	boolean saveOrUpdateExpertBespeak(StartExpertInfo startExpertInfo);
	/**
	 * 学生预约
	 * @param studentBespeak
	 * @return
	 */
	boolean saveOrUpdateStudentbespeak(Studentbespeak studentBespeak);
	/**
	 * (Student)我的预约
	 * @param page
	 * @param id
	 */
	void findPageStudentBespeakById(Page page, Integer id);
	/**
	 * (Expert)我的预约
	 * @param page
	 * @param id
	 */
	void findPageStudentBespeakByEepertId(Page page, Integer id);
	/**
	 * 预约审核
	 * @param checkkey
	 * @param stdid
	 */
	boolean updateStudentBespeakById(String checkkey, Integer stid);
	/**
	 * 添加谈话
	 * @param talkcontent
	 * @param stid
	 */
	void updateStudentBespeakTalkById(String talkcontent,Integer stid);
	/**
	 * 专家列表
	 * @param page
	 * @param values 
	 * @param property 
	 */
	void findPageStartExpertInfoById(Page page, String[] property, String[] values);
	/**
	 * 专家详情
	 * @param id
	 * @return
	 */
	StartExpertInfoDTO findPageStartExpertInfoById(Integer id);
	/**
	 * 取消预约
	 * @param sbid
	 */
	void updateBespeakState(Integer sbid);
	/**
	 * 已指定专家
	 * @param values 
	 * @param property 
	 * @param page 
	 */
	void findPageAlreadyEnter(Page page, String[] property, String[] values);
	/**
	 * 删除预约
	 * @param sbid
	 */
	void deleteExpert(Integer sbid);
	/**
	 * 待指定专家
	 * @param page
	 * @param property
	 * @param values
	 */
	void findPageWaitEnter(Page page, String[] property, String[] values);
	/**
	 * 给待指定专家学生，分配专家
	 * @param stid
	 * @param id
	 */
	boolean updateStudentBespeakByIds(Integer stid, Integer id);
	/**
	 * 我的预约详情
	 * @param sbid
	 */
	StudentbespeakDTO findMySbDetailById(Integer sbid);
	/**
	 * 发送确认预约提示信息
	 * @param stid
	 * @return
	 */
	Studentbespeak findSbByStid(Integer stid);
	/**
	 * 单位/办公下拉菜单
	 * @return
	 */
	List findUnitComb();
	/**
	 * 专家发布的预约信息
	 * @param id
	 * @return
	 */
	StartExpertInfo findSEIByStid(Integer id);
	/**
	 * 管理员端预约学生信息
	 * @param page
	 * @param id
	 * @param xm
	 */
	void findSBStudent(Page page, String experttype, String xm);
	/**
	 * 获取专家信息
	 * @param id
	 * @return
	 */
	Expert findExpertById(Integer id);
	/**
	 * 获取学生信息
	 * @param stdid
	 * @return
	 */
	Student findStudentById(Integer stdid);
	/**
	 * 保存专家信息
	 * @param expert
	 * @return
	 */
	boolean saveOrUpdateExpertInfo(Expert expert);

}
