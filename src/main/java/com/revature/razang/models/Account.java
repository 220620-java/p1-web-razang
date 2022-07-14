package com.revature.razang.models;

import com.revature.razang.utility.BankUtils;

public class Account {

	public enum AccountType {
		CHECKING,
		SAVINGS
	}

	private long accountNumber; 
	private AccountType accountType; 
	private double balance;
	
	public Account() {
		this.accountNumber = BankUtils.generateRandomAccountNumber();
	}

	public Account(long accountNumber, AccountType accountType, double balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long account_no) {
		this.accountNumber = account_no;
	}
	
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void SetAccountType(AccountType account_type) {
		this.accountType = account_type;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		String retString = String.format("Account {0}: TYPE={1}, BALANCE=${2}", accountNumber, accountType.toString(), balance);
		return retString;
	} 
	
	
	
	

	
}
