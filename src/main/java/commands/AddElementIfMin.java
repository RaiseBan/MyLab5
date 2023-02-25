/**
 * The AddElementIfMin class represents the command to add a new worker element to the collection if
 * it has the smallest salary in the collection.
 */
package commands;

import data.Worker;
import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.CommunicationControl;
import support.Console;

public class AddElementIfMin extends AbstractCommand{
    /**
     * CollectionControl object for performing operations on the collection.
     */
    CollectionControl collectionControl;
    /**
     * CommunicationControl object for handling user input/output.
     */
    CommunicationControl communicationControl;
    /**
     * Constructs the AddElementIfMin command with the given CollectionControl and CommunicationControl objects.
     * @param collectionControl CollectionControl object for performing operations on the collection.
     * @param communicationControl CommunicationControl object for handling user input/output.
     */

    public AddElementIfMin(CollectionControl collectionControl, CommunicationControl communicationControl) {
        super("add_if_min", "Добавить новым элемент в коллекцю, если меньше" +
                "минимального в коллекции");
        this.collectionControl = collectionControl;
        this.communicationControl = communicationControl;
    }
    /**
     * Executes the AddElementIfMin command by prompting the user to create a new worker element and adding
     * it to the collection if it has the smallest salary in the collection.
     * @param argument the command argument string
     */
    public void execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongArgumentsException();
            Console.writeln("создайте новый элемент перед тем как сравнивать:");
            Worker newWorker = new Worker(communicationControl.setName(),
                    communicationControl.setCoordinates(),
                    communicationControl.setSalary(), communicationControl.choosePosition(),
                    communicationControl.chooseStatus(), communicationControl.setPerson());
            if (!collectionControl.addIfSmallerSalary(newWorker)) newWorker = null;
        }catch (WrongArgumentsException e){
            Console.err("Превышенно кол-во аргементов");
        }
    }
}
