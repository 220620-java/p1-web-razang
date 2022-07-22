package com.revature.razang.exceptions;

/**
 * Exception that throws when the same account number already exists in the database!
 * @author Colby Tang
 */
public class AccountAlreadyExistsException extends ObjectAlreadyExistsException {
    
    public AccountAlreadyExistsException () {
        super("Account already exists in the database!");
    }

    public AccountAlreadyExistsException (String username) {
        super ("Account [" + username + "] already exists in the database! ");
    }
}
