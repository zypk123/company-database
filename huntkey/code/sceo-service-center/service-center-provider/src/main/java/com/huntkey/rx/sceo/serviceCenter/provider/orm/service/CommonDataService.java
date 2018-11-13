package com.huntkey.rx.sceo.serviceCenter.provider.orm.service;


import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.DataProvider;

import java.util.Map;

/**
 * Created by zhanglb on 2017/7/6.
 */

public interface CommonDataService {


	void saveData(DataProvider dataProvider, Map<String, String> map);
}
