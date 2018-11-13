
package com.huntkey.rx.commons.abstracts;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 控制器支持类
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	/**
	 * 获取服务器根路径
	 * 
	 * @param request
	 * @return 例如：http://192.168.1.5:8080/userCenter/
	 */
	public String getRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		return basePath;
	}

	/**
	 * uri解析
	 * 
	 * @param param
	 * @return
	 */
	public String decodeUri(String param) {
		if (StringUtils.isBlank(param)) {
			return param;
		}
		try {
			String no = URLDecoder.decode(param, "UTF-8");
			return no;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return param;
		}
	}

}
