package com.mike.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.mike.core.BlogConstants;
import com.mike.pojo.Article;
import com.mike.pojo.CategorySub;

public class CategorySubController extends Controller {
	public void index() {
		final Integer categorySubId = getParaToInt(0);
		Integer pageNum = getParaToInt("p", 1);
		Page<Article> page = Article.dao.paginateByCache("article", "categorySubId_" + categorySubId + "_" + pageNum, pageNum, BlogConstants.PAGE_SIZE, 
				"select * ", 
				"from article where finish = 1 and categorySubId = ? order by id desc",
				categorySubId);
		CategorySub categorySub = CacheKit.handle("article", "categorySubId_" + categorySubId, new IDataLoader() {
			@Override
			public Object load() {
				return CategorySub.dao.findById(categorySubId);
			}
		}); 
		setAttr("page", page);
		setAttr("title", "类别:" + categorySub.getStr(CategorySub.NAME) + " -- " + BlogConstants.TITLE);
		render("articles.html");
	}
}
