package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 呈现类实体
 * 
 */
public class ShowEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 5617130705418459L;
    
    /**名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="show_name", className="")
    private String show_name;

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }



}