package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmPropertyService;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Component
public class EdmPropertyHystrix implements EdmPropertyService {

	@Override
	public Result getProperty(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropertyTree(String id, int flag) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}
	@Override
	public Result checkEdmpCode(String edmpCode, String edmpEdmcId,String edmpParentId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result checkEdmpName(String edmpName, String edmpEdmcId, String edmpParentId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result add(EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result addConsts(EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result update(EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result delete(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result deleteIds(String[] ids) {
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
	public Result updateMethod(String uid, String mid) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result updateTriggerCond(String id,String triggerCondId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getConnectsByEdcnEdmpId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getConvolutesByEdcoEdmpId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getLinksByEdmlEdmpId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getUnitsByEdunEdmpId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getCondsByPropertyId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}


	@Override
	public Result getSameClassUnitsListByEdunEdmpId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEdmConnectById(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEdmpCode(String classId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}


	@Override
	public Result getValueLimitById(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result updateProperty(EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getTwoProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEnumTwoProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropertyAndClass(String propertyId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropertyTypeAndValue(String classId, String edmpCode) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getConvolutionPropertiesByVersionAndEnName(String edmdVer, String edmcNameEn) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

//	@Override
//	public Result changePropertiesVisible(String ids, byte b) {
//		Result result = new Result();
//		result.setRetCode(Result.RECODE_ERROR);
//		result.setErrMsg("调用服务降级处理逻辑！");
//		return result;
//	}

	@Override
	public Result clearLimitAndFormula(Map<String, String> map) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectGroups(String edmcId, String edmpId, String isSource) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropTree(String edmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEnumInfoCode(String className, String propCode) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}
}
