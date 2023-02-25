package data;

/**
 * The {@code Status} enumeration represents the different statuses that
 * an employee can have within a company.
 *
 * <p>The available statuses are:</p>
 *
 * <ul>
 *   <li>{@link #HIRED}</li>
 *   <li>{@link #RECOMMENDED_FOR_PROMOTION}</li>
 *   <li>{@link #REGULAR}</li>
 *   <li>{@link #PROBATION}</li>
 * </ul>
 *
 * <p>The {@link #nameList()} method returns a comma-separated list of the
 * names of all the available statuses.</p>
 */
public enum Status {

    /**
     * The employee is currently hired.
     */
    HIRED,

    /**
     * The employee is recommended for promotion.
     */
    RECOMMENDED_FOR_PROMOTION,

    /**
     * The employee is a regular employee.
     */
    REGULAR,

    /**
     * The employee is on probation.
     */
    PROBATION;

    /**
     * Returns a comma-separated list of the names of all the available statuses.
     *
     * @return a comma-separated list of status names
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Status statusType : values()) {
            nameList.append(statusType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}

