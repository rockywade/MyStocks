package com.cxstock.biz.secondbookstore.imp;

import java.util.Date;
import java.util.List;

import com.cxstock.biz.secondbookstore.SecondBookStoreBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.SecondBookStore;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;

public class SecondBookStoreBizImp implements SecondBookStoreBiz{
	
	private BaseDAO  baseDao;

	@Override
	public boolean saveOrUpdaSecondBookStore(SecondBookStore pojo) {
		SecondBookStore store = new SecondBookStore();
		if(null != pojo.getId()) {
			store = (SecondBookStore) baseDao.loadById(SecondBookStore.class,pojo.getId());
			store.setUpdatetime(DateUtil.format(new Date()));
		}else{
			store.setPublishtime(DateUtil.format(new Date()));
			store.setHighlight(0);
			store.setTopflag(0);
			store.setPopularity(0);
			store.setStatus(2);//0通过(展示中) 1隐藏中  2预发布
		}
		
		store.setContent(pojo.getContent());
		if(!"".equals(pojo.getAttachment())){
			store.setAttachment(pojo.getAttachment());
		}
		if(!"".equals(pojo.getImage())){
			store.setImage(pojo.getImage());
		}
		
		store.setNickname(pojo.getNickname());
		//qa.setPopularity(pojo.getPopularity());
		store.setPublisher(pojo.getPublisher());
		store.setSubject(pojo.getSubject());
		store.setStoreType(pojo.getStoreType());
		baseDao.saveOrUpdate(store);
		pojo.setId(store.getId());
		return true;
	}



	@Override
	public void update(String ids, Integer type, Integer value) {
		String hql = "";
		//审核 0展现  1隐藏
		if(1==type){
			if(0==value){
				hql = "update SecondBookStore set status = "+value+" where id in ("+ids+")";
			}else{
				hql = "update SecondBookStore set status = "+value+",highlight=0,topflag=0,toptime=''  where id in ("+ids+")";
			}
			
		}else if(2==type){//置顶
			if(1==value){
				Date date = new Date();
				String d = DateUtil.format(date);
				hql = "update SecondBookStore set status =0,topflag = "+value+",toptime='"+d+"' where id in ("+ids+")";
			}else{//取消置顶
				hql = "update SecondBookStore set topflag = "+value+",toptime='' where id in ("+ids+")";
			}
			
		}else if(3==type){//高亮
			hql = "update SecondBookStore set highlight = "+value+" where id in ("+ids+")";
		}else if(4==type){//正式发布
			hql = "update SecondBookStore set status = 0 where id in ("+ids+")";
		}
		baseDao.update(hql);
	}



	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}



	/**
	 * 分页查询
	 */
	@Override
	public void findPageSecondBookStore(Page page,String hql) {
		hql += " order by toptime desc,publishtime desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countQuery("select count(*) "+hql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/**
	 * 分页查询
	 */
	@Override
	public void findPageMySecondBookStore(Page page, String hql) {
		hql += " order by publishtime desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countQuery("select count(*) "+hql);
		page.setRoot(list);
		page.setTotal(total);
	}


    /**
     * 删除
     */
	@Override
	public void deleteSecondBookStore(Integer id) {
		String hql = "delete from SecondBookStore where status=2 and   id="+id;;
		baseDao.update(hql);
		
	}
	
	/**
     * 删除
     */
	@Override
	public void delete(String ids) {
		String hql = "delete from SecondBookStore where  id in ("+ids+")";
		baseDao.update(hql);
		
	}



	@Override
	public void updatePopularity(Integer id) {
		String hql = "update SecondBookStore set popularity = popularity+1 where id="+id;;
		baseDao.update(hql);
	}



	@Override
	public void updateReplynickname(Integer id, String nickname) {
		String hql = "update SecondBookStore set replynickname = '"+nickname+"' where id="+id;;
		baseDao.update(hql);
	}
	
	@Override
	public SecondBookStore loadById(Integer id){
		return (SecondBookStore) baseDao.loadById(SecondBookStore.class, id);
	}
 }
