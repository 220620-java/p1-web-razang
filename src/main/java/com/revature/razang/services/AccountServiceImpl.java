package com.revature.razang.services;
import java.util.List;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class AccountServiceImpl implements AccountService {
	private UserDAOImpl userDAO = new UserDAOImpl(); 
	private AccountDAOImpl accountDAO = new AccountDAOImpl(); 
	
	boolean isCustomer = false; 

	@Override
	public User signUp(User customer) {
		// TODO Auto-generated method stub
		return userDAO.createUser(customer); 
		
	}

	@Override
	public User signIn(String username, String passwd) {
		// TODO Auto-generated method stub
		User customer = userDAO.findByUsername(username); 
		if (customer != null && passwd.equals(customer.getPassword())) {
			isCustomer = true; 
			return customer; 
		}
		return null;
	}

	@Override
	public Account createAccount(User customer) {
		// TODO Auto-generated method stub
		return  accountDAO.create(customer); 
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
		return accountDAO.delete(customer); 
	}

	@Override
	public Account depositIntoAccount(User customer, double amount) {
		// TODO Auto-generated method stub
		return accountDAO.depositIntoAccount(customer, amount); 
		
	}

	@Override
	public Account withdrawFromAccount(User customer, double amount) {
		// TODO Auto-generated method stub
		return accountDAO.withdraw(customer, amount); 
	}

	@Override
	public double viewBalance(User customer) {
		// TODO Auto-generated method stub
		
		return accountDAO.balance(customer);
	}

	@Override
	public List<User> viewAccountHolders() {
		// TODO Auto-generated method stub
		return accountDAO.displayAccountHolders(); 
	}





}
