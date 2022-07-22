package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.data.AccountDAOImpl;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.utilities.WebUtils;

@Disabled("Disabled until I mock everything")
@ExtendWith(MockitoExtension.class)
class AccountDataTest {
	
	// Object under test
	@InjectMocks
	private AccountDAOImpl account = new AccountDAOImpl(); 
	
	@Mock	
	private User customer; 
	
	@Mock
	private WebUtils generateNum; 
	
	@Mock
	private AccountDAOImpl ac; 
	
	@Mock
	private Account mockAccount; 
	

	@Test
	public void testCreate() {
		//setup
		int mockId = 2; 
		long mockAccountNumber = 2030401050111L;
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// Mockito.when(WebUtils.generateRandomAccountNumber()).thenReturn(mockAccountNumber);
		Account testAccount = new Account(2030401050111L, "CHECKING", 0.00); 
		Account myAccount;
		try {
			myAccount = account.create(testAccount);
			assertNotNull(myAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
//
	@Test
	public void testDelete() {
		// int mockId = 2; 
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// Account testAccount = new Account(2030401050111L, "CHECKING", 0.00); 
		// Account myAccount;
		// try {
		// 	account.delete(testAccount);
		// 	// assertNotNull(myAccount);
		// } catch (SQLException e) {
		// 	e.printStackTrace();
		// } 
		
	}
	
	@Test
	public void testFindById() {
		// Account myAccount = account.findById(2);
		// assertNotNull(myAccount); 
		// myAccount = account.findById(0); 
		// assertNull(myAccount); 
	}
	
	@Test
	public void testDeposite() {
		// int mockId = 2; 
		
		// Account testAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 0.00); 
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// Account myAccount = account.depositIntoAccount(customer, 100); 
		// assertNotNull(myAccount); 
		
	}
	
	@Test
	public void testWithdraw() {
		// int mockId = 2; 
		// double amount = 100.00; 
		
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// Account myAccount = account.withdraw(customer, amount); 
		// assertNotNull(myAccount); 
	
	}
	
	@Test
	public void testBalance() {
		// int mockId = 2; 
		// Mockito.when(customer.getUserId()).thenReturn(mockId); 
		// double balance = account.balance(customer); 
		
		// assertEquals(100.0, balance);
	}
	
	@Test
	public void testNegativeWithdraw () {
		// Account testAccount = new Account(2030401050111L, Account.AccountType.CHECKING, 0.00); 
		// accountDAO
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
