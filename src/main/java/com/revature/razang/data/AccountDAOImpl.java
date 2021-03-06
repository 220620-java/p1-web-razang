package com.revature.razang.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.utilities.WebUtils;
import com.revature.razangorm.orm.ObjectRelationalMapper;
import com.revature.razangorm.orm.ObjectRelationalMapperImpl;

public class AccountDAOImpl implements AccountDAO {

	ObjectRelationalMapper orm = new ObjectRelationalMapperImpl();
	Account userAccount = null;
	WebUtils gen = new WebUtils();
	
	final double DEFAULT_VALUE = 0.00; 

	
	/** 
	 * @param t
	 * @return Account
	 * @throws SQLException
	 * @throws RecordNotFound
	 */
	@Override
	public Account create(Account account) throws SQLException, AccountAlreadyExistsException {
		if (findById(account) != null) {
			throw new AccountAlreadyExistsException();
		}
		return (Account) orm.create(account, "bank.accounts");
	}

	
	/** 
	 * @param account
	 * @return Account
	 */
	@Override
	public Account findById(Account account) {
		Account foundAccount = (Account) orm.findById(account, "bank.accounts");
		return foundAccount;
	}
	
	/** 
	 * @return List<Account>
	 */
	@Override
	public List<Account> findAll() {
		List<Object> retrievedObjects = orm.findAll(Account.class, "bank.accounts");
		List<Account> createdAccounts = retrievedObjects.stream().map(acc -> (Account)acc).collect(Collectors.toList());
		return createdAccounts;
	}

	
	/** 
	 * @param t
	 * @return Account
	 */
	@Override
	public Account update(Account account) throws RecordNotFound {
		Account findAccount = account;
		if (findById(findAccount) == null) {
			throw new RecordNotFound(account);
		}
		return (Account) orm.update (account, "bank.accounts");
	}

	
	/** 
	 * @param t
	 * @return Account
	 */
	@Override
	public Account delete(Account t) throws RecordNotFound {
		if (findById(t) == null) {
			throw new RecordNotFound(t);
		}
		return (Account) orm.delete(t, "bank.accounts");
	}

	
	/** 
	 * @param account
	 * @param amount
	 */
	@Override
	public void depositIntoAccount(Account account, double amount) throws RecordNotFound {
		if (findById(account) == null) {
			throw new RecordNotFound(account);
		}
		Map<String, Object> fields = new HashMap<String,Object>();
		fields.put("balance", account.getBalance() + amount);
		orm.updateField("accountnumber", (int)account.getAccountNumber(), fields, "accounts");
	}

	
	/** 
	 * @param account
	 * @param amount
	 * @throws NegativeBalanceException
	 */
	@Override
	public void withdrawAccount(Account account, double amount) throws NegativeBalanceException, RecordNotFound {
		if (findById(account) == null) {
			throw new RecordNotFound(account);
		}
		Map<String, Object> fields = new HashMap<String,Object>();
		if (account.getBalance() - amount < 0) {
			String message = "CANNOT WITHDRAW " + amount + "! (" + (account.getBalance() - amount) + ")";
			throw new NegativeBalanceException(message);
		}
		fields.put("balance", account.getBalance() - amount);
		orm.updateField("accountnumber", (int)account.getAccountNumber(), fields, "accounts");
	}

	
	/** 
	 * @param account
	 * @return double
	 */
	public double getBalance(Account account) throws RecordNotFound {
		if (findById(account) == null) {
			throw new RecordNotFound(account);
		}
		return userAccount.getBalance();
	}

	
	/** 
	 * @param account
	 * @return User
	 */
	public User getAccountUser(Account account) throws RecordNotFound {
		Integer userid = (Integer) orm.getValueById("accountnumber", (int)account.getAccountNumber(), "userid", "accounts");
		if (userid == null) {
			throw new RecordNotFound();
		}
		User user = (User) orm.findById(userid, "bank.users");
		if (user != null) {
			return user;
		}
		return null;
	}

	
	/** 
	 * @param account
	 * @param user
	 */
	@Override
	public void setAccountUser(Account account, User user) throws RecordNotFound, SQLException {
		if (findById(account) == null) {
			throw new RecordNotFound(account);
		}
		UserDAO userDAO = new UserDAOImpl();
		try {
			if (userDAO.findById(user) == null) {
				throw new RecordNotFound(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		Map<String, Object> fields = new HashMap<String,Object>();
		fields.put("userid", user.getUserId());
		orm.updateField("accountnumber", (int)account.getAccountNumber(), fields, "accounts");
	}
}
