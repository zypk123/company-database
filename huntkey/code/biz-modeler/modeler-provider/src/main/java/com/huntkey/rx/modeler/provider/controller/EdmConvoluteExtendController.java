package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExtend;
import com.huntkey.rx.modeler.provider.service.EdmConvoluteExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by linziy on 2017/4/16.
 */
//卷积属性周期关系扩展表
@RestController
@RequestMapping("/convoluteExtends")
public class EdmConvoluteExtendController {

    private static Logger log = LoggerFactory.getLogger(EdmConvoluteExtendController.class);

    @Autowired
    public EdmConvoluteExtendService edmConvoluteExtendService;

    /**
     * 根据属性id获取周期值
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getClassById(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmConvoluteExtend> edmConvoluteExtendList = edmConvoluteExtendService.selectListByPropertyId(id);
        result.setData(edmConvoluteExtendList);
        return result;
    }

}
