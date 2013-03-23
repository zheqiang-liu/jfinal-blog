package com.mike.pojo;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User>{

	private static final long serialVersionUID = 8575578491128991292L;
	public static final User dao = new User();

	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public String getUsername(){
		return this.getStr("username");
	}
}
