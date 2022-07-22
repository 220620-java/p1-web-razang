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
import com.revature.razang.exceptions.AccountAlreadyExistsException;
import com.revature.razang.exceptions.NegativeBalanceException;
import com.revature.razang.exceptions.RecordNotFound;
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

		Mockito.when(customer.getUserId()).thenReturn(mockId); 
		Mockito.when(WebUtils.generateRandomAccountNumber()).thenReturn(mockAccountNumber);
		Account testAccount = new Account(2030401050111L, "CHECKING", 0.00);
		Account myAccount;
		try {
			myAccount = account.create(testAccount);
			assertNotNull(myAccount);
		} catch (SQLException | AccountAlreadyExistsException e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void testDelete() {
		int mockId = 2; 
		Mockito.when(customer.getUserId()).thenReturn(mockId);
		Account testAccount = new Account(2030401050111L, "CHECKING", 0.00);
		Account myAccount;
		try {
			myAccount = account.delete(testAccount);
			assertNotNull(myAccount);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}  
	}
	
	@Test
	public void testFindById() {
		Account testAccount = new Account(2, "CHECKING", 0.00);
		Account myAccount = account.findById(testAccount);
		assertNotNull(myAccount);
		Account nullAccount = new Account(0, "CHECKING", 0.00);
		myAccount = account.findById(nullAccount); 
		assertNull(myAccount); 
	}
	
	@Test
	public void testDeposit() {
		int mockId = 2; 
		
		Account testAccount = new Account(2030401050111L, "CHECKING", 0.00); 
		Mockito.when(customer.getUserId()).thenReturn(mockId); 
		try {
			account.depositIntoAccount(testAccount, 100);
			assertEquals(100.00, account.getBalance(testAccount));
		} catch (RecordNotFound e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testWithdraw() {
		int mockId = 2; 
		double amount = 100.00; 
		Account testAccount = new Account(2030401050111L, "CHECKING", amount); 
		Mockito.when(customer.getUserId()).thenReturn(mockId);
		try {
			account.withdrawAccount(testAccount, 100);
			assertEquals(0.00, account.getBalance(testAccount));
		} catch (RecordNotFound | NegativeBalanceException e) {
			e.printStackTrace();
		} 
	
	}
	
	@Test
	public void testBalance() {
		int mockId = 2; 
		Mockito.when(customer.getUserId()).thenReturn(mockId);
		Account testAccount = new Account(2030401050111L, "CHECKING", 100.00); 
		try {
			double balance = account.getBalance(testAccount);
			assertEquals(100.0, balance);
		} catch (RecordNotFound e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNegativeWithdraw () {
		Account testAccount = new Account(2030401050111L, "CHECKING", 0.00);
		assertThrows (NegativeBalanceException.class, () -> account.withdrawAccount(testAccount, -100.00));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
