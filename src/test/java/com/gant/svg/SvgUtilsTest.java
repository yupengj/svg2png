package com.gant.svg;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class SvgUtilsTest {

	public static String PNG_PARH = "C:\\Users\\gant\\git\\svgTopng\\src\\main\\resources\\target.png";
	public static String SVG_PARH = "C:\\Users\\gant\\git\\svgTopng\\src\\main\\resources\\source1.svg";
	public String uri = null;
	public Svg svg = null;

	@Before
	public void beforeTest() {
		File file = new File(SVG_PARH);
		uri = file.toURI().toString();
		svg = new Svg();
	}

	@Test
	public void testRemoveWatermarkToPng() {
		try {
			SvgUtils.removeWatermarkToPng(uri, PNG_PARH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
