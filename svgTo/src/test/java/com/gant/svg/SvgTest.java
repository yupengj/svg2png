package com.gant.svg;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SvgTest {

	public static String PATH = "N:\\workspace\\svgTo\\src\\main\\resources\\source.svg";
	public static String PRINT_PARH = "N:\\workspace\\svgTo\\src\\main\\resources\\source1.svg";
	public String uri = null;
	public Svg svg = null;
	
	@Before
	public void beforeTest(){
		File file = new File(PATH);
		uri = file.toURI().toString();
		svg = new Svg();
	}
	
	@Test
	public void testCreateSvg(){
		Document dom = svg.createSvgDoc(uri);
		System.out.println(dom);
	}
	
	@Test
	public void testGetNodes(){
		Document doc = svg.createSvgDoc(uri);
		NodeList nodes = svg.getNodes(doc);
		svg.readNodes(nodes);
	}
	
	@Test 
	public void testRemoveNodes(){
		Document doc = svg.createSvgDoc(uri);
		NodeList nodes = svg.getNodes(doc);
		svg.removeNodes(nodes);
		svg.readNodes(nodes);
	}
	
	@Test
	public void testPrintSvgFile() throws IOException{
		Document doc = svg.createSvgDoc(uri);
		NodeList nodes = svg.getNodes(doc);
		svg.removeNodes(nodes);
		
		//svg.readNodes(svg.getNodes(doc));
		
		svg.printSvgFile(PRINT_PARH, doc);
	}
}
