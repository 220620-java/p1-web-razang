package com.revature.razang.exceptions;

/**
 * Exception for when an object already exists in the database!
 * Used for inheriting for other unique database entry exceptions.
 * @author Colby Tang
 */
public abstract class ObjectAlreadyExistsException extends Exception {
    public ObjectAlreadyExistsException () {
        super ("Object already exists!");
    }

    public ObjectAlreadyExistsException (String message) {
        super (message);
    }

    public ObjectAlreadyExistsException (Object obj, String message) {
        super (message + ": " + obj.getClass().getName());
    }
}
