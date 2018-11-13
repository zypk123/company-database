package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodAndArgVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodVO;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.service.EdmMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: EdmMethodController
 * @Description: 方法Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:02:59
 */
@RestController
@RequestMapping("/methods")
public class EdmMethodController {

	private static Logger log = LoggerFactory.getLogger(EdmMethodController.class); // 得到log对象

	@Autowired
	EdmMethodService edmMethodService; // 注入Service

	@Autowired
	private DefaultGenerateStorageClient defaultGenerateStorageClient;

	@Value("${modeler.version}")
	private String version;

	/**
	 * 通过ID查询方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result getMethodById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmMethodAndArgVO edmMethodAndArgVO = edmMethodService.selectByPrimaryKey(id);
		result.setData(edmMethodAndArgVO);
		return result;
	}

	/**
	 * 通过ID查询方法基本数据（供oozie用）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public Result getSimpleMethodById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmMethod edmMethod = edmMethodService.getSimpleMethodById(id);
		result.setData(edmMethod);
		return result;
	}

	/**
	 * 条件查询，带分页
	 * @param edmmName
	 * @param edmmClasses 所属类 多个以逗号分隔
	 * @param pageNum
	 * @param pageSize
	 * @return RESTFul接口返回对象格式
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result getMethods(@RequestParam(required = false) String edmmType,
						@RequestParam(required = false) String edmmProgramType,@RequestParam(required = false) String edmmName,
						@RequestParam(required = false) String edmmClasses,@RequestParam(required = false)String edmmStatus,
						@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		Pagination<EdmMethodVO> edmMethod = edmMethodService.selectByExample(edmmType, edmmProgramType, edmmName,
				edmmClasses,edmmStatus, pageNum, pageSize);
		result.setData(edmMethod);
		return result;
	}

	/**
	 * 根据类id与方法名校验方法名唯一性
	 * @param edmmName
	 * @return
	 */
	@RequestMapping(value = "/edmmName", method = RequestMethod.GET)
	public Result checkData(@RequestParam String edmcId,@RequestParam String edmmName) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据异常校验
		String errorStr = edmMethodService.checkUnique(edmcId,edmmName);
		if (!StringUtils.isEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 根据类名与方法名校验方法名唯一性（bizDriver使用）
	 * @param edmmName
	 * @return
	 */
	@RequestMapping(value = "/checkEdmmName", method = RequestMethod.GET)
	public Result checkEdmmName(@RequestParam String edmcName,@RequestParam String edmmName) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据异常校验
		String errorStr = edmMethodService.checkEdmmName(edmcName,edmmName);
		if (!StringUtils.isEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 新增方法
	 * @param edmMethodAndArgVO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmMethodAndArgVO edmMethodAndArgVO) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		//新增时判断类id是否为空，bizdriver注册方法时通过传递类名进行注册
		EdmMethodVO edmMethodVO = edmMethodAndArgVO.getEdmMethod_in();
		String edmcId =edmMethodVO.getEdmmEdmcId();
		if(StringUtil.isNullOrEmpty(edmcId)){
			edmcId = edmMethodService.getEdmcIdByEdmcName(edmMethodVO.getEdmcName(),version);
			if (!StringUtil.isNullOrEmpty(edmcId)){
				edmMethodAndArgVO.getEdmMethod_in().setEdmmEdmcId(edmcId);
			}
		}
		// 数据异常校验
		String errorStr = edmMethodService.checkUnique(edmcId,edmMethodAndArgVO.getEdmMethod_in().getEdmmName());
		// 校验失败
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		edmMethodService.insertEdmMethodAndArgVO(edmMethodAndArgVO);
		return result;
	}

	/***
	 * 更新方法
	 * @param edmMethodAndArgVO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmMethodAndArgVO edmMethodAndArgVO) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmMethodVO edmMethodVO = edmMethodAndArgVO.getEdmMethod_in();
		String oldName = edmMethodService.geMethodNameById(edmMethodVO.getId());// 更新之前的方法名
		if(!StringUtil.isNullOrEmpty(edmMethodVO.getEdmmName()) && !edmMethodVO.getEdmmName().equals(oldName)){
			String errorStr = edmMethodService.checkUnique(edmMethodVO.getEdmmEdmcId(),edmMethodVO.getEdmmName());
			if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
				result.setRetCode(Result.RECODE_VALIDATE_ERROR);
				result.setErrMsg(errorStr);
				return result;
			}
		}
		edmMethodService.updateMethodAndArg(edmMethodAndArgVO);
		return result;
	}

	/***
	 * 更新方法基本信息
	 * @param edmMethod
	 * @return
	 */
	@RequestMapping(value = "/baseInfo", method = RequestMethod.PUT)
	public Result updateBaseInfo(@RequestBody EdmMethod edmMethod) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.updateByPrimaryKey(edmMethod);
		return result;
	}
	/**
	 * 根据id删除方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.deleteByPrimaryKey(id);
		return result;
	}

	/**
	 * 批量删除方法
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
	public Result deleteMethods(@PathVariable("ids") String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.deleteMethods(ids);
		return result;
	}

	/**
	 * 方法的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.move(ids);
		return result;
	}

	/**
	 * 查询方法分类结构树。
	 * @return
	 */
	@RequestMapping(value = "/methodTypeTree", method = RequestMethod.GET)
	public Result queryClassMethodTypeTree() {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmMethodTypeVO> list = edmMethodService.queryClassMethodTypeTree();
		result.setData(list);
		return result;
	}

	/**
	 * 方法与类的关系查询
	 * @param type
	 * @param name
	 * @param version
	 * @param className
	 * @return
	 */
	@RequestMapping(value = "/classes", method = RequestMethod.GET)
	public Result queryMethodsAndClasses( String type, String name, String version, String className) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmMethodVO> list = edmMethodService.queryMethodsAndClasses(type, name, version, className);
		result.setData(list);
		return result;
	}

	/**
	 * 根据类id查方法id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/classes/{id}", method = RequestMethod.GET)
	public Result queryMethodIdsByClassId(@PathVariable String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String[] methodIds = edmMethodService.selectMethodIdsByClassId(id);
		result.setData(methodIds);
		return result;
	}

	/**
	 * 上传方法体
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadMethodBody", method = RequestMethod.POST)
	public Result uploadMethodBody(@RequestParam("file") MultipartFile file) throws Exception{
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmMethod edmMethod =new EdmMethod();
		InputStream inputStream = file.getInputStream();
		String groupName = Constant.FILE_GROUP_NAME;
		long fileSize = inputStream.available();
		String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
		StorePath path = defaultGenerateStorageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
		System.out.println("path.getFullPath()="+path.getFullPath());
		System.out.println("file.getOriginalFilename()="+file.getOriginalFilename());
		edmMethod.setEdmmProgramSourceName(file.getOriginalFilename());
		edmMethod.setEdmmProgramStorage(path.getFullPath());
		result.setData(edmMethod);
		return result;
	}

	/**
	 * 根据方法名获取方法体
	 * @param edmmName
	 * @return
	 */
	@RequestMapping(value = "/getMethodBody", method = RequestMethod.GET)
	public Result getMethodBody(@RequestParam String edmmName) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		StringBuffer methodBody = edmMethodService.getMethodBody(edmmName);
		result.setData(methodBody);
		return result;
	}

	/**
	 * 通过方法id禁用方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateEdmmStatus",method = RequestMethod.PUT)
	public Result stopMethodById(@RequestParam(value = "id") String id,
								 @RequestParam(value = "edmmStatus") String edmmStatus){
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.updateEdmmStatus(id,edmmStatus);
		return result;
	}

	/**
	 * 根据方法id与类id获取方法名与类名
	 * @param id
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/{id}/{classId}", method = RequestMethod.GET)
	public Result getMethodByIdClassId(@PathVariable(value = "id") String id,
								@PathVariable(value = "classId") String classId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmMethodService.getMethodByIdClassId(id,classId));
		return result;
	}

	/**
	 * 根据方法id 保存触发条件id
	 * @param id
	 * @param condId
	 * @return
	 */
	@RequestMapping(value = "/{id}/{condId}",method = RequestMethod.PUT)
	public Result updateTriggerCond(@PathVariable(value = "id") String id,
							 @PathVariable(value = "condId") String condId){
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.updateTriggerCond(id,condId);
		return result;
	}

	/**
	 *根据方法id 删除触发条件id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/cond",method = RequestMethod.DELETE)
	public Result deleteTriggerCond(@PathVariable(value = "id") String id){
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodService.deleteTriggerCond(id);
		return result;
	}

	/**
	 * 根据类名与方法名获取方法（bizdriver用）
	 * @param edmcName
	 * @param edmmName
	 * @return
	 */
	@RequestMapping(value = "/edmMethod/{edmcName}/{edmmName}")
	public Result getEdmMethodByEdmmName(@PathVariable(value = "edmcName")String edmcName,@PathVariable(value = "edmmName")String edmmName) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmMethodService.getEdmMethodByEdmcNameAndEdmmName(edmcName,edmmName));
		return result;
	}
}
