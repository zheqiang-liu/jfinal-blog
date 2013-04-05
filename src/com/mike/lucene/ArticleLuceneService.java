package com.mike.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jfinal.plugin.activerecord.Page;
import com.mike.pojo.Article;

public class ArticleLuceneService implements IArticleLucene {
	
	private static Logger log = Logger.getLogger(ArticleLuceneService.class);
	
	private ArticleLuceneService(){
		
	}
	public static ArticleLuceneService me(){
		return new ArticleLuceneService();
	}
	@Override
	public void add(Article article) {
		Document doc = toDoc(article);
		IndexWriter iw = LuceneUtil.getIW();
		try {
			iw.addDocument(doc);
		} catch (CorruptIndexException e) {
			log.error("CorruptIndexException",e);
		} catch (IOException e) {
			log.error("IOException",e);
		}finally{
			 LuceneUtil.closeIW(iw);
		}
	}

	@Override
	public void indexAll() {
		List<Article> articles = Article.dao.find("select id,content from article order by id");
		IndexWriter iw = LuceneUtil.getIW();
		try {
			for (Article article : articles) {
				Document doc = toDoc(article);
				iw.addDocument(doc);
			}
		} catch (CorruptIndexException e) {
			log.error("CorruptIndexException",e);
		} catch (IOException e) {
			log.error("IOException",e);
		}finally{
			LuceneUtil.closeIW(iw);
		}
	}
	
	private Document toDoc(Article article){
		Document doc = new Document();
		doc.add(new Field("id", article.getInt(Article.ID).toString(), Store.YES, Index.NO));
		doc.add(new Field("content", Jsoup.parse(article.getStr(Article.CONTENT)).text(), Store.NO, Index.ANALYZED_NO_NORMS));
		return doc;
	}
	@Override
	public Page<Article> query(String keyword, Integer pageSize, Integer pageNo) {
		Page<Article> result = null;
		IndexSearcher is = LuceneUtil.getIS();
		QueryParser qp = new QueryParser(Version.LUCENE_36, "content", new IKAnalyzer());
		try {
			Query query = qp.parse(keyword);
			TopDocs hits = is.search(query, 10000);
			
			int totalHits = hits.totalHits;
			int startIndex = (pageNo - 1) * pageSize;
			if(startIndex > totalHits){
				return null;
			}
			int endIndex = pageNo * pageSize - 1;
			if(endIndex > totalHits){
				endIndex = totalHits;
			}
			int totalPage = totalHits / pageSize + ( totalHits % pageSize == 0 ? 0 : 1 ); 
			List<Article> list = new ArrayList<Article>(); 
			for(int i = startIndex; i < endIndex; i++){
				Document doc = is.doc(hits.scoreDocs[i].doc);
				list.add(Article.dao.findById(doc.get("id")));
			}
			result = new Page<Article>(list, pageNo, pageSize, totalPage, totalHits);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
