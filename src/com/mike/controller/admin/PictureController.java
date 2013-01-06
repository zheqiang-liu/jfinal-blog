package com.mike.controller.admin;

import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.util.PathUtil;
import com.mike.interceptor.SessionInterceptor;
import com.mike.util.ZipUtil;
@Before(SessionInterceptor.class)
public class PictureController extends Controller{
private static final String rootPath = PathUtil.getWebRootPath();;
	public void index(){
		File img = new File(rootPath + "/img/u");
		File[] imgs = img.listFiles();
		setAttr("imgs", imgs);
		render("list_folder.html");
	}
	public void open(){
		String path = getPara(0);
		setAttr("path", path);
		File img = new File(rootPath + "/img/u/" + path.replaceAll("_", "/"));
		File[] imgs = img.listFiles();
		setAttr("imgs", imgs);
		setAttr("pFolder", getPara("pFolder"));
		if(path.split("_").length == 3){
			render("list_pic.html");
		}else{
			render("list_folder.html");
		}	
	}
	public void download(){
		String path = getPara(0);
		String img = rootPath + "/img/u/" + path.replaceAll("_", "/");
		ZipUtil.zip(img,rootPath + "/img/temp/" + path);
		renderFile("/img/temp/" + path + ".zip");
	}
	public void cleanTemp(){
		File temp = new File(rootPath + "/img/temp");
		File[] files = temp.listFiles();
		for(File file:files){
			file.delete();
		}
		render("clean_temp.html");
	}
}
