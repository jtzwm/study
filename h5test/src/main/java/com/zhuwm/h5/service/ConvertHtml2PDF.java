package com.zhuwm.h5.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ConvertHtml2PDF {
	@SuppressWarnings("deprecation")
	public void convert(String htmlFileName){
		try {
			InputStream is = new FileInputStream(htmlFileName);
			//FileReader fReader = new FileReader(htmlFileName);
			
			OutputStream os = new FileOutputStream("D:\\temp\\out.pdf");
			Document document = new Document();  
			PdfWriter writer = PdfWriter.getInstance(document,os);  
			document.open();  
			
/*			HTMLWorker htmlWorker = new HTMLWorker(document);  
			htmlWorker.parse(fReader);
			//writer.
*/			
			XMLWorkerHelper.getInstance().parseXHtml(writer,document, is);  
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
