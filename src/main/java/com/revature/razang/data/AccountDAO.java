package com.revature.razang.data;

import java.sql.SQLException;

import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.models.Account;

public interface AccountDAO extends DataAccessObject<Account> {
	public void depositIntoAccount(Account account, double amount);
	public void withdrawAccount(Account account, double amount) throws NegativeBalanceException; 
	public double getBalance(Account account);
}
