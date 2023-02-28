package commands;

import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.Console;

/**
 * Command to display information about the collection.
 */
public class Info extends AbstractCommand {
    CollectionControl collectionControl;

    /**
     * Creates a new instance of Info command.
     *
     * @param collectionControl the collection control object
     */
    public Info(CollectionControl collectionControl) {
        super("info", "выводит информацию о коллекции (тип, дата инициализации, кол-во элементов и т.д.)");
        this.collectionControl = collectionControl;
    }

    /**
     * Executes the Info command.
     *
     * @param argument the command argument (not used in this command)
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentsException();
            collectionControl.getInfo();
        } catch (WrongArgumentsException e) {
            Console.writeln(e.getMessage());
        }
    }
}
