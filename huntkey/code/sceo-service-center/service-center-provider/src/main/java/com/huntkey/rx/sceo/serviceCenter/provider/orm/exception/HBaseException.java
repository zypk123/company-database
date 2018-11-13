package com.huntkey.rx.sceo.serviceCenter.provider.orm.exception;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月28日 上午10:13:26
 * 
 **********************************************************************/
public class HBaseException extends DBException {
	private static final long serialVersionUID = -1300088770306801411L;

	public HBaseException(String msg) {
		super("HBase异常：" + msg);
	}
}
