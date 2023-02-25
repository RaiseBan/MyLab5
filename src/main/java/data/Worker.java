package data;
import java.time.ZonedDateTime;
import java.util.Objects;
/**

 Represents a worker with a unique ID, name, coordinates, creation date, salary, position, status, and personal information.

 Implements the Comparable interface for comparing workers based on salary.
 */
public class Worker implements Comparable<Worker>{
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Double salary;
    private Position position;
    private Status status;
    private Person person;
    public static int idCounter = 0;

    /**
     * Constructs a new Worker with the specified name, coordinates, salary, position, status, and person.
     * The worker's ID is automatically generated.
     *
     * @param name the name of the worker
     * @param coordinates the coordinates of the worker
     * @param salary the salary of the worker
     * @param position the position of the worker
     * @param status the status of the worker
     * @param person the personal information of the worker
     */
    public Worker (String name, Coordinates coordinates,
                   Double salary, Position position, Status status, Person person){
        this.id = ++idCounter;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    /**
     * Constructs a new Worker with the specified ID, name, coordinates, salary, position, status, and person.
     *
     * @param id the ID of the worker
     * @param name the name of the worker
     * @param coordinates the coordinates of the worker
     * @param salary the salary of the worker
     * @param position the position of the worker
     * @param status the status of the worker
     * @param person the personal information of the worker
     */
    public Worker (int id, String name, Coordinates coordinates,
                   Double salary, Position position, Status status, Person person){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    /**
     * Returns the name of the worker.
     *
     * @return the name of the worker
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ID of the worker.
     *
     * @param id the ID to be set
     * @return the worker with the updated ID
     */
    public Worker setID(int id){
        this.id = id;
        return this;
    }

    /**
     * Returns the coordinates of the worker.
     *
     * @return the coordinates of the worker
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the salary of the worker.
     *
     * @return the salary of the worker
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Returns the position of the worker.
     *
     * @return the position of the worker
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the status of the worker.
     *
     * @return the status of the worker
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the personal information of the worker.
     *
     * @return the personal information of the worker
     */
    public Person getPerson() {
        return person;
    }


    /**
     * Overrides the compareTo method of the Comparable interface to compare workers based on their salary.
     *
     * @param otherWorker the worker to compare to
     * @return an integer value indicating the comparison result
     */
    @Override
    public int compareTo(Worker otherWorker) {
        return this.name.compareTo(otherWorker.getName());
    }

    /**
     * Overrides the hashCode method to generate a hash value for Worker objects based on their attributes.
     *
     * @return an integer value representing the hash value of this object
     */
    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + (int) salary.hashCode() + position.hashCode() +
                status.hashCode() + person.hashCode();
    }

    /**
     * Overrides the equals method to check if this Worker object is equal to another object.
     *
     * @param obj the object to compare to
     * @return a boolean value indicating whether or the objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Worker) {
            Worker workerObj = (Worker) obj;
            return name.equals(workerObj.getName()) && coordinates.equals(workerObj.getCoordinates()) &&
                    (Objects.equals(salary, workerObj.getSalary())) && (status == workerObj.getStatus()) &&
                    (position == workerObj.getPosition()) && person.equals(workerObj.getPerson());
        }
        return false;
    }

    /**
     * Overrides the toString method to return a string representation of the Worker object.
     *
     * @return a string representation of the Worker object
     */
    @Override
    public String toString() {
        return "Worker [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate="
                + creationDate + ", salary=" + salary + ", position=" + position + ", status=" + status + ", person="
                + person + "]";
    }
}
