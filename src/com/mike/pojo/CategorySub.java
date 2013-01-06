package com.mike.pojo;

import com.jfinal.plugin.activerecord.Model;

public class CategorySub extends Model<CategorySub> implements Comparable<CategorySub>{

	private static final long serialVersionUID = 510211481802210079L;
	public static final CategorySub dao = new CategorySub();
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PID = "pId";
	public static final String ORDER = "order";


	private CategorySuper _categorySuper;
	public CategorySuper getPcategory() {
		if (_categorySuper == null) {
			_categorySuper = CategorySuper.dao.findById(getInt("pId"));
		}
		return _categorySuper;
	}

	@Override
	public int compareTo(CategorySub o) {
		if(getInt(ORDER) >= o.getInt(ORDER))
			return 1;
		else
			return -1;
	}
}
