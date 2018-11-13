package com.huntkey.rx.commons.utils.uuid;

import java.security.SecureRandom;
import java.util.UUID;

public class UuidCreater {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static long longUUID() {
		return UUID.randomUUID().getMostSignificantBits();
	}

	public static long absLongUUID() {
		long r;
		do {
			r = longUUID();
		} while (r <= 2162214659975806976L);
		return r;
	}

}
