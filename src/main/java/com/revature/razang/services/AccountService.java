package com.revature.razang.services;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountService {
	public Account createAccount(Account account);
	public User getAccountHolderById(int id);
	public User updateAccount (User customer);
	public void deleteAccount(Account account); 
	public Account depositIntoAccount(User account_id, double amount); 
	public Account withdrawFromAccount(User account_id, double amount); 
	public double viewBalance(User customer);
}
