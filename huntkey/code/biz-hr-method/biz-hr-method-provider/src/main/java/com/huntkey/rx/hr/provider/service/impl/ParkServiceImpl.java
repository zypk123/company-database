/**
 * Project Name:biz-hr-method-provider
 * File Name:ParkServiceImpl.java
 * Package Name:com.huntkey.rx.hr.provider.service.impl
 * Date:2017年11月22日上午10:09:52
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.dao.ParkDao;
import com.huntkey.rx.hr.provider.service.ParkService;

/**
 * ClassName:ParkServiceImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月22日 上午10:09:52
 * @author   lijie
 * @version  
 * @see 	 
 */
@Component
public class ParkServiceImpl implements ParkService{
    
    @Autowired
    private ParkDao parkDao;
    
    @Override
    public Result getAll() {
        
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(parkDao.getAll());
        return result;
    }
    
}

