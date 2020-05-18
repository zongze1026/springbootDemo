package com.zongze.util;

//import com.jhlabs.image.GaussianFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Create By xzz on 2019/11/22
 */
public class FileUtil {

    private static volatile boolean exists = true;

    /**
     * 文件上传
     *
     * @param:
     * @return:
     */
    public static String upload(MultipartFile file, String storeDir, String fileName, long sizeLimit) {
        makeStoreDir(storeDir);
        String errorMessage = null;
        errorMessage = checkSize(file, errorMessage, sizeLimit);
        if (StringUtils.isBlank(errorMessage)) {
            FileOutputStream out = null;
            InputStream in = null;
            String storePath = storeDir + fileName;
            try {
                in = file.getInputStream();
                out = new FileOutputStream(storePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(errorMessage)) {
            return "error";
        }
        return "success";
    }


    public static void download(String storeDir, HttpServletResponse response, String mimeType) {
        InputStream in = null;
        OutputStream out = null;
        try {
            response.setContentType(mimeType);
            in = new FileInputStream(storeDir);
            out = response.getOutputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    /**
//     * 下载添加水印
//     * storeDir 原图存储路径
//     * mimeType 文件类型，通过request获得
//     * thumbnailFont 水印文字
//     * fontType 字体
//     * fontSize 文字大小
//     * fontAlpha 文字透明度
//     * pictureSharpness 图片模糊处理清晰度
//     * @param:
//     * @return:
//     */
//    public static void downloadWithWater(String storeDir, HttpServletResponse response, String mimeType,
//                                         String thumbnailFont, String fontType, int fontSize, float fontAlpha, int pictureSharpness) {
//        OutputStream out = null;
//        InputStream in = null;
//        try {
//            response.setContentType(mimeType);
//            out = response.getOutputStream();
//            in = new FileInputStream(storeDir);
//            BufferedImage src = ImageIO.read(in);
//            int width = src.getWidth(null);
//            int height = src.getHeight(null);
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = image.createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            g.drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
//            GaussianFilter gaussianFilter = new GaussianFilter();
//            gaussianFilter.setRadius(pictureSharpness);
//            gaussianFilter.filter(src, image);
//            g.setColor(Color.RED);
//            g.setFont(new Font(fontType, 3, fontSize));
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
//                    fontAlpha));
//            g.rotate(75.00, (double) width / 2, (double) height / 2);
//            int x = -width / 2;
//            int y = -height / 2;
//            int markWidth = fontSize * thumbnailFont.length();// 字体长度
//            int markHeight = fontSize;// 字体高度
//            // 循环添加水印
//            while (x < width * 1.5) {
//                y = -height / 2;
//                while (y < height * 1.5) {
//                    g.drawString(thumbnailFont, x, y);
//                    y += markHeight + 120;
//                }
//                x += markWidth + 120;
//            }
//            g.drawString(thumbnailFont, x, y);
//            g.dispose();
//            ImageIO.write(image, "jpeg", out);
//            out.flush();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }


    /**
     * 检查文件大小
     */
    private static String checkSize(MultipartFile file, String message, long sizeLimit) {
        StringBuilder buffer = new StringBuilder();
        if (file == null) {
            message = "上传文件不存在";
        } else {
            if (file.getSize() > sizeLimit) {  //文件大于10M不上传
                buffer.append(file.getOriginalFilename()).append("[文件大小大于").append(sizeLimit / 1024 / 1024).append("M不允许上传]");
                message = buffer.toString();
            }
        }
        return message;
    }


    /**
     * 检查存储文件夹，没有则创建
     *
     * @param storeDir
     * @param:
     * @return:
     */
    private static void makeStoreDir(String storeDir) {
        if (exists) {
            synchronized (FileUtil.class) {
                if (exists) {
                    File dir = new File(storeDir);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    exists = false;
                }
            }
        }
    }


}
