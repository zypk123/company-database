package com.huntkey.rx.sceo.formula.provider.engine.entity;

import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 简单数据提供器实现
 *
 * @author chenfei on 2017/6/27.
 */
public class SimpleDataProvider implements DataProvider {

    private Map<String, Object> dataContext = null;

    public SimpleDataProvider() {
        this.dataContext = new HashMap<String, Object>();
    }
    public SimpleDataProvider(Map dataContext){
        Map dataContextNew = new HashMap();
        for(Map.Entry entry : (Set<Map.Entry>)dataContext.entrySet()) {
            if (List.class.isInstance(entry.getValue())) {
                List list = (List) entry.getValue();
                dataContextNew.put(entry.getKey(), list.toArray());
            } else {
                dataContextNew.put(entry.getKey(), entry.getValue());
            }
        }
        this.dataContext = dataContextNew;
    }
    @Override
    public Map<String, Object> getDataContext() {
        return dataContext;
    }

    @Override
    public Object getValue(String varName, String defaultValue) {

        Object varValue = dataContext.get(varName);

        return varValue;
    }

    @Override
    public String toString() {
        return "SimpleDataProvider{" +
                "dataContext=" + dataContext +
                '}';
    }
}