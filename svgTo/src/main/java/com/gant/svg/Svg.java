package com.gant.svg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Svg {

	public Document createSvgDoc(String uri) {
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
		Document doc = null;
		try {
			doc = f.createDocument(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public NodeList getNodes(Document doc) {
		// 根节点 <svg></svg>
		Element element = doc.getDocumentElement();
		NodeList nodes = element.getChildNodes();
		return nodes;
	}

	public void removeNodes(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.hasChildNodes()) {
				removeNodes(node.getChildNodes());
			}
			if (getNodeValue().equals(node.getNodeValue())) {
				node.getParentNode().removeChild(node);
			}
		}
	}

	public String getNodeValue() {
		String value = "";
		Properties prop = new Properties();
		try {
			prop.load(Svg.class.getResource("/config.properties").openStream());
			value = prop.getProperty("node_value");
//			value+=" ";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void readNodes(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.hasChildNodes()) {
				readNodes(node.getChildNodes());
			}

//			String nodeName = node.getNodeName();
//			String nodeValue = node.getNodeValue();
			//System.out.println(i + " name:" + nodeName + " value:" + nodeValue);
			//System.out.println();

		}
	}

	public void printSvgFile(String path, Document document) throws IOException {
		File file = new File(path);

		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		svgGenerator.stream(out, true);
	}
}
