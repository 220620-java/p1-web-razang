package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.models.User;

@ExtendWith(MockitoExtension.class)
class UserDataTest {

	@InjectMocks 
	private UserDAO userDAO = new UserDAOImpl(); 
	
	@Mock
	private User customer;
	
	
	@Test
	public void testCreateCustomer() {
		System.out.println("Create Customer");
		User customer = new User(9, "Mubasher", new Date(2021-01-01),
				"mmm@gmail.com", "19021100110", "3322222");
		User createdCustomer;
		try {
			createdCustomer = userDAO.create(customer);
			assertEquals(customer, createdCustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByUsername() {
		String username = "Huda"; 
		User customer = userDAO.findByUsername(username); 
		assertNotNull(customer); 
	}
	
	@Test
	public void testUpdateEmail() {
		User customer = new User(10, "newUser", new Date(2021-01-01),
				"user@gmail.com", "19021100110", "3322222");
		userDAO.update(customer);
	}
	
	@Test
	public void testDeleteCustomer() {
		int mockId = 7; 
		Mockito.when(customer.getUserId()).thenReturn(mockId); 
		userDAO.delete(customer);
		// assertEquals(customer, user);
	}

	@Test
	public void getAllUsers () {
		List<User> allUsers = userDAO.findAll();
		assertNotNull(allUsers);
	}
}
