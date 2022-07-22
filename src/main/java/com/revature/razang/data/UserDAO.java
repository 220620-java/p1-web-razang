package com.revature.razang.data;

import com.revature.razang.models.User;

public interface UserDAO extends DataAccessObject<User> {
	
	/**
	 * Reads object
	 * Lookup the object in the data source using the given string. 
	 * @param s string passed by the caller and the source of search
	 * @return object of type T if found else, null. 
	 */
	public User findByUsername(User user);

	/**
	 * Validates the password from the user object and from the user input.
	 * Encrypts the inputted password with the user's salt and tests if they're equal.
	 * @param user - the user from the database
	 * @param password - the password to check against
	 * @return true if passwords match, otherwise false.
	 * @author Colby Tang
	 */
    public boolean validatePassword(User user, String password);

}
