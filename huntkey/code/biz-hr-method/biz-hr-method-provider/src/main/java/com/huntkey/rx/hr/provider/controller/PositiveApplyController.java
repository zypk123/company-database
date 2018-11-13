package com.huntkey.rx.hr.provider.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.model.ApplyPositiveDTO;
import com.huntkey.rx.hr.common.model.EdmApplyPositiveConstants;
import com.huntkey.rx.hr.common.model.EdmAttachment;
import com.huntkey.rx.hr.provider.service.EdmAttachmentService;
import com.huntkey.rx.hr.provider.service.PositiveApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import java.io.InputStream;


/**
 * @author yaoss
 */
@RestController
@Validated
@RequestMapping(value = "/hr/positive")
public class PositiveApplyController {

    @Autowired
    public EdmAttachmentService edmAttachmentService;

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Autowired
    PositiveApplyService positiveApplyService;

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"dept", "startTime", "endTime", "no","ordeStatus","oepaAuditIdea", "pageNum", "pageSize"},
            methodDesc = "查询转正申请单列表")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result queryPositive(@RequestParam(required = false, value = "dept") String dept,
                                @RequestParam(required = false, value = "startTime") String startTime,
                                @RequestParam(required = false, value = "endTime") String endTime,
                                @RequestParam(required = false, value = "no") String no,
                                @RequestParam(required = false, value = "ordeStatus") String ordeStatus,
                                @RequestParam(required = false, value = "oepaAuditIdea") String oepaAuditIdea,
                                @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int pageNum,
                                @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int pageSize) {

        return positiveApplyService.queryPositive(dept, startTime, endTime, no, ordeStatus,oepaAuditIdea,pageNum, pageSize);
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "转正申请单加载详情方法")
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public Result loadPositive(@RequestParam @NotBlank(message = "id") String id) {
        return positiveApplyService.loadPositiveByOrdeId(id);
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "转正申请单加载详情方法")
    @RequestMapping(value = "/loadOrdeByEmpId", method = RequestMethod.GET)
    public Result loadOrdeByEmpId(@RequestParam @NotBlank(message = "id") String id) {
        return positiveApplyService.loadEmpData(id);
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"applyPositiveDTO"},
            methodDesc = "转正申请单保存方法")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addPositive(@RequestBody JSONObject applyPositiveDTO) throws Exception {
        return positiveApplyService.savePositive(applyPositiveDTO);
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "转正申请单批准通过方法")
    @RequestMapping(value = "/empPositive", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) throws Exception{
        return positiveApplyService.empPositive(orderInstanceId,handlerType);
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "转正申请单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet)throws Exception {
        return positiveApplyService.auditPostivieOrder(auditSet);
    }

    /**
     * 附件上传
     *
     * @param file
     * @return
     */
    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"file"},
            methodDesc = "转正申请单批准通过方法")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result uploadPositive(@RequestParam("file") MultipartFile file) throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmAttachment edmAttachment = new EdmAttachment();
        InputStream inputStream = file.getInputStream();
        String groupName = "group1";
        long fileSize = inputStream.available();
        String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
        StorePath path = defaultGenerateStorageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
        System.out.println("path.getFullPath()=" + path.getFullPath());
        System.out.println("file.getOriginalFilename()=" + file.getOriginalFilename());
        edmAttachment.setEdmaSourceName(file.getOriginalFilename());
        edmAttachment.setEdmaPath(path.getFullPath());
        result.setData(edmAttachment);
        return result;
    }

    /**
     * 附件下载
     *
     * @param id
     * @param response
     * @return
     */
    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id", "response"},
            methodDesc = "转正申请单批准通过方法")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam("id") String id, HttpServletResponse response) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        ResponseEntity<byte[]> responseEntity = edmAttachmentService.fastDFSDownload(id, response);
        return responseEntity;
    }

    @MethodRegister(
            edmClass = EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"applyPositiveDTO"},
            methodDesc = "转正申请单提交方法（含校验）")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submitPositive(@RequestBody JSONObject applyPositiveDTO) throws Exception{
        return positiveApplyService.submitAddOrder(applyPositiveDTO);
    }

}
