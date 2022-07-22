package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.revature.razang.exceptions.ObjectAlreadyExistsException;
import com.revature.razang.exceptions.RecordNotFound;

import com.revature.razang.models.User;

@Disabled("Disabled until I mock everything")
@ExtendWith(MockitoExtension.class)
class UserDataTest {

	@InjectMocks 
	private UserDAO userDAO = new UserDAOImpl(); 
	
	@Mock
	private User customer;
	
	@Test
	public void testCreateCustomer() {
		System.out.println("Create Customer");
		User user = new User(3, "ctang2", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		User createdCustomer;
		try {
			createdCustomer = userDAO.create(user);
			assertEquals(user, createdCustomer);
		} catch (SQLException | ObjectAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByUsername() {
		String username = "ctang";
		User user = new User();
		user.setUsername(username);
		User customer = userDAO.findByUsername(user); 
		assertNotNull(customer); 
	}
	
	@Test
	public void testUpdate() {
		User user = new User(3, "ctangUPDATE", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		try {
			userDAO.update(user);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCustomer() {
		// int mockId = 7; 
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// userDAO.delete(customer);
		// assertEquals(customer, user);
		User user = new User(3, "ctangUPDATE", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		try {
			userDAO.delete(user);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllUsers () {
		List<User> allUsers = userDAO.findAll();
		allUsers.stream().forEach(user -> System.out.println(user));
		assertNotNull(allUsers);
	}
}
