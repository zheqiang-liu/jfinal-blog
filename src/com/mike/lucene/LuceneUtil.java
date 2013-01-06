package com.mike.lucene;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.mike.pojo.Article;

public class LuceneUtil {
	private static File saveDir = new File("temp/lucene");
	private static Analyzer analyzer = new IKAnalyzer();
	private static Directory dir;
	private static IndexWriterConfig cfg = new IndexWriterConfig(
			Version.LUCENE_36, analyzer);
	static {
		try {
			dir = FSDirectory.open(saveDir);
			cfg.setOpenMode(OpenMode.CREATE_OR_APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addArticle(Article article) {
		Document doc = new Document();
		doc.add(new Field("id", article.getInt(Article.ID).toString(), Store.YES, Index.NO));
		doc.add(new Field("content", Jsoup.parse(article.getStr(Article.CONTENT)).text(), Store.NO,
				Index.ANALYZED_NO_NORMS));
		IndexWriter iw = getIW();
		try {
			iw.addDocument(doc);
			iw.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addArticles(List<Article> articles) {
		IndexWriter iw = getIW();
		try {
			for (Article article : articles) {
				Document doc = new Document();
				doc.add(new Field("id", article.getInt(Article.ID).toString(), Store.YES, Index.NO));
				doc.add(new Field("content", Jsoup.parse(article.getStr(Article.CONTENT)).text(), Store.NO,
						Index.ANALYZED_NO_NORMS));
				iw.addDocument(doc);
			}
			iw.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IndexWriter getIW() {
		IndexWriter iw = null;
		try {
			iw = new IndexWriter(dir, cfg);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return iw;
	}
}
