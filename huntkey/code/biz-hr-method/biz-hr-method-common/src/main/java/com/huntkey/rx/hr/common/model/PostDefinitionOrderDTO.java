package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by weijian on 2017/11/20.
 */
public class PostDefinitionOrderDTO extends OrderDTO{
    @JSONField(name = "id")
    String id;
    @JSONField(name = "opde_prop")
    String opdeProp;//职位属性
    @JSONField(name = "opde_remark")
    String opdeRemark;//备注
    @JSONField(name = "opde_type")
    String opdeType;//职位类别
    @JSONField(name = "opde_edit_type")
    String opdeEditType   ;//单据类型 1.新增 2，修改
    @JSONField(name = "opde_post_set")
    List<opdePostSetDTO> opdePostSet;//职位列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpdeProp() {
        return opdeProp;
    }

    public void setOpdeProp(String opdeProp) {
        this.opdeProp = opdeProp;
    }

    public String getOpdeRemark() {
        return opdeRemark;
    }

    public void setOpdeRemark(String opdeRemark) {
        this.opdeRemark = opdeRemark;
    }

    public String getOpdeType() {
        return opdeType;
    }

    public void setOpdeType(String opdeType) {
        this.opdeType = opdeType;
    }

    public String getOpdeEditType() {
        return opdeEditType;
    }

    public void setOpdeEditType(String opdeEditType) {
        this.opdeEditType = opdeEditType;
    }

    public List<opdePostSetDTO> getOpdePostSet() {
        return opdePostSet;
    }

    public void setOpdePostSet(List<opdePostSetDTO> opdePostSet) {
        this.opdePostSet = opdePostSet;
    }
}
