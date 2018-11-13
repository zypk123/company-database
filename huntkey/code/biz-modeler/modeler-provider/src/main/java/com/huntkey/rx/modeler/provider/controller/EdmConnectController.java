package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.provider.service.EdmConnectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by linziy on 2017/4/17.
 */
@RestController
@RequestMapping("/connects")
public class EdmConnectController {
    private static Logger log = LoggerFactory.getLogger(EdmConnectController.class);
    @Autowired
    private EdmConnectService edmConnectService;

    /**
     * 新增联动
     * @param edmConnect
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    public Result add(@RequestBody EdmConnect edmConnect) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConnectService.insert(edmConnect);
        result.setData(edmConnect.getId());
        return result;
    }

    /**
     * 根据id删除联动
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConnectService.delete(id);
        return result;
    }

    /**
     * 批量删除联动属性（废弃）
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable String[] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConnectService.deleteIds(ids);
        return result;
    }

    /**
     *  修改联动数据
     * @param edmConnect
     * @return
     */

    @RequestMapping( method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConnect edmConnect){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConnectService.update(edmConnect);
        return result;
    }

    /**
     * 根据id查找联动
     * @param id
     * @return RResult
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getConnectById(@PathVariable(value = "id")  String id){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmConnectService.getConnectByEdmpId(id));
        return result;
    }

    /*
    * 联动条件查询,带分页（废弃）
    * @param edcnEdmpId(edcn_edmp_id)  //属性id
    * @param edcnLinkPreservable(edcn_link_preservable) //是否保持联动 0、1
    * @param edcnUpdateType(edcn_update_type) //更新类型 1、2、3
    * @param edcnUpdateType(is_del) //是否删除
    * @oaram pageNum
    * @param pageSize
    * @return RESTFul 接口返回对象格式
    * */
    @RequestMapping(method=RequestMethod.GET)
    public Result query(@RequestParam(required = false, value = "edcnEdmpId")String edcnEdmpId,
                        @RequestParam(required = false, value = "edcnLinkPreservable")Byte edcnLinkPreservable,
                        @RequestParam(required = false, value = "edcnUpdateType")Byte edcnUpdateType,
                        @RequestParam(required = false, defaultValue = "1")int pageNum,
                        @RequestParam(required =false,defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Pagination<EdmConnect> edmConnectList = edmConnectService.selectByExample(edcnEdmpId,edcnLinkPreservable , edcnUpdateType, pageNum, pageSize);
        result.setData(edmConnectList);
        return result;
    }

    @RequestMapping(value = "/{classId}/connects", method=RequestMethod.GET)
    public Result queryAll(@PathVariable(value = "classId")String classId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmConnect> edmConnectList = edmConnectService.getConnectsOfClass(classId);
        result.setData(edmConnectList);
        return result;
    }
}
