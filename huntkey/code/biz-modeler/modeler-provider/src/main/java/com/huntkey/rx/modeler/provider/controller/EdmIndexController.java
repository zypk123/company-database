package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import com.huntkey.rx.modeler.provider.service.EdmIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @ClassName: EdmIndexController
 * @Description: 索引接口Controller
 * @author: zhuyj
 * @date: 2017年9月22日上午11:35:15
 *
 */
@RestController
@RequestMapping("/indexes")
public class EdmIndexController {

    private static Logger log = LoggerFactory.getLogger(EdmPropertyController.class);

    @Autowired
    public EdmIndexService edmIndexService;


    /**
     * 新增索引
     * @param edmIndexVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmIndexVO edmIndexVO,HttpServletRequest request) {

        String userId = (String)request.getSession().getAttribute("account");
        edmIndexVO.setAdduser(userId);
//        Map<String,String> sysUser = (Map<String,String>)casSerivce.getSysUser(userId).getData();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        // 数据校验
        String errorStr = null;
        //验证索引键列是否为空
        errorStr = edmIndexService.checkIndexIndexProppertyIds(edmIndexVO.getIndexProppertyIds());

        //验证索引名称是否重复
        errorStr = edmIndexService.checkIndexName(edmIndexVO.getIndexName(),edmIndexVO.getEdmcId(),null);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }

        //验证是否已存在主键索引
        if ("primary".equals(edmIndexVO.getIndexType())) {
            errorStr = edmIndexService.checkPrimaryIndex(edmIndexVO.getEdmcId(),null);
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        edmIndexService.insert(edmIndexVO);
        result.setData(edmIndexVO);
        return result;
    }

    /**
     * 修改索引
     * @param edmIndexVO
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@Validated @RequestBody EdmIndexVO edmIndexVO,HttpServletRequest request) {
        String userId = (String)request.getSession().getAttribute("account");
        edmIndexVO.setModuser(userId);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        // 数据校验
        //验证索引名称是否重复
        String errorStr = null;
        errorStr = edmIndexService.checkIndexName(edmIndexVO.getIndexName(), edmIndexVO.getEdmcId(), edmIndexVO.getId());
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }

        if ("primary".equals(edmIndexVO.getIndexType())) {
            errorStr = edmIndexService.checkPrimaryIndex(edmIndexVO.getEdmcId(), edmIndexVO.getId());
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        edmIndexService.update(edmIndexVO);
        result.setData(edmIndexVO);
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "ids") String [] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmIndexService.deletes(ids);
        return result;
    }
}
