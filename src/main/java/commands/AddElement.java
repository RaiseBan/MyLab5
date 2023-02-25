/**
 * The {@code AddElement} class represents a command that adds a new worker element to the collection.
 * The command requires the communicationControl and collectionControl instances to communicate with the user
 * and modify the collection.
 * Extends {@code AbstractCommand} abstract class.
 */
package commands;

import data.Worker;
import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.CommunicationControl;
import support.Console;

public class AddElement extends AbstractCommand{
    /**
     * The communicationControl instance used for user interaction.
     */
    private CommunicationControl communicationControl;
    /**
     * The collectionControl instance used to modify the collection.
     */
    private CollectionControl collectionControl;
    /**
     * Constructs a new AddElement instance with the given communicationControl and collectionControl instances.
     * @param communicationControl the communicationControl instance to use for user interaction.
     * @param collectionControl the collectionControl instance to use for collection modification.
     */
    public AddElement(CommunicationControl communicationControl, CollectionControl collectionControl) {
        super("addElement", "Добавить элемент в коллекцию");
        this.communicationControl = communicationControl;
        this.collectionControl = collectionControl;
    }
    /**
     * Adds a new worker element to the collection.
     * @param argument The command argument, not used in this command.
     */
    public void execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongArgumentsException();
            collectionControl.addToCollection(new Worker(communicationControl.setName(),
                    communicationControl.setCoordinates(),
                    communicationControl.setSalary(), communicationControl.choosePosition(),
                    communicationControl.chooseStatus(), communicationControl.setPerson()));
        }catch (WrongArgumentsException e){
            Console.err(e.getMessage());
        }
    }

}
