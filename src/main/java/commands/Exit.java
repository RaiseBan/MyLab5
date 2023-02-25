package commands;

import exceptions.WrongArgumentsException;
import support.Console;

/**

 The Exit class extends the AbstractCommand class and represents a command that terminates the program.

 It inherits the command name and description from the parent class.
 */
public class Exit extends AbstractCommand {

    /**

     Constructor for Exit class. It calls the constructor of the parent AbstractCommand class to set the command name and description.
     */
    public Exit(){
        super("exit", "прекращает работу программы");
    }
    /**

     Executes the command to terminate the program.
     @param argument The argument for the command (empty string expected).
     @throws WrongArgumentsException if the argument is not empty.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentsException();
            Console.write("terminating the program");
            System.exit(0);
        } catch (WrongArgumentsException e){
            Console.err("exceeded number of arguments");
        }
    }
}