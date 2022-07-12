package com.revature.raza.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class TestCustomer {
	
	Customer customer = new Customer(1, "RAZA", new Date(2000-01-01), "raza@gmail.copm", "123456789", "1234"); 

	@Test
	public void testGetName() {
		assertEquals("RAZA", customer.getUsername()); 
		assertNotEquals("Shan", customer.getUsername()); 
	}
	
	@Test
	public void testGetId() {
		assertEquals(1, customer.getCustomer_id()); 
		assertNotEquals(0, customer.getCustomer_id()); 
	}

}
