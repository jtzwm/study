package com.zhuwm.h5.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ITextManipulatingPDFTest {
	
	private String src="d:\\temp\\out2.pdf";
	private String dest="d:\\temp\\out3.pdf";

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void test() {
		ITextManipulatingPDF manipulatingPDF=new ITextManipulatingPDF();
		manipulatingPDF.manipulatePdf(src, dest);
	}

}
