package com.mike.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.jfinal.util.PathUtil;
import com.mike.interceptor.SessionInterceptor;

@Before(SessionInterceptor.class)
public class FileController extends Controller{
	public void upload() {
		String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		UploadFile file = getFile("imgFile", PathUtil.getWebRootPath() + "/temp");
		File source = file.getFile();
		String fileName = file.getFileName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String prefix;
		if(".png".equals(extension) || ".jpg".equals(extension) || ".gif".equals(extension)){
			prefix = "img";
			fileName = generateWord() + extension;
		}else{
			prefix = "file";
		}
		JSONObject json = new JSONObject();
		try {
			FileInputStream fis = new FileInputStream(source);
			File targetDir = new File(PathUtil.getWebRootPath() + "/" + prefix + "/u/"
					+ path);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			File target = new File(targetDir, fileName);
			if (!target.exists()) {
				target.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(target);
			byte[] bts = new byte[300];
			while (fis.read(bts, 0, 300) != -1) {
				fos.write(bts, 0, 300);
			}
			fos.close();
			fis.close();
			json.put("error", 0);
			json.put("url", "/" + prefix + "/u/" + path + "/" + fileName);
			source.delete();
		} catch (FileNotFoundException e) {
			json.put("error", 1);
			json.put("message", "上传出现错误，请稍后再上传");
		} catch (IOException e) {
			json.put("error", 1);
			json.put("message", "文件写入服务器出现错误，请稍后再上传");
		}
		renderJson(json.toJSONString());
	}

	private String generateWord() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(5, 9);
		return result;
	}
}
