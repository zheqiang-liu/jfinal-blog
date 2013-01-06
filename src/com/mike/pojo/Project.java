package com.mike.pojo;

import com.jfinal.plugin.activerecord.Model;

public class Project extends Model<Project>{

	private static final long serialVersionUID = -4866153915887357878L;
	public static final Project dao = new Project();
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String FINISH = "finish";
	
	
	private String _finish;
	public String getFinish() {
		if(null == _finish){
			if(getBoolean(FINISH)){
				_finish = "已结束";
			}else{
				_finish ="正在继续创中...";
			}
		}
		return _finish;
	}

}
