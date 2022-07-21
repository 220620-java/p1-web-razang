package com.revature.razang.services;
import java.sql.SQLException;
import java.util.List;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.models.Account;

public class AccountServiceImpl implements AccountService {
	private AccountDAOImpl accountDAO = new AccountDAOImpl(); 
	
	/** 
	 * @param account
	 * @return Account
	 */
	@Override
	public Account createAccount(Account account) {
		try {
			return accountDAO.create(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/** 
	 * @param id
	 * @return Account
	 */
	@Override
	public Account getAccountById(int id) {
		try {
			return accountDAO.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/** 
	 * @return List<Account>
	 */
	@Override
	public List<Account> getAllAccounts() {
		return accountDAO.findAll();
	}

	
	/** 
	 * @param account
	 */
	@Override
	public void updateAccount(Account account) {
		accountDAO.update(account);
	}
	
	
	/** 
	 * @param account
	 */
	@Override
	public void deleteAccount(Account account) {
		accountDAO.delete(account); 
	}

	
	/** 
	 * @param account
	 * @param amount
	 */
	@Override
	public void depositIntoAccount(Account account, double amount) {
		accountDAO.depositIntoAccount(account, amount);
	}

	
	/** 
	 * @param account
	 * @param amount
	 */
	@Override
	public void withdrawFromAccount(Account account, double amount) {
		try {
			accountDAO.withdrawAccount(account, amount);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
	}

	
	/** 
	 * @param account
	 * @return double
	 */
	@Override
	public double getBalance(Account account) {
		return accountDAO.getBalance(account);
	}

}
