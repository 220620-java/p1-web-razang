package com.revature.raza.services;
import com.revature.raza.models.Account;

import com.revature.raza.data.BankCustomer;
import com.revature.raza.data.CustomerAccount;
import com.revature.raza.ds.List;
import com.revature.raza.models.Customer;

public class BankServiceImpl implements BankService {
	private BankCustomer bc = new BankCustomer(); 
	private CustomerAccount ca = new CustomerAccount(); 
	
	boolean isCustomer = false; 

	@Override
	public Customer signUp(Customer customer) {
		// TODO Auto-generated method stub
		return bc.createCustomer(customer); 
		
	}

	@Override
	public Customer signIn(String username, String passwd) {
		// TODO Auto-generated method stub
		Customer customer = bc.findByUsername(username); 
		if (customer != null && passwd.equals(customer.getPasswd())) {
			isCustomer = true; 
			return customer; 
		}
		return null;
	}

	@Override
	public Account createAccount(Customer customer) {
		// TODO Auto-generated method stub
		return  ca.create(customer); 
		
	}
	
	@Override
	public Account deleteAccount(Customer customer) {
		// TODO Auto-generated method stub
		return ca.delete(customer); 
	}

	@Override
	public Account depositeFund(Customer customer, double amount) {
		// TODO Auto-generated method stub
		return ca.deposite(customer, amount); 
		
	}

	@Override
	public Account withdrawFund(Customer customer, double amount) {
		// TODO Auto-generated method stub
		return ca.withdraw(customer, amount); 
	}

	@Override
	public double viewBalance(Customer customer) {
		// TODO Auto-generated method stub
		
		return ca.balance(customer);
	}

	@Override
	public List<Customer> viewAccountHolders() {
		// TODO Auto-generated method stub
		return ca.displayAccountHolders(); 
	}

	

}
