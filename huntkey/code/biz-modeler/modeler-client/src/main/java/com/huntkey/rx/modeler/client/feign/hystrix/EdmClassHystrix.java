package com.huntkey.rx.modeler.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmClassService;
import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.vo.EdmClassMethodVO;
import org.springframework.stereotype.Component;

@Component
public class EdmClassHystrix implements EdmClassService {

	@Override
	public Result getClassById(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getSimpleClassById(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getDataProperties(String edmcId, String edmpId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getRelateClass(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result add(EdmClass edmClass) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result update(EdmClass edmClass) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result updateClassMethods(EdmClassMethodVO edmClassMethodVO) {
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
	public Result getProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getFatherProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getFatherConsts(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getMethodByClassId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getAttachments(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropertyNameAndByEdmClassId(String id, String edmpName) {
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
	public Result getConsts(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;

	}

	@Override
	public Result copy(EdmClass edmClass) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result checkEdmcCode( String edmcCode, String edmcEdmdId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result checkEdmcName( String edmcName, String edmcEdmdId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result checkEdmcNameEn( String edmcNameEn, String edmcEdmdId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result checkEdmcShortName(String edmcShortName, String edmcEdmdId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result isChild(String id, String sid) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result updateIsDelByClassIdAndMethodId(String classId, String methodId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result updateBatchIsDelByClassIdAndMethodId( String methodIds) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getUnitClass(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEnumClass(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getStatisticClass(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getFatherMethodByClassId(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result queryClassTree(String modelerId, String[] edmcNameEns) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPublishModelerClass() {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getClassAndRelateClass(String classId, String[] classIdArray) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getClassFormatsByCid(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getChooseRelateProperty(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result isSet(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEntityByVersionAndEnglishName(String edmdVer, String edmcNameEn) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectAssemblesByEdmcId(String edmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectProperties(String edmcId, String assembleId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectIndexByEdmcId(String edmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getParentIndexByEdmcId(String edmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}
	@Override
	public Result getMethodsByEdmcNameVer(String className,String methodName){
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEdmClasses(String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getMonitorClasses(String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getResourcesClasses(String version) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getEdmClassesByEdmcCode(String edmcCode) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getPropertiesOfEdmClass(String classId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getLinkMethod(String classId, String edmmMethodType) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getAllProperties(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result getRelateClasses(String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}

	@Override
	public Result selectEdmClassesAndProps(String ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_ERROR);
		result.setErrMsg("调用服务降级处理逻辑！");
		return result;
	}
}
