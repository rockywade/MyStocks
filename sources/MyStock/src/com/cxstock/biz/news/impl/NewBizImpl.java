package com.cxstock.biz.news.impl;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.news.NewsBiz;
import com.cxstock.biz.news.dto.NewsDto;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.News;
import com.cxstock.utils.pubutil.Page;

public class NewBizImpl implements NewsBiz{

	private BaseDAO baseDao;		//dao层接口参数
	
	
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 更新新闻
	 */
	public void updateNews(News news) {
		baseDao.saveOrUpdate(news);
	}

	@Override
	public void findMyNews(Page page, String[] property, String[] value,
			String writer) {
		String hql = "from News as model where 1=1";
		for(int i=0; i<property.length;i++){
			if(value[i]!=null){
				hql +=" and model."+property[i]+" like '%"+value[i]+"%'";
			}
		}
		String chql = "select count(*) "+hql;
		String[] orderName = {"newsdate"};
		String[] orderType = {"DESC"};
		List<News> list = baseDao.findPageAndOrder(hql, orderName, orderType, page.getStart(), page.getLimit());
		
		List<NewsDto> dtos = new ArrayList<NewsDto>();
		
		if(list != null && list.size() > 0) {
			for(News news : list) {
				NewsDto dto = new NewsDto();
				dto.setActivityid(news.getActivityid());
				dto.setContent(news.getContent());
				dto.setHighlight(news.getHighlight());
				dto.setNewscheckstyle(news.getNewscheckstyle());
				dto.setNewsdate(news.getNewsdate());
				dto.setNewsid(news.getNewsid());
				dto.setNewstitle(news.getNewstitle());
				dto.setProperty(news.getProperty());
				dto.setTotop(news.getTotop());
				dto.setWebsite(news.getWebsite());
				dto.setWriter(writer);
				dtos.add(dto);
			}
			
		}
		int total = baseDao.countQuery(chql);
		page.setRoot(dtos);
		page.setTotal(total);
	}

	@Override
	public int deleteByIdAndWriter(String newsId, String writer) {
		if(newsId!=null && !newsId.equals("") && writer!=null &&!writer.equals("")){
			String sql = "delete from tbl_news where newsid = '"+newsId+"' and writer = '"+writer+"'";
			baseDao.excuteBySql(sql);
		}
		return 0;
	}
}
