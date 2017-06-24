package com.gant.svg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	public static void convert2Png(File svgFile, File jpgFile) throws IOException, TranscoderException {
		InputStream in = new BufferedInputStream(new FileInputStream(svgFile));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(jpgFile));
		convert2Png(in, out);
	}

	public static void convert2Png(InputStream in, OutputStream out) throws IOException, TranscoderException {
		Transcoder transcoder = new PNGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
				out.flush();
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public static void convert2Png(Document document, File pngFile) throws IOException, TranscoderException {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pngFile));
		convert2Png(document, out);
	}

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

	public static void removeWatermark(String uri, String pngFilePath) {
		Document doc = svg.createSvgDoc(uri);
		NodeList nodes = svg.getNodes(doc);
		svg.removeNodes(nodes);
		File png = new File(pngFilePath);
		try {
			png.createNewFile();
			SvgUtils.convert2Png(doc, png);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TranscoderException e) {
			e.printStackTrace();
		}
	}
}
