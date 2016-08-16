package com.zhuwm.h5.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class ITextManipulatingPDF {
	
	private List<Integer> listPages=new ArrayList<Integer>();

	public void manipulatePdf(String src, String dest) {
		try {
			PdfReader reader = new PdfReader(src);
			reader.removeAnnotations();
			
			

			//找文本
			int tempPageNumber=findText("以下无正文", reader);

			//删除指定page
			deletePages(reader, listPages);
			
			//保存新文件
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
			stamper.close();
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deletePages(PdfReader reader,List<Integer> pageNumbers) throws FileNotFoundException, DocumentException, IOException{
		//reader.selectPages(String.format("1,%s-%s, 2-%s, %s", startToc, n-1, startToc - 1, n));
		reader.selectPages(pageNumbers);
		//PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		//stamper.close();
	}
	
	/**
	 * 在pdf文档中找文本
	 * @author littl
	 * @param text
	 * @param reader
	 * @throws IOException
	 */
	private int findText(String text,PdfReader reader) throws IOException{
		int pages=reader.getNumberOfPages();
		String tempPageText=null;
		for (int page = 1; page <= pages; page++) {
			tempPageText=PdfTextExtractor.getTextFromPage(reader, page);
			//System.out.println(tempPageText);
			listPages.add(new Integer(page));
			
			if(tempPageText.contains("以下无正文")){
				System.out.println("==='以下无正文'，在第"+page+"页");
				return page;
			}

		}
		return -1;
	}

}
