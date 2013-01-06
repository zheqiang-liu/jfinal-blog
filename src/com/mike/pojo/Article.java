package com.mike.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jfinal.plugin.activerecord.Model;
import com.mike.core.BlogConstants;
import com.mike.util.StringUtil;

public class Article extends Model<Article> {

	private static final long serialVersionUID = 6298774837273546513L;
	public static final Article dao = new Article();

	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String CREATE_DATE_TIME = "createDateTime";
	public static final String TYPE = "type";
	public static final String URL = "url";
	public static final String CATEGORYSUB_ID = "categorySubId";
	public static final String VIEWCOUNT = "viewCount";
	public static final String CONTENT = "content";
	public static final String REPLYCOUNT = "replyCount";
	public static final String TAGS = "tags";
	public static final String PROJECT_ID = "projectId";
	public static final String FINISH = "finish";

	private String _createDateTime;

	public String get_createDateTime() {
		if (null == _createDateTime) {
			_createDateTime = BlogConstants.format
					.format(getTimestamp(CREATE_DATE_TIME));
		}
		return _createDateTime;
	}

	private String _content;

	public String get_content() {
		if (null == _content) {
			Document doc = Jsoup.parse(getStr(CONTENT));
			Elements eles = doc.select("img[src^=/img/u]");
			Iterator<Element> iter = eles.listIterator();
			while (iter.hasNext()) {
				Element ele = iter.next();
				String src = ele.attr("src");
				//"http://abap_img.cloudfoundry.com" + 
				ele.attr("data-src", src);
				ele.attr("src", "/img/s/grey.png");
				ele.attr("class", "lazy");
				ele.attr("alt",getStr(TITLE));
			}
			eles = doc.select("pre.prettyprint");
			iter = eles.listIterator();
			Set<String> brushs = new HashSet<String>();
			while (iter.hasNext()) {
				Element ele = iter.next();
				String cls = ele.attr("class");
				String lang = cls.substring(cls.lastIndexOf(":") + 1);
				brushs.add(BlogConstants.SHL_MAPPING.get(lang));
			}
			for (String brush : brushs) {
				doc.append("<script type=\"text/javascript\" src=\"/syntaxhighlighter/scripts/shBrush"
						+ brush + ".js\"></script>");
			}
			_content = doc.html();
		}
		return _content;
	}

	private String brief;

	public String getBrief() {
		if (null == brief) {
			brief = StringUtil.subString(Jsoup.parse(getStr(CONTENT)).text(),
					2500);
		}
		return brief;
	}

	private String _desc;

	public String get_desc() {
		if (null == _desc) {
			_desc = StringUtil.subString(Jsoup.parse(getStr(CONTENT)).text(),
					300);
		}
		return _desc;
	}

	private String _type;

	public String get_type() {
		if (null == _type) {
			switch (getInt("type")) {
			case 0:
				_type = "原创";
				break;
			case 1:
				_type = "转载";
				break;
			}
		}
		return _type;
	}

	private CategorySub _categorySub;

	public CategorySub getCategorySub() {
		if (_categorySub == null) {
			_categorySub = CategorySub.dao.findById(getInt(CATEGORYSUB_ID));
		}
		return _categorySub;
	}

	private List<String> _tags;

	public List<String> get_tags() {
		if (null == _tags) {
			_tags = Arrays.asList(getStr(TAGS).split(","));
		}
		return _tags;
	}

	private Project _project;

	public Project getProject() {
		if (_project == null) {
			_project = Project.dao.findById(getInt(PROJECT_ID));
		}
		return _project;
	}

	private List<Comment> _comments;

	public List<Comment> getComments() {
		if (_comments == null) {
			_comments = Comment.dao
					.find("select * from comment where articleId = ? and pId = 0 order by id asc",
							getInt(ID));
		}
		return _comments;
	}

	public List<Article> _relates;
	public List<Article> getRelates() {
		if (_relates == null) {
			_relates = Article.dao
					.find("select id,title from article where finish = 1 and id != ? and categorySubId = ? order by id desc limit 0,4",getInt(ID),getInt(CATEGORYSUB_ID));
		}
		return _relates;
	}
}
