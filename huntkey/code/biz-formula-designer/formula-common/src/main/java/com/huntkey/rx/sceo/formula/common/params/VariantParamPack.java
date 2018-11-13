package com.huntkey.rx.sceo.formula.common.params;

import java.util.List;

/**
 * @author chenfei on 2017/7/13.
 */
public class VariantParamPack {

    private String id;

    private String edmName;

    private List<VariantParamItem> items;

    public List<VariantParamItem> getItems() {
        return items;
    }

    public void setItems(List<VariantParamItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    @Override
    public String toString() {
        return "VariantParamPack{" +
                "id='" + id + '\'' +
                ", edmName='" + edmName + '\'' +
                ", items=" + items +
                '}';
    }
}
