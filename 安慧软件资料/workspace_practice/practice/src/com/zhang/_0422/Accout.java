package com.zhang._0422;

/**
 * 银行取钱，账户类
 **/
public class Accout {

	private String accoutNo;// 账户编号

	private double balance;// 账户余额

	private String accoutName;// 账户名称

	public Accout() {
		super();
	}

	public Accout(String accoutNo, String accoutName, double balance) {
		super();
		this.accoutNo = accoutNo;
		this.balance = balance;
		this.accoutName = accoutName;
	}

	public String getAccoutNo() {
		return accoutNo;
	}

	public void setAccoutNo(String accoutNo) {
		this.accoutNo = accoutNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccoutName() {
		return accoutName;
	}

	public void setAccoutName(String accoutName) {
		this.accoutName = accoutName;
	}

	// 根据accoutNohe来计算Accout的hashcode和判断equals
	@Override
	public int hashCode() {
		return accoutNo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == Accout.class) {
			Accout target = (Accout) obj;
			return target.getAccoutNo().equals(accoutNo);
		}
		return false;
	}
}