package com.revature.razang.data;

import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountDAO extends DataAccessObject<Account> {
	public void depositIntoAccount(Account account, double amount) throws RecordNotFound;
	public void withdrawAccount(Account account, double amount) throws NegativeBalanceException, RecordNotFound; 
	public double getBalance(Account account) throws RecordNotFound;
	public User getAccountUser(Account account);
	public void setAccountUser(Account account, User user) throws RecordNotFound;
}
