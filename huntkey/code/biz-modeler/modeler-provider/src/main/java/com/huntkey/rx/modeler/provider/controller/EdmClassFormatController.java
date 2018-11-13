package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import com.huntkey.rx.modeler.common.model.vo.EdmClassFormatVO;
import com.huntkey.rx.modeler.provider.service.EdmClassFormatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classFormats")
public class EdmClassFormatController {

    private static Logger log = LoggerFactory.getLogger(EdmClassFormatController.class);

    @Autowired
    public EdmClassFormatService edmClassFormatService;

    /**
     * 根据类Id查询对象呈现格式列表
     * @param edmcId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryList(@RequestParam String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClassFormatVO> edmClassFormatVOList = edmClassFormatService.selectClassFormatListByEdmcId(edmcId);
        result.setData(edmClassFormatVOList);
        return result;
    }

    /**
     * 批量新增对象呈现格式
     *
     * @param edmClassFormatList
     * @return
     */
    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public Result insertBatch(@Validated @RequestBody List<EdmClassFormat> edmClassFormatList) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmClassFormatService.insertBatch(edmClassFormatList);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 批量删除对象呈现格式
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatch/{ids}", method = RequestMethod.PUT)
    public Result deleteBatch(@PathVariable String[] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmClassFormatService.deleteBatch(ids);
        return result;
    }

    /**
     * 获取类的特征值及显示格式
     * @param classId
     * @return
     */
    @RequestMapping(value = "/getCharacterAndFormat", method = RequestMethod.GET)
    public Result getCharacterAndFormat(@RequestParam String classId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map map = edmClassFormatService.getCharacterAndFormat(classId);
        result.setData(map);
        return result;
    }

    /**
     * 根据类id获取类的对象列表（呈现、枚举以及其他类）
     * @param classId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAppearAndEnumObject", method = RequestMethod.GET)
    public Result getAppearAndEnumObject(@RequestParam String classId,@RequestParam(required = false)String wordName,@RequestParam(required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Pagination<Map<String,String>> list = edmClassFormatService.getAppearAndEnumObject(classId,wordName,pageNum,pageSize);
        result.setData(list);
        return result;
    }

    /**
     * 获取枚举列表
     * @return
     */
    @RequestMapping(value = "/selectWordlists",method = RequestMethod.GET)
    public Result selectWordlists(@RequestParam(value = "wordName",required = false)String wordName,
                                  @RequestParam(required = false, defaultValue = "1")int pageNum,@RequestParam(required = false, defaultValue = "15")int pageSize) throws Exception{
        //Result result = new Result();;
        return edmClassFormatService.selectWordlists(wordName,pageNum,pageSize);
    }

}
