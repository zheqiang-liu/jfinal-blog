package com.mike.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtil {
	private static File saveDir = new File("temp/lucene");
	private static Analyzer analyzer = new IKAnalyzer();
	private static Directory dir;
	private static IndexWriterConfig cfg = new IndexWriterConfig(
			Version.LUCENE_36, analyzer);
	static {
		try {
			dir = FSDirectory.open(saveDir);
			cfg.setOpenMode(OpenMode.CREATE);
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

	public static void closeIW(IndexWriter iw) {
		if (iw != null) {
			try {
				iw.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static IndexSearcher getIS() {
		IndexSearcher is = null;
		try {
			is = new IndexSearcher(dir, true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return is;
	}

	public static void closeIS(IndexSearcher is) {
		if (is != null) {
			try {
				is.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
