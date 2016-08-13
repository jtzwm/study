package com.zhuwm.h5.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.model.DocumentProperties;
import org.apache.poi.hwpf.usermodel.Bookmarks;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ReadWordService {
	
	//处理docx
	public void readWordXFile(){
		String fileName="d:\\temp\\中融信托-集合资金信托信托合同范本1.docx";

		InputStream is;
		try {
			is = new FileInputStream(fileName);
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

	//读取html到string，
	public String loadHtmlFile(){
        FileReader fr;
        StringBuilder sb=new StringBuilder();
		try {
			fr = new FileReader("D:\\temp\\out.html");
	        BufferedReader br=new BufferedReader(fr);
	       
	        while(br.readLine()!=null){
	            sb.append(br.readLine());
	            
	        }
	        br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	//处理doc文件
	public void readReadWordFile(){
		
		try {
			String fileName="d:\\temp\\中融信托-集合资金信托信托合同范本2.doc";
			//String fileName="d:\\temp\\temp.doc";
			InputStream is =new FileInputStream(fileName);
			HWPFDocument doc = new HWPFDocument(is); 
			Range range = doc.getRange();  
			
			//删除文档多余的属性，避免生成html的meta属性
			deleteProperties(doc);
			
			//删除所有bookmark
			deleteBookmarks(doc.getBookmarks());					
			//替换所有变量
			replaceVariable(range);
			//找到并删除指宝文字后的段落
			deleteRange(doc, range);

			//生成html，并处理html
			generateHtml(doc);
						
			//将html转成pdf
			new ConvertHtml2PDF().convert("D:\\temp\\out_new.html");

			System.out.println("*********重新输出 "+doc.getRange().numParagraphs());
			//doc.write(new FileOutputStream("D:\\temp\\out.doc"));
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
	}

	private void deleteProperties(HWPFDocument doc) {
		DocumentProperties dProperties = doc.getDocProperties();
		int i=dProperties.getCCh();
		DocumentSummaryInformation dsi = doc.getDocumentSummaryInformation();
		dsi.setCompany(null);

		
	}



	private void generateHtml(HWPFDocumentCore wordDocument) throws ParserConfigurationException, IOException, TransformerFactoryConfigurationError, TransformerException {
		WordToHtmlConverter converter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		
		converter.processDocument(wordDocument); 
		System.out.println("*********输出html"+converter.getDocument());
		
		//Writer writer = new FileWriter(new File("D:\\temp\\out.html"));  
		Writer strWriter = new StringWriter();
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();  
		transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );  
		//是否添加空格  
		transformer.setOutputProperty( OutputKeys.INDENT, "yes" );  
		transformer.setOutputProperty( OutputKeys.METHOD, "html" );  
		transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );  
		
		transformer.transform(  
		                new DOMSource(converter.getDocument() ),  
		                new StreamResult( strWriter ) );  
		//writer.close();
		
		
/*		//处理一遍html，做标签。这块要优化
		 File file = new File("D:\\temp\\out.html");
	        BufferedReader reader =  new BufferedReader(new FileReader(file));
	        StringBuilder sBuilder= new StringBuilder();
	        String tempString=null;
	        while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sBuilder.append(tempString);
            }*/
	        org.jsoup.nodes.Document doc = Jsoup.parse(strWriter.toString());  
	        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
	        doc.outputSettings().prettyPrint(true);
	        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
	       //Element content = doc.getElementById("meta");
	       
	         	       Elements metas = doc.select("table[class$=t1]");
	       for (Element meta : metas) {
	    	   meta.remove();
	    	 }
	       /*	       
Elements metas1 = doc.select("meta[http-equiv$=Content-Type]");
	       for (Element meta : metas1) {
	    	   //Element newElemnet= meta.clone();
	    	   meta.remove();
	    	 }
	       
	       Elements meta2 = doc.select("head");
	       for (Element meta : meta2) {
	    	   meta2.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></meta> ");
	    	 }
	        //reader.close();
*/	        
            File file1 = new File("D:\\temp\\out_new.html");  
            FileOutputStream  fos = new FileOutputStream(file1);  
            BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));  
            bw.write(doc.html());  
            bw.close();
            fos.close();
		
	}



	private void deleteRange(HWPFDocument doc, Range range) {
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
				Range rangeNew=new Range(range.getParagraph(i+1).getStartOffset(),range.getEndOffset(),doc);
				
				rangeNew.delete();
				break;
			};
		}
		
/*			Paragraph tempRange=null;
		for(int i=30;i<paraNum;i++){
			tempRange=range.getParagraph(i);
			//tempRange.
			tempRange.delete();
		}*/
	}

	//删除标签
	public void deleteBookmarks(Bookmarks bookmarks){
		for(int i=0;i<bookmarks.getBookmarksCount();i++){
			System.out.println(bookmarks.getBookmark(i).toString());
			bookmarks.remove(i);
		}
	}
	
	//替换变量
	public void replaceVariable(Range range){
		range.replaceText("${contract_no}", "替换后的合同号");
	}
}
