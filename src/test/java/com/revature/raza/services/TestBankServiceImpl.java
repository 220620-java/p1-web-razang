package com.revature.raza.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.raza.data.BankCustomer;
import com.revature.raza.data.CustomerAccount;
import com.revature.raza.models.Account;
import com.revature.raza.models.Customer;

@ExtendWith(MockitoExtension.class)
class TestBankServiceImpl {
	
	//Object under test
	@InjectMocks
	private BankServiceImpl bankService = new BankServiceImpl(); 
	
	//Dependencies to mock
	@Mock
	private BankCustomer bankCustomer; 
	
	
	@Mock
	private CustomerAccount account; 
	
	@Mock
	private Customer customer; 
 
	@Test
	public void testSignUp() {
		
		Mockito.when(bankCustomer.createCustomer(customer)).
		thenReturn(customer); 
		Customer c = bankService.signUp(customer); 
		assertNotNull(c); 
	}
	
	@Test
	public void testSignIn() {
		String username = "Saba"; 
		String passwd = "abcabcabc"; 
		Customer mockCustomer  = new Customer(1,"Saba",
				new Date(2000-01-01), "abc@gmail.com", "2012222222"
				, "abcabcabc"); 
		Mockito.when(bankCustomer.findByUsername(username)).
		thenReturn(mockCustomer); 
		Customer c = bankService.signIn(username, passwd); 
		assertNotNull(c); 
	}
	
	@Test
	public void testCreateAccount() {
		Account mockAccount = new Account("2030401050111", "Checking", 100.0); 
		Mockito.when(account.create(customer)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.createAccount(customer);
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testDeleteAccount() {
		Account mockAccount = new Account("2030401050111", "Checking", 100.0); 
		Mockito.when(account.delete(customer)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.deleteAccount(customer);
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testDepositeFund() {
		Account mockAccount = new Account("2030401050111", "Checking", 200.0); 
		Mockito.when(account.deposite(customer, 100)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.depositeFund(customer, 100); 
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testWithdrawFund() {
		Account mockAccount = new Account("2030401050111", "Checking", 100.0); 
		Mockito.when(account.withdraw(customer, 100)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.withdrawFund(customer, 100); 
		assertNotNull(returnAccount); 
	}
	
	@Test
	public void testViewBalance() {
		
		Mockito.when(account.balance(customer)).thenReturn(100.0); 
		double returnedBalance = bankService.viewBalance(customer); 
		assertTrue(returnedBalance > 0); 
	}
	
	

}
