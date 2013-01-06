package com.mike.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.mike.interceptor.SessionInterceptor;
import com.mike.pojo.Project;
@Before(SessionInterceptor.class)
public class ProjectController extends Controller {
	private Project project;

	public void index() {
		Integer pageNum = getParaToInt("p", 1);
		Page<Project> page = Project.dao.paginate(pageNum, 10, "select *",
				"from project");
		setAttr("page", page);
		render("project.html");
	}

	public void addProject() {
		render("addProject.html");
	}

	public void addProjectSubmit() {
		project = getModel(Project.class);
		project.save();
		setAttr("error", 0);
		setAttr("msg", "添加成功");
		renderJson();
	}

	public void editProject() {
		project = Project.dao.findById(getPara(0));
		setAttr("project", project);
		render("editProject.html");
	}

	public void editProjectSubmit() {
		project = getModel(Project.class);
		project.update();
		setAttr("error", 0);
		setAttr("msg", "success");
		renderJson();
	}
	
	public void deleteProject(){
		Project.dao.deleteById(getParaToInt(0));
		index();
	}
}
