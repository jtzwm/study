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
			System.out.println("==段落数"+paraNum);
			for(int i=0;i<paraNum; i++){
				System.out.println("=====段落"+(i+1)+":"+range.getParagraph(i));
				
				Paragraph paragraph=range.getParagraph(i);
				String strTemp=paragraph.text();
				
				if(strTemp.indexOf("以下无正文")!=-1){
					System.out.println("*********找到以下无正文，在第"+i+"段");
					Range rangeNew=new Range(i,paraNum,doc);
					rangeNew.delete();
					doc.write(new FileOutputStream("D:\\temp\\test.doc"));
					is.close();
					break;
				};
				//System.out.println(range.getParagraph(i).text());				
			}			
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
