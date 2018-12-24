package com.cy.cache.domain;

import java.io.Serializable;


/**
 * @author lilei
 * 用户安全公用DAO
 * 负责封装数据
 */
public class CacheDao implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7109336144482926074L;
	

	private String code;

    private String name;
    
    private String parentCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	/** 
	* @Title: setProperty 
	* @Description: 反射用到的方法 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void setProperty(String...args){
		this.code = args[0];
		this.name = args[1];
		if(args.length==3){
			this.parentCode = args[2];
		}
	}
	
}
