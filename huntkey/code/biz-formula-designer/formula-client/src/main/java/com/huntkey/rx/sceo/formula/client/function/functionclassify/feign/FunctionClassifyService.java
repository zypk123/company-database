package com.huntkey.rx.sceo.formula.client.function.functionclassify.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.function.functionclassify.feign.hystrix.FunctionClassifyHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 函数分类feign
 *
 * @author zhangyu
 * @create 2017-07-27 11:50
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = FunctionClassifyHystrixImpl.class)
public interface FunctionClassifyService {

    /**
     * 新建函数分类
     *
     * @param ftmFunctionClassify
     * @return
     */
    @RequestMapping(value = "/functionClassify/createFunctionClassify", method = RequestMethod.POST)
    Result createFunctionClassify(@RequestBody FtmFunctionClassify ftmFunctionClassify);

    /**
     * 删除函数分类
     *
     * @param fnccId
     * @return
     */
    @RequestMapping(value = "/functionClassify/deleteFunctionClassify/{fnccId}", method = RequestMethod.DELETE)
    Result deleteFunctionClassify(@PathVariable("fnccId") String fnccId);

    /**
     * 修改函数分类
     *
     * @param ftmFunctionClassify
     * @return
     */
    @RequestMapping(value = "/functionClassify/updateFunctionClassify", method = RequestMethod.PUT)
    Result updateFunctionClassify(@RequestBody FtmFunctionClassify ftmFunctionClassify);

    /**
     * 获取函数分类列表
     *
     * @return
     */
    @RequestMapping(value = "/functionClassify/getFtmFunctionClassifyList", method = RequestMethod.GET)
    Result getFtmFunctionClassifyList();

    /**
     * 根据ID获取函数分类
     *
     * @param fnccId
     * @return
     */
    @RequestMapping(value = "/functionClassify/selectByFnccId/{fnccId}", method = RequestMethod.GET)
    Result selectByFnccId(@PathVariable("fnccId") String fnccId);


    /**
     * 获取有效状态的函数分类的名称
     *
     * @return
     */
    @RequestMapping(value = "/functionClassify/getClassifyNameList", method = RequestMethod.GET)
    Result getClassifyNameList();

}
