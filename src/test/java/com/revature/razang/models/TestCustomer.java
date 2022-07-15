package com.revature.razang.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.revature.razang.models.User;

class TestCustomer {
	
	User customer = new User(1, "RAZA", new Date(2000-01-01), "raza@gmail.copm", "123456789", "1234"); 

	@Test
	public void testGetName() {
		assertEquals("RAZA", customer.getUsername()); 
		assertNotEquals("Shan", customer.getUsername()); 
	}
	
	@Test
	public void testGetId() {
		assertEquals(1, customer.getUserId()); 
		assertNotEquals(0, customer.getUserId()); 
	}

}
