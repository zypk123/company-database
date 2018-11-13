package com.huntkey.rx.modeler.client.feign.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmMethodArgService;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;

@Component
public class EdmMethodArgHystrix implements EdmMethodArgService {

	@Override
	public Result insertInputArg(EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result insertReturnArg(EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result deleteRetArg( String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result update(@RequestBody EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getInputArgById(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getReturnArgById(String id) {
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

	@Override
	public Result checkData(String edmaName, String edmaEdmmId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

}
