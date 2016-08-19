package com.zhuwm.h5.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PdfBoxManipulatingPDFTest {
	
	private String src="d:\\temp\\out2.pdf";
	private String dest="d:\\temp\\out3.pdf";

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testReplaceText() {
		PdfBoxManipulatingPDF pdfBox =new PdfBoxManipulatingPDF();
		try {
			pdfBox.replaceText(src,dest);
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
