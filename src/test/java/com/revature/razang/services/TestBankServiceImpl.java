package com.revature.razang.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.services.AccountServiceImpl;

@Disabled
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
		
		// Mockito.when(bankCustomer.createUser(customer)).
		// thenReturn(customer); 
		// User c = bankService.signUp(customer); 
		// assertNotNull(c); 
	}
	
	@Test
	public void testSignIn() {
		// String username = "Saba"; 
		// String passwd = "abcabcabc"; 
		// User mockCustomer  = new User(1,"Saba",
		// 		new Date(2000-01-01), "abc@gmail.com", "2012222222"
		// 		, "abcabcabc"); 
		// Mockito.when(bankCustomer.findByUsername(username)).
		// thenReturn(mockCustomer); 
		// User c = bankService.signIn(username, passwd); 
		// assertNotNull(c); 
	}
	
	@Test
	public void testCreateAccount() {
		Account mockAccount = new Account(2030401050111L, "CHECKING", 100.0); 
		try {
			Mockito.when(account.create(mockAccount)).thenReturn(mockAccount);
		} catch (AccountAlreadyExistsException | SQLException e1) {
			e1.printStackTrace();
		} 
		
		try {
			Account returnAccount;
			returnAccount = bankService.createAccount(mockAccount);
			assertNotNull(returnAccount); 
		} catch (AccountAlreadyExistsException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testDeleteAccount() {
		Account mockAccount = new Account(2030401050111L, "CHECKING", 100.0); 
		
		try {
			bankService.deleteAccount(mockAccount);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testDepositFund() {
		Account mockAccount = new Account(2030401050111L, "CHECKING", 200.0); 
		
		try {
			bankService.depositIntoAccount(mockAccount, 100);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		} 
		assertEquals(mockAccount.getBalance(), 100); 
	}
	
	@Test 
	public void testWithdrawFund() {
		Account mockAccount = new Account(2030401050111L, "CHECKING", 100.0); 
		
		try {
			bankService.withdrawFromAccount(mockAccount, 100);
		} catch (RecordNotFound | NegativeBalanceException e) {
			e.printStackTrace();
		} 
		assertNotNull(mockAccount);
	}
	
	@Test
	public void testViewBalance() {
		Account mockAccount = new Account(2030401050111L, "CHECKING", 100.0); 
		Mockito.when(mockAccount.getBalance()).thenReturn(100.0); 
		double returnedBalance;
		try {
			returnedBalance = bankService.getBalance(mockAccount);
			assertTrue(returnedBalance > 0); 
		} catch (RecordNotFound e) {
			e.printStackTrace();
		} 
	}
	
	

}
