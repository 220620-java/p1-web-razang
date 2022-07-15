package com.revature.razang.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.razang.models.User;
import com.revature.razang.utility.BankUtils;
import com.revature.razang.utility.ConnectionObject;

public class UserDAOImpl implements UserDAO {

	private ConnectionObject connObj = ConnectionObject.getConnectionUtil();
	List<User> customers = new ArrayList<>();

	@Override
	public User createUser(User t) {
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
				t.setUserId(result.getInt("customer_id"));
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
	public User findByUsername(String s) {
		// TODO Auto-generated method stub
		
		User customer = null; 
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
				
				customer = new User(customer_id, username, birthDate, email, phone, passwd); 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public User updateUser(User user) {
		boolean isUpdated = false; 
		try (Connection conn = connObj.getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "update Customer "
					+ "set email=? " 
					+ "where customer_id=?";
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, user.getEmail());
			st.setInt(2, user.getUserId());
			
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
		return user;
	}

	@Override
	public User deleteUser(User user) {
		try (Connection conn = connObj.getConnection()) {
			
			conn.setAutoCommit(false);
			String sql = "delete from Customer where "
					+ " customer_id = ?";
		
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, user.getUserId());
			
			int rowDeleted = st.executeUpdate(); 
			if (rowDeleted == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch (SQLException e) {
			e.getStackTrace(); 
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		try (Connection conn = connObj.getConnection()) {
			
			String sql = "Select * from customer"; 
			Statement st = conn.createStatement(); 
			ResultSet result = st.executeQuery(sql); 
			
			while(result.next()) {
				int customer_id = result.getInt("customer_id"); 
				String username = result.getString("usenrame"); 
				Date birthDate = result.getDate("birthDate"); 
				String email = result.getString("email"); 
				String phone = result.getString("phone");
				String passwd = result.getString("passwd"); 
				
				User customer = new User(customer_id, username, birthDate, email, phone, passwd); 
				customers.add(customer); 
			}
			
		}catch(SQLException e) {
			e.getMessage(); 
			return null; 
			
		}
		
		return customers;
	}

	@Override
	public User create(User t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validatePassword(String username, String password) {
		String dbPass = "pass";
		byte[] salt = {12, 16};
		String ePass = BankUtils.generateEncryptedPassword(password, salt);

		if (!ePass.equals(dbPass)) {
			System.out.println("Passwords do not match! ");
			return false;
		}
		return ePass.equals(dbPass);
	}
	
	

	
 
	
	
	

	
	

}
