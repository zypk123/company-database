package com.huntkey.rx.sceo.serviceCenter.provider.orm.exception;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月28日 上午10:13:26
 * 
 **********************************************************************/
public class MysqlException extends DBException {
	private static final long serialVersionUID = 801051074678386214L;

	public MysqlException(String msg) {
		super("Mysq异常：" + msg);
	}
}
