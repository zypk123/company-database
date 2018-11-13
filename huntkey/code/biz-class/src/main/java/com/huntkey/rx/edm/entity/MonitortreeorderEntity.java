package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 监管设置单类实体
 * 
 */
public class MonitortreeorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 9892554256475570L;
    
    /**变更类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_order_type", className="")
    private Integer mtor_order_type;
    /**变更监管树类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_cls_id", className="")
    private String mtor_cls_id;
    /**变更树对象根节点*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_order_root", className="MonitorEntity")
    private String mtor_order_root;
    /**节点集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_node_set", className="MtorMtorNodeSetaEntity")
    private List<MtorMtorNodeSetaEntity> mtor_node_set;

    public Integer getMtor_order_type() {
        return mtor_order_type;
    }

    public void setMtor_order_type(Integer mtor_order_type) {
        this.mtor_order_type = mtor_order_type;
    }

    public String getMtor_cls_id() {
        return mtor_cls_id;
    }

    public void setMtor_cls_id(String mtor_cls_id) {
        this.mtor_cls_id = mtor_cls_id;
    }

    public String getMtor_order_root() {
        return mtor_order_root;
    }

    public void setMtor_order_root(String mtor_order_root) {
        this.mtor_order_root = mtor_order_root;
    }

    public MonitorEntity loadMtor_order_root() {
        String propertyName = "mtor_order_root";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<MtorMtorNodeSetaEntity> loadMtor_node_set() {
        String propertyName = "mtor_node_set";
        String rootClass = "";
        return (List<MtorMtorNodeSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMtor_node_set(List<MtorMtorNodeSetaEntity> mtor_node_set) {
        this.mtor_node_set = mtor_node_set;
    }

    public List<MtorMtorNodeSetaEntity> getMtor_node_set() {
        return mtor_node_set;
    }



}