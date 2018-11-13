package com.huntkey.rx.commons.exception;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceoGlobleExceptionHandler extends BasicErrorController {

	public SceoGlobleExceptionHandler(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
		super(errorAttributes, errorProperties);
	}

	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        try {
			System.out.println("自定义全局异常处理执行了----HTML-------------------");
			ErrorProperties eps = super.getErrorProperties();
        	response.setCharacterEncoding("UTF-8");
			response.getWriter().write(createErrorMsg().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		System.out.println("自定义全局异常处理执行了----JSON-------------------");
		boolean b = true;// this.getTraceParameter(request);
		Map<String, Object> body = this.getErrorAttributes(request,b);
		body.putAll(createErrorMsg());
		HttpStatus status = getStatus(request);
		System.out.println(status.value() + "---------" + b);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
	private Map<String, Object> createErrorMsg(){
		Map<String, Object> m = new HashMap<String,Object>();
		m.put("retCode",Result.RECODE_ERROR);
		m.put("errMsg","请求处理异常");
		return m;
	}
	/*private String createErrorMsg(){
		Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("请检查URL和参数是否正确");
        return result.toString();
	}*/
}
