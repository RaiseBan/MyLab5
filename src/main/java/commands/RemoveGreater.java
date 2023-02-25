package commands;

import data.Worker;
import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.CommunicationControl;
import support.Console;

/**
 * The RemoveGreater class represents a command to remove all elements from the collection
 * that are greater than the specified element.
 */
public class RemoveGreater extends AbstractCommand{
    CollectionControl collectionControl;
    CommunicationControl communicationControl;

    /**
     * Constructs the RemoveGreater object with the specified CollectionControl and CommunicationControl objects.
     *
     * @param collectionControl the CollectionControl object to be used
     * @param communicationControl the CommunicationControl object to be used
     */
    public RemoveGreater(CollectionControl collectionControl, CommunicationControl communicationControl) {
        super("remove_greater", "Remove all elements from the collection that are greater than the specified element");
        this.collectionControl = collectionControl;
        this.communicationControl = communicationControl;
    }

    /**
     * Executes the RemoveGreater command by invoking the removeGreater() method on the CollectionControl object.
     *
     * @param argument the argument to be passed to the command
     */
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongArgumentsException();
            collectionControl.removeGreater(new Worker(communicationControl.setName(),
                    communicationControl.setCoordinates(), communicationControl.setSalary(),
                    communicationControl.choosePosition(), communicationControl.chooseStatus(),
                    communicationControl.setPerson()));
            collectionControl.updateAllIDs();
        }catch (WrongArgumentsException e){
            Console.err(e.getMessage());
        }

    }
}
