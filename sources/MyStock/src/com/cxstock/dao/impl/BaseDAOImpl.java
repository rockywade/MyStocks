package com.cxstock.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Classes;

/** 统一数据访问接口实现 */
@SuppressWarnings("unchecked")
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

	/** 保存或更新指定的持久化对象 */
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	/** 保存或更新指定的持久化对象 */
	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/** 删除指定ID的持久化对象 */
	public void deleteById(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
	}

	/** 删除指定ID的持久化对象 */
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	/** 加载指定ID的持久化对象 */
	public Object loadById(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	/** 加载满足条件的持久化对象 */
	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
		List list = getHibernateTemplate().find(hql1);
		if (list.size() > 0)
			obj = list.get(0);
		return obj;
	}

	/** 查询指定类的满足条件的持久化对象 */
	public List findByHql(String hql) {
		try {
			final String hql1 = hql;
			return getHibernateTemplate().find(hql1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 装载指定类的查询结果 */
	public List findInProperty(String clazz, String propertyName, String value) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ " in (" + value + ")";
		return getHibernateTemplate().find(hql);
	}

	/** 装载指定类的查询结果 */
	public List findLikeProperty(String clazz, String propertyName, String value) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ " like ?";
		return getHibernateTemplate().find(hql, value);
	}

	/** 装载指定类的查询结果 */
	public List findByProperty(String clazz, String propertyName, Object value) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ "= ?";
		System.out.println(value);
		return getHibernateTemplate().find(hql, value);
	}

	/** 装载指定类的查询结果 */
	public List findByProperty(String clazz, String[] propertyName,
			Object[] value) {
		String hsql = "from " + clazz + " as model where 1=1";
		for (int i = 0; i < propertyName.length; i++) {
			hsql += " and model." + propertyName[i] + "= ?";
		}
		return getHibernateTemplate().find(hsql, value);
	}

	/** 装载指定类的所有持久化对象 */
	public List listAll(String clazz) {
		return getHibernateTemplate().find("from " + clazz);
	}

	/** 条件更新数据 */
	public int update(String hql) {
		final String hql1 = hql;
		return ((Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql1);
						return query.executeUpdate();
					}
				})).intValue();
	}

	/** 统计指定类的所有持久化对象 */
	public int countAll(String clazz) {
		final String hql = "select count(*) from " + clazz;
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 统计指定类的查询结果 */
	public int countQuery(String hql) {
		final String counthql = hql;
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(counthql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 统计指定类的指定属性查询结果 */
	public int countQueryByclazzAndProperty(String clazz, String propertyname,
			String valuename, String property, String value) {
		final String hql = "select count(*) from " + clazz
				+ " as model where model." + property + "= '" + value
				+ "'and model." + propertyname + " like '%" + valuename + "%'";
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 统计指定类的查询结果 */
	public int count(String clazz, String[] property, Object[] value) {
		StringBuffer counthql = new StringBuffer("select count(*) from "
				+ clazz + " where 1=1 ");
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				counthql.append(" and " + property[i] + " like '%" + value[i]
						+ "%'");
			}
		}
		final String hql = counthql.toString();
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 统计指定类的查询结果 */
	public int count1(String clazz,String[] property,Object[] value,String orderName3,String classcode ,List<Classes> classcodevalue ) {
		StringBuffer counthql = new StringBuffer("select count(*) from "+clazz+" where 1=1 ");
		for(int i=0;i<property.length;i++){
			if(null!=value[i]){
				counthql.append(" and "+property[i] + " like '%"+value[i]+"%'");
			}
		}
		if(null != classcodevalue &&classcodevalue.size()>0){
			String idstr = classcodevalue.toString();
			String strList = idstr.substring(idstr.indexOf("[")+1, idstr.indexOf("]"));
			counthql.append(" and classcode in (" + strList + ")");
		}
		counthql.append(" and "+orderName3+" >= 30 ");
		final String hql = counthql.toString();
		Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	
	
	
	/** 分页装载指定类的查询结果 */
	public List findInProperty(String clazz, String propertyName, String value,
			int start, int limit) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ " in (" + value + ")";
		return this.findByHql(hql, start, limit);
	}

	/** 分页装载指定类的查询结果 */
	public List findLikeProperty(String clazz, String propertyName,
			String value, int start, int limit) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ " like '" + value + "%'";
		return this.findByHql(hql, start, limit);
	}

	/** 分页装载指定类的查询结果 指定类、模糊查询字段、字段值、指定字段 、字段值 */
	public List findByProperty(String clazz, String property, String valuename,
			String propertyName, String value, int start, int limit) {
		String hql = "from " + clazz + " as model where model." + propertyName
				+ "= '" + value + "'";
		if (null != valuename) {
			hql += " and model." + property + " like '%" + valuename + "%'";
		}
		return this.findByHql(hql, start, limit);
	}

	/** 分页装载指定类的查询结果 = */
	public List findByProperty(String clazz, String[] propertyName,
			Object[] value, int start, int limit) {
		String hql = "from " + clazz + " as model where 1=1";
		for (int i = 0; i < propertyName.length; i++) {
			hql += " and model." + propertyName[i] + "= '" + value[i] + "'  ";
		}
		return this.findByHql(hql, start, limit);
	}
	


	/** 
	 * 分页装载指定类的查询结果
	 * 排序查询 
	 */

	public List findByProperty(String clazz, String[] propertyName,
			Object[] value, String[] orderName, String[] orderType, int start,
			int limit) {
		String hql = "from " + clazz + " as model where 1=1";
		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i]) {
				hql += " and model." + propertyName[i] + " like '%" + value[i]
						+ "%'";
			}

		}
		String order = "";
		for (int i = 0; i < orderName.length; i++) {
			if(i==0){
				order += " order by " ;
			}
			order += "model." + orderName[i] + " " + orderType[i]+",";
		}
		if(order.length()>0){
			order = order.substring(0,order.length()-1);
		}
		return this.findByHql(hql+order, start, limit);
	}
	
	
	/** 
	 * 
	 * 排序 
	 */

	public List findPageAndOrder(String hql, String[] orderName, String[] orderType, int start,
			int limit) {
		String order = "";
		for (int i = 0; i < orderName.length; i++) {
			if(i==0){
				order += " order by " ;
			}
			order += " model." + orderName[i] + " " + orderType[i]+",";
		}
		if(order.length()>0){
			order = order.substring(0,order.length()-1);
		}
		return this.findByHql(hql+order, start, limit);
	}

	/**
	 * 分页装载指定类的查询结果 排序查询
	 */
	public List findByProperty3(String clazz, String[] propertyName,
			Object[] value, String orderName1, String orderName2,
			String orderName3, int start, int limit) {
		String hql = "from " + clazz + " as model where 1=1";

		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i]) {
				hql += " and model." + propertyName[i] + " like '%" + value[i]
						+ "%'";
			}

		}
		hql = " " + hql + " order by  model." + orderName1 + " DESC , model."
				+ orderName2 + " DESC, model." + orderName3 + " DESC";

		return this.findByHql(hql, start, limit);
	}

	
	
	/**
	 * 分页装载指定类的查询结果 排序（两个条件）查询
	 */
	public List findByProperty(String clazz, String[] propertyName,
			Object[] value, String orderName1, String orderName2, int start,
			int limit) {
		String hql = "from " + clazz + " as model where 1=1";

		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i]) {
				hql += " and model." + propertyName[i] + " like '%" + value[i]
						+ "%'";
			}

		}
		hql = " " + hql + " order by  model." + orderName1 + " DESC , model."
				+ orderName2 + " DESC";

		return this.findByHql(hql, start, limit);
	}

	/**
	 * 分页， 搜索 排序（一个条件）
	 */
	public List findByProperty1(String clazz, String[] propertyName,
			Object[] value, String orderName, int start, int limit) {
		String hql = "from " + clazz + " as model where 1=1";

		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i]) {
				hql += " and model." + propertyName[i] + " like '%" + value[i]
						+ "%'";
			}

		}

		hql = " " + hql + " order by  model." + orderName + " DESC ";
		return this.findByHql(hql, start, limit);
	}
	
	
	/** 
	 * 分页装载指定类的查询结果
	 * 排序（两个条件）判断某个值大于什么数查询 
	 */
	public List findByProperty4(String clazz, String[] propertyName, Object[] value, String orderName1,String orderName2, String orderName3, 
			String classcode ,List<Classes> classcodevalue ,int start, int limit) {
		String hql = "from "+clazz+" as model where 1=1";
	
		for ( int i = 0; i < propertyName.length ; i++) {
			if(null!=value[i]){
				hql += " and model." + propertyName[i] + " like '%"+value[i]+"%'";
			}
			
		  }
		 if(null != classcodevalue &&classcodevalue.size()>0){
			    String idstr = classcodevalue.toString();
				String strList = idstr.substring(idstr.indexOf("[")+1, idstr.indexOf("]"));
			  hql += "   and model."+classcode+" in("+strList+") ";
			
		  }
		 hql += " and model."+orderName3+" >=30  order by  model." + orderName1 + " DESC , model." + orderName2 + " DESC";

		return this.findByHql(hql, start, limit);
	}
	
	
	
	

	/** 分页装载指定类的查询结果 like */
	public List findByPropertyLike(String clazz, String[] propertyName,
			Object[] value, int start, int limit) {
		String hql = "from " + clazz + " as model where 1=1";
		for (int i = 0; i < propertyName.length; i++) {
			if (null != value[i]) {
				hql += " and model." + propertyName[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		return this.findByHql(hql, start, limit);
	}

	/** 分页装载指定类的所有持久化对象 */
	public List listAll(String clazz, int start, int limit) {
		final int pStart = start;
		final int pLimit = limit;
		final String hql = "from " + clazz;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(pLimit);
				query.setFirstResult(pStart);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}

	/** 分页查询指定类的满足条件的持久化对象 */
	public List findByHql(String hql, int start, int limit) {
		final int pStart = start;
		final int pLimit = limit;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				query.setMaxResults(pLimit);
				query.setFirstResult(pStart);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}
	
	/** 
	 * 分页装载指定类的查询结果
	 * 排序 时间段的查询22
	 */
	public List findByProperty5(String clazz, String[] propertyName, Object[] value, 
			String timeName,String orderName1,final String startTime,final String endTime,int start, int limit) {
		String hql = "from "+clazz+" as model where 1=1";
		
		for ( int i = 0; i < propertyName.length ; i++) {
			if(null!=value[i]){
				hql += " and model." + propertyName[i] + " like '%"+value[i]+"%'";
			}
			
		  }
		if(null != startTime && !"".equals(startTime) &&
				null != endTime && !"".equals(endTime)){
			hql += " and    model."+timeName+"  <= '"+endTime+"' ";
			hql += " and    model."+timeName+" >=  '"+startTime+"' ";
			
		}
		
		hql += " order by  model." + orderName1 + " DESC ";
	   
		return this.findByHql(hql, start, limit);
	}
	
	/** 
	 * 分页装载指定类的查询结果
	 * 排序 时间段的查询22
	 */
	public List findByProperty6(String clazz, String[] propertyName, Object[] value, 
			String timeName,String orderName1,final String startTime,final String endTime) {
		String hql = "from "+clazz+" as model where 1=1";
		for ( int i = 0; i < propertyName.length ; i++) {
			if(null!=value[i]){
				hql += " and model." + propertyName[i] + " like '%"+value[i]+"%'";
			}
			
		  }
		if(null != startTime && !"".equals(startTime) &&
				null != endTime && !"".equals(endTime)){
			hql += " and    model."+timeName+"  <= '"+endTime+"' ";
			hql += " and    model."+timeName+" >=  '"+startTime+"' ";
		}
		hql += " order by  model." + orderName1 + " DESC ";
	   
		return this.findByHql(hql);
	}
	
	
	/** 统计指定类的查询结果22*/
	public int count5(String clazz,String[] propertyName,Object[] value,
			String timeName ,final String startTime,final String endTime) {
		StringBuffer counthql = new StringBuffer("select count(*) from "+clazz+" where 1=1 ");
		for(int i=0;i<propertyName.length;i++){
			if(null!=value[i]){
				counthql.append(" and "+propertyName[i] + " like '%"+value[i]+"%'");
			}
		}
		if(null != startTime && !"".equals(startTime) &&
				null != endTime && !"".equals(endTime)){
		counthql.append(" and  "+timeName+"  <= '"+endTime+"' ");
		counthql.append(" and   "+timeName+"  >= '"+startTime+"' ");
		}
		final String hql = counthql.toString();
		Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	
	

	
	
	
	
	
	/** 分页查询指定类的时间的持久化对象 */
	public List findByHqlAndDate(String hql,final Date startTime,final Date endTime, int start, int limit) {
		final int pStart = start;
		final int pLimit = limit;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				query.setDate(0, startTime);
				query.setDate(1, endTime);
				query.setMaxResults(pLimit);
				query.setFirstResult(pStart);
				
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

	/** 从连接池中取得一个JDBC连接 */
	@SuppressWarnings("deprecation")
	public Connection getConnection() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession()
				.connection();
	}

	/** 批量保存、修改 */
	public void saveOrUpdateAll(Collection collection) {
		getHibernateTemplate().saveOrUpdateAll(collection);
	}

	/** 调用存储过程 */
	public void callProcedure(String call) {
		SQLQuery query = this.getSession().createSQLQuery(call);
		query.executeUpdate();
	}

	/* 统计指定类指定属性的统计结果 */
	public int countPropertyAndValue(String clazz, String[] property,
			String[] value, String propertyname, String usernum) {
		StringBuffer counthql = new StringBuffer("select count(*) from "
				+ clazz + " where 1=1 ");
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				counthql.append(" and " + property[i] + " like '%" + value[i]
						+ "%'");
			}
		}
		counthql.append(" and " + propertyname + "='" + usernum + "'");
		final String hql = counthql.toString();
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}
	
	/**
	 * 统计条数
	 */
	public int count2(String clazz, String propertyname, String usernum) {
		StringBuffer counthql = new StringBuffer("select count(*) from "
				+ clazz + " where 1=1 ");
		counthql.append(" and " + propertyname + "='" + usernum + "'");
		final String hql = counthql.toString();
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
		
	}
   
	/**
	 * 根据连个值
	 * 统计条数
	 */
	public int count3(String clazz, String propertyname1, String value1,String propertyname2, String value2) {
		StringBuffer counthql = new StringBuffer("select count(*) from "
				+ clazz + " where 1=1 ");
		counthql.append(" and " + propertyname1 + "='" + value1 + "'");
		counthql.append(" and " + propertyname2 + "='" + value2 + "'");
		final String hql = counthql.toString();
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
		
	}
	
	
	/* 分页装在指定类指定属性的查询结果 */
	public List findClazzByPropertyAndValue(String clazz, String[] property,
			String[] value, String propertyname, String usernum, int start,
			int limit) {
		String hql = "from " + clazz + " as model where 1=1";
		for (int i = 0; i < property.length; i++) {
			if (null != value[i]) {
				hql += " and model." + property[i] + " like '%" + value[i]
						+ "%'";
			}
		}
		hql += " and model." + propertyname + "='" + usernum + "'";
		return this.findByHql(hql, start, limit);
	}

	/** sum值统计 */
	public int findSum(String hql) {
		final String counthql = hql;
		Long sum = (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(counthql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		if(null==sum){
			return 0;
		}
		return sum.intValue();
	}

	
	public int excuteBySql(String sql){    
        int result ;    
        SQLQuery query = this.getSession().createSQLQuery(sql);    
        result = query.executeUpdate();    
        return result;    
    }  
}