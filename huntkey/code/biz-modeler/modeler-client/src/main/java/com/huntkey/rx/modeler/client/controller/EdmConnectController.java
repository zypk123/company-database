package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmConnectService;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/17.
 */
@RestController
@RequestMapping("/v1/connects")
public class EdmConnectController {
    private static Logger log = LoggerFactory.getLogger(EdmConnectController.class);

    @Autowired
    private EdmConnectService edmConnectService;

    /**
     * 新增联动属性
     * @param edmConnect
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmConnect edmConnect){
        Result result = edmConnectService.add(edmConnect);
        return result;
    }

    /**
     * 根据id删除属性（废弃）
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        Result result = edmConnectService.delete(id);
        return result;
    }

    /**
     * 批量删除联动属性（废弃）
     * @param ids
     * @return
     */
    @RequestMapping(value="/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable String[] ids){
        Result result = edmConnectService.deleteIds(ids);
        return result;
    }

    /**
     * 修改联动数据
     * @param edmConnect
     * @return
     */
    @RequestMapping( method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConnect edmConnect){
        Result result = edmConnectService.update(edmConnect);
        return result;
    }

    /**
     * 根据id查找联动
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getbyid(@PathVariable(value="id") String id) {
        Result result = edmConnectService.getConnectById(id);
        return result;
    }

    /**
     * 联动条件查询,带分页（废弃）
     * @param edcnEdmpId
     * @param edcnLinkPreservable
     * @param edcnUpdateType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public Result query(@RequestParam(required = false,value = "edcnEdmpId")String edcnEdmpId,
                        @RequestParam(required = false,value = "edcnLinkPreservable")Byte edcnLinkPreservable,
                        @RequestParam(required = false,value = "edcnUpdateType")Byte edcnUpdateType,
                        @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,@RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize){
        Result result =  edmConnectService.select(edcnEdmpId,edcnLinkPreservable,edcnUpdateType,pageNum,pageSize);
        return result;
    }

    /**
     * 查询类下所有的联动属性
     * @param classId
     * @return
     */
    @RequestMapping(value = "/{classId}/connects", method=RequestMethod.GET)
    public Result queryAll(@PathVariable(value = "classId")String classId) {
        Result result = edmConnectService.getConnectsOfClass(classId);
        return result;
    }
}
