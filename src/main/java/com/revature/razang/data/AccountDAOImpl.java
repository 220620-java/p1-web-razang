package com.revature.razang.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.utilities.WebUtils;
import com.revature.razangorm.utilities.ConnectionObject;

public class AccountDAOImpl implements AccountDAO {

	private ConnectionObject connObj = ConnectionObject.getConnectionUtil();
	Account userAccount = null;
	WebUtils gen = new WebUtils();
	
	final double DEFAULT_VALUE = 0.00; 

	@Override
	public Account create(User account) {
		int customer_id = account.getUserId();
		long account_no = WebUtils.generateRandomAccountNumber();
		
		try (Connection conn =  connObj.getConnection()) {
			
			conn.setAutoCommit(false);

			String sql = "insert into BankAccount "
					+ "(account_no, balance, customer_id) "
					+ "values (?, ?, ?)"; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setLong(1, account_no);
			st.setDouble(2, 0.00);
			st.setInt(3, customer_id);
			
			int rowAdded = st.executeUpdate(); 
			if (rowAdded == 1) {
				userAccount = new Account(account_no, Account.AccountType.CHECKING, DEFAULT_VALUE); 
				conn.commit();
				
			} else {
				conn.rollback();
				userAccount = null; 
			}	
		}catch (SQLException e) {
			userAccount = null; 
			System.out.println(e.getMessage()); 
		}
		
		return userAccount;
	}

	@Override
	public Account delete(User account) {
		try (Connection conn = connObj.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "delete from bankaccount "
					+ "where customer_id=? "; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, account.getUserId());
			
			userAccount = findById(account.getUserId()); 
			
			int rowDeleted = st.executeUpdate(); 
			if (rowDeleted == 1) {
				conn.commit();
			}else {
				conn.rollback();
				userAccount = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userAccount; 
	}

	@Override
	public Account findById(int id) {
		userAccount = null; 
		try (Connection conn = connObj.getConnection()){
			
			String sql = "select * from bankaccount "
					+ "where customer_id =?"; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setInt(1, id);
			
			ResultSet result = st.executeQuery();
			
			if (result.next()) {
				userAccount =
						new Account(
							result.getLong("account_no"),
								Account.AccountType.CHECKING , result.getDouble("balance")); 
				
			}
		}catch(SQLException e) {
			e.getMessage(); 
		}
		
		return userAccount;
	}

	@Override
	public Account depositIntoAccount(User customer, double amount) {
		userAccount = findById(customer.getUserId()); 
		double currentBalance = userAccount.getBalance(); 
		currentBalance += amount; 
		userAccount.setBalance(currentBalance);
		try (Connection conn = connObj.getConnection()){
			
			conn.setAutoCommit(false);
			String sql = "update bankaccount "
					+ "set balance =? "
					+ "where customer_id = ?"; 
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setDouble(1, currentBalance);
			st.setInt(2, customer.getUserId());
			
			int rowUpdated = st.executeUpdate();
			
			if (rowUpdated == 1) {
				conn.commit();
			} else {
				conn.rollback();
				userAccount = null; 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccount; 
	}
	
	public List<User> displayAccountHolders() {
		
		User customer = null; 
		List<User> allAccountHolders = new ArrayList<>();
		
		try (Connection conn = connObj.getConnection()) {
			
			String sql = "select c.customer_id, c.username, c.birthDate, c.email, c.phone, c.passwd "
					+ "from customer c join bankaccount b "
					+ "using(customer_id)";
			
			Statement st = conn.createStatement(); 
			ResultSet result = st.executeQuery(sql); 
			
			while (result.next()) {
				int customer_id = result.getInt("customer_id"); 
				String username = result.getString("username"); 
				Date birthDate = result.getDate("birthDate"); 
				String email = result.getString("email"); 
				String phone = result.getString("phone"); 
				String passwd = result.getString("passwd"); 
				
				customer = new User(customer_id, username, birthDate, email, phone, passwd); 
				allAccountHolders.add(customer);	
			}
			
			
		}catch (SQLException e) {
			e.getMessage(); 
		}
		return allAccountHolders;
		
	}

	@Override
	public Account withdraw(User customer, double amount) {
		userAccount = findById(customer.getUserId()); 
		double currentBalance = userAccount.getBalance(); 
		if (currentBalance >= amount) {
			currentBalance = currentBalance - amount; 
			userAccount.setBalance(currentBalance);
			return userAccount; 
		}else {
			userAccount = null;
		}
		
		try (Connection conn = connObj.getConnection()){
			
			conn.setAutoCommit(false);
			String sql = "update bankaccount "
					+ "set balance =? "
					+ "where customer_id = ?"; 
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setDouble(1, currentBalance);
			st.setInt(2, customer.getUserId());
			
			int rowUpdated = st.executeUpdate();
			
			if (rowUpdated == 1) {
				conn.commit();
			} else {
				conn.rollback();
				userAccount = null; 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccount;
	}

	@Override
	public double balance(User customer) {
		userAccount = findById(customer.getUserId()); 
		if (userAccount != null) {
			return userAccount.getBalance() ;
		}
		return -1.00; 
	}

	@Override
	public Account create(Account t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Account t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub
		
	}
	
	

}
