package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmAttachmentService;
import com.huntkey.rx.modeler.common.model.EdmAttachment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Component
public class EdmAttachmentHystrix implements EdmAttachmentService {

	@Override
	public Result checkEdmaName(String edmaName,String edmaEdmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result add(EdmAttachment edmAttachment) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result update(EdmAttachment edmAttachment) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result delete(String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result upload(MultipartFile file) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result download(String id,HttpServletResponse response) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result move(String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

}
