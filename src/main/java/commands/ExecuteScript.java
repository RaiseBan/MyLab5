/**
 * The commands package contains classes that represent specific commands that can be executed on the collection of objects.
 */

package commands;

import exceptions.EmptyInputException;
import exceptions.WrongArgumentsException;
import support.*;
import support.Console;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The ExecuteScript class extends the AbstractCommand class and represents a command that executes a script.
 * <p>
 * It takes two objects as parameters - CollectionControl and CommunicationControl - to access the collection of objects and change the scanner used for user input.
 * <p>
 * It inherits the command name and description from the parent class.
 */
public class ExecuteScript extends AbstractCommand {
    CollectionControl collectionControl;
    CommunicationControl communicationControl;

    /**
     * Constructor for ExecuteScript class.
     *
     * @param collectionControl    An object of the CollectionControl class, which is used to access the collection of objects.
     * @param communicationControl An object of the CommunicationControl class, which is used to change the scanner used for user input.
     */

    public ExecuteScript(CollectionControl collectionControl, CommunicationControl communicationControl) {
        super("execute_script", "выполняет скрипт");
        this.collectionControl = collectionControl;
        this.communicationControl = communicationControl;
    }

    /**
     * Executes the command to run a script.
     *
     * @param argument The argument for the command (the path to the script file).
     * @throws WrongArgumentsException if the argument is empty.
     */

    @Override
    public void execute(String argument) {

        try {
            if (argument.isEmpty()) throw new WrongArgumentsException();
            FileControl.checkFilePermissions(argument);
            fileProcessor(argument);
            File fileData = new File("outputData.txt");
            File fileCommands = new File("outputCommands.txt");
            InputStream fileIn = Files.newInputStream(fileData.toPath());

            Scanner scanner = new Scanner(fileCommands);

            try {
                // Заменяем стандартный поток ввода на InputStream из файла
                communicationControl.changeScanner(fileIn);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] args = (line.trim() + " ").split(" ");
                    HashMap<String, Command> commandMap = collectionControl.sendCommandMap();
                    if (commandMap.containsKey(args[0].trim())) {
                        String argumentForExecute;
                        if (args.length == 2) {
                            argumentForExecute = args[1];
                        } else {
                            argumentForExecute = "";
                        }
                        commandMap.get(args[0]).execute(argumentForExecute);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Восстанавливаем стандартный поток ввода
                communicationControl.changeScanner(System.in);
                // Закрываем InputStream из файла
                try {
                    fileIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (WrongArgumentsException e) {
            Console.err("название скрипта не введено");

        } catch (IOException e) {
            Console.err("");
        }
    }

    /**
     * Processes the script file and separates the lines that contain command arguments from the lines that do not.
     * <p>
     * Writes the command arguments to a separate file, and writes the lines without command arguments to another file.
     *
     * @param file The path to the script file.
     */
    private void fileProcessor(String file) {
        String outputDataName = "outputData.txt";
        String outputCommandsName = "outputCommands.txt";

        try (Scanner scanner = new Scanner(new File(file));
             FileOutputStream fosData = new FileOutputStream(outputDataName)) {
            FileOutputStream fosCommand = new FileOutputStream(outputCommandsName);

            while (scanner.hasNextLine()) {
                String[] args;
                String line = scanner.nextLine().trim();
                args = (line.trim() + " ").split(" ", 2);
                if (args.length == 0) throw new EmptyInputException();
                if (!collectionControl.sendCommandMap().containsKey(args[0].trim())) {
                    fosData.write((line + "\n").getBytes());
                } else {
                    fosCommand.write((args[0] + args[1] + "\n").getBytes());
                }
            }


        } catch (IOException e) {
            Console.writeln("Файла не найдено");
        }
    }
}
