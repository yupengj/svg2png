package com.gant.svg;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class SvgUtilsTest {

	public static String JPG_PARH = "N:\\workspace\\svgTo\\src\\main\\resources\\target.jpg";
	public static String SVG_PARH = "N:\\workspace\\svgTo\\src\\main\\resources\\source.svg";
	public String uri = null;
	public Svg svg = null;
	
	@Before
	public void beforeTest(){
		File file = new File(SVG_PARH);
		uri = file.toURI().toString();
		svg = new Svg();
	}
	
	@Test
	public void testConvert2Jpg(){
		try {
			File svg = new File(SVG_PARH);
			File jpg = new File(JPG_PARH);
			SvgUtils.convert2Png(svg, jpg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveWatermark(){
		try {
			SvgUtils.removeWatermark(uri, JPG_PARH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
