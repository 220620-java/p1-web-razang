package com.revature.raza.models;

public class Account {
	
	private String account_no; 
	private String account_type; 
	private double balance;
	
	public Account() {}
	public Account(String account_no, String account_type, double balance) {
		super();
		this.account_no = account_no;
		this.account_type = account_type;
		this.balance = balance;
	}
	
	public String getAccount_no() {
		return account_no;
	}
	
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	
	public String getAccount_type() {
		return account_type;
	}
	
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	public double getBalance() {
		return balance;
	}
	
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [account_no=" + account_no 
				+ ", account_type=" + account_type 
				+ ", balance=" + balance + "]";
	} 
	
	
	
	

	
}
