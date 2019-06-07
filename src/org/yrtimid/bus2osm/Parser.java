package org.yrtimid.bus2osm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

public class Parser {

	public static void Parse(InputStream stream) throws XPathExpressionException, FileNotFoundException {
		Document doc = null;
		
		Tidy tidy = new Tidy(); // obtain a new Tidy instance
		OutputStream output = new FileOutputStream("result.html");
		tidy.setXmlOut(true);
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setQuiet(true);
		tidy.setXHTML(true);
		//tidy.setXmlPi(true);
		tidy.setMakeClean(true);
		doc = tidy.parseDOM(stream, output); 
		
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder;
//		try {
//			builder = factory.newDocumentBuilder();
//			doc = builder.parse(stream);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		XPathExpression tableExpr = null;
		XPathExpression stopNameExpr = null;
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();

		tableExpr = xpath.compile("//table[@id='Table1']");
		stopNameExpr = xpath.compile("//tr[@class='SmallTableRow']");
		//expr = xpath.compile("/table[id='Table1']/text()");
		Object result = tableExpr.evaluate(doc, XPathConstants.NODESET);
		NodeList tables = (NodeList) result;
	
		//NodeList nodes = doc.getElementsByTagName("table");
//		doc.getDocumentElement().getChildNodes()
		// Cast the result to a DOM NodeList

		for (int i=0; i<tables.getLength();i++){
			Node table = (Node)tables.item(i);
			
			NodeList nameRows = (NodeList)stopNameExpr.evaluate(table, XPathConstants.NODESET);
			for(int t=0; t<nameRows.getLength(); t++){
				Node nameNode = (Node)nameRows.item(t).getChildNodes().item(0);
				org.w3c.tidy.DOMElementImpl el = (org.w3c.tidy.DOMElementImpl)nameNode;

				System.out.println(nameNode.toString());
			}
		}
	
	}
}
