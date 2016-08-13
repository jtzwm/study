package com.zhuwm.h5.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ConvertHtml2PDFTest {
	
	private String htmlFileName="D:\\temp\\out_new.html";
	private String docFileName="D:\\temp\\中融信托-集合资金信托信托合同范本1.doc";
	private String pdfFileName="D:\\temp\\out2.pdf";

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void test() {
		ConvertHtml2PDF convertHtml2PDF= new ConvertHtml2PDF();
		//convertHtml2PDF.convert(htmlFileName);
		//convertHtml2PDF.convert1(htmlFileName,pdfFileName);
		convertHtml2PDF.convert2(docFileName,pdfFileName);
		assertTrue(true);
		
	}
	

	

}
