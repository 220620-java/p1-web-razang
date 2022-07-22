package com.revature.razang.exceptions;

// because this extends Exception (rather than RuntimeException),
// it is a checked exception, meaning that we have to handle it
// using a try-catch or throws.
public class RecordNotFound extends Exception {

    public RecordNotFound () {
        super("Could not find record in the database!");
    }

    public RecordNotFound (Object object) {
        super("Could not find record " + object.getClass().getName() + " in the database!");
    }

    public RecordNotFound (String message) {
        super(message);
    }

}
