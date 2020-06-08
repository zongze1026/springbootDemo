package com.zongze.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By xzz on 2019/8/6
 * 图片工具类：生成二维码、合成图片
 */
public class ImageUtil {


    public static BufferedImage enQRCode(String contents, int width, int height) throws WriterException {
        //定义二维码参数
        final Map<EncodeHintType, Object> hints = new HashMap(8) {
            {
                //编码
                put(EncodeHintType.CHARACTER_SET, "UTF-8");
                //容错级别
                put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                //边距
                put(EncodeHintType.MARGIN, 0);
            }
        };
        return enQRCode(contents, width, height, hints);
    }


    /**
     * 生成二维码
     *
     * @param contents 二维码内容
     * @param width    图片宽度
     * @param height   图片高度
     * @param hints    二维码相关参数
     * @return BufferedImage对象
     * @throws WriterException 编码时出错
     * @throws IOException     写入文件出错
     */
    public static BufferedImage enQRCode(String contents, int width, int height, Map hints) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundPath 背景图路径
     * @param bufferedImage  图片
     * @param x  图片在背景图上绘制的x轴起点
     * @param y  图片在背景图上绘制的y轴起点
     * @return
     */
    public static BufferedImage drawImage(String backgroundPath, BufferedImage bufferedImage, int x, int y) throws IOException {
        //读取背景图的图片流
        BufferedImage backgroundImage;
        try (InputStream imagein = new FileInputStream(backgroundPath)) {
            backgroundImage = ImageIO.read(imagein);
        }
        return drawImage(backgroundImage, bufferedImage, x, y);
    }


    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @param bufferedImage  图片
     * @param x 图片在背景图上绘制的x轴起点
     * @param y 图片在背景图上绘制的y轴起点
     * @return
     * @throws IOException
     */
    public static BufferedImage drawImage(BufferedImage backgroundImage, BufferedImage bufferedImage, int x, int y) throws IOException {

        Graphics2D g = backgroundImage.createGraphics();
        g.drawImage(bufferedImage, (backgroundImage.getWidth()-x)/2, (backgroundImage.getHeight()-y)/2,
                bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        return backgroundImage;
    }

    /**
     * 将文字绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @param x  文字在背景图上绘制的x轴起点
     * @param y  文字在背景图上绘制的y轴起点
     * @return
     * @throws IOException
     */
    public static BufferedImage drawString(BufferedImage backgroundImage, String text, int x, int y, Font font, Color color) {
        //绘制文字
        Graphics2D g = backgroundImage.createGraphics();
        //设置颜色
        g.setColor(color);
        //消除锯齿状
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //设置字体
        g.setFont(font);
        //绘制文字
        g.drawString(text, x, y);
        return backgroundImage;
    }


    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage) throws IOException {
        return bufferedImageToInputStream(backgroundImage, "png");
    }

    /**
     * backgroundImage 转换为输出流
     *
     * @param backgroundImage
     * @param format
     * @return
     * @throws IOException
     */
    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage, String format) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (ImageOutputStream imOut = ImageIO.createImageOutputStream(bs)) {
            ImageIO.write(backgroundImage, format, imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());
            return is;
        }
    }

    /**
     * 保存为文件
     *
     * @param is
     * @param fileName
     * @throws IOException
     */
    public static void saveFile(InputStream is, String fileName) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(is);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        }
    }

    public static void main(String[] args) {
        createQrCode();
    }


    private static void createQrCode() {
        //二维码宽度
        int width = 100;
        //二维码高度
        int height = 100;
        //二维码内容
        String contcent = "http://www.baidu.com/?wd=妈妈";
        BufferedImage zxingImage = null;
        try {
            //二维码图片流
            zxingImage = ImageUtil.enQRCode(contcent, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        //背景图片地址
        String backgroundPath = "C:\\Users\\Administrator\\Desktop\\aa\\719565155309977600.jpg";
        InputStream inputStream = null;
        try {
            //合成二维码和背景图
            BufferedImage image = ImageUtil.drawImage(backgroundPath, zxingImage, width, height);
            //绘制文字
            //读取本地字体
            Font font = new Font("微软雅黑", Font.BOLD, 35);
            //读取外部字体
//            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Windows\\Fonts\\msyhbd.ttc"));
            //手动设置字体大小,粗体
            font = font.deriveFont(35F).deriveFont(Font.BOLD);
            //文字内容
            String text = "17000";
            image = ImageUtil.drawString(image, text, 375, 647, font, new Color(244, 254, 189));
            //图片转inputStream
            inputStream = ImageUtil.bufferedImageToInputStream(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存的图片路径
        String originalFileName = "C:\\Users\\Administrator\\Desktop\\aa\\719565109977600.jpg";
        try {
            //保存为本地图片
            ImageUtil.saveFile(inputStream, originalFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
