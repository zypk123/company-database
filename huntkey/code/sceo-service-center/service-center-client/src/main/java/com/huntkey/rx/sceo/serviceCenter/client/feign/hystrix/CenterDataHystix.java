package com.huntkey.rx.sceo.serviceCenter.client.feign.hystrix;

import com.huntkey.rx.sceo.serviceCenter.client.feign.CenterDataService;
import org.springframework.stereotype.Component;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;


/**
 * Created by zhanglb on 2017/6/24.
 */
@Component
public class CenterDataHystix implements CenterDataService {

    @Override
    public Result add(String data) {
        return getResultData(null);
    }

    @Override
    public Result delete(String data) {
        return getResultData(null);
    }

    @Override
    public Result find(String data) {
        return getResultData(null);
    }

    @Override
    public Result update(String data) {
        return getResultData(null);
    }

    public Result getResultData(String errMsg){
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        if(!StringUtil.isNullOrEmpty(errMsg)){
            result.setErrMsg(errMsg);
        }else{
            result.setErrMsg("连接不上服务");
        }
        return result;
    }
}
