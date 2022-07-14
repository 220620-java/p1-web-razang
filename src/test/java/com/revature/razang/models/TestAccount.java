package com.revature.razang.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.razang.models.Account;
import com.revature.razang.models.Account.AccountType;

class TestAccount {

	Account account = new Account(102030405060L, AccountType.CHECKING, 0.00); 
	
	
	@Test
	public void testGetAccount() {
		assertEquals(102030405060L, account.getAccountNumber()); 
		assertNotEquals(00000000000L, account.getAccountNumber()); 
	}

}
