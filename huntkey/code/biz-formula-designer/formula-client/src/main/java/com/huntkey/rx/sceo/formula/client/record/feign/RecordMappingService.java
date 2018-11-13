package com.huntkey.rx.sceo.formula.client.record.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.record.feign.hystrix.RecordMappingHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 源系统操作记录映射Service
 *
 * @author nidongx
 * @create 2017-07-21 16:52
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = RecordMappingHystrixImpl.class)
public interface RecordMappingService {
    
    /**
     * 根据源关联编码和源关联类型查询公式描述
     * @param sourceMappingId
     * @param sourceMappingType
     * @return
     */
    @RequestMapping(value = "/recordMapping/queryFormularDec", method = RequestMethod.GET)
    Result queryFormularDec(@RequestParam(required = false, value = "sourceMappingId", defaultValue = "") String sourceMappingId,
                            @RequestParam(required = false, value = "sourceMappingType", defaultValue = "") String sourceMappingType
    );

    /**
     * 新增源系统记录映射
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    @RequestMapping(value = "/recordMapping/addRecordMapping", method = RequestMethod.POST)
    Result addRecordMapping(@RequestBody SourceSystemRecordMapping sourceSystemRecordMapping);


    /**
     * 修改源系统记录映射(将状态更新为可用)
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    @RequestMapping(value = "/recordMapping/updateRecordMapping", method = RequestMethod.PUT)
    Result updateRecordMapping(@RequestBody SourceSystemRecordMapping sourceSystemRecordMapping);

    /**
     * 根据源关联编码和源关联类型查询映射信息
     * @param sourceMappingId
     * @param sourceMappingType
     * @return
     */
    @RequestMapping(value = "/recordMapping/queryRecord", method = RequestMethod.GET)
    Result queryRecord(@RequestParam(required = false, value = "sourceMappingId", defaultValue = "") String sourceMappingId,
                       @RequestParam(required = false, value = "sourceMappingType", defaultValue = "") String sourceMappingType
    );
}
