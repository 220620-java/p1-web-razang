package com.revature.razang.data;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountDAO extends DataAccessObject<Account> {
	public Account create(User account); 
	public Account delete(User account); 
	public Account findById(int id);
	public Account depositIntoAccount(User customer, double amount); 
	public Account withdraw(User customer, double amount); 
	public double balance(User customer); 

}
