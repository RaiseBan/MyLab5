/**
 * The {@code CommandControl} class is responsible for mapping commands and storing them in a HashMap for easy access.
 * It also provides a reference to the {@code CollectionControl} object that manages the collection.
 */
package support;

import commands.Command;

import java.util.HashMap;

public class CommandControl {
    private final HashMap<String, Command> commandMapping = new HashMap<>();
    private Command help;
    private Command info;
    private Command printFieldOfPerson;
    private Command removeElementByID;
    private Command removeGreater;
    private Command saveCollection;
    private Command show;
    private Command sort;
    private Command updateByID;
    private CollectionControl collectionControl;

    /**
     * Constructs a {@code CommandControl} object with the given commands and {@code CollectionControl} object.
     *
     * @param addElement          the {@code Command} object for adding a new element to the collection
     * @param addElementIfMin     the {@code Command} object for adding a new element to the collection if its salary is less than the minimum salary of all elements in the collection
     * @param clear               the {@code Command} object for clearing the collection
     * @param executeScript       the {@code Command} object for executing a script file
     * @param exit                the {@code Command} object for exiting the program
     * @param filterGreaterStatus the {@code Command} object for filtering the collection by status greater than a given status
     * @param groupByStatus       the {@code Command} object for grouping the collection by status
     * @param help                the {@code Command} object for displaying help information
     * @param info                the {@code Command} object for displaying information about the collection
     * @param printFieldOfPerson  the {@code Command} object for printing the value of the person field of all elements in the collection
     * @param removeElementByID   the {@code Command} object for removing an element from the collection by ID
     * @param removeGreater       the {@code Command} object for removing all elements from the collection greater than a given element
     * @param saveCollection      the {@code Command} object for saving the collection to a file
     * @param show                the {@code Command} object for displaying all elements in the collection
     * @param sort                the {@code Command} object for sorting the collection by salary
     * @param updateByID          the {@code Command} object for updating an element in the collection by ID
     * @param collectionControl   the {@code CollectionControl} object that manages the collection
     */

    public CommandControl(Command addElement, Command addElementIfMin, Command clear,
                          Command executeScript, Command exit, Command filterGreaterStatus,
                          Command groupByStatus, Command help, Command info, Command printFieldOfPerson,
                          Command removeElementByID, Command removeGreater,
                          Command saveCollection, Command show, Command sort, Command updateByID, CollectionControl collectionControl) {
        this.help = help;
        this.info = info;
        this.printFieldOfPerson = printFieldOfPerson;
        this.removeElementByID = removeElementByID;
        this.removeGreater = removeGreater;
        this.saveCollection = saveCollection;
        this.show = show;
        this.sort = sort;
        this.updateByID = updateByID;
        commandMapping.put(addElement.getName(), addElement);
        commandMapping.put(addElementIfMin.getName(), addElementIfMin);
        commandMapping.put(clear.getName(), clear);
        commandMapping.put(executeScript.getName(), executeScript);
        commandMapping.put(exit.getName(), exit);
        commandMapping.put(filterGreaterStatus.getName(), filterGreaterStatus);
        commandMapping.put(groupByStatus.getName(), groupByStatus);
        commandMapping.put(this.help.getName(), this.help);
        commandMapping.put(this.info.getName(), this.info);
        commandMapping.put(this.printFieldOfPerson.getName(), this.printFieldOfPerson);
        commandMapping.put(this.removeElementByID.getName(), this.removeElementByID);
        commandMapping.put(this.removeGreater.getName(), this.removeGreater);
        commandMapping.put(this.show.getName(), this.show);
        commandMapping.put(this.sort.getName(), this.sort);
        commandMapping.put(this.updateByID.getName(), this.updateByID);
        commandMapping.put(this.saveCollection.getName(), this.saveCollection);
        this.collectionControl = collectionControl;
        this.collectionControl.getMappingOfCommands(commandMapping);
    }

    /**
     * Returns the mapping of command names to Command objects in this CommandControl.
     *
     * @return a HashMap containing the mapping of command names to Command objects
     */
    public HashMap<String, Command> getMapping() {
        return commandMapping;
    }

}