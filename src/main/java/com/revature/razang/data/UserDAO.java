package com.revature.razang.data;

import java.util.List;

import com.revature.razang.models.User;

public interface UserDAO extends DataAccessObject<User> {
	
	/**
	 * Create object
	 * Takes an object of type T and stores it in the data source
	 * @param t the object supplied by the caller
	 * @return
	 */
	// public User createUser(User user); 
	
	/**
	 * Reads object
	 * Lookup the object in the data source using the given string. 
	 * @param s string passed by the caller and the source of search
	 * @return object of type T if found else, null. 
	 */
	// public User findByUsername(String s); 
	
	public User findByUsername(User user);
	
	/**
	 * updates object
	 * Update the object in the data source if id matches. 
	 * @param t object of type T that needs update
	 */
	// public User updateUser(User user); 
	
	/**
	 * Deletes the given record in the data source 
	 * @param t object that needs to be removed. 
	 * @return deleted object if existed else null. 
	 */
	// public User deleteUser (User user); 
	
	/**
	 * Gets all the users
	 * @return A list of all the users.
	 */
	// public List<User> getAllUsers(); 
	
    public boolean validatePassword(User user, String password);

}
