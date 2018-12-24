package cy.its.service.common;

public 
class Base64 {

	public static String encodeString(String src) {
		return new String(encode(src.getBytes()));
	}

	public static String encodeBytes(byte[] data) {
		return String.valueOf(encode(data));
	}

	/**
	 * 将原始数据编码为base64编码
	 */
	static public char[] encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];
		boolean quad , trip;
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			quad = false;
			trip = false;
			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	public static String decodeForString(String target) {
		return new String(decode(target.toCharArray()));
	}

	public static byte[] decodeForBytes(String target) {
		return decode(target.toCharArray());
	}

	/**
	 * 将base64编码的数据解码成原始数据
	 */
	static public byte[] decode(char[] data) {
		int len = ((data.length + 3) / 4) * 3;
		if (data.length > 0 && data[data.length - 1] == '=')
			--len;
		if (data.length > 1 && data[data.length - 2] == '=')
			--len;
		byte[] out = new byte[len];
		int shift = 0;
		int accum = 0;
		int index = 0;
		int value;
		for (int ix = 0; ix < data.length; ix++) {
			value = codes[data[ix] & 0xFF];
			if (value >= 0) {
				accum <<= 6;
				shift += 6;
				accum |= value;
				if (shift >= 8) {
					shift -= 8;
					out[index++] = (byte) ((accum >> shift) & 0xff);
				}
			}
		}
		if (index != out.length)
			throw new Error("miscalculated data length!");
		return out;
	}

	static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();

	static private byte[] codes = new byte[256];

	static {
		for (int i = 0; i < 256; i++)
			codes[i] = -1;
		for (int i = 'A'; i <= 'Z'; i++)
			codes[i] = (byte) (i - 'A');
		for (int i = 'a'; i <= 'z'; i++)
			codes[i] = (byte) (26 + i - 'a');
		for (int i = '0'; i <= '9'; i++)
			codes[i] = (byte) (52 + i - '0');
		codes['+'] = 62;
		codes['/'] = 63;
	}

	public static void main(String[] args) throws Exception {
		// 加密成base64
		String strSrc = "林";
		String strOut = new String(Base64.encode(strSrc.getBytes()));
		System.out.println(strOut);

		String strOut2 = new String(Base64.decode(strOut.toCharArray()));
		System.out.println(strOut2);
	}
}
