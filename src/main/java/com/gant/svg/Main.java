package com.gant.svg;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// 当前运行文件夹
		String path = System.getProperty("user.dir");
		File file = new File(path);
		eachFile(file, 1);
	}

	/**
	 * 转换每个文件
	 * 
	 * @param file
	 *            文件夹
	 * @param level
	 *            层级
	 */
	public static void eachFile(File file, int level) {
		// 文件夹
		if (file.isDirectory()) {
			String[] fileList = file.list();
			for (String fileName : fileList) {
				if (fileName.endsWith(".svg")) {
					convert(file.toPath().toString(), fileName);
				}
			}
		}
	}

	/**
	 * 把 svg 文件去掉水印转成 png 文件
	 * 
	 * @param filePath
	 *            要转换的文件路径
	 * @param fileName
	 *            svg 文件名称
	 */
	public static void convert(String filePath, String fileName) {
		String uri = filePath + "\\" + fileName;
		String pngFilePath = filePath + "\\" + fileName.replace(".svg", ".png");
		
		try {
			SvgUtils.removeWatermarkToPng(uri, pngFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转成 png 文件失败", e);
		}
	}
}
