package com.revature.raza.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.raza.models.Account;
import com.revature.raza.models.Customer;
import com.revature.raza.utility.Generator;

@ExtendWith(MockitoExtension.class)
class TestCustomerAccount {
	
	// Object under test
	@InjectMocks
	private CustomerAccount account = new CustomerAccount(); 
	
	@Mock
	private Customer customer; 
	
	@Mock
	private Generator generateNum; 
	
	@Mock
	private CustomerAccount ac; 
	
	@Mock
	private Account mockAccount; 
	

	@SuppressWarnings("static-access")
	@Test
	public void testCreate() {
		//setup
		int mockId = 2; 
		String mockAccountNumber = "2030401050111"; 
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Mockito.when(generateNum.randomGerenator()).thenReturn(mockAccountNumber); 
		
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
		
		Account testAccount = new Account("2030401050111", "Checking", 0.00); 
		Mockito.when(customer.getCustomer_id()).thenReturn(mockId); 
		Account myAccount = account.deposite(customer, 100); 
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
