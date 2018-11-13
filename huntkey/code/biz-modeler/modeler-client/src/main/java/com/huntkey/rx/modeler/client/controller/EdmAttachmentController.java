package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmAttachmentService;
import com.huntkey.rx.modeler.common.model.EdmAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: EdmMethodController
 * @Description: 方法api接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/attachments")
//@Api(value = "附件页面", description = "提供附件的新增、修改、删除和上传、下载服务")
public class EdmAttachmentController {

	private static Logger log = LoggerFactory.getLogger(EdmAttachmentController.class);

	@Autowired
	private EdmAttachmentService edmAttachmentService; // 注入Service

	/**
	 * 附件名校验
	 * @param edmaName
	 * @param edmaEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmaName", method = RequestMethod.GET)
	public Result checkData(@RequestParam String edmaName,
							@RequestParam String edmaEdmcId) {
		Result result = edmAttachmentService.checkEdmaName(edmaName, edmaEdmcId);
		return result;
	}

	/**
	 * 新增附件
	 * @param edmAttachment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmAttachment edmAttachment) {
		Result result = edmAttachmentService.add(edmAttachment);
		return result;
	}

	/**
	 * 修改附件
	 * @param edmAttachment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmAttachment edmAttachment) {
		Result result = edmAttachmentService.update(edmAttachment);
		return result;
	}

	/**
	 * 批量删除附件
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("ids") String[] ids) {
		Result result = edmAttachmentService.delete(ids);
		return result;
	}

	/**
	 * 附件的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result moveUpDown(@RequestBody String[] ids) {
		Result result = edmAttachmentService.move(ids);
		return result;
	}

	/**
	 * 上传附件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result upload(@RequestParam("file") MultipartFile file) {
		Result result = edmAttachmentService.upload(file);
		return result;
	}

	/**
	 * 附件下载
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public Result download(@RequestParam("id") String id, @RequestParam HttpServletResponse response) {
		Result result = edmAttachmentService.download(id, response);
		return result;
	}

}
