package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmMethodTypeHystrix;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gujing on 2017/4/20 0020.
 */
@FeignClient(value = "modeler-provider", fallback = EdmMethodTypeHystrix.class)
public interface EdmMethodTypeService {

    /**
     * 获取所有方法分类
     * @param
     * @return
     */
    @RequestMapping(value = "/methodtypes",method = RequestMethod.GET)
    public Result queryAll();

    /**
     * 根据类id获取方法分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/methodtypes/{id}", method = RequestMethod.GET)
    public Result query(@PathVariable("id") String id);

    /**
     * 新增方法分类
     *
     * @param edmMethodType
     * @return
     */
    @RequestMapping(value = "/methodtypes",method = RequestMethod.POST)
    public Result add(@RequestBody EdmMethodType edmMethodType);

    /**
     * 修改方法分类
     *
     * @param edmMethodType
     * @return
     */
    @RequestMapping(value = "/methodtypes",method = RequestMethod.PUT)
    public Result update(@RequestBody EdmMethodType edmMethodType);

    /**
     * 根据id删除方法分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/methodtypes/{id}",method = RequestMethod.DELETE)
    public Result  delete(@PathVariable("id") String id);


    /**
     *方法树节点的本级下的方法移动到指定节点
     * @param sourceMethodtypeId
     * @param aimMethodtypeId
     * @return
     */
    @RequestMapping(value = "/methodtypes/move/{sourceMethodtypeId}/{aimMethodtypeId}", method = RequestMethod.PUT)
    public Result moveMethodtypeMethodToMethodtype(@PathVariable("sourceMethodtypeId") String sourceMethodtypeId,@PathVariable("aimMethodtypeId") String aimMethodtypeId);

    /**
     * 方法分类检验方法分类编码唯一性
     * @param edmtCode
     * @return
     */
    @RequestMapping(value = "/methodtypes/edmtCode", method = RequestMethod.GET)
    public Result checkCodeUnique(@RequestParam("edmtCode") String edmtCode);


    /**
     * 方法分类检验方法分类名称唯一性
     * @param edmtName
     * @return
     */
    @RequestMapping(value = "/methodtypes/edmtName", method = RequestMethod.GET)
    public Result checkNameUnique(@RequestParam("edmtName") String edmtName);

    @RequestMapping(value = "/methodtypes/edmtParentId", method = RequestMethod.GET)
    Result checkFatherNode(@RequestParam("edmtParentId") String edmtParentId,@RequestParam("id") String id);
}
