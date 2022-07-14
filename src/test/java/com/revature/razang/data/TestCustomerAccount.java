package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.utility.BankUtils;

@ExtendWith(MockitoExtension.class)
class TestCustomerAccount {
	
	// Object under test
	@InjectMocks
	private AccountDAOImpl account = new AccountDAOImpl(); 
	
	@Mock
	private User customer; 
	
	@Mock
	private BankUtils generateNum; 
	
	@Mock
	private AccountDAOImpl ac; 
	
	@Mock
	private Account mockAccount; 
	

	@Test
	public void testCreate() {
		//setup
		int mockId = 2; 
		long mockAccountNumber = 2030401050111L;
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Mockito.when(BankUtils.generateRandomAccountNumber()).thenReturn(mockAccountNumber);
		
		Account myAccount = account.create(customer); 
		
		assertNotNull(myAccount); 
	}
//
	@Test
	public void testDelete() {
		int mockId = 2; 
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Account myAccount = account.delete(customer);  
		assertNotNull(myAccount); 
		
	}
	
	@Test
	public void testFindById() {
		Account myAccount = account.findById(2);
		assertNotNull(myAccount); 
		myAccount = account.findById(0); 
		assertNull(myAccount); 
	}
	
	@Test
	public void testDeposite() {
		int mockId = 2; 
		
		Account testAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 0.00); 
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Account myAccount = account.depositIntoAccount(customer, 100); 
		assertNotNull(myAccount); 
		
	}
	
	@Test
	public void testWithdraw() {
		int mockId = 2; 
		double amount = 100.00; 
		
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Account myAccount = account.withdraw(customer, amount); 
		assertNotNull(myAccount); 
	
	}
	
	@Test
	public void testBalance() {
		int mockId = 2; 
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		double balance = account.balance(customer); 
		
		assertEquals(100.0, balance);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
