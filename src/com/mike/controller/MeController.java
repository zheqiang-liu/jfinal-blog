package com.mike.controller;

import com.jfinal.core.Controller;

public class MeController extends Controller {
	public void index() {
		render("me.html");
	}
	public void resume(){
		render("resume.html");
	}
}
