package com.revature.raza.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAccount {

	Account account = new Account("102030405060", "Checking", 0.00); 
	
	
	@Test
	public void testGetAccount() {
		assertEquals("102030405060", account.getAccount_no()); 
		assertNotEquals("00000000000", account.getAccount_no()); 
	}

}
