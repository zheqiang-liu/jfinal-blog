package com.mike.pojo;

import com.jfinal.plugin.activerecord.Model;
import com.mike.core.BlogConstants;

public class Message extends Model<Message> {

	private static final long serialVersionUID = -7865154193069659852L;
	public static final Message dao = new Message();

	public static final String ID = "id";
	public static final String CONTENT = "content";
	public static final String EMAIL = "email";
	public static final String DATE_TIME = "dateTime";
	public static final String NICK = "nick";

	private String _dateTime;

	public String get_dateTime() {
		if (null == _dateTime) {
			_dateTime = BlogConstants.format
					.format(getTimestamp(DATE_TIME));
		}
		return _dateTime;
	}
}
