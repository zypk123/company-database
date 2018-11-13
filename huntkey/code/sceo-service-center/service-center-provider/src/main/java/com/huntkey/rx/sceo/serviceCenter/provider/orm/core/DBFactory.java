package com.huntkey.rx.sceo.serviceCenter.provider.orm.core;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase.HBaseHandler;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.db.mysql.MySQLHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月22日 下午7:39:52
 * 
 **********************************************************************/

// DB 工厂类，根据参数的db类型返回对应的服务处理对象
@Component
public class DBFactory {

	@Autowired
	private MySQLHandler mysqlHandler;

	@Autowired
	private HBaseHandler hbaseHandler;

	/**
	 * @param type
	 *            数据库类型；
	 * @return
	 */
	public final DBHandler getDBHandler(DBType type) {
		switch (type) {
		case HBASE: {
			return hbaseHandler;
		}
		case MYSQL: {
			return mysqlHandler;
		}
		}
		return null;
	}
}
