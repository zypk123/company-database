package com.huntkey.rx.sceo.formula.provider.record.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 源系统操作记录映射Controller
 *
 * @author nidongx
 * @create 2017-07-21 16:50
 **/
@RestController
@RequestMapping("/recordMapping")
public class RecordMappingController {

    private static Logger log = LoggerFactory.getLogger(RecordMappingController.class);

    @Autowired
    RecordMappingService recordMappingService;

    /**
     * 新增源系统记录映射
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    @RequestMapping(value = "/addRecordMapping", method = RequestMethod.POST)
    public Result addRecordMapping(@RequestBody SourceSystemRecordMapping sourceSystemRecordMapping) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call sourceSystemRecordMapping addRecordMapping service");
            recordMappingService.addSourceSystemRecordMapping(sourceSystemRecordMapping);
            // 返回主键ID
            result.setData(sourceSystemRecordMapping.getRecdId());
        } catch (Exception e) {
            log.error("新增源系统记录映射发生错误", e);
            throw new RuntimeException("新增源系统记录映射发生错误", e);
        }
        return result;
    }

    /**
     * 修改源系统记录映射(将状态更新为可用)
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    @RequestMapping(value = "/updateRecordMapping", method = RequestMethod.PUT)
    public Result updateRecordMapping(@RequestBody SourceSystemRecordMapping sourceSystemRecordMapping) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call sourceSystemRecordMapping updateRecordMapping service");
            recordMappingService.updateSourceSystemRecordMapping(sourceSystemRecordMapping);
            // 返回主键ID
            result.setData(sourceSystemRecordMapping.getRecdId());
        } catch (Exception e) {
            log.error("修改源系统记录映射(将状态更新为可用)发生错误", e);
            throw new RuntimeException("修改源系统记录映射(将状态更新为可用)发生错误", e);
        }
        return result;
    }

    /**
     * 根据源关联编码和源关联类型查询公式描述
     *
     * @param sourceMappingId,sourceMappingType (1:属性公式、2:属性限值、3:关联条件)
     * @return
     */
    @RequestMapping(value = "/queryFormularDec", method = RequestMethod.GET)
    public Result queryFormularDec(@RequestParam(required = false, value = "sourceMappingId", defaultValue = "") String sourceMappingId,
                                   @RequestParam(required = false, value = "sourceMappingType", defaultValue = "") String sourceMappingType
    ) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call sourceSystemRecordMapping queryFormularDec service");
            String dec = recordMappingService.queryFormularDec(sourceMappingId, sourceMappingType);
            result.setData(dec);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call provider RecordMappingController queryFormularDec error!");
            throw new RuntimeException("系统异常!", e);
        }
        return result;
    }

    /**
     * 根据源关联编码和源关联类型查询映射信息
     *
     * @param sourceMappingId,sourceMappingType (1:属性公式、2:属性限值、3:关联条件)
     * @return
     */
    @RequestMapping(value = "/queryRecord", method = RequestMethod.GET)
    public Result queryRecord(@RequestParam(required = false, value = "sourceMappingId", defaultValue = "") String sourceMappingId,
                              @RequestParam(required = false, value = "sourceMappingType", defaultValue = "") String sourceMappingType
    ) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call sourceSystemRecordMapping queryRecord service");
            SourceSystemRecordMapping sourceSystemRecordMapping = recordMappingService.queryRecord(sourceMappingId, sourceMappingType, null);
            result.setData(sourceSystemRecordMapping);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call provider RecordMappingController queryRecord error!");
            throw new RuntimeException("系统异常!", e);
        }
        return result;
    }

}
