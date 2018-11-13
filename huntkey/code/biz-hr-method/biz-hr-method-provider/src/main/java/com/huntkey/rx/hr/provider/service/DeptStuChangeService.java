/**
 * Project Name:biz-hr-method-provider
 * File Name:DeptChangeService.java
 * Package Name:com.huntkey.rx.hr.provider.service
 * Date:2017年11月16日上午11:55:29
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;

/**
 * ClassName:DeptChangeService 部门异动单操作
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月16日 上午11:55:29
 * @author   lijie
 * @version  
 * @see 	 
 */
public interface DeptStuChangeService {

    Result addDept(String order);

    Result modifyDept(String orderId);

    void updateDeptOrderStatus(String orderId, String orderStatus);
}

