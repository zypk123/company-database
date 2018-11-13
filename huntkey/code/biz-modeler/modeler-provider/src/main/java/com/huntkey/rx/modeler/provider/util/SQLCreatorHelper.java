package com.huntkey.rx.modeler.provider.util;

import com.huntkey.rx.modeler.common.model.DBIndex;
import com.huntkey.rx.modeler.common.model.EdmTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoucj on 2017/11/6.
 */
public class SQLCreatorHelper {
    private Map<String, List<EdmTable>> map;
    private List<DBIndex> indexList;

    public SQLCreatorHelper() {
        this.map = new HashMap<>();
        this.indexList = new ArrayList<>();
    }

    public SQLCreatorHelper(Map<String, List<EdmTable>> map, List<DBIndex> indexList) {
        this.map = map;
        this.indexList = indexList;
    }

    public Map<String, List<EdmTable>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<EdmTable>> map) {
        this.map = map;
    }

    public List<DBIndex> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<DBIndex> indexList) {
        this.indexList = indexList;
    }
}
