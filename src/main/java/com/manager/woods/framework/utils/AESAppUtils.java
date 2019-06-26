package com.apricot.woods.framework.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES加密与解密（兼容android）
 */
public class AESAppUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AESAppUtils.class);
	private static final String IV = "7Ndt1mNx04Z3g69I";
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String CHARSET = "utf8";

	/**
	 * 加密
	 * @param data 要加密的内容
	 * @param KEY 加密密钥
	 * @return
	 */
	public static String aesEncrypt(String data, String KEY) {
		try {
			String encryptKey = KEY;
			SecretKeySpec skeySpec = getKey(encryptKey);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(data.getBytes());

			return bytesToHexString(encrypted);
		} catch (Exception e) {
			logger.error("AESAppUtils aesEncrypt err.", e);
			return null;
		}
	}

	/**
	 * 解密
	 * @param data 要解密的内容
	 * @param KEY 解密密钥
	 * @return
	 */
	public static String aesDecrypt(String data, String KEY) {
		try {
			String decryptKey = KEY;
			SecretKeySpec skeySpec = getKey(decryptKey);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = hexStringToBytes(data);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, CHARSET);

			return originalString;
		} catch (Exception e) {
			logger.error("AESAppUtils aesDecrypt err.", e);
			return null;
		}
	}
	
	private static SecretKeySpec getKey(String strKey) throws Exception {//NOSONAR
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16];

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
		return skeySpec;
	}

	// 将二进制转换成16进制
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv.toUpperCase());
		}
		return stringBuilder.toString();
	}

	// 将16进制转换成二进制
	private static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || "".equals(hexString)) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

}