package com.revature.razang.services;

import java.util.List;

import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public interface AccountService {
	public Account createAccount(Account account) throws SQLException, AccountAlreadyExistsException;
	public Account getAccountById (Account account);
	public List<Account> getAllAccounts ();
	public void updateAccount (Account account) throws RecordNotFound;
	public void deleteAccount(Account account) throws RecordNotFound; 
	public void depositIntoAccount(Account account, double amount) throws RecordNotFound; 
	public void withdrawFromAccount(Account account, double amount) throws RecordNotFound, NegativeBalanceException; 
	public double getBalance(Account account) throws RecordNotFound;
	public User getAccountUser(Account account);
	public void setAccountUser(Account account, User user) throws RecordNotFound, SQLException;
	public Account createAccount(User customer);
	public User getAccountHolderById(int id);
	public List<User> viewAccountHolders(); 
	public User updateAccount (User customer);
	public Account deleteAccount(User customer); 
	public Account depositIntoAccount(User account_id, double amount); 
	public Account withdrawFromAccount(User account_id, double amount); 
	public double viewBalance(User customer);
}
