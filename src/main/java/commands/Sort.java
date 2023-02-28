package commands;

import support.CollectionControl;

/**
 * The Sort class represents a command to sort the collection in natural order.
 */
public class Sort extends AbstractCommand {
    CollectionControl collectionControl;

    /**
     * Constructs the Sort object with the specified CollectionControl object.
     *
     * @param collectionControl the CollectionControl object to be used
     */
    public Sort(CollectionControl collectionControl) {
        super("sort", "Отсортировать коллекцию в естественном порядке");
        this.collectionControl = collectionControl;
    }

    /**
     * Executes the Sort command by invoking the sort() method on the CollectionControl object.
     *
     * @param argument the argument to be passed to the command
     */
    @Override
    public void execute(String argument) {
        collectionControl.sort();
    }
}
