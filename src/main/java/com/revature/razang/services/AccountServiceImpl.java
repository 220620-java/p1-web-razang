package com.revature.razang.services;
import java.util.List;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class AccountServiceImpl implements AccountService {
	private UserDAOImpl userDAO = new UserDAOImpl(); 
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
	public Account createAccount(User customer) {
		// TODO Auto-generated method stub
		return  accountDAO.create(customer); 
	}

	@Override
	public User getAccountHolderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * @param account
	 * @throws RecordNotFound
	 */

	@Override
	public User updateAccount(User customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * @param account
	 * @param amount
	 * @throws RecordNotFound
	 */

	@Override
	public Account deleteAccount(User customer) {
		// TODO Auto-generated method stub
		return accountDAO.delete(customer); 
	}


	/** 
	 * @param account
	 * @param amount
	 * @throws RecordNotFound
	 */

	@Override
	public Account depositIntoAccount(User customer, double amount) {
		// TODO Auto-generated method stub
		return accountDAO.depositIntoAccount(customer, amount); 
		
	}

	
	/** 
	 * @param account
	 * @return double
	 * @throws RecordNotFound
	 */

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
	
	/** 
	 * @param account
	 * @param user
	 * @throws RecordNotFound
	 * @throws SQLException
	 */

	@Override
	public List<User> viewAccountHolders() {
		// TODO Auto-generated method stub
		return accountDAO.displayAccountHolders(); 
	}





}
