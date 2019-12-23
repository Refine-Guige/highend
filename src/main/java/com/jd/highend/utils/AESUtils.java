package com.jd.highend.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;


/**
 * @author guwei
 * @version 1.0
 * @date 2018/8/16
 * @descrition 前后端请求参数的解密
 */
public class AESUtils {

    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String AES_KEY = "aUF3dHB3ZA==YHRD";

    /**
     * aes解密
     * @param encrypt   内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) {
        try {
            return aesDecrypt(encrypt, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * aes加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        try {
            return aesEncrypt(content, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * 测试
     * 前端js将参数加密提交到后台如何解密
     * 首先获取服务端的私钥:将客户端的公钥加密后获得的结果
     * 通过服务端的私钥和客户端传递的加密字符串即可实现解密
     */
    public static void main(String[] args) throws Exception {
        //String content = "{\"login_name\":\"admin\",\"password\":\"Dj2018&&\",\"loginCnt\":0}";
//        String content = "{\"userId\": \"029b6571f3af4a2a8ce9d5c090bc3484\",\"pinCode\": \"dj-test\"}";
        String content = "{\"login_name\":\"admin\",\"password\":\"Dj2018&&\",\"verifyCode\":\"\",\"loginCnt\":\"\"}";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + AES_KEY);
        String encrypt = aesEncrypt(content, AES_KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt("ZEOomeu1d2TALjwxhJeMS0XPW8IRRAwMS+GrYPbe/PJ8UEQ5zOJNB45OkAIWqd8neNjCdXB8poxs17jNVG0AE1Dfg4L2wIebFB7Cl7IETS688PtwtRPBU+Xy+17OHXbZ", AES_KEY);
        System.out.println("解密后：" + decrypt);
    	String encryptContent = "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";
    	String str = "https://spread.jd.com/spread-activity/activity_july_0711.html?channel=jdjrbanner&jrcontainer=h5&jrlogin=true&jrcolseweb=false&jrwallet=false&jrxviewtype=false&jrgobackrefresh=false&sid=cff3eba1a95e033abea0fe4c50a6768w";
        System.out.println(str.length());
    }
}




