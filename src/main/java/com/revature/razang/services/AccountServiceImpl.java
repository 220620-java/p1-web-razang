package com.revature.razang.services;
import java.sql.SQLException;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class AccountServiceImpl implements AccountService {
	private AccountDAOImpl accountDAO = new AccountDAOImpl(); 
	
	@Override
	public Account createAccount(Account account) {
		try {
			return accountDAO.create(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	public void deleteAccount(Account account) {
		accountDAO.delete(account); 
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
}
