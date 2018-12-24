package cy.its.service.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SecurityEncode {

	final static Map<String, Cipher> cipher = new HashMap<String, Cipher>();
	final static String KEY = "%s_%d";
	final static Charset UTF8_CHARSET = Charset.forName("UTF-8");

	/**
	 * MD5方式加密字符串
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * 
	 * 
	 * @comment 程序的价值体现在两个方面:它现在的价值,它未来的价值
	 */
	// public static String encoderByMd5(String str)
	// throws NoSuchAlgorithmException, UnsupportedEncodingException {
	// // 确定计算方法
	// MessageDigest md5 = MessageDigest.getInstance("MD5");
	// Base64Encoder base64en = new BASE64Encoder();
	// // 加密后的字符串
	// String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
	// return newstr;
	// }

	/**
	 * DES加解密
	 * 
	 * @param plainText
	 *            要处理的byte[]
	 * @param key
	 *            密钥
	 * @param mode
	 *            模式
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * 
	 *
	 */
	private static byte[] coderByDES(byte[] plainText, String key, int mode)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
		return getCipher(key, mode).doFinal(plainText);
	}

	/**
	 * 生产8位的key
	 * 
	 * @param key
	 *            字符串形式
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 *
	 */
	private static byte[] makeKey(String key) throws UnsupportedEncodingException {
		byte[] keyByte = new byte[8];
		byte[] keyResult = key.getBytes(UTF8_CHARSET);
		for (int i = 0; i < keyResult.length && i < keyByte.length; i++) {
			keyByte[i] = keyResult[i];
		}
		return keyByte;
	}

	/**
	 * DES加密
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            密钥
	 * @return
	 * 
	 *
	 */
	public static String encoderByDES(String plainText, String key) {
		try {
			return byteArr2HexStr(coderByDES(plainText.getBytes(UTF8_CHARSET), key, Cipher.ENCRYPT_MODE));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return StringUtil.EMPTY_STR;
	}

	/**
	 * DES解密
	 * 
	 * @param secretText
	 *            密文
	 * @param key
	 *            密钥
	 * @return
	 * 
	 *
	 */
	public static String decoderByDES(String secretText, String key) {
		try {
			return new String(coderByDES(hexStr2ByteArr(secretText), key, Cipher.DECRYPT_MODE), UTF8_CHARSET);
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
		
		return StringUtil.EMPTY_STR;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 */
	private static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * ConstValue.INT_2);
		int intTmp;
		for (int i = ConstValue.INT_0; i < iLen; i++) {
			intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < ConstValue.INT_0) {
				intTmp += ConstValue.INT_256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < ConstValue.INT_16) {
				sb.append(ConstValue.CHAR_ZERO);
			}
			sb.append(Integer.toString(intTmp, ConstValue.INT_16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws NumberFormatException
	 */
	private static byte[] hexStr2ByteArr(String strIn) throws NumberFormatException {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	private static Cipher getCipher(String key, int mode) throws InvalidKeyException, UnsupportedEncodingException,
			InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		String k = String.format(KEY, key, mode);
		synchronized (cipher) {
			Cipher c = cipher.get(k);
			if (c == null) {
				c = createCipher(k, mode);
				cipher.put(k, c);
			}

			return c;
		}
	}

	private static Cipher createCipher(String key, int mode) throws UnsupportedEncodingException, InvalidKeyException,
			InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecureRandom sr = new SecureRandom();
		byte[] resultKey = makeKey(key);
		DESKeySpec desSpec = new DESKeySpec(resultKey);
		SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(desSpec);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(mode, secretKey, sr);
		return cipher;
	}
}
