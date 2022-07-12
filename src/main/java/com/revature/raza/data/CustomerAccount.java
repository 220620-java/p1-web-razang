package com.revature.raza.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.raza.ds.ArrayList;
import com.revature.raza.ds.List;
import com.revature.raza.models.Account;
import com.revature.raza.models.Customer;
import com.revature.raza.utility.ConnectionObject;
import com.revature.raza.utility.Generator;

public class CustomerAccount implements AccountAccessObject<Account>{

	private ConnectionObject connObj = ConnectionObject.getConnectionUtil();
	Account userAccount = null;
	Generator gen = new Generator(); 

	@Override
	public Account create(Customer account) {
		// TODO Auto-generated method stub
		int customer_id = account.getCustomer_id();
		String account_no = gen.randomGerenator(); 
		double defaultVal = 0.00; 
		
		
		try (Connection conn =  connObj.getConnection()) {
			
			conn.setAutoCommit(false);

			String sql = "insert into BankAccount "
					+ "(account_no, balance, customer_id) "
					+ "values (?, ?, ?)"; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, account_no);
			st.setDouble(2, 0.00);
			st.setInt(3, customer_id);
			
			int rowAdded = st.executeUpdate(); 
			if (rowAdded == 1) {
				userAccount = new Account(account_no, "Checking", defaultVal); 
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
	public Account delete(Customer account) {
		// TODO Auto-generated method stub
		try (Connection conn = connObj.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "delete from bankaccount "
					+ "where customer_id=? "; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, account.getCustomer_id());
			
			userAccount = findById(account.getCustomer_id()); 
			
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
	public Account findById(int account) {

		// TODO Auto-generated method stub
		userAccount = null; 
		try (Connection conn = connObj.getConnection()){
			
			String sql = "select * from bankaccount "
					+ "where bankaccount.customer_id =?"; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setInt(1, account);
			
			ResultSet result = st.executeQuery();
			
			if (result.next()) {
				userAccount =
						new Account(result.getString("account_no"),
								"Checking" ,result.getDouble("balance")); 
				
			}
		}catch(SQLException e) {
			e.getMessage(); 
		}
		
		return userAccount;
	}

	@Override
	public Account deposite(Customer customer, double amount) {
		// TODO Auto-generated method stub
		userAccount = findById(customer.getCustomer_id()); 
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
			st.setInt(2, customer.getCustomer_id());
			
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
	
	public List<Customer> displayAccountHolders() {
		
		Customer customer = null; 
		List<Customer> allAccountHolders = new ArrayList<>();
		
		
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
				
				customer = new Customer(customer_id, username, birthDate, email, phone, passwd); 
				allAccountHolders.add(customer);	
			}
			
			
		}catch (SQLException e) {
			e.getMessage(); 
		}
		return allAccountHolders;
		
	}

	@Override
	public Account withdraw(Customer customer, double amount) {
		// TODO Auto-generated method stub
		userAccount = findById(customer.getCustomer_id()); 
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
			st.setInt(2, customer.getCustomer_id());
			
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
	public double balance(Customer customer) {
		// TODO Auto-generated method stub
		userAccount = findById(customer.getCustomer_id()); 
		if (userAccount != null) {
			return userAccount.getBalance() ;
		}
		return -1.00; 
	}
	
	

}
