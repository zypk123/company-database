package ah.its.wrokflow.domain.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnResultUtil {
	/**
	 * @param status 状态 AAAAAA成功，000000失败
	 * @param msg 结果信息
	 * @param data 返回数据
	 */
	public static  Map<String, Object> returnResult(String status,String msg,Object data) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", status);
		returnMap.put("msg", msg);
		returnMap.put("data", data);
		return returnMap;
	}
	/**
	 * @param data 返回数据
	 */
	public static  Map<String, Object> returnSuccessResult(Object data) {
		return returnResult("AAAAAA",null,data);
	}
	/**
	 * @param msg 失败信息
	 */
	public static  Map<String, Object> returnErrorResult(String msg) {
		return returnResult("000000",msg,null);
	}
}