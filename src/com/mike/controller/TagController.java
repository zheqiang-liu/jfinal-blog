package com.mike.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.mike.core.BlogConstants;
import com.mike.pojo.Article;

public class TagController extends Controller {
	public void index() {
		final String tag = getPara(0);
		final Integer pageNum = getParaToInt("p", 1);
		Page<Article> page = Article.dao.paginateByCache("article", "tag_" + tag + "_" + pageNum, pageNum, BlogConstants.PAGE_SIZE, 
				"select * ", 
				"from article where finish = 1 and tags like ? order by id desc", 
				"%" + tag + "%"); 
		setAttr("page", page);
		setAttr("title", "标签:" + tag + " -- " + BlogConstants.TITLE);
		render("articles.html");
	}
}
