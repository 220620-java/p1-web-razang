package com.revature.razang.services;

import java.util.List;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountService {
	public Account createAccount(User customer);
	public User getAccountHolderById(int id);
	public List<User> viewAccountHolders(); 
	public User updateAccount (User customer);
	public Account deleteAccount(User customer); 
	public Account depositIntoAccount(User account_id, double amount); 
	public Account withdrawFromAccount(User account_id, double amount); 
	public double viewBalance(User customer);
}
