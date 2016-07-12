package com.zhuwm.h5.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.stereotype.Service;

@Service
public class ReadWordService {

	public void readReadWordFile(){
		
		try {
			InputStream is =new FileInputStream("d:\\temp\\中融信托-集合资金信托信托合同范本.doc");
			//WordExtractor extractor = new WordExtractor(is);  	
			HWPFDocument doc = new HWPFDocument(is); 
			//System.out.println(doc.getText());
			Range range = doc.getRange();  
			int paraNum= range.numParagraphs();
			int sectNum=range.numSections();
			
			System.out.println("==段落数"+paraNum);
			System.out.println("==section数"+paraNum);
			System.out.println("==字符数"+doc.getRange().getEndOffset());
			String strTemp="";
			Paragraph paragraph=null;
/*			for(int i=0;i<paraNum; i++){
				System.out.println("=====段落"+(i+1)+":"+range.getParagraph(i));
				
				paragraph=range.getParagraph(i);
				strTemp=paragraph.text();
				
				if(strTemp.indexOf("以下无正文")!=-1){
					System.out.println("*********找到以下无正文，在第"+i+"段");
					
					
					Range rangeNew=new Range(range.getParagraph(i+1).getStartOffset(),range.getEndOffset(),doc);
					rangeNew.delete();
					
					break;
				};


			}*/
			
			
			System.out.println("*********重新输出 ");
			System.out.println(doc.getRange().getEndOffset());
			doc.write(new FileOutputStream("D:\\temp\\test.doc"));
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}

}
