package exceptions;

import java.io.IOException;

/**
 * This exception is thrown when the user provides empty input where it is not allowed.
 */
public class EmptyInputException extends IOException {

    /**
     * Constructs a new EmptyInputException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyInputException(String message) {
        super(message);
    }

    /**
     * Constructs a new EmptyInputException with a default error message.
     */
    public EmptyInputException() {
        super("Input cannot be empty!");
    }
}
