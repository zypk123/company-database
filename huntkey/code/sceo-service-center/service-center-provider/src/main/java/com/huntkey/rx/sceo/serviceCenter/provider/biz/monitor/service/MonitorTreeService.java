package com.huntkey.rx.sceo.serviceCenter.provider.biz.monitor.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/**
 * Created by zhaomj on 2017/8/10.
 */
public interface MonitorTreeService {
    
    JSONArray getMonitorClasses(String treeName, String beginTime, String endTime,String edmdVer,String edmcNameEn) throws DBException ;

    JSONArray getNodeResources(String name, List<String> nodes, String edmId,String edmName, int type) throws DBException;
    
    JSONArray searchResourceObj(String resourceClassId,String resourceValue,int isFuzzyQuery);
}
