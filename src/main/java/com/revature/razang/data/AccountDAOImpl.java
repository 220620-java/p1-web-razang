package com.revature.razang.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.models.Account;
import com.revature.razang.utilities.WebUtils;
import com.revature.razangorm.orm.ObjectRelationalMapper;
import com.revature.razangorm.orm.ObjectRelationalMapperImpl;

public class AccountDAOImpl implements AccountDAO {

	ObjectRelationalMapper orm = new ObjectRelationalMapperImpl();
	Account userAccount = null;
	WebUtils gen = new WebUtils();
	
	final double DEFAULT_VALUE = 0.00; 

	@Override
	public Account create(Account t) throws SQLException {
		return (Account) orm.create(t, "bank.accounts");
	}

	@Override
	public Account findById(int id) throws SQLException {
		Account foundAccount = (Account) orm.findById(id, "bank.accounts");
		return foundAccount;
	}

	@Override
	public List<Account> findAll() {
		List<Object> retrievedObjects = orm.findAll(Account.class, "bank.accounts");
		List<Account> createdAccounts = retrievedObjects.stream().map(acc -> (Account)acc).collect(Collectors.toList());
		return createdAccounts;
	}

	@Override
	public Account update(Account t) {
		return (Account) orm.update (t, "bank.accounts");
	}

	@Override
	public void delete(Account t) {
		orm.delete(t, "bank.accounts");
	}

	@Override
	public void depositIntoAccount(Account account, double amount) {
		Map<String, Object> fields = new HashMap<String,Object>();
		fields.put("balance", account.getBalance() + amount);
		orm.updateField("accountnumber", (int)account.getAccountNumber(), fields, "accounts");
	}

	@Override
	public void withdrawAccount(Account account, double amount) throws NegativeBalanceException {
		Map<String, Object> fields = new HashMap<String,Object>();
		if (account.getBalance() - amount < 0) {
			String message = "CANNOT WITHDRAW " + amount + "! (" + (account.getBalance() - amount) + ")";
			throw new NegativeBalanceException(message);
		}
		fields.put("balance", account.getBalance() - amount);
		orm.updateField("accountnumber", (int)account.getAccountNumber(), fields, "accounts");
	}

	@Override
	public double getBalance(Account account) {
		try {
			userAccount = findById((int)account.getAccountNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		if (userAccount != null) {
			return userAccount.getBalance() ;
		}
		return 0;
	}

}
