package com.cxstock.biz.secondbookstorejudge;

import com.cxstock.pojo.SecondBookStoreJudge;
import com.cxstock.utils.pubutil.Page;

/**
 * 学习资料接口
 * @author root
 *
 */
public interface SecondBookStoreJudgeBiz {
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param property
	 * @param value
	 */
	public void findPageSecondBookStoreJudge(Page page,String[] property ,Object[] value,Integer userId);
	
	/**
	 * 保存
	 * @param OnlineQAJudgeDTO
	 * @return
	 */
	public boolean saveSecondBookStoreJudge(SecondBookStoreJudge secondBookStoreJudge);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteSecondBookStoreJudge(Integer id);
	
	
	
	
	
}
