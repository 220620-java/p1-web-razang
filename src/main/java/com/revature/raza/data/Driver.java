package com.revature.raza.data;



import java.sql.Date;

import com.revature.raza.models.Customer;

public class Driver {

	public static void main(String[] args) {
		
		//
		// TODO Date object outputs the workng date
//		
		Customer user1 = new Customer(1, "Saba", 
				new Date(1997-04-15),
				"Saba@gmail.com", "1234567890", "123343"); 
		
		BankCustomer a = new BankCustomer(); 
//		a.createCustomer(user1); 
//		
//		Customer result = a.findByUsername("Saba"); 
//		
//		System.out .println(result.toString());
//		a.updateEmail(1, "sabah.sanah@gmail.com");
		a.deleteCustomer(user1);
		
	}

}
