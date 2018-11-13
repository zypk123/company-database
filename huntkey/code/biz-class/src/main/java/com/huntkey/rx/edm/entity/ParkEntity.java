package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 园区类实体
 * 
 */
public class ParkEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 645395000575203L;
    
    /**代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpak_code", className="")
    private String rpak_code;
    /**名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpak_name", className="")
    private String rpak_name;
    /**详细地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpak_addr", className="")
    private String rpak_addr;

    public String getRpak_code() {
        return rpak_code;
    }

    public void setRpak_code(String rpak_code) {
        this.rpak_code = rpak_code;
    }

    public String getRpak_name() {
        return rpak_name;
    }

    public void setRpak_name(String rpak_name) {
        this.rpak_name = rpak_name;
    }

    public String getRpak_addr() {
        return rpak_addr;
    }

    public void setRpak_addr(String rpak_addr) {
        this.rpak_addr = rpak_addr;
    }



}