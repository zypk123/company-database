package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmAttachmentHystrix;
import com.huntkey.rx.modeler.common.model.EdmAttachment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by licj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider", fallback = EdmAttachmentHystrix.class)
public interface EdmAttachmentService {

	@RequestMapping(value = "/attachments/edmaName", method = RequestMethod.GET)
	Result checkEdmaName(@RequestParam(value = "edmaName")  String edmaName,
						 @RequestParam(value = "edmaEdmcId") String edmaEdmcId);

	@RequestMapping(value = "/attachments", method = RequestMethod.POST)
	Result add(@RequestBody EdmAttachment edmAttachment);

	@RequestMapping(value = "/attachments", method = RequestMethod.PUT)
	Result update(@RequestBody EdmAttachment edmAttachment);

	@RequestMapping(value = "/attachments/{ids}", method = RequestMethod.DELETE)
	Result delete(@PathVariable("ids") String[] ids);

	@RequestMapping(value = "/attachments/move", method = RequestMethod.PUT)
	Result move(@RequestBody String[] ids);

	@RequestMapping(value = "/attachments/upload", method = RequestMethod.POST)
	Result upload(@RequestParam(required = true, value = "file") MultipartFile file);

	@RequestMapping(value = "/attachments/download", method = RequestMethod.GET)
	Result download(@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "response") HttpServletResponse response);
}
