package com.mike.controller;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.mike.core.BlogConstants;
import com.mike.email.Email;
import com.mike.pojo.Article;
import com.mike.pojo.Comment;

public class ArticleController extends Controller {
	public void index() {
		final Integer articleId = getParaToInt(0);
		Article article = CacheKit.handle("article", "id_" + articleId, new IDataLoader() {
			@Override
			public Object load() {
			    return Article.dao.findById(articleId);
			}
		});
		Article.dao.clear().set("id", articleId).set("viewCount", article.getInt("viewCount") + 1).update();
		setAttr("article", article);
		
		Article previous = CacheKit.handle("article", "id_p_" + articleId, new IDataLoader() {
			@Override
			public Object load() {
			    return Article.dao.findFirst("select id,title from article where id < ?  order by id desc limit 0,1", articleId);
			}
		});
		if(previous != null){
			setAttr("previous", previous);
		}
		
		Article next = CacheKit.handle("article", "id_n_" + articleId, new IDataLoader() {
			@Override
			public Object load() {
			    return Article.dao.findFirst("select id,title from article where id > ?  order by id asc limit 0,1", articleId);
			}
		});
		if(next != null){
			setAttr("next", next);
		}
		render("detail.html");
	}
	public void hotsView(){
		final Integer pageNum = getParaToInt("p") == null ? 1 : getParaToInt("p");
		Page<Article> page = Article.dao.paginateByCache("article", "hotsView_" + pageNum, pageNum, BlogConstants.PAGE_SIZE, 
				"select * ", 
				"from article where finish = 1 order by viewCount desc");
		setAttr("title", "阅读排行  -- " + BlogConstants.TITLE);
		setAttr("page", page);
		render("articles.html");
	}
	public void hotsReply(){
		final Integer pageNum = getParaToInt("p") == null ? 1 : getParaToInt("p");
		Page<Article> page = Article.dao.paginateByCache("article", "hotsReply_" + pageNum, pageNum, BlogConstants.PAGE_SIZE, 
				"select * ", 
				"from article where finish = 1 order by replyCount desc"); 
		setAttr("title", "点评排行  -- " + BlogConstants.TITLE);
		setAttr("page", page);
		render("articles.html");
	}
	public void addComment(){
		Comment comment = getModel(Comment.class);
		comment.set("dateTime", new Date());
		comment.save();
		CacheKit.remove("article", "id_" + comment.getInt(Comment.ARTICLE_ID));
		CacheKit.remove("article", "recently_comments");
		Email _email = new Email();
		_email.setSubject("有来自" + comment.getStr(Comment.NICK) + "的新评论");
		_email.setContent("<strong>内容</strong>:" + comment.getStr(Comment.CONTENT) + "<div><a href='http://abap.cloudfoundry.com/admin'>快去处理吧</a></div>");
		_email.send();
		JSONObject json = new JSONObject();
		json.put("error", 0);
		json.put("msg", "success");
		renderJson(json.toJSONString());
	}
}
