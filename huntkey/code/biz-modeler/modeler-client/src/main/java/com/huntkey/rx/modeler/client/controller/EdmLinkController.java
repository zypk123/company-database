package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmLinkService;
import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/19.
 */

@RestController
@RequestMapping("/v1/links")
//@Api(value = "关联属性维护服务", description = "提供关联属性增加、修改、删除和查询服务")
public class EdmLinkController {
    private static Logger log = LoggerFactory.getLogger(EdmLinkController.class);
    @Autowired
    private EdmLinkService edmLinkService;

    /**
     * 根据id获取关联属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getLinkById(@PathVariable(value="id") String id) {
        Result result = edmLinkService.getLinkById(id);
        return result;
    }

    /**
     * 被关联属性验证
     * @param edmpId
     * @param linkedId
     * @return
     */
    @RequestMapping(value = "/edmlEdmpLinkId",method = RequestMethod.GET)
    public Result checkLinedId(@RequestParam String edmpId, @RequestParam String linkedId){
        Result result =  edmLinkService.checkLinedId(edmpId,linkedId);
        return result;
    }

    /**
     * 插入关联属性
     * @param edmLinkVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmLinkVO edmLinkVO){
        Result result =  edmLinkService.add(edmLinkVO);
        return result;
    }

    /**
     * 修改关联属性
     * @param edmLinkVO
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmLinkVO edmLinkVO){
        Result result = edmLinkService.update(edmLinkVO);
        return result;
    }

    /**
     * 删除关联属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = edmLinkService.delete(id);
        return result;
    }

    /**
     * 批量删除关联属性
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable String[] ids) {
        Result result =  edmLinkService.deleteIds(ids);
        return result;
    }


    /**
     * 关联属性条件查询,带分页(废弃)
     * @param edmlEdmpId
     * @param edmlEdmpLinkId
     * @param edmlCond
     * @param edmlFormula
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result query(@RequestParam(required = false,value = "edmlEdmpId") String edmlEdmpId,
                        @RequestParam(required = false,value ="edmlEdmpLinkId")String edmlEdmpLinkId,
                        @RequestParam(required = false,value="edmlCond")String edmlCond,
                        @RequestParam(required = false,value="edmlFormula")String edmlFormula,
                        @RequestParam(required = false,defaultValue="1")int pageNum,@RequestParam(required = false,defaultValue = "10") int pageSize){
        Result result = edmLinkService.select(edmlEdmpId,edmlEdmpLinkId,edmlCond,edmlFormula,pageNum,pageSize);//接受服务抛出的result
        return result;
    }


    @RequestMapping(value = "/formula/{id}", method = RequestMethod.PUT)
    public Result clearEdmlFormula(@PathVariable(value = "id") String id) {
        Result result = edmLinkService.clearEdmlFormula(id);
        return result;
    }

    @RequestMapping(value = "/edmLinks", method = RequestMethod.GET)
    Result getEdmLinks(@RequestParam(value = "edmpId") String edmpId,
                       @RequestParam(value = "edmlCond") String edmlCond) {
        Result result = edmLinkService.getEdmLinks(edmpId, edmlCond);
        return result;
    }

    @RequestMapping(value = "/{id}/edmLink", method = RequestMethod.PUT)
    Result moveEdmLink(@PathVariable(value = "id") String id,
                       @RequestBody EdmCond edmCond) {
        Result result = edmLinkService.moveEdmLink(id, edmCond);
        return result;
    }
}
