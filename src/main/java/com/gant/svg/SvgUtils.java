package com.gant.svg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SvgUtils {

	public static Svg svg = new Svg();

	/**
	 * 
	 * @param document
	 * @param pngFile
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public static void convert2Png(Document document, File pngFile) throws IOException, TranscoderException {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pngFile));
		convert2Png(document, out);
	}

	/**
	 * 
	 * @param document
	 * @param out
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public static void convert2Png(Document document, OutputStream out) throws IOException, TranscoderException {
		Transcoder transcoder = new PNGTranscoder();
		TranscoderInput input = new TranscoderInput(document);
		try {
			TranscoderOutput output = new TranscoderOutput(out);
			transcoder.transcode(input, output);
			out.flush();
		} finally {
			out.close();
		}
	}

	/**
	 * 把 svg 文件的水印去掉
	 * 
	 * @param uri
	 *            svg 文件的 uri
	 * @return Document
	 */
	private static Document removeWatermark(String uri) {
		Document doc = svg.createSvgDoc(uri);
		NodeList nodes = svg.getNodes(doc);
		svg.removeNodes(nodes);
		return doc;
	}

	/**
	 * 把 svg 文件的水印去掉 并转成 png 文件
	 * 
	 * @param uri
	 * @param pngFilePath
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public static void removeWatermarkToPng(String uri, String pngFilePath) throws IOException, TranscoderException {
		Document doc = removeWatermark(uri);
		File png = new File(pngFilePath);
		png.createNewFile();
		SvgUtils.convert2Png(doc, png);
	}

}
