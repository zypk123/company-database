package com.huntkey.rx.sceo.serviceCenter.provider.orm.exception;

/***********************************************************************
 * @author chenxj												      
 * 																	  
 * @email: kaleson@163.com											  
 * 																	  
 * @date : 2017年6月28日 上午10:13:26											 
 *																	  															 
 **********************************************************************/
public class DBException extends Exception {
	private static final long serialVersionUID = -3539406479608852141L;

	public DBException(String msg){
		super("DB异常" + msg);
	}
	
}
