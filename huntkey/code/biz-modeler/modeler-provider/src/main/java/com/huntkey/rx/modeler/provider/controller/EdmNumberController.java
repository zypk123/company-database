package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.druid.util.StringUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmNumber;
import com.huntkey.rx.modeler.provider.service.EdmNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author liucs
 * 2017-11-21 13:33:07
 */
@RestController
@RequestMapping("/numbers")
public class EdmNumberController {
    private static Logger log = LoggerFactory.getLogger(EdmNumberController.class);

    @Autowired
    private EdmNumberService edmNumerService;

    /**
     * 修改流水号
     * @param edmNumber
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result update(@RequestBody EdmNumber edmNumber){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = null;
        if(null == edmNumber.getEdmnType()){
            errorStr = "规则类型不能为空";
        }else{
            //返回职位编号
            String endoceStr = edmNumerService.update(edmNumber);
            result.setData(endoceStr);
        }
        result.setErrMsg(errorStr);
        return result;
    }
}
