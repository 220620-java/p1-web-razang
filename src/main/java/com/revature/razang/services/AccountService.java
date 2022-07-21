package com.revature.razang.services;

import java.util.List;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountService {
	public Account createAccount(Account account);
	public Account getAccountById (int id);
	public List<Account> getAllAccounts ();
	public void updateAccount (Account account);
	public void deleteAccount(Account account); 
	public void depositIntoAccount(Account account, double amount); 
	public void withdrawFromAccount(Account account, double amount); 
	public double getBalance(Account account);
	public User getAccountUser(Account account);
	public void setAccountUser(Account account, User user);
}
