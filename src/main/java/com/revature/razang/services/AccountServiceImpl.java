package com.revature.razang.services;
import java.sql.SQLException;
import java.util.List;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class AccountServiceImpl implements AccountService {
	private AccountDAOImpl accountDAO = new AccountDAOImpl(); 
	
	/** 
	 * @param account
	 * @return Account
	 * @throws AccountAlreadyExistsException
	 * @throws SQLException
	 */
	@Override
	public Account createAccount(Account account) throws AccountAlreadyExistsException, SQLException {
		try {
			return accountDAO.create(account);
		} catch (AccountAlreadyExistsException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
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
	 * @throws RecordNotFound
	 */
	@Override
	public void updateAccount(Account account) throws RecordNotFound {
		try {
			accountDAO.update(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/** 
	 * @param account
	 * @throws RecordNotFound
	 */
	@Override
	public void deleteAccount(Account account) throws RecordNotFound {
		try {
			accountDAO.delete(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	/** 
	 * @param account
	 * @param amount
	 * @throws RecordNotFound
	 */
	@Override
	public void depositIntoAccount(Account account, double amount) throws RecordNotFound {
		try {
			accountDAO.depositIntoAccount(account, amount);
		} catch (RecordNotFound e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 
	 * @param account
	 * @param amount
	 * @throws RecordNotFound
	 */
	@Override
	public void withdrawFromAccount(Account account, double amount) throws RecordNotFound, NegativeBalanceException {
		try {
			accountDAO.withdrawAccount(account, amount);
		} catch (NegativeBalanceException | RecordNotFound e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/** 
	 * @param account
	 * @return double
	 * @throws RecordNotFound
	 */
	@Override
	public double getBalance(Account account) throws RecordNotFound {
		try {
			return accountDAO.getBalance(account);
		} catch (RecordNotFound e) {
			e.printStackTrace();
			throw e;
		}
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
	 * @throws RecordNotFound
	 * @throws SQLException
	 */
	@Override
	public void setAccountUser(Account account, User user) throws RecordNotFound, SQLException {
		try {
			accountDAO.setAccountUser(account, user);
		} catch (RecordNotFound | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
