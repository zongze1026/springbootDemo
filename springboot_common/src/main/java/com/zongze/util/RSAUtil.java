package com.zongze.util;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 非对称加密 唯一广泛接受并实现 数据加密&数字签名 公钥加密、私钥解密 私钥加密、公钥解密
 * 
 * @author jjs
 *
 */
public class RSAUtil {

    private static RSAPublicKey rsaPublicKey;
    private static RSAPrivateKey rsaPrivateKey;

    static {
        // 1、初始化密钥
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);// 64的整倍数
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public Key : " + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            System.out.println("Private Key : " + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 公钥加密，私钥解密
     * @author jijs
     */
    public static void pubEn2PriDe(String src) {
        try {
            //公钥加密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("公钥加密，私钥解密 --加密: " + Base64.encodeBase64String(result));

            //私钥解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密，私钥解密 --解密: " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * 私钥加密，公钥解密
     * @author jijs
     */
    public static void priEn2PubDe(String src) {

        try {
            //私钥加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("私钥加密，公钥解密 --加密 : " + Base64.encodeBase64String(result));

            //公钥解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私钥加密，公钥解密   --解密: " + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String src = "852147";
        pubEn2PriDe(src);  //公钥加密，私钥解密
        priEn2PubDe(src);  //私钥加密，公钥解密
    }
}