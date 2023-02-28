/**
 * The ExecuteScript class represents a command to execute a script file.
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


public class ExecuteScript extends AbstractCommand {
    CollectionControl collectionControl;
    CommunicationControl communicationControl;


    /**
     * Constructs a new ExecuteScript instance with the specified collection and communication controls.
     *
     * @param collectionControl    the collection control instance
     * @param communicationControl the communication control instance
     */
    public ExecuteScript(CollectionControl collectionControl, CommunicationControl communicationControl) {
        super("execute_script", "выполняет скрипт");
        this.collectionControl = collectionControl;
        this.communicationControl = communicationControl;
    }


    /**
     * Executes the command with the specified argument.
     *
     * @param argument the argument for the command
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
            communicationControl.setUnsetLoop();


            try {

                // Заменяем стандартный поток ввода на InputStream из файла
                communicationControl.changeScanner(fileIn);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] args = (line.trim() + " ").split(" ");
                    HashMap<String, Command> commandMap = collectionControl.sendCommandMap();
                    for (String key : commandMap.keySet()) {
                        if ((key.equalsIgnoreCase(args[0].trim())) &&(!key.equalsIgnoreCase("execute_script"))) {
                            String argumentForExecute;
                            if (args.length == 2) {
                                argumentForExecute = args[1];
                            } else {
                                argumentForExecute = "";
                            }

                            commandMap.get(key).execute(argumentForExecute);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Восстанавливаем стандартный поток ввода
                communicationControl.setUnsetLoop();
                communicationControl.changeScanner(System.in);
                // Закрываем InputStream из файла
                fileCommands.delete();
                fileData.delete();
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
     * Processes the script file and writes the commands to a file.
     *
     * @param file the script file to process
     */
    private void fileProcessor(String file) {
        String outputDataName = "outputData.txt";
        String outputCommandsName = "outputCommands.txt";

        try (Scanner scanner = new Scanner(new File(file));
             FileOutputStream fosData = new FileOutputStream(outputDataName)) {
            FileOutputStream fosCommand = new FileOutputStream(outputCommandsName);

            while (scanner.hasNextLine()) {
                String[] args;
                String line = scanner.nextLine();
                args = (line.trim() + " ").split(" ", 2);
                if (args.length == 0) throw new EmptyInputException();
                if (!collectionControl.sendCommandMap().containsKey(args[0].trim())) {
                    fosData.write((line + "\n").getBytes());
                } else {
                    fosCommand.write((args[0] + " " + args[1] + "\n").getBytes());
                }
            }

        } catch (IOException e) {
            Console.writeln("Файла не найдено");
        }
    }
}
