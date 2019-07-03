package com.zongze.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create By xzz on 2019/7/2
 */
public class MD5Util {

    private static String token = "wxtoken";
    private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encript(String sign) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("sha1");
            byte[] bytes = digest.digest(sign.getBytes());
            for (byte b : bytes) {
                buffer.append(chars[(b >> 4) & 15]);
                buffer.append(chars[b & 15]);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }





}
