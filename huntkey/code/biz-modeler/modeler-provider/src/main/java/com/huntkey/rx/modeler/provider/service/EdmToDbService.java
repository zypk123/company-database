package com.huntkey.rx.modeler.provider.service;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by zhuyj on 2017/10/30.
 */
public interface EdmToDbService {
    String dbCreator(String[] ids);

    String dbAllCreator(String version);

    String dbPassive(String version,Map<String,String> pathMap);


}

