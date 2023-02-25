package exceptions;

import support.Console;

import java.io.IOException;

/**
 * This exception is thrown when the wrong number of arguments is provided to a method or function.
 */
public class WrongArgumentsException extends IOException {
    /**
     * Constructs a new WrongArgumentsException with the specified detail message.
     *
     * @param message the detail message
     */
    public WrongArgumentsException(String message) {
        super(message);
    }

    /**
     * Constructs a new WrongArgumentsException with a default error message.
     * This constructor also prints an error message to the console using the Console.err() method.
     */
    public WrongArgumentsException() {
        Console.err("Неверное кол-во аргементов");
    }
}
