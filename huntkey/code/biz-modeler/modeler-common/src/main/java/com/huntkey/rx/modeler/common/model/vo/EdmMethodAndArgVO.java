package com.huntkey.rx.modeler.common.model.vo;

import com.huntkey.rx.modeler.common.model.EdmMethodArg;

import java.util.List;

public class EdmMethodAndArgVO {

	//方法(插入)
	private EdmMethodVO edmMethod_in;

	//方法返回值(插入)
	private EdmMethodArg edmMethodreturn_in;

	//方法输入参数(插入)
	private List<EdmMethodArg> edmMethodInsertList_in;

	//方法(展示)
	private EdmMethodVO edmMethod_show;

	//方法返回值(展示)
	private EdmMethodArgVO edmMethodreturn_show;

	//方法输入参数(展示)
	private List<EdmMethodArgVO> edmMethodInsertList_show;

	public EdmMethodVO getEdmMethod_show() {
		return edmMethod_show;
	}

	public EdmMethodVO getEdmMethod_in() {
		return edmMethod_in;
	}

	public void setEdmMethod_in(EdmMethodVO edmMethod_in) {
		this.edmMethod_in = edmMethod_in;
	}

	public void setEdmMethod_show(EdmMethodVO edmMethod_show) {
		this.edmMethod_show = edmMethod_show;
	}

	public EdmMethodArg getEdmMethodreturn_in() {
		return edmMethodreturn_in;
	}

	public void setEdmMethodreturn_in(EdmMethodArg edmMethodreturn_in) {
		this.edmMethodreturn_in = edmMethodreturn_in;
	}

	public List<EdmMethodArg> getEdmMethodInsertList_in() {
		return edmMethodInsertList_in;
	}

	public void setEdmMethodInsertList_in(List<EdmMethodArg> edmMethodInsertList_in) {
		this.edmMethodInsertList_in = edmMethodInsertList_in;
	}

	public EdmMethodArgVO getEdmMethodreturn_show() {
		return edmMethodreturn_show;
	}

	public void setEdmMethodreturn_show(EdmMethodArgVO edmMethodreturn_show) {
		this.edmMethodreturn_show = edmMethodreturn_show;
	}

	public List<EdmMethodArgVO> getEdmMethodInsertList_show() {
		return edmMethodInsertList_show;
	}

	public void setEdmMethodInsertList_show(List<EdmMethodArgVO> edmMethodInsertList_show) {
		this.edmMethodInsertList_show = edmMethodInsertList_show;
	}

	@Override
	public String toString() {
		return "${" +
				"edmMethod_in:" + edmMethod_in +
				", edmMethodreturn_in:" + edmMethodreturn_in +
				", edmMethodInsertList_in:" + edmMethodInsertList_in +
				", edmMethod_show:" + edmMethod_show +
				", edmMethodreturn_show:" + edmMethodreturn_show +
				", edmMethodInsertList_show:" + edmMethodInsertList_show +
				'}';
	}
}
