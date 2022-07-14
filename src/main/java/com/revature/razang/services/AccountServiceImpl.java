package com.revature.razang.services;
import java.util.List;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class AccountServiceImpl implements AccountService {
	private UserDAOImpl bc = new UserDAOImpl(); 
	private AccountDAOImpl ca = new AccountDAOImpl(); 
	
	boolean isCustomer = false; 

	@Override
	public User signUp(User customer) {
		// TODO Auto-generated method stub
		return bc.createUser(customer); 
		
	}

	@Override
	public User signIn(String username, String passwd) {
		// TODO Auto-generated method stub
		User customer = bc.findByUsername(username); 
		if (customer != null && passwd.equals(customer.getPasswd())) {
			isCustomer = true; 
			return customer; 
		}
		return null;
	}

	@Override
	public Account createAccount(User customer) {
		// TODO Auto-generated method stub
		return  ca.create(customer); 
	}

	@Override
	public User getAccountHolderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateAccount(User customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Account deleteAccount(User customer) {
		// TODO Auto-generated method stub
		return ca.delete(customer); 
	}

	@Override
	public Account depositIntoAccount(User customer, double amount) {
		// TODO Auto-generated method stub
		return ca.depositIntoAccount(customer, amount); 
		
	}

	@Override
	public Account withdrawFromAccount(User customer, double amount) {
		// TODO Auto-generated method stub
		return ca.withdraw(customer, amount); 
	}

	@Override
	public double viewBalance(User customer) {
		// TODO Auto-generated method stub
		
		return ca.balance(customer);
	}

	@Override
	public List<User> viewAccountHolders() {
		// TODO Auto-generated method stub
		return ca.displayAccountHolders(); 
	}





}
