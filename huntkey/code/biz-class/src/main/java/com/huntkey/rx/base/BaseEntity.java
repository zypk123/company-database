package com.huntkey.rx.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity  implements Serializable {

	private String id;

	private String creuser;

	private Date cretime;

	private String moduser;

	private Date modtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuser() {
		return moduser;
	}

	public void setModuser(String moduser) {
		this.moduser = moduser;
	}

	public Date getModtime() {
		return modtime;
	}

	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

	public String getCreuser() {
		return creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	public Date getCretime() {
		return cretime;
	}

	public void setCretime(Date cretime) {
		this.cretime = cretime;
	}

}
