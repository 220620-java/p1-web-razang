package com.revature.razang.exceptions;

/**
 * Exception that throws when balance would go negative.
 * @author Colby Tang
 */
public class NegativeBalanceException extends Exception {

    public NegativeBalanceException () {}

    public NegativeBalanceException (String message) {
        super(message);
    }

}
