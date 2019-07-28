package com.john.www.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @Author:""
 * @Description: word和html的转换工具类
 * @Date: 9:33 2018/8/23
 * @Modified By:
 */
public class WordHtmlTransitionUtils {

    private static String uploadPath;

    @Value("${upload.path}")
    public  void setUploadPath(String uploadPath) {
        WordHtmlTransitionUtils.uploadPath = uploadPath;
    }

    /**
     * 功能描述: 将doc文件转为html文件
     *
     * @param file 转换的文件
     * @author:""
     * @return: 返回转换后的文件夹
     * @date: Created in 9:39 2018/8/23
     */
    public static File docToHtml(File file) throws Exception {
        //word文件的名称
        String wordFileName = file.getName();
        //获取文件的名称
        String fileName = wordFileName.substring(0, wordFileName.lastIndexOf("."));
        //文件夹的名称
        String upLoadDirPath = uploadPath + "/" + fileName + "/";
        //html文件的路径
        String htmlFilePath = upLoadDirPath + fileName + ".html";
        //图片文口夹的路径
        String htmlImgPath = upLoadDirPath + "image/";

        File imgFile = new File(htmlImgPath);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(file));

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        // 保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(htmlImgPath + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });

        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(htmlFilePath));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        file.delete();
        return new File(upLoadDirPath);
    }

    /**
     * 功能描述: 将docx文件转为html文件
     *
     * @param file 转换的文件
     * @author:""
     * @return:
     * @date: Created in 20:43 2018/8/23
     */
    public static File docxToHtml(File file) throws Exception {



        //word文件的名称
        String wordFileName = file.getName();
        //获取文件的名称
        String fileName = wordFileName.substring(0, wordFileName.lastIndexOf("."));
        //文件夹的名称
        String upLoadDirPath = uploadPath + "/" + fileName + "/";
        //html文件的路径
        String htmlFilePath = upLoadDirPath + fileName + ".html";
        //图片文口夹的路径
        String htmlImgPath = upLoadDirPath + "image/";

        File imgFile = new File(htmlImgPath);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(file));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(htmlImgPath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(htmlFilePath),
                    "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        file.delete();
        return new File(upLoadDirPath);
    }

}
