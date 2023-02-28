/**
 * The {@code Show} class represents a command that outputs all elements of the collection in string representation.
 * Implements {@link Command} interface.
 */
package commands;

import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.Console;
import support.FileControl;

public class SaveCollection extends AbstractCommand {
    FileControl fileControl;
    CollectionControl collectionControl;

    /**
     * Constructs a new {@code Show} object with the specified {@link CollectionControl} object.
     *
     * @param collectionControl the {@link CollectionControl} object for controlling the collection
     */
    public SaveCollection(FileControl fileControl, CollectionControl collectionControl) {
        super("save", "сохранить коллекцию в файл");
        this.fileControl = fileControl;
        this.collectionControl = collectionControl;
    }

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentsException();
            collectionControl.saveCollection(argument);

        } catch (WrongArgumentsException e) {
            Console.err(e.getMessage());
        }
    }
}
