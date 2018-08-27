package com.runxsports.provider.cs.cms.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

	/**
	 * 创建一个日期目录
	 * 
	 * @param path
	 * @return
	 */
	public static String createDateDir(String path) {
		StringBuilder tempPath = new StringBuilder();
		tempPath.append(path);
		if(!path.endsWith("/")) {
			tempPath.append("/");
		}
		tempPath.append(DateUtils.formatDate(new Date(), "yyyyMMdd")).append("/");
		
		String dirPath = tempPath.toString();
		createDir(dirPath);
		return dirPath;
	}
	
	/**
	 * 创建目录录
	 * 
	 * @param path
	 */
	public static void createDir(String pathName) {
		File file = new File(pathName);
		if (file.exists()) {
			return;
		}
		if (file.mkdir()) {
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
			return;
		}
		File canonFile = null;
		try {
			canonFile = file.getCanonicalFile();
		} catch (IOException e) {
			log.info(e.getMessage() != null? e.getMessage():e.getCause().toString());
			return;
		}
		File parent = canonFile.getParentFile();
		createDir(parent.getPath());
		createDir(canonFile.getPath());
	}
	
	/**
	 * 获取扩展名(含.,若.jpg)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExt(String fileName) {
		int pos = 0;
		if((pos = fileName.lastIndexOf(".")) != -1) {
			return fileName.substring(pos);
		}
		
		throw new CmsException(CmsErrorCodeEnum.CMS9083007);
	}
}
