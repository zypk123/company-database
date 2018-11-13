package com.huntkey.rx.sceo.serviceCenter.provider.orm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.dao.FormDataMapper;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.DataProvider;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.FullCriteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.service.CommonDataService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.MysqlDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhanglb on 2017/7/6.
 */

@Service
public class CommonDataServiceImpl implements CommonDataService {

	private static Logger logger = LoggerFactory.getLogger(CommonDataServiceImpl.class);

	@Autowired
	private FormDataMapper dataMapper;

	@Override
	public void saveData(DataProvider dataProvider, Map<String, String> map){

	}



}
