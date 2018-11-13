/**
 * Project Name:biz-hr-method-common
 * File Name:ParkDTO.java
 * Package Name:com.huntkey.rx.hr.common.model
 * Date:2017年11月22日上午9:53:36
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * ClassName:ParkDTO 园区类DTO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月22日 上午9:53:36
 * @author   lijie
 * @version  
 * @see 	 
 */
public class ParkDTO {
    
    @JSONField(name = NodeConstant.ID)
    private String id;
    
    @JSONField(name = ParkConstants.RPAK_CODE)
    private String rpakCode;
    
    @JSONField(name = ParkConstants.RPAK_NAME)
    private String rpakName;
    
    @JSONField(name = ParkConstants.RPAK_ADDR)
    private String rpakAddr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRpakCode() {
        return rpakCode;
    }

    public void setRpakCode(String rpakCode) {
        this.rpakCode = rpakCode;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }

    public String getRpakAddr() {
        return rpakAddr;
    }

    public void setRpakAddr(String rpakAddr) {
        this.rpakAddr = rpakAddr;
    }
    
}

