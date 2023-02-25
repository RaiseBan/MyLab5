/**
 * This class is responsible for interaction with the user via console.
 * It uses Scanner object to read user input, CommandControl to execute commands
 * and FileControl and CollectionControl to read/write data.
 */
package support;

import commands.Command;
import data.Worker;
import exceptions.EmptyInputException;
import exceptions.InputException;
import exceptions.WrongArgumentsException;

import java.util.List;
import java.util.Scanner;

public class Console {
    private final Scanner scanner;
    private FileControl fileControl;
    private CollectionControl collectionControl;
    private final CommandControl commandControl;

    /**
     * Constructs a new Console object with a Scanner, CommandControl, FileControl,
     * and CollectionControl object.
     *
     * @param scanner           the Scanner object to read user input
     * @param commandControl    the CommandControl object to execute commands
     * @param fileControl       the FileControl object to read/write data
     * @param collectionControl the CollectionControl object to add workers to collection
     */
    public Console(Scanner scanner, CommandControl commandControl, FileControl fileControl, CollectionControl collectionControl) {
        this.scanner = scanner;
        this.commandControl = commandControl;
        this.fileControl = fileControl;
        this.collectionControl = collectionControl;

    }

    /**
     * Starts the interactive mode where the program reads user input and executes
     * corresponding commands until the program is terminated.
     *
     * @throws InputException      if an invalid command is entered
     * @throws EmptyInputException if the user enters an empty command
     */
    public void interactive() {
        String[] userCommand;
        List<Worker> setWorkerList = fileControl.readXmlFile();
        if (setWorkerList != null){
            for (Worker worker : setWorkerList) {
                collectionControl.addToCollection(worker);
            }
        }


        while (true) {
            try {
                Console.write(">>");
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                if (userCommand.length == 0) throw new EmptyInputException();
                chooseCommand(userCommand).execute(userCommand[1]);
            } catch (InputException e) {
                Console.err("Введенной команды не существет. Введите командочку help");
            } catch (EmptyInputException e) {
                Console.err(e.getMessage());
            }
        }
    }

    /**
     * Returns the Command object associated with the given user command.
     *
     * @param userCommand the user command to get the Command object for
     * @return the Command object associated with the given user command
     * @throws InputException if an invalid command is entered
     */

    public Command chooseCommand(String[] userCommand) throws InputException {
        for (String key : commandControl.getMapping().keySet()) {
            if (key.equalsIgnoreCase(userCommand[0])) {
                return commandControl.getMapping().get(key);
            }
        }
        throw new InputException();
    }

    /**
     * Prints the given object to the console without a newline character.
     *
     * @param outPut the object to print to the console
     */

    public static void write(Object outPut) {
        System.out.print(outPut);
    }

    /**
     * Prints the given object to the console with a newline character.
     *
     * @param outPut the object to print to the console
     */
    public static void writeln(Object outPut) {
        System.out.println(outPut);
    }

    /**
     * Prints the given object to the console with an "Error: " prefix and a newline character.
     *
     * @param outPut the object to print to the console
     */
    public static void err(Object outPut) {
        System.out.println("Error: " + outPut);
    }
}
