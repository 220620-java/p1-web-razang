package com.revature.raza.services;

import com.revature.raza.models.Customer;

public interface Service {
	
	
	public Customer signUp(Customer customer);
	
	public Customer signIn(String username, String passwd); 
	
	

}
