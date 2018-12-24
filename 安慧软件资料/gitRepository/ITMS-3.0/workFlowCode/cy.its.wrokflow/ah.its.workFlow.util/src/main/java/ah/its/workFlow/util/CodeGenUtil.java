package ah.its.workFlow.util;

import java.util.UUID;

/**
* @Title: CodeGenUtil.java 
* @Package ah.its.workFlow.util 
* @Description: 生成唯一码
* @author lil@cychina.cn
* @date 2016年4月20日 下午5:12:22 
* @version V1.0   
 */
public class CodeGenUtil {
	public static  String   getUDDICode(){
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString(); 
        str = str.replaceAll("-", "");
        return str;
	}
}
