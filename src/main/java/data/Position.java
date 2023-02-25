package data;

/**
 * The {@code Position} enumeration represents the different positions
 * that an employee can hold within a company.
 *
 * <p>The available positions are:</p>
 *
 * <ul>
 *   <li>{@link #DIRECTOR}</li>
 *   <li>{@link #HEAD_OF_DIVISION}</li>
 *   <li>{@link #HEAD_OF_DEPARTMENT}</li>
 *   <li>{@link #MANAGER_OF_CLEANING}</li>
 * </ul>
 *
 * <p>The {@link #nameList()} method returns a comma-separated list of the
 * names of all the available positions.</p>
 */
public enum Position {

    /**
     * The director position.
     */
    DIRECTOR,

    /**
     * The head of division position.
     */
    HEAD_OF_DIVISION,

    /**
     * The head of department position.
     */
    HEAD_OF_DEPARTMENT,

    /**
     * The manager of cleaning position.
     */
    MANAGER_OF_CLEANING;

    /**
     * Returns a comma-separated list of the names of all the available positions.
     *
     * @return a comma-separated list of position names
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Position positionType : values()) {
            nameList.append(positionType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
