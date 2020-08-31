package com.zongze.util;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Date 2020/8/27 16:10
 * @Created by xzz
 */
public class ImageWithWater {



    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            int length = getLength(pressText);
            int v = (int) ((width * 0.8) / length);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, v));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.rotate(75, (double) width / 2, (double) height / 2);


            File file2 = new File("C:\\Users\\13916\\Desktop\\image2\\fff222.jpg");
            g.drawString(pressText, (float) (width*0.1),height/2);
            g.dispose();
//            ImageInputStream in = ImageIO.createImageInputStream(bufferedImage);
            ImageIO.write(bufferedImage, "jpg", file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }


    public static void main(String[] args) {
        pressText("C:\\Users\\13916\\Desktop\\image2\\fff.jpg", "购车无忧保投保专用", "宋体", Font.BOLD|Font.ITALIC, 50, Color.black, 100, 100, 0.4f);
//        double sqrt = Math.sqrt(8);
//        System.out.println(sqrt);
    }



}
