package com.dt.core;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public interface TestDataReader {
public default String getData(String nodeName){

	 try {
		 	String testDataFilePath=System.getProperty("user.dir")+"/resources/TestDataXML/TestData.xml";
		 	File inputFile = new File(testDataFilePath);
	        SAXReader saxReader = new SAXReader();
	        Document document = saxReader.read(inputFile);
	        String mobileTesting = document.selectSingleNode("//"+nodeName.split("\\.")[0]+"/"+nodeName.split("\\.")[1]).getText();
			return mobileTesting;
	 }
catch(Exception e)
{
	e.printStackTrace();
	return null;
}
}
}
