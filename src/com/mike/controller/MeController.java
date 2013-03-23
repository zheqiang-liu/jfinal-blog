package com.mike.controller;

import com.jfinal.core.Controller;

public class MeController extends Controller {
	public void index() {
		render("me.html");
	}
	public void introduce(){
		render("introduce.html");
	}
	public void experience(){
		render("experience.html");
	}
	public void skill(){
		render("skill.html");
	}
}
