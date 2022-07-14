package com.revature.razang.services;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;

public class BankDriver {
	
	private static Account ac = null; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User result = null; 
//		Customer user1 = new Customer(1, "Huda", 
//				new Date(2020-01-01),
//				"Huda@gmail.com", "10101010101", "123456789"); 
		
		AccountServiceImpl bs = new AccountServiceImpl(); 
//		Customer result = bs.signUp(user1); 
//		if (result != null) {
//			System.out.println("Signed up successfully");
//		}
		result = bs.signIn("Huda", "123456789"); 
		if (result != null) {
			System.out.println("Signed in successfully");
		}
		
		ac = bs.createAccount(result); 
		if (ac != null) {
			System.out.println("Account created successfully");
		}
		
//		ac = bs.deleteAccount(result); 
//		if (ac != null) { 
//			System.out.println("Account DELETED succesfully ");
//		}
//		ac = bs.depositeFund(result, 100); 
//		if (ac != null) {
//			System.out.println("Amount deposited");
//		}
		
		ac = bs.withdrawFromAccount(result, 100); 
		if (ac.getBalance() < 0) {
			System.out.println("Not enough balance");
		}else {
			System.out.println("Money withdrawn. Remaining balance is: " + ac.getBalance());
		}
		
		double balance = bs.viewBalance(result); 
		System.out.println("The current balance is: "  + balance ) ;
	}

}
