package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class NbrlNbrlTextSetcEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6057681042100901L;
    
    /**文本内容*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_test_content", className="")
    private String nbrl_test_content;

    public String getNbrl_test_content() {
        return nbrl_test_content;
    }

    public void setNbrl_test_content(String nbrl_test_content) {
        this.nbrl_test_content = nbrl_test_content;
    }



}