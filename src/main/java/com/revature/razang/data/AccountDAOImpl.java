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
import com.revature.razangorm.orm.ObjectRelationalMapper;
import com.revature.razangorm.orm.ObjectRelationalMapperImpl;
import com.revature.razangorm.utilities.ConnectionObject;

public class AccountDAOImpl implements AccountDAO {

	private ConnectionObject connObj = ConnectionObject.getConnectionUtil();
	ObjectRelationalMapper orm = new ObjectRelationalMapperImpl();
	Account userAccount = null;
	WebUtils gen = new WebUtils();
	
	final double DEFAULT_VALUE = 0.00; 

	@Override
	public Account depositIntoAccount(User customer, double amount) {
		try {
			userAccount = findById(customer.getUserId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
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
	

	@Override
	public Account withdraw(User customer, double amount) {
		try {
			userAccount = findById(customer.getUserId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
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
		try {
			userAccount = findById(customer.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		if (userAccount != null) {
			return userAccount.getBalance() ;
		}
		return -1.00; 
	}

	@Override
	public Account create(Account t) throws SQLException {
		return (Account) orm.create(t, "accounts");
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
