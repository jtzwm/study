package com.zhuwm.h5.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class ReadWordService {
	
	public void readWordXFile(){
		InputStream is;
		try {
			is = new FileInputStream("d:\\temp\\中融信托-集合资金信托信托合同范本1.docx");
			XWPFDocument doc = new XWPFDocument(is);
			List<XWPFParagraph> list=doc.getParagraphs();
			System.out.println("ListSize:"+list.size());
			XWPFParagraph paragraph=null;
			int deleteFlat=0;
			for(int i=0;i<list.size();i++){
				paragraph=list.get(i);
				String strTemp=paragraph.getText();
				if(strTemp.indexOf("以下无正文")!=-1){
					System.out.println("****找到以下无正文："+(i+1));
					deleteFlat=i;
					break;
				}
			}
			
			for(int i=deleteFlat+1;i<list.size();i++){
				paragraph=list.get(i);
				
				List<XWPFRun> runs = paragraph.getRuns();
				
				for(int j=0;j<runs.size();j++){
					paragraph.removeRun(j);
				}
				
			}
			FileOutputStream os = new FileOutputStream("D:\\temp\\test.docx");  
		      doc.write(os);  
		      os.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readReadWordFile(){
		
		try {
			InputStream is =new FileInputStream("d:\\temp\\中融信托-集合资金信托信托合同范本1.doc");
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
			int deleteIndex=0;
			for(int i=0;i<paraNum; i++){
				System.out.println("=====段落"+(i+1)+":"+range.getParagraph(i));
				
				paragraph=range.getParagraph(i);
				strTemp=paragraph.text();
				System.out.println(strTemp);
				if(strTemp.indexOf("以下无正文")!=-1){
					System.out.println("*********找到以下无正文，在第"+(i+1)+"段");
					deleteIndex=i;
/*					Range rangeNew=new Range(range.getParagraph(i+1).getStartOffset(),range.getEndOffset(),doc);
					rangeNew.delete();*/
					break;
				};
			}
			Range tempRange=null;
			for(int i=deleteIndex;i<paraNum;i++){
				tempRange=range.getParagraph(i);
				tempRange.delete();
			}
			System.out.println("*********重新输出 "+doc.getRange().numParagraphs());
			System.out.println(doc.getRange().text());
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
