package com.huntkey.rx.commons.utils.rest;


/**
 * Rest接口返回值
 * @ClassName: Result
 * @Description:
 * @author: zhangyu
 * @date: 2017年4月11日上午11:18:27
 *
 */
public class Result {
	
	public static Integer RECODE_SUCCESS=1;
	public static Integer RECODE_ERROR=0;
	public static Integer RECODE_UNLOGIN=-1;
	public static Integer RECODE_VALIDATE_ERROR=-2;

	public Result() {
		this.retCode = Result.RECODE_SUCCESS;
	}

	private String errMsg;
	
	private Integer retCode;
	
	private Object data;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return " {\"errMsg\":" + errMsg + ", \"retCode\":" + retCode + ", \"data\":" + data + "}";
	}
	
}
