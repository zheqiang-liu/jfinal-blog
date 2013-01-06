package com.mike.pojo;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.mike.core.BlogConstants;

public class Comment extends Model<Comment>{
	private static final long serialVersionUID = -1899158449878197294L;
	public static final Comment dao = new Comment();
	
	public static final String ID = "id";
	public static final String CONTENT = "content";
	public static final String EMAIL = "email";
	public static final String ARTICLE_ID = "articleId";
	public static final String DATE_TIME = "dateTime";
	public static final String NICK = "nick";
	public static final String PID = "pId";
	
	private String _dateTime;

	public String get_dateTime() {
		if (null == _dateTime) {
			_dateTime = BlogConstants.format
					.format(getTimestamp(DATE_TIME));
		}
		return _dateTime;
	}
	private List<Comment> _comments;
	public List<Comment> get_comments(){
		if(_comments == null){
			_comments = Comment.dao.find("select * from comment where pId=?", getInt(ID));
		}
		return _comments;
	}
}
