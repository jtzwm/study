package com.zhuwm.h5.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ConvertHtml2PDF {
    static final int wdFormatPDF = 17;// PDF 格式    
	//使用jacob
	public void convert2(String sfileName,String toFileName) {
		System.out.println("启动Word...");      
        long start = System.currentTimeMillis();      
        ActiveXComponent app = null;  
        Dispatch doc = null;  
        try {      
            app = new ActiveXComponent("Word.Application");      
            app.setProperty("Visible", new Variant(false));  
            Dispatch docs = app.getProperty("Documents").toDispatch();   
//          doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();   
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] {                    
               sfileName, new Variant(false),new Variant(true) }, new int[1]).toDispatch();               
            System.out.println("打开文档..." + sfileName);  
            System.out.println("转换文档到PDF..." + toFileName);      
            File tofile = new File(toFileName);      
            if (tofile.exists()) {      
                tofile.delete();      
            }        
//          Dispatch.call(doc, "SaveAs",  destFile,  17);                    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {                
                toFileName, new Variant(17) }, new int[1]);    
            long end = System.currentTimeMillis();      
            System.out.println("转换完成..用时：" + (end - start) + "ms.");                
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:文档转换失败：" + e.getMessage());      
        } finally {  
            Dispatch.call(doc,"Close",false);  
            System.out.println("关闭文档");  
            if (app != null)      
                app.invoke("Quit", new Variant[] {});      
            }  
          //如果没有这句话,winword.exe进程将不会关闭  
           ComThread.Release();   
	}
	
  
    public void convert1(String htmlFileName,String pdfFileName){
    	try {
    	 Document document = new Document();
    	 
         // step 2
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
         writer.setInitialLeading(12.5f);
  
         // step 3
         document.open();
  
         // step 4
  
         // CSS
         CSSResolver cssResolver = new StyleAttrCSSResolver();
         CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream("p2 {font-family:宋体}".getBytes()));
         cssResolver.addCss(cssFile);
  
         // HTML
         XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
         fontProvider.register("D:\\temp\\宋体.ttf");
         fontProvider.register("D:\\temp\\楷体_GB2312.ttf");
         fontProvider.register("D:\\temp\\times.ttf");
         fontProvider.addFontSubstitute("楷体_GB2312", "楷体_GB2312");
         fontProvider.addFontSubstitute("Times New Roman", "times");
         fontProvider.addFontSubstitute("宋体", "宋体");
         CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
         HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
         htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
  
         // Pipelines
         PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
         HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
         CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
  
         // XML Worker
         XMLWorker worker = new XMLWorker(css, true);
         XMLParser p = new XMLParser(worker);
         
			p.parse(new FileInputStream(htmlFileName));
			
	         // step 5
	         document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  


    }
    
	
	@SuppressWarnings("deprecation")
	public void convert(String htmlFileName){
		try {
			InputStream is = new FileInputStream(htmlFileName);
			//FileReader fReader = new FileReader(htmlFileName);
			
			OutputStream os = new FileOutputStream("D:\\temp\\out.pdf");
			
			Document document = new Document();  
			PdfWriter writer = PdfWriter.getInstance(document,os);  
			document.open();  

			 XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
			 fontImp.register("D:\\temp\\宋体.ttf");
			 fontImp.register("D:\\temp\\楷体_GB2312.ttf");
			 fontImp.register("D:\\temp\\times.ttf");
			 
			XMLWorkerHelper.getInstance().parseXHtml(writer,document, 
					is,null,Charset.forName("UTF-8"),fontImp);  

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
