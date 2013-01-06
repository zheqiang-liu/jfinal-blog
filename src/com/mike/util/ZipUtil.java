package com.mike.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	private static final int BUFFER = 1024;

	public static void zip(File path) {
		zip(path.getAbsolutePath(), path.getAbsolutePath());
	}

	public static void zip(String path) {
		zip(path, path);
	}

	public static void zip(String srcPath, String desPath) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(desPath
					+ ".zip"));
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
					fos, BUFFER));
			File inputFile = new File(srcPath);
			if (inputFile.isDirectory()) {
				handlerDir(inputFile, zos, "");
			} else {
				FileInputStream fileIn = new FileInputStream(inputFile);
				ZipEntry zipEntry = new ZipEntry(inputFile.getName());
				zos.putNextEntry(zipEntry);
				BufferedInputStream bis = new BufferedInputStream(fileIn,
						BUFFER);
				byte[] data = new byte[BUFFER];
				while (bis.read(data, 0, BUFFER) != -1) {
					zos.write(data, 0, BUFFER);
				}
				bis.close();
				fileIn.close();
			}
			zos.closeEntry();
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handlerDir(File inputFile, ZipOutputStream zos,
			String base) {
		FileInputStream fileIn;
		File[] files;
		files = inputFile.listFiles();
		try {
			if (files.length != 0) {
				base = base + inputFile.getName() + "/";
				for (File file : files) {
					if (file.isDirectory()) {
						handlerDir(file, zos, base);
					} else {
						fileIn = new FileInputStream(file);
						ZipEntry zipEntry = new ZipEntry(base + file.getName());
						zos.putNextEntry(zipEntry);
						BufferedInputStream bis = new BufferedInputStream(
								fileIn, BUFFER);
						byte[] data = new byte[BUFFER];
						while (bis.read(data, 0, BUFFER) != -1) {
							zos.write(data, 0, BUFFER);
						}
						bis.close();
						fileIn.close();
						zos.closeEntry();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
