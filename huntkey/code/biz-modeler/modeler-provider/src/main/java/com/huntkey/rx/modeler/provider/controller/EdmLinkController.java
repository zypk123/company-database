package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.EdmLink;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import com.huntkey.rx.modeler.provider.service.EdmLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by linziy on 2017/4/18.
 */
@RestController
@RequestMapping("/links")
public class EdmLinkController {
    private static Logger log = LoggerFactory.getLogger(EdmLinkController.class);

    @Autowired
    private EdmLinkService edmLinkService;

    /*
     * 获取关联属性
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getLinkById(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmLinkVO edmLinkVO = edmLinkService.getById(id);
        result.setData(edmLinkVO);
        return result;
    }

    /*
    * 关联属性验证
    * */
    @RequestMapping(value = "/edmlEdmpLinkId",method = RequestMethod.GET)
    public Result checkLinedId(@RequestParam String edmpId, @RequestParam String linkedId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmLinkService.checkLinkId(edmpId,linkedId);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }
    /*
     * 插入关联属性
     * */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmLinkVO edmLinkVO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmLinkService.checkLinkId(edmLinkVO.getEdmlEdmpId(),edmLinkVO.getEdmlEdmpLinkId());;
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        edmLinkService.insertVO(edmLinkVO);
        result.setData(edmLinkVO.getId());
        return result;
    }
    /*
     * 删除关联属性
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmLinkService.delete(id);
        return result;
    }

    /*
     * 批量删除关联属性
     *
     * */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable String[] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmLinkService.deleteIds(ids);
        return result;
    }

    /*
      * 更新关联属性
      * */
    @RequestMapping( method = RequestMethod.PUT)
    public Result update(@RequestBody EdmLinkVO edmLinkVO){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String oldLinkId = edmLinkService.selectLinkidById(edmLinkVO.getId());
        if(!StringUtil.isNullOrEmpty(oldLinkId) && !oldLinkId.equals(edmLinkVO.getEdmlEdmpLinkId())){
            String errorStr = edmLinkService.checkLinkId(edmLinkVO.getEdmlEdmpId(),edmLinkVO.getEdmlEdmpLinkId());;
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
            edmLinkService.updateVO(edmLinkVO,oldLinkId,true);//true 关联属性改变了，需要调用调度创建执行计划
        }else {
            edmLinkService.updateVO(edmLinkVO,oldLinkId,false);
        }
        return result;
    }

    /*
    * 关联属性条件查询,带分页(废弃)
    * 属性
    * @param edmlEdmpId (edml_edmp_id) //属性id
    * @param edmlEdmpLinkId(edmlEdmpLinkId)//关联属性ID
    * @param edmlCond(edmlCond)//关联定位条件
    * @param edmlFormula(edmlFormula)//关联计算公式
    * @oaram pageNum
    * @param pageSize
    * @return RESTFul 接口返回对象格式
    * */
    @RequestMapping(method= RequestMethod.GET)
    public Result query(@RequestParam(required = false,value = "edmlEdmpId")String edmlEdmpId,
                        @RequestParam(required = false,value = "edmlEdmpLinkId")String edmlEdmpLinkId,
                        @RequestParam(required = false,value = "edmlCond")String edmlCond,
                        @RequestParam(required = false,value = "edmlFormula")String edmlFormula,
                        @RequestParam(required = false,value = "edcnLinkPreservable")Byte edcnLinkPreservable,
                        @RequestParam(required = false,value = "edclUpdateType")Byte edclUpdateType,
                        @RequestParam(required = false,value = "edclUpdateTimeliness")String edclUpdateTimeliness,
                        @RequestParam(required = false,value = "edclUpdateTime")String edclUpdateTime,
                        @RequestParam(required = false,defaultValue="1")int pageNum,@RequestParam(required =false,defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Pagination<EdmLink> edmLinkList = edmLinkService.selectByExample(edmlEdmpId, edmlEdmpLinkId, edmlCond,edmlFormula,
                edcnLinkPreservable,edclUpdateType,edclUpdateTimeliness,edclUpdateTime,
                pageNum, pageSize);
        result.setData(edmLinkList);
        return result;
    }

    /**
     * 根据关联公式id置空edml_formula字段
     * @param id
     * @return
     */
    @RequestMapping(value = "/formula/{id}", method = RequestMethod.PUT)
    public Result clearEdmlFormula(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmLinkService.clearEdmlFormula(id);
        return result;
    }

    @RequestMapping(value = "/edmLinks", method = RequestMethod.GET)
    public Result getEdmLinks(@RequestParam(value = "edmpId") String edmpId,
                              @RequestParam(value = "edmlCond") String edmlCond) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmLinkService.getEdmLinks(edmpId, edmlCond));
        return result;
    }

    @RequestMapping(value = "/{id}/edmLink", method = RequestMethod.PUT)
    public Result moveEdmLink(@PathVariable(value = "id") String id,
                            @RequestBody EdmCond edmCond) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmLinkService.moveEdmLink(id.split(","), edmCond);
        return result;
    }

    /**
     * 根据edml_link_id获取edml_edmp_id
     * @param edmlLinkId
     * @return
     */
    @RequestMapping(value = "/{edmlLinkId}/edmpId", method = RequestMethod.GET)
    public Result getEdmlEdmpId(@PathVariable(value = "edmlLinkId") String edmlLinkId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmLinkService.getEdmlEdmpId(edmlLinkId));
        return result;
    }
}
