package com.huntkey.rx.sceo.formula.common.params;

import java.util.List;
import java.util.Map;

/**
 * @author chenfei on 2017/8/25 0025.
 */
public class VariantBatchParam {

    private List<IdMapper> idMappers;
    private Map<String, Object> dataMap;
    private String edmName;

    public List<IdMapper> getIdMappers() {
        return idMappers;
    }

    public void setIdMappers(List<IdMapper> idMappers) {
        this.idMappers = idMappers;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }
}
