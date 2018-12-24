/**
 * @Title: MD5Util.java
 * @Package cy.its.com.util
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年11月13日 上午10:11:16
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package sync.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
  * @ClassName: MD5Util
  * @Description: 给字符串进行md5加密
  * @author zuop zuop@cychina.cn
  * @date 2015年11月13日 上午10:11:16
  *
  */
public class MD5Util {
	public static String md5(String in) throws NoSuchAlgorithmException{
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','b','d','e','f'};
		// 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(in.getBytes());
        // 获得密文
        byte[] md = mdInst.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
       return new String(str);
	}
}
