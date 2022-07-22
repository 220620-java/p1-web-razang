package com.revature.razang.services;
import java.sql.SQLException;
import java.util.List;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

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
	 * @param account
	 * @return Account
	 */
	@Override
	public Account getAccountById(Account account) {
		return accountDAO.findById(account);
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
		try {
			accountDAO.update(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param account
	 */
	@Override
	public void deleteAccount(Account account) {
		try {
			accountDAO.delete(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		} 
	}
	
	/** 
	 * @param account
	 * @param amount
	 */
	@Override
	public void depositIntoAccount(Account account, double amount) {
		try {
			accountDAO.depositIntoAccount(account, amount);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}

	/** 
	 * @param account
	 * @param amount
	 */
	@Override
	public void withdrawFromAccount(Account account, double amount) {
		try {
			accountDAO.withdrawAccount(account, amount);
		} catch (NegativeBalanceException | RecordNotFound e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param account
	 * @return double
	 */
	@Override
	public double getBalance(Account account) {
		try {
			return accountDAO.getBalance(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	/** 
	 * @param account
	 * @return User
	 */
	@Override
	public User getAccountUser(Account account) {
		return accountDAO.getAccountUser(account);
	}
	
	/** 
	 * @param account
	 * @param user
	 */
	@Override
	public void setAccountUser(Account account, User user) {
		try {
			accountDAO.setAccountUser(account, user);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}

}
