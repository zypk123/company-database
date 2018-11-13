package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmClassFormatService;
import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EdmClassFormatHystrix implements EdmClassFormatService {

	@Override
	public Result queryList( String edmcId){
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result insertBatch(List<EdmClassFormat> edmClassFormatList) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result deleteBatch(String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getCharacterAndFormat(String classId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getAppearAndEnumObject(String classId,String wordName, int pageNum, int pageSize) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectWordlists( String wordName, int pageNum, int pageSize) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}
}
