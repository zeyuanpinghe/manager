package com.manager.woods.framework.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 为前后端加密定义的加密算法
 */
public class AESForJs {

    /**
     * 进行AES加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptAES(String data,String key) throws Exception {
        String md5key = MD5ForJs.getMD5By16(key);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding");   //参数分别代表 算法名称/加密模式/数据填充方式
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(md5key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(md5key.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 进行AES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptAES(String data,String key) throws Exception {
        String md5key = MD5ForJs.getMD5By16(key);
        try {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding");
            SecretKeySpec keyspec = new SecretKeySpec(md5key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(md5key.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}