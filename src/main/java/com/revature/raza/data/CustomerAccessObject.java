package com.revature.raza.data;

import java.util.List;

import com.revature.raza.models.Customer;


public interface CustomerAccessObject<T> {
	
	/**
	 * Create object
	 * Takes an object of type T and stores it in the data source
	 * @param t the object supplied by the caller
	 * @return
	 */
	public T createCustomer(T t); 
	
	/**
	 * Reads object
	 * Lookup the object in the data source using the given string. 
	 * @param s string passed by the caller and the source of search
	 * @return object of type T if found else, null. 
	 */
	public T findByUsername(String s); 
	
	
	/**
	 * updates object
	 * Update the object in the data source if id matches. 
	 * @param t object of type T that needs update
	 */
	public boolean updateEmail(int i, String t); 
	
	
	/**
	 * Deletes the given record in the data source 
	 * @param t object that needs to be removed. 
	 * @return deleted object if existed else null. 
	 */
	public boolean deleteCustomer (T t); 
	
	
	public List<T> getAllCustomers(); 
	

}
