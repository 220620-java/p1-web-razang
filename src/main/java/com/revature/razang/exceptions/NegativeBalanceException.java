package com.revature.razang.exceptions;

// because this extends Exception (rather than RuntimeException),
// it is a checked exception, meaning that we have to handle it
// using a try-catch or throws.
public class NegativeBalanceException extends Exception {

    public NegativeBalanceException () {}

    public NegativeBalanceException (String message) {
        super(message);
    }

}
