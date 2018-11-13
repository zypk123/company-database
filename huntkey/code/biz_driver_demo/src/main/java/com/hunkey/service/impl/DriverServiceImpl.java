package com.hunkey.service.impl;

import com.hunkey.service.DriverService;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.stereotype.Service;

/**
 * Created by cjq on 2017/10/17
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public Result test(String methodName) {
        Result result = new Result();
        result.setData(methodName);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }
}
