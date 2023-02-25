package exceptions;

import java.io.IOException;

/**
 * This exception is thrown when a user does not have the necessary permissions to perform an action.
 */
public class PermissionsDeniedException extends IOException {

    /**
     * Constructs a new PermissionsDeniedException with the specified detail message.
     * @param message the detail message
     */
    public PermissionsDeniedException(String message){
        super(message);
    }

    /**
     * Constructs a new PermissionsDeniedException with a default error message.
     */
    public PermissionsDeniedException(){
        super("Permission denied");
    }
}
