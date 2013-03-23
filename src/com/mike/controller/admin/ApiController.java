package com.mike.controller.admin;

import com.jfinal.core.Controller;

public class ApiController extends Controller{
	public void index(){
		render("api.html");
	}
	public void sap_class(){
		render("sap_class.html");
	}
}
