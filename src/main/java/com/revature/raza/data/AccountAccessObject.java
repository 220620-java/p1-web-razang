package com.revature.raza.data;

import com.revature.raza.models.Customer;

public interface AccountAccessObject<T> {
	
	public T create(Customer account); 
	public T delete(Customer account); 
	public T findById(int account);
	public T deposite(Customer customer, double amount); 
	public T withdraw(Customer customer, double amount); 
	public double balance(Customer customer); 

}
