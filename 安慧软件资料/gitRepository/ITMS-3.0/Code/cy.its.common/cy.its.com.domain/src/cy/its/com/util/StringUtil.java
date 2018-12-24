package cy.its.com.util;

import java.io.IOException;
import java.util.UUID;

public class StringUtil {

	public final static String EMPTY_STR = "";

	public static boolean isNullOrEmpty(String str) {
		return str == null || EMPTY_STR.equals(str);
	}

	public static String padRight(String str, int totalWidth, char paddingChar) {
		if (StringUtil.isNullOrEmpty(str)) {
			str = EMPTY_STR;
		}

		int strLen = str.length();
		if (strLen >= totalWidth) {
			return str;
		}

		StringBuilder sb = new StringBuilder(totalWidth);
		sb.append(str);
		while (sb.length() < totalWidth) {
			sb.append(paddingChar);
		}

		return sb.toString();
	}

	public static String padLeft(String str, int totalWidth, char paddingChar) {
		if (StringUtil.isNullOrEmpty(str)) {
			str = EMPTY_STR;
		}

		int strLen = str.length();
		if (strLen >= totalWidth) {
			return str;
		}

		StringBuilder sb = new StringBuilder(totalWidth);
		int len = totalWidth - strLen;
		while (sb.length() < len) {
			sb.append(paddingChar);
		}
		sb.append(str);

		return sb.toString();
	}

	public static String trimEnd(String target, char c) {
		if (StringUtil.isNullOrEmpty(target) || !target.endsWith(String.valueOf(c))) {
			return target;
		}

		for (int i = target.length() - 1; i >= 0; i--) {
			if (target.charAt(i) == c) {
				if (i == 0) {
					return EMPTY_STR;
				}
				continue;
			} else {
				return target.substring(0, i + 1);
			}
		}
		return target;
	}

	public static String trimStart(String target, char c) {
		if (StringUtil.isNullOrEmpty(target) || !target.startsWith(String.valueOf(c))) {
			return target;
		}

		int len = target.length() - 1;
		for (int i = 0; i <= len; i++) {
			if (target.charAt(i) == c) {
				if (i == len) {
					return EMPTY_STR;
				}
				continue;
			} else {
				return target.substring(i);
			}
		}
		return target;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static int getDefaultByteCount(String text) {
		if (isNullOrEmpty(text)) {
			return 0;
		}
		return text.getBytes().length;
	}

	public static String getErrorDetail(Exception e) {
		java.io.Writer w = null;
		java.io.PrintWriter pw = null;
		try {
			w = new java.io.StringWriter();
			pw = new java.io.PrintWriter(w);
			e.printStackTrace(pw);
			return w.toString();
		} catch (Exception e2) {
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (pw != null) {
				pw.close();
			}
		}

		return EMPTY_STR;
	}

	public static Boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		}

		if (a != null) {
			return a.equals(b);
		}

		return b.equals(a);
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		return obj == null || EMPTY_STR.equals(String.valueOf(obj).trim());
	}
	
	/**
	 * 对行政区划代码进行转换
	 * @param distritCodes
	 * @return
	 */
	public static String convertDistritCodes(String distritCodes){
		if(!StringUtil.isNullOrEmpty(distritCodes)){
			StringBuffer strb = new StringBuffer();
			String [] distritCodesArray = distritCodes.split(",");
			for(String distritCode:distritCodesArray){
				//省
				if("0000".equals(distritCode.substring(2, 6))){
					strb.append(distritCode.substring(0, 2));
				}
				//地市
				else if("00".equals(distritCode.substring(4, 6))){
					if(strb.length()>0){
						strb.append(",");
					}
					strb.append(distritCode.substring(0, 4));
				}else{
					if(strb.length()>0){
						strb.append(",");
					}
					strb.append(distritCode);
				}
			}
			return strb.toString();
		}
		return null;
	}
	
}
