package com.revature.razang.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.services.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestBankServiceImpl {
	
	//Object under test
	@InjectMocks
	private AccountServiceImpl bankService = new AccountServiceImpl(); 
	
	//Dependencies to mock
	@Mock
	private UserDAOImpl bankCustomer; 
	
	
	@Mock
	private AccountDAOImpl account; 
	
	@Mock
	private User customer; 
 
	@Test
	public void testSignUp() {
		
		Mockito.when(bankCustomer.createUser(customer)).
		thenReturn(customer); 
		User c = bankService.signUp(customer); 
		assertNotNull(c); 
	}
	
	@Test
	public void testSignIn() {
		String username = "Saba"; 
		String passwd = "abcabcabc"; 
		User mockCustomer  = new User(1,"Saba",
				new Date(2000-01-01), "abc@gmail.com", "2012222222"
				, "abcabcabc"); 
		Mockito.when(bankCustomer.findByUsername(username)).
		thenReturn(mockCustomer); 
		User c = bankService.signIn(username, passwd); 
		assertNotNull(c); 
	}
	
	@Test
	public void testCreateAccount() {
		Account mockAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 100.0); 
		Mockito.when(account.create(customer)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.createAccount(customer);
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testDeleteAccount() {
		Account mockAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 100.0); 
		Mockito.when(account.delete(customer)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.deleteAccount(customer);
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testDepositeFund() {
		Account mockAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 200.0); 
		Mockito.when(account.depositIntoAccount(customer, 100)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.depositIntoAccount(customer, 100); 
		assertNotNull(returnAccount); 
	}
	
	@Test 
	public void testWithdrawFund() {
		Account mockAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 100.0); 
		Mockito.when(account.withdraw(customer, 100)).thenReturn(mockAccount); 
		
		Account returnAccount = bankService.withdrawFromAccount(customer, 100); 
		assertNotNull(returnAccount); 
	}
	
	@Test
	public void testViewBalance() {
		
		Mockito.when(account.balance(customer)).thenReturn(100.0); 
		double returnedBalance = bankService.viewBalance(customer); 
		assertTrue(returnedBalance > 0); 
	}
	
	

}
