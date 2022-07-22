package com.revature.razang.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class TestCustomer {
	
	User user = new User(1, "RAZA", new Date(2000-01-01), "raza@gmail.copm", "123456789", "1234"); 

	@Test
	public void testGetName() {
		assertEquals("RAZA", user.getUsername()); 
		assertNotEquals("Shan", user.getUsername()); 
	}
	
	@Test
	public void testGetId() {
		assertEquals(1, user.getUserId()); 
		assertNotEquals(0, user.getUserId()); 
	}

	@Test
	public void passwordEncrypted () {
		String password = "1234";
		User newUser =  new User(2, "ctang", new Date(2000-01-01), "colby@email.com", "123456789", password);
		assertNotEquals(password, newUser.getPassword());
		System.out.println("Password=" + user.getPassword() + " Salt=" + user.getSalt());
	}

}
