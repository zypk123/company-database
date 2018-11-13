package com.huntkey.rx.purchase.common.model.goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liangh on 2017/12/27 0027.
 * 此DTO只是用于单位树
 */
public class UnitTreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//单位树id

    private String label;//单位树显示的名称

    private List<UnitTreeDTO> children;//下级单位集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<UnitTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<UnitTreeDTO> children) {
        this.children = children;
    }
}
