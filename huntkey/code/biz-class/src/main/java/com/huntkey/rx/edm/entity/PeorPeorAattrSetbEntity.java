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
public class PeorPeorAattrSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6341251060132416L;
    
    /**属性名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aaname", className="")
    private String peor_aaname;
    /**限制方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aalimit", className="")
    private String peor_aalimit;
    /**限定公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aacond", className="")
    private String peor_aacond;

    public String getPeor_aaname() {
        return peor_aaname;
    }

    public void setPeor_aaname(String peor_aaname) {
        this.peor_aaname = peor_aaname;
    }

    public String getPeor_aalimit() {
        return peor_aalimit;
    }

    public void setPeor_aalimit(String peor_aalimit) {
        this.peor_aalimit = peor_aalimit;
    }

    public String getPeor_aacond() {
        return peor_aacond;
    }

    public void setPeor_aacond(String peor_aacond) {
        this.peor_aacond = peor_aacond;
    }



}