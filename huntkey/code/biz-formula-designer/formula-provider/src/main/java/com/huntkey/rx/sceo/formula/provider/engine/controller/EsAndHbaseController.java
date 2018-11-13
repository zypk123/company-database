package com.huntkey.rx.sceo.formula.provider.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.vo.EsHbaseCriteriaVo;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EsAndHbaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lulx on 2017/7/4 0004 下午 2:07
 */
@RestController
@RequestMapping("/esAndHbase")
public class EsAndHbaseController {

    private static Logger log = LoggerFactory.getLogger(EsAndHbaseController.class);

    @Autowired
    private EsAndHbaseService esAndHbaseService;

    /**
     *@desc 查询EDM数据
     *@pars [esHbaseCriteriaVo]
     *@date 2017/7/4 0004 下午 3:04 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/queryFromEsAndHbase", method = RequestMethod.POST)
    public Result queryFromEsAndHbase(@RequestBody EsHbaseCriteriaVo esHbaseCriteriaVo) {

        Result result = null;
        try {
            String datas = JSONObject.toJSONString(esHbaseCriteriaVo);
            result = esAndHbaseService.queryFromEsAndHbase(datas);
            if (result.getRetCode().equals( Result.RECODE_ERROR)) {
                result.setErrMsg("查询EDM数据接口出错...");
            }
        } catch (Exception e) {
            log.error("查询EDM数据接口出错",e);
            throw new RuntimeException("查询EDM数据接口出错",e);
        }
        return result;
    }
}
