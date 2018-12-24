package cy.its.violation.rest.dto;

/**
*@title:ViolationUtil.java
*@Package:cy.its.violation.com
*@Description:TODO
*@author:jinhb@cychina.cn
*@date: 2015年9月22日 上午9:53:22
*@version v1.0
 */
public class ViolationUtil {
	
	/**
	 * 字符串为空判断
	 */
	public static boolean IsNullOrEmpty(String str){
		return str==null||"".equals(str);
	}

}
