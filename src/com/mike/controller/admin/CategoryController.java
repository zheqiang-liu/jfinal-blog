package com.mike.controller.admin;

import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.mike.core.BlogConfig;
import com.mike.interceptor.SessionInterceptor;
import com.mike.pojo.CategorySub;
import com.mike.pojo.CategorySuper;
@Before(SessionInterceptor.class)
public class CategoryController extends Controller {
	private CategorySuper categorySuper;
	private CategorySub categorySub;

	public void index() {
		Set<CategorySuper> categoryList = new TreeSet<CategorySuper>(CategorySuper.dao
				.find("select * from category_super"));
		setAttr("categoryList", categoryList);
		render("category.html");
	}
	public void refreshInMenu(){
		BlogConfig.updateCategorySuperList();
		renderNull();
	}
	public void addCategorySuper(){
		setAttr("count", Db.queryLong("select count(id) from category_super"));
		render("addCategorySuper.html");
	}
	public void addCategorySub(){
		setAttr("pId", getPara("pId"));
		setAttr("count", Db.queryLong("select count(id) from category_sub where pId=?",getPara("pId")));
		render("addCategorySub.html");
	}
	public void addCategorySuperSubmit() {
		categorySuper = getModel(CategorySuper.class);
		categorySuper.save();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}

	public void addCategorySubSubmit() {
		categorySub = getModel(CategorySub.class);
		categorySub.save();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}

	public void deleteCategorySuper() {
		CategorySuper.dao.deleteById(getPara(0));
		redirect("/admin/article/category.html");
	}
	public void deleteCategorySub() {
		CategorySub.dao.deleteById(getPara(0));
		redirect("/admin/article/category.html");
	}

	public void editCategorySuper() {
		categorySuper = CategorySuper.dao.findById(getPara(0));
		setAttr("categorySuper", categorySuper);
		setAttr("count", Db.queryLong("select count(id) from category_super"));
		render("editCategorySuper.html");
	}

	public void editCategorySuperSubmit() {
		categorySuper = getModel(CategorySuper.class);
		categorySuper.update();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void editCategorySub() {
		categorySub = CategorySub.dao.findById(getPara(0));
		setAttr("categorySub", categorySub);
		setAttr("count", Db.queryLong("select count(id) from category_sub where pId=?",categorySub.getPcategory().getInt("id")));
		render("editCategorySub.html");
	}

	public void editCategorySubSubmit() {
		categorySub = getModel(CategorySub.class);
		categorySub.update();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	public void loadCategorySub(){
		Set<CategorySub> categorySubList = new TreeSet<CategorySub>(CategorySub.dao.find("select * from category_sub where pId = ?",getParaToInt("pId")));
		JSONArray array = new JSONArray();
		for(CategorySub categorySub : categorySubList){
			JSONObject json = new JSONObject();
			json.put("id", categorySub.getInt("id"));
			json.put("name", categorySub.getStr("name"));
			json.put("order", categorySub.getInt("order"));
			array.add(json);
		}
		renderJson(array.toJSONString());
	}
}
