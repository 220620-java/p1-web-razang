package com.revature.raza.services;

import com.revature.raza.ds.List;
import com.revature.raza.models.Account;
import com.revature.raza.models.Customer;

public interface BankService extends Service {

	public Account createAccount(Customer customer); 
	
	public Account deleteAccount(Customer customer); 
	
	public Account depositeFund(Customer account_id, double amount); 
	
	public Account withdrawFund(Customer account_id, double amount); 
	
	public double viewBalance(Customer customer);
	
	public List<Customer> viewAccountHolders(); 
}
