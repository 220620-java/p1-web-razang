package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.models.User;

@ExtendWith(MockitoExtension.class)
class TestBankCustomer {

	@InjectMocks 
	private UserDAOImpl bankCustomer = new UserDAOImpl(); 
	
	@Mock
	private User customer;
	
	
	@Test
	public void testCreateCustomer() {
		User customer = new User(9, "Mubasher", new Date(2021-01-01),
				"mmm@gmail.com", "19021100110", "3322222"); 
		assertEquals(customer, bankCustomer.createUser(customer)); 
	}
	
	@Test
	public void testFindByUsername() {
		String username = "Huda"; 
		User customer = bankCustomer.findByUsername(username); 
		assertNotNull(customer); 
	}
	
	@Test
	public void testUpdateEmail() {
		User user = bankCustomer.updateUser(customer); 
		assertEquals(customer, user);
		
	}
	
	@Test
	public void testDeleteCustomer() {
		int mockId = 7; 
		Mockito.when(customer.getUserId()).thenReturn(mockId); 
		User user = bankCustomer.deleteUser(customer); 
		assertEquals(customer, user);
	}
}
