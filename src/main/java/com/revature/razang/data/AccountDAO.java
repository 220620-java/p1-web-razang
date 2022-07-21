package com.revature.razang.data;

import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountDAO extends DataAccessObject<Account> {
	public void depositIntoAccount(Account account, double amount);
	public void withdrawAccount(Account account, double amount) throws NegativeBalanceException; 
	public double getBalance(Account account);
	public User getAccountUser(Account account);
	public void setAccountUser(Account account, User user);
}
