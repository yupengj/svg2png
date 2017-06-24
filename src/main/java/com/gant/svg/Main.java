package com.gant.svg;

import java.io.File;


public class Main {
	
	public static void main(String[] args) {
		// 当前运行文件夹
		String path = System.getProperty("user.dir");
		File file = new File(path);
		eachFile(file, 1);
	}
	
	
	public static void eachFile(File file,int level){
		// 文件件
		if (file.isDirectory()) {
			String[] fileList = file.list();
			for (String fileName : fileList) {
				if (fileName.endsWith(".svg")) {
					removeWatermark(file.toPath().toString(),fileName);
				}
			}
		}
	}
	
	
	public static void removeWatermark(String filePath,String fileName){
		String uri = filePath+"\\"+fileName;
		String pngFilePath = filePath+"\\"+fileName.replace(".svg", ".png");
		File file = new File(uri);
		SvgUtils.removeWatermark(file.toURI().toString(), pngFilePath);
	}
}
