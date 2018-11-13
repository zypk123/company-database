package com.huntkey.rx.sceo.serviceCenter.provider.orm.service.impl;


import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBFactory;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBType;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.service.PersistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月22日 下午5:56:21
 * 
 **********************************************************************/

/*
 * 第一步：读取参数 criteria 的类名，获取它被实例化的数据库类型；需要调用服务；
 * 第二步：根据数据库类型，调用分支的对象去处理参数，
 * 第三步：解析参数，调用服务处理；
 */
@Service
public class PersistanceServiceImpl implements PersistanceService {

	@Autowired
	private DBFactory dbFactory;

	@Override
	public DataSet load(DBType dbType, Criteria criteria) throws DBException {
		return dbFactory.getDBHandler(dbType).load(criteria);
	}

	@Override
	public String add(DBType dbType, Criteria criteria) throws DBException {
		return dbFactory.getDBHandler(dbType).merge(criteria);
	}

	@Override
	public void delete(DBType dbType, Criteria criteria) throws DBException {
		dbFactory.getDBHandler(dbType).delete(criteria);
	}

	@Override
	public DataSet find(DBType dbType, Criteria criteria) throws DBException {
		return dbFactory.getDBHandler(dbType).find(criteria);
	}

	@Override
	public void update(DBType dbType, Criteria criteria) throws DBException {
		dbFactory.getDBHandler(dbType).update(criteria);
	}


	public long count (DBType dbType, Criteria criteria) throws DBException {
		return dbFactory.getDBHandler(dbType).count(criteria);
	}

	public DataSet richfind(DBType dbType, Criteria criteria) throws DBException{
		return dbFactory.getDBHandler(dbType).richfind(criteria);
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		return dbFactory.getDBHandler(DBType.MYSQL).query(sql);
	}
}
