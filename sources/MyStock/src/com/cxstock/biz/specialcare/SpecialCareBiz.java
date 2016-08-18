package com.cxstock.biz.specialcare;

import java.util.List;

import com.cxstock.pojo.SpecialCare;
import com.cxstock.pojo.Student;
import com.cxstock.utils.pubutil.Page;

/**
 * 线上答疑接口
 * @author root
 *
 */
public interface SpecialCareBiz {
	
	/**
	 * 修改
	 * @param id
	 */
	public void update(Integer id,String economic,String academic,String mental);
	
	/**
	 * 保存、修改
	 * @param pojo
	 */
	public boolean saveOrUpdateSpecialCare(SpecialCare  pojo);
	
	
	/**
	 * 分页，排序，搜索
	 * @param page
	 * @param property
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 */
	public void findPageSpecialCare(Page page,String logincode,String zydm, String[] property,Object[] value);
	
	public List<SpecialCare> findByProperty(String clazz, String[] propertyName,
			Object[] value) ;
	
	public SpecialCare loadById(Integer id) ;
	
	public SpecialCare getSpecialCare(String xh);
	
	
}
