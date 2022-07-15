package com.revature.razang.driver;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.services.AccountServiceImpl;

public class BankApplication {
	private static AccountServiceImpl bankService = new AccountServiceImpl(); 
	
	private static Scanner scanner = new Scanner(System.in);
	private static boolean hasAccount = false; 
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to our Raza's Bank Application");
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
		boolean isActive = true; 
		User customer = null; 
		Account account = null; 
		
		List<User> accountHolders = new ArrayList<>(); 
		while (isActive) {
			if (customer == null) {
				System.out.println("Choose one of the following options \n"
						+ "1. Sign in\n"
						+ "2. Sign up\n"
						+ "3. Enter \\e for Exit");
				
				String input = scanner.nextLine(); 
				switch(input) {
				case "1": 
					customer = signIn(); 
					break; 
				case "2": 
					customer = signUp(); 
					break; 
				default: 
					isActive = false; 
					System.out.println("Thanks for visiting!");
				}
				
			}
			while (customer != null) {
				System.out.println("Choose one of the followings: \n ");	
				System.out.println("A. Create Account \n"
						+ "B. Deposite fund \n"
						+ "C. Withdraw fund \n"
						+ "D. View balance \n"
						+ "E. Delete your acccount\n"
						+ "F. View Account Holders\n"
						+ "G. To exit\n"); 
				String input = scanner.nextLine().toUpperCase(); 
				
				switch(input) {
				case "A": 
					account = createAccount(customer); 
					break; 
				case "B": 
					account = depositeFund(customer); 
					break; 
				case "C": 
					account = withdrawFund(customer); 
					break; 
				case "D": 
					double balance = viewBalance(customer);
					break; 
				case "E": 
					account = deleteAccount(customer); 
					break; 
				case "F": 
						// accountHolders();
						break; 
				case "G": 
						System.out.println("signing out...");
						customer = null; 
						account = null; 
				default: 
						System.out.println("Leaving.... Bye bye\n");
						customer = null; 
						account = null; 
				}
			}
		
		}//outer while loop
		scanner.close();
	}


	// private static void accountHolders() {
	// 	// TODO Auto-generated method stub
	// 	List<User> accountHolders = bankService.viewAccountHolders(); 
	// 	System.out.println("We are pround of our account holders: \n");
	// 	User customer = new User(); 
	// 	for (int i = 0; i < accountHolders.size(); i++) {
	// 		customer = accountHolders.get(i); 
	// 		System.out.println(customer.toString());
	// 	}
	// 	System.out.println("End\n");
		
	// }
		
		


	private static User signIn() {
		boolean isSigning = true; 
		
		while (isSigning) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine(); 
			System.out.println("Enter your password");
			String passwd = scanner.nextLine(); 
			User customer = bankService.signIn(username, passwd); 
			
			if (customer == null) {
				System.out.println("No matching user. \n"
						+ "Try again? (y/n");
				String input = scanner.nextLine().toLowerCase(); 
				if(!input.equals("y")) {
					isSigning = false; 
				}
			}else {
				return customer; 
			}
		}
		return null; 
	}

	private static User signUp() throws ParseException {
		boolean isSigningUp = true; 
		User customer = null; 
		while (isSigningUp) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine(); 
			System.out.println("Enter your birth date (yyyy-mm-dd): ");
			String date = scanner.nextLine(); 
			Date birthDate = Date.valueOf(date);
			System.out.println("Enter your email: ");
			String email = scanner.nextLine(); 
			System.out.println("Enter your phone number: ");
			String phone = scanner.nextLine(); 
			System.out.println("Enter your password: ");
			String passwd = scanner.nextLine(); 
			
			customer = new User(1, username, birthDate, email, phone, passwd); 
			customer = bankService.signUp(customer); 
			
			if(customer == null) {
				System.out.println("Customer already exists. \n"
						+ "Try again? (y/n)");
				String input = scanner.nextLine().toLowerCase(); 
				if ("n".equals(input)) {
					isSigningUp = false; 
					return customer; 
				} 
			} else {
				System.out.println("Created successfully!");
				isSigningUp = false; 
			}
		}
		return customer; 
		
	}

	private static Account createAccount(User customer) {
		// TODO Auto-generated method stub
		Account account = null; 
		account = bankService.createAccount(customer); 
		if (account != null) {
			hasAccount = true; 
			System.out.println("Account created successfully \n");
			
		}
		return account;
	}
	
	private static Account depositeFund(User customer) {
		// TODO Auto-generated method stub
		if (!hasAccount) {
			System.out.println("You do not have an account.Please create one");
			return null; 
		}
		Account account = null; 
		System.out.println("Enter the amount: ");
		String input = scanner.nextLine(); 
		
		double amount = Double.parseDouble(input); 
		account = bankService.depositIntoAccount(customer, amount); 
		if (account != null) {
			System.out.println("$" + amount + "is added to your account ");
			System.out.println("You new balance is: " + "$" + account.getBalance());
		}else {
			System.out.println("We encountered an issue with the deposite. Try later");
			return null; 
		}
		return account;
	}

	private static Account withdrawFund(User customer) {
		
		if (!hasAccount) {
			System.out.println("You do not have an account.Please create one");
			return null; 
		}
		
		Account account = null; 
		System.out.println("Enter the amount: ");
		String input = scanner.nextLine(); 
		double amount = Double.parseDouble(input); 
		
		account = bankService.withdrawFromAccount(customer, amount); 
		if (account != null) {
			System.out.println("$" + amount + " is withdrawn from the account");
			System.out.println("Current balance is: " + account.getBalance());
		}else {
			System.out.println("You do not have sufficient fund");
		}
		return account;
		
	}
	
	private static double viewBalance(User customer) {
		// TODO Auto-generated method stub
		if (!hasAccount) {
			System.out.println("You do not have account.Please create one");
			return -1.00; 
		}
		double balance = bankService.viewBalance(customer);
		System.out.println("Your current balance is: " + balance);
		return balance; 
	}

	private static Account deleteAccount(User customer) {
		// TODO Auto-generated method stub
		if(!hasAccount) {
			System.out.println("You do not have an account to delete. "
					+ "Please create one first");
			return null; 
		}
		Account account = null; 
		account = bankService.deleteAccount(customer); 
		if (account != null) {
			hasAccount = false; 
			System.out.println("Account deleted successfully!");
		}
		return account;
	}

	

	
	





















}







