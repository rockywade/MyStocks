package com.cxstock.biz.news;

import com.cxstock.pojo.News;
import com.cxstock.utils.pubutil.Page;

public interface NewsBiz {

	public void updateNews(News news);
	
	void findMyNews(Page page,String[] property,String[] value,String writer);
	
	int deleteByIdAndWriter(String newsId, String writer);
}
