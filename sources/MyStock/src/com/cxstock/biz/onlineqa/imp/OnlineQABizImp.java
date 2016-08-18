package com.cxstock.biz.onlineqa.imp;

import java.util.Date;
import java.util.List;

import com.cxstock.biz.onlineqa.OnlineQABiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.OnlineQA;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.DateUtil;

public class OnlineQABizImp implements OnlineQABiz{
	
	private BaseDAO  baseDao;

	@Override
	public boolean saveOrUpdaOnlineQA(OnlineQA pojo) {
		OnlineQA qa = new OnlineQA();
		if(null != pojo.getId()) {
			qa = (OnlineQA) baseDao.loadById(OnlineQA.class,pojo.getId());
			qa.setUpdatetime(DateUtil.format(new Date()));
		}else{
			qa.setPublishtime(DateUtil.format(new Date()));
			qa.setHighlight(0);
			qa.setTopflag(0);
			qa.setPopularity(0);
			qa.setStatus(2);//0通过(展示中) 1隐藏中  2预发布
		}
		
		qa.setContent(pojo.getContent());
		if(!"".equals(pojo.getAttachment())){
			qa.setAttachment(pojo.getAttachment());
		}
		if(!"".equals(pojo.getImage())){
			qa.setImage(pojo.getImage());
		}
		
		qa.setNickname(pojo.getNickname());
		//qa.setPopularity(pojo.getPopularity());
		qa.setPublisher(pojo.getPublisher());
		qa.setSubject(pojo.getSubject());
		baseDao.saveOrUpdate(qa);
		pojo.setId(qa.getId());
		return true;
	}



	@Override
	public void update(String ids, Integer type, Integer value) {
		String hql = "";
		//审核 0展现  1隐藏
		if(1==type){
			if(0==value){
				hql = "update OnlineQA set status = "+value+" where id in ("+ids+")";
			}else{
				hql = "update OnlineQA set status = "+value+",highlight=0,topflag=0,toptime=''  where id in ("+ids+")";
			}
			
		}else if(2==type){//置顶
			if(1==value){
				Date date = new Date();
				String d = DateUtil.format(date);
				hql = "update OnlineQA set status =0,topflag = "+value+",toptime='"+d+"' where id in ("+ids+")";
			}else{//取消置顶
				hql = "update OnlineQA set topflag = "+value+",toptime='' where id in ("+ids+")";
			}
			
		}else if(3==type){//高亮
			hql = "update OnlineQA set highlight = "+value+" where id in ("+ids+")";
		}else if(4==type){//正式发布
			hql = "update OnlineQA set status = 0 where id in ("+ids+")";
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
	public void findPageOnlineQA(Page page, String hql) {
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
	public void findPageMyOnlineQA(Page page, String hql) {
		hql += " order by  publishtime desc";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countQuery("select count(*) "+hql);
		page.setRoot(list);
		page.setTotal(total);
	}


    /**
     * 删除
     */
	@Override
	public void deleteOnlineQA(Integer id) {
		String hql = "delete from OnlineQA where status=2 and  id="+id;;
		baseDao.update(hql);
		
	}
	
	/**
     * 删除
     */
	@Override
	public void delete(String ids) {
		String hql = "delete from OnlineQA where  id in ("+ids+")";
		baseDao.update(hql);
		
	}




	@Override
	public void updatePopularity(Integer id) {
		String hql = "update OnlineQA set popularity = popularity+1 where id="+id;;
		baseDao.update(hql);
	}



	@Override
	public void updateReplynickname(Integer id, String nickname) {
		String hql = "update OnlineQA set replynickname = '"+nickname+"' where id="+id;;
		baseDao.update(hql);
	}
	
	@Override
	public OnlineQA loadById(Integer id){
		return (OnlineQA) baseDao.loadById(OnlineQA.class, id);
	}
 }
