package com.revature.raza.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.raza.models.Customer;
import com.revature.raza.utility.ConnectionObject;

public class BankCustomer implements CustomerAccessObject<Customer> {

	private ConnectionObject connObj = ConnectionObject.getConnectionUtil();

	@Override
	public Customer createCustomer(Customer t) {
		// TODO Auto-generated method stub
		try (Connection conn = connObj.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "insert into Customer "
						+ "(customer_id, username, birthdate, email, phone, passwd)"
						+ "values"
						+ "	(default, ?, ?, ?, ?, ?)";
			
			//auto generated column
			String[] autoKeys = {"customer_id"}; 
			PreparedStatement st = conn.prepareStatement(sql, autoKeys); 
			
			st.setString(1, t.getUsername());
			st.setDate(2,t.getBirthDate());
			st.setString(3, t.getEmail());
			st.setString(4, t.getPhone());
			st.setString(5, t.getPasswd());
			
			int rowsAdded = st.executeUpdate(); 
			ResultSet result = st.getGeneratedKeys();
			
			if (result.next() && rowsAdded == 1) {
				t.setCustomer_id(result.getInt("customer_id"));
				conn.commit();
			}else {
				conn.rollback();
				return null; 
			}
			
		}catch (SQLException e) {
			t = null; 
			System.out.println(e.getMessage());
		}
		
		return t;
	}

	@Override
	public Customer findByUsername(String s) {
		// TODO Auto-generated method stub
		
		Customer customer = null; 
		try (Connection conn = connObj.getConnection()) {
			String sql = "select * from Customer  "
					+ "where "
					+ "username = ?"; 
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, s);
			
			ResultSet result = st.executeQuery(); 
			
			if (result.next()) {
				int customer_id = result.getInt("customer_id"); 
				String username = result.getString("username"); 
				Date birthDate = result.getDate("birthDate"); 
				String email = result.getString("email"); 
				String phone = result.getString("phone"); 
				String passwd = result.getString("passwd"); 
				
				customer = new Customer(customer_id, username, birthDate, email, phone, passwd); 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public boolean updateEmail(int id, String email) {
		// TODO Auto-generated method stub
		boolean isUpdated = false; 
		try (Connection conn = connObj.getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "update Customer "
					+ "set email=? " 
					+ "where customer_id=?";
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, email);
			st.setInt(2, id);
			
			int rowUpdated = st.executeUpdate(); 
			
			if (rowUpdated == 1) {
				isUpdated = true; 
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated; 
		
		
	}

	@Override
	public boolean deleteCustomer(Customer t) {
		// TODO Auto-generated method stub
		boolean isDeleted = false; 
		try (Connection conn = connObj.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "delete from Customer where "
					+ " customer_id = ?";
		
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, t.getCustomer_id());
			
			int rowDeleted = st.executeUpdate(); 
			if (rowDeleted == 1) {
				conn.commit();
				isDeleted = true; 
			}else {
				conn.rollback();
			}
			
		}catch (SQLException e) {
			e.getStackTrace(); 
		}
		return isDeleted;
	}

	

	
	
	

	
	

}
