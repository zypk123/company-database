package com.huntkey.rx.sceo.serviceCenter.provider.core;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年7月15日 上午11:52:45
 * 
 **********************************************************************/
public class LoadData extends SearchData {

	private static final long serialVersionUID = -1647111229957622373L;

	private String id;

	public LoadData(String edmName, String id) {
		super(edmName);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
