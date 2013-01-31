package com.mike.controller.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.mike.interceptor.SessionInterceptor;
import com.mike.pojo.Article;
import com.mike.pojo.CategorySuper;
import com.mike.pojo.Comment;
import com.mike.pojo.Project;

@Before(SessionInterceptor.class)
public class ArticleController extends Controller {
	private Article article;

	public void index() {
		Integer pageNum = getParaToInt("p", 1);
		Page<Article> page = Article.dao.paginate(pageNum, 10, "select *",
				"from article order by id desc");
		setAttr("page", page);
		render("list.html");
	}
	public void delete(){
		Article.dao.clear().set("id", getPara(0)).delete();
		CacheKit.removeAll("article");
		index();
	}
	public void edit(){
		article = Article.dao.findById(getPara(0));
		setAttr("article",article);
		List<CategorySuper> categorySuperList = CategorySuper.dao.find("select * from category_super");
		List<Project> projectList = Project.dao.find("select * from project where finish = 0");
		setAttr("projectList", projectList);
		setAttr("categorySuperList", categorySuperList);
		render("edit.html");
	}
	public void comments(){
		Integer pageNum = getParaToInt("p", 1);
		Page<Comment> page = Comment.dao.paginate(pageNum, 10, "select *","from comment order by id desc");
		setAttr("page", page);
		render("comments.html");
	}
	public void deleteComment(){
		Comment comment = Comment.dao.findById(getPara("id"));
		comment.delete();
		CacheKit.remove("article", "id_" + comment.getInt(Comment.ARTICLE_ID));
		CacheKit.remove("article", "recently_comments");
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void editArticle(){
		article = getModel(Article.class);
		article.update();
		CacheKit.removeAll("article");
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void editTempArticle(){
		article = getModel(Article.class);
		article.update();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void add() {
		List<CategorySuper> categorySuperList = CategorySuper.dao
				.find("select * from category_super");
		List<Project> projectList = Project.dao.find("select * from project where finish = 0");
		setAttr("projectList", projectList);
		setAttr("categorySuperList", categorySuperList);
		render("add.html");
	}

	public void addAtricle() {
		article = getModel(Article.class);
		article.set(Article.CREATE_DATE_TIME,new Date());
		article.save();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void finish(){
		Article.dao.clear().set("id", getPara("id")).set("finish", 1).update();
		CacheKit.removeAll("article");
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void notFinish(){
		Article.dao.clear().set("id", getPara("id")).set("finish", 0).update();
		CacheKit.removeAll("article");
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}


	
}
