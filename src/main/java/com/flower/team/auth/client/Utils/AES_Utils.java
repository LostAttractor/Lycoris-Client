package com.flower.team.auth.client.Utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_Utils {
    private final static String AES = "AES";
    private final static String UTF8 = "UTF-8";
    //定义一个16byte的初始向量
    private static final String IV_STRING = "Linsk110011ksniL";

    /**
     * 	产生一个16位的密钥字符串
     * @return
     */
    public static String generateSecreKey() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid.substring(0, 16);
    }
    public static String aesEncry(String content,String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] contentByte = content.getBytes(UTF8);
        byte[] keyByte = key.getBytes();
        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(keyByte ,AES);
        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);
        byte[] encryptedBytes = cipher.doFinal(contentByte);
        String encodedString = Base64.getEncoder().encodeToString(encryptedBytes);
        return encodedString;
    }

    public static String aesDecry(String content,String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] contentByte = Base64.getDecoder().decode(content);
        byte[] keyByte = key.getBytes();
        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(keyByte ,AES);
        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
        byte[] result  = cipher.doFinal(contentByte);
        return new String(result,UTF8);
    }



}
