package com.mike.lucene;

import com.jfinal.plugin.activerecord.Page;
import com.mike.pojo.Article;

public interface IArticleLucene {
	public void add(Article article);

	public void indexAll();

	public Page<Article> query(String keyword, Integer pageSize, Integer pageNo);
}
