package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 岗位树类实体
 * 
 */
public class ReportinglineEntity extends MonitorEntity implements Serializable {
    private static final long serialVersionUID = 9112908429946457L;
    
    /**所属资源类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="moni_resource_class", className="")
    private String moni_resource_class;

    public String getMoni_resource_class() {
        return moni_resource_class;
    }

    public void setMoni_resource_class(String moni_resource_class) {
        this.moni_resource_class = moni_resource_class;
    }



}