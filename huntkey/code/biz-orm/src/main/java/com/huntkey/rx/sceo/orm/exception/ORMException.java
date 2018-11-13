package com.huntkey.rx.sceo.orm.exception;

/***********************************************************************
 * @author chenxj												      
 * 																	  
 * @email: kaleson@163.com											  
 * 																	  
 * @date : 2017年11月30日 下午2:10:54											 
 *																	  															 
 **********************************************************************/
public class ORMException extends RuntimeException {
	private static final long serialVersionUID = 6056538767697391221L;

	public ORMException(String msg){
		super("ORM Exception: " + msg);
	}
}
