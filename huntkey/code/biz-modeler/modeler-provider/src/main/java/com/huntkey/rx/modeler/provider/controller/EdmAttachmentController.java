package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmAttachment;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.service.EdmAttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by licj on 2017/4/13.
 */
@RestController
@RequestMapping("/attachments")
public class EdmAttachmentController {

	private static Logger log = LoggerFactory.getLogger(EdmAttachmentController.class);

	@Autowired
	public EdmAttachmentService edmAttachmentService;

	@Autowired
	private DefaultGenerateStorageClient defaultGenerateStorageClient;

	/**
	 * 在进行新增附件之前的验证附件名
	 * @param edmaName
	 * @param edmaEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmaName", method = RequestMethod.GET)
	public Result checkEdmaName(@RequestParam String edmaName,@RequestParam String edmaEdmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String errorStr = edmAttachmentService.checkUnique(edmaName, edmaEdmcId);
		if (!StringUtils.isEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 新增附件
	 * @param edmAttachment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmAttachment edmAttachment) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据唯一性校验
		String errorStr = edmAttachmentService.checkUnique(edmAttachment.getEdmaName(),edmAttachment.getEdmaEdmcId());
		if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		edmAttachmentService.insert(edmAttachment);
		return result;
	}

	/**
	 * 修改附件
	 * @param edmAttachment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmAttachment edmAttachment) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String oldName = edmAttachmentService.getAttachmentNameById(edmAttachment.getId());// 更新之前的附件名
		if (!StringUtil.isNullOrEmpty(edmAttachment.getEdmaName()) && !oldName.equals(edmAttachment.getEdmaName())){
			String errorStr = edmAttachmentService.checkUnique(edmAttachment.getEdmaName(),edmAttachment.getEdmaEdmcId());
			if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
				result.setRetCode(Result.RECODE_VALIDATE_ERROR);
				result.setErrMsg(errorStr);
				return result;
			}
		}
		edmAttachmentService.update(edmAttachment);
		return result;
	}


	/**
	 * 根据id删除附件批量删除（逻辑删除）
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("ids") String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmAttachmentService.deleteBatch(ids);
		return result;
	}

	/**
	 * 附件的上移、下移
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmAttachmentService.move(ids);
		return result;
	}

	/**
	 * 附件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result upload(@RequestParam("file") MultipartFile file) throws Exception{
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmAttachment edmAttachment =new EdmAttachment();
		InputStream inputStream = file.getInputStream();
		String groupName = Constant.FILE_GROUP_NAME;
		long fileSize = inputStream.available();
		String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
		StorePath path = defaultGenerateStorageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
		System.out.println("path.getFullPath()="+path.getFullPath());
		System.out.println("file.getOriginalFilename()="+file.getOriginalFilename());
		edmAttachment.setEdmaSourceName(file.getOriginalFilename());
		edmAttachment.setEdmaPath(path.getFullPath());
		result.setData(edmAttachment);
		return result;
	}

	/**
	 * 附件下载
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public  ResponseEntity<byte[]> download(@RequestParam("id") String id,HttpServletResponse response) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		ResponseEntity<byte[]> responseEntity = edmAttachmentService.fastDFSDownload(id,response);
		return responseEntity;
	}
}
