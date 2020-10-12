package com.zongze.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;

/**
 * @Date 2020/9/30 11:17
 * @Created by xzz
 */
public class PDF_Test {



    public static void main(String[] args) throws DocumentException, IOException {

        //https://blog.csdn.net/wqiancangq/article/details/80665361
        //https://blog.csdn.net/qq_33251859/article/details/80927353

//        createPdf();
//        URL url = new URL("https://statics.haibaobaoxian.com/cw/image/1598952893513.jpg");

//        URL url = new URL("");
//        InputStream inputStream = url.openConnection().getInputStream();



    }

    private static void createPdf() throws DocumentException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        //图片1
//        Image image1 = Image.getInstance("D:\\image\\abc.jpg");
        Image image1 = Image.getInstance(new URL("http://statics.haibaobaoxian.com/cw/image/1598952893513.jpg"));
        //设置图片位置的x轴和y周
//        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        document.add(image1);

        Image image2 = Image.getInstance(new URL("http://statics.haibaobaoxian.com/cw/image/1598952893513.jpg"));
        //设置图片位置的x轴和y周
//        image2.setAbsolutePosition(100f, 850f);
        //设置图片的宽度和高度
        image2.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image2);

        Image image3 = Image.getInstance(new URL("http://statics.haibaobaoxian.com/cw/image/1598952893513.jpg"));
        //设置图片位置的x轴和y周
//        image3.setAbsolutePosition(100f, 1150f);
        //设置图片的宽度和高度
        image3.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image3);

        List list = new List();
        list.add(new ListItem("name：  zhangsan"));
        list.add(new ListItem("age：  23"));
        list.add(new ListItem("teacher：  boshi"));

        document.add(list);

//        //图片2
//        Image image2 = Image.getInstance(new URL("http://static.cnblogs.com/images/adminlogo.gif"));
//        //将图片2添加到pdf文件中
//        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器

        writer.close();


        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\13916\\Desktop\\excel\\bos.pdf");
        int len = -1;
        byte[] buffer = new byte[1024];
        while ((len = bis.read(buffer))!= -1){
            outputStream.write(buffer,0,len);
        }

        outputStream.close();
        bis.close();
        bos.close();


    }


}
