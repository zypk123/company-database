package com.huntkey.rx.sceo.formula.client.function.definefunc.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.config.FeignMultipartSupportConfig;
import com.huntkey.rx.sceo.formula.client.function.definefunc.feign.hystrix.DefineFunctionHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nidongx on 2017/7/26.
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", configuration = FeignMultipartSupportConfig.class, fallback = DefineFunctionHystrixImpl.class)
public interface DefineFunctionService {

    /**
     * 查询自定义函数列表
     *
     * @param fundFunCatagoryId
     * @param fundFunName
     * @param fundState
     * @param sortName
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return boolean
     */
    @RequestMapping(value = "/defineFunction/queryDefineFunction", method = RequestMethod.GET)
    Result queryDefineFunction(@RequestParam(value = "fundFunCatagoryId", required = false) String fundFunCatagoryId,
                                      @RequestParam(value = "fundFunName", required = false) String fundFunName,
                                      @RequestParam(value = "fundState", required = false) String fundState,
                                      @RequestParam(required = false, value = "sortName") String sortName,
                                      @RequestParam(required = false, value = "sortOrder") String sortOrder,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    /**
     * createCustomizeFunction
     * @param file
     * @param fundFunName
     * @param fundState
     * @param fundFunDesc
     * @param fundModifyRemarks
     * @param fundFunClassifyId
     * @param fundFunClassifyCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/defineFunction/createCustomizeFunction",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            method = RequestMethod.POST)
    Result createCustomizeFunction(@RequestPart("file") MultipartFile file,
                                   @RequestParam("fundFunName") String fundFunName,
                                   @RequestParam("fundState") String fundState,
                                   @RequestParam("fundFunDesc") String fundFunDesc,
                                   @RequestParam("fundModifyRemarks") String fundModifyRemarks,
                                   @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                   @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception;

    /**
     * 根据ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/defineFunction/getCompileFunByCompileId/{funcId}", method = RequestMethod.GET)
    Result getCompileFunByCompileId(@PathVariable("funcId") String funcId);

    /**
     * 根据ID查找自定义函数定义数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/defineFunction/getDefineFunByDefineId/{funcId}", method = RequestMethod.GET)
    Result getDefineFunByDefineId(@PathVariable("funcId") String funcId);

    /**
     * updateCustomizeFunction
     * @param file
     * @param fundId
     * @param fundFunName
     * @param fundState
     * @param fundFunDesc
     * @param fundFunClassifyId
     * @param fundFunClassifyCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/defineFunction/updateCustomizeFunction",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            method = RequestMethod.POST)
    Result updateCustomizeFunction(@RequestPart("file") MultipartFile file,
                                   @RequestParam("fundId") String fundId,
                                   @RequestParam("fundFunName") String fundFunName,
                                   @RequestParam("fundState") String fundState,
                                   @RequestParam("fundFunDesc") String fundFunDesc,
                                   @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                   @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception;

    /**
     * 删除自定义函数
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/defineFunction/deleteCustomizeFunction/{funcId}", method = RequestMethod.DELETE)
    Result deleteCustomizeFunction(@PathVariable("funcId") String funcId);

    /**
     * 根据函数分类ID查找自定义函数
     *
     * @param classifyId
     * @return
     */
    @RequestMapping(value = "/defineFunction/getFunctionDefinitionByClassifyId/{classifyId}", method = RequestMethod.GET)
    Result getFunctionDefinitionByClassifyId(@PathVariable("classifyId") String classifyId);

    /**
     * getFunctionDescriberById
     * @param funcId
     * @return
     */
    @RequestMapping(value = "/defineFunction/getFunctionDescriberById/{funcId}", method = RequestMethod.GET)
    Result getFunctionDescriberById(@PathVariable("funcId") String funcId);
}