package com.revature.razang.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountService {
	public Account createAccount(Account account);
	public Account getAccountById (Account account);
	public List<Account> getAllAccounts ();
	public void updateAccount (Account account) throws RecordNotFound;
	public void deleteAccount(Account account) throws RecordNotFound; 
	public void depositIntoAccount(Account account, double amount) throws RecordNotFound; 
	public void withdrawFromAccount(Account account, double amount) throws RecordNotFound, NegativeBalanceException; 
	public double getBalance(Account account) throws RecordNotFound;
	public User getAccountUser(Account account);
	public void setAccountUser(Account account, User user) throws RecordNotFound, SQLException;
}
