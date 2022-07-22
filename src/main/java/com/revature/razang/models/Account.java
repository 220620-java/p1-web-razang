package com.revature.razang.models;

import com.revature.razang.utilities.WebUtils;
import com.revature.razangorm.annotations.Id;

/**
 * @author Raza Ghulam
 * @author Colby Tang
 */
public class Account {

	@Id
	private long accountNumber;
	private String accountType; 
	private double balance;

	/**
	 * Generates a random account number, defaults to CHECKING, and sets the balance to 0.
	 */
	public Account() {
		this.accountNumber = WebUtils.generateRandomAccountNumber();
		this.accountType = "CHECKING";
		this.balance = 0.0f;
	}

	public Account(long accountNumber, String accountType, double balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	/** Gets the account number which can be randomly generated if instantiated with no parameters.
	 * @return long
	 */
	public long getAccountNumber() {
		return accountNumber;
	}
	
	/** Sets the account number.
	 * @param account_no
	 */
	public void setAccountNumber(long account_no) {
		this.accountNumber = account_no;
	}
	
	/** Gets the account type: CHECKING or SAVINGS.
	 * @return String
	 */
	public String getAccountType() {
		return accountType.toString();
	}
	
	/** 
	 * @param account_type
	 */
	public void SetAccountType(String account_type) {
		this.accountType = account_type;
	}
	
	/** 
	 * @return double
	 */
	public double getBalance() {
		return balance;
	}
	
	/** 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		String retString = String.format("Account %d: TYPE=%s, BALANCE=$%f", accountNumber, accountType, balance);
		return retString;
	} 
}
