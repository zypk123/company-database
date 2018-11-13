package com.huntkey.rx.hr.provider.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by licj on 2017/4/13.
 */
public interface EdmAttachmentService {



	ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response);

	/**
	 * 批量删除
	 * @param ids
	 */

}
