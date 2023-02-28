package commands;

/**
 * An abstract class that implements the {@link Command} interface and provides
 * basic functionality for all commands.
 */

public abstract class AbstractCommand implements Command {
    /**
     * The name of the command.
     */
    private String name;
    /**
     * The description of the command.
     */
    private String description;

    /**
     * Constructs an abstract command with the specified name and description.
     *
     * @param name        the name of the command
     * @param description the description of the command
     */

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;

    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the command.
     *
     * @return the description of the command
     */

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Compares this abstract command with the specified object for equality.
     *
     * @param o the object to be compared for equality with this command
     * @return true if the specified object is equal to this command, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCommand)) return false;
        AbstractCommand absObj = (AbstractCommand) o;
        return name.equals(absObj.getName()) && description.equals(absObj.getDescription());
    }

    /**
     * Returns a hash code for this abstract command.
     *
     * @return a hash code for this command
     */
    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }
}
