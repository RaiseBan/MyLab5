package support;

import commands.Command;
import data.Person;
import data.Status;
import data.Worker;
import exceptions.InputException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionControl {
    private final ArrayList<Worker> workersCollection = new ArrayList<>();
    HashMap<String, Command> BufferOfCommandMap;
    protected static LocalDateTime timeInitialization = null;
    FileControl fileControl;
    CommunicationControl communicationControl;

    /**
     * CollectionControl constructor which accepts a FileControl and CommunicationControl object to be used by the class.
     *
     * @param fileControl          FileControl object used to perform file operations.
     * @param communicationControl CommunicationControl object used to perform user input/output operations.
     */
    public CollectionControl(FileControl fileControl, CommunicationControl communicationControl) {
        this.fileControl = fileControl;
        this.communicationControl = communicationControl;
        this.BufferOfCommandMap = null;
    }

    /**
     * Method for setting the buffer of commands for the CollectionControl class.
     *
     * @param map a HashMap containing String keys and Command values.
     */
    public void getMappingOfCommands(HashMap<String, Command> map) {
        this.BufferOfCommandMap = map;
    }

    /**
     * Method for retrieving the buffer of commands for the CollectionControl class.
     *
     * @return a HashMap containing String keys and Command values.
     */
    public HashMap<String, Command> sendCommandMap() {
        return this.BufferOfCommandMap;
    }

    /**
     * Method for clearing the workersCollection ArrayList.
     */
    public void clear() {
        workersCollection.clear();
        Console.writeln("Коллекция очистилась...");

    }

    /**
     * Method for sorting the workersCollection ArrayList.
     */
    public void sort() {
        Collections.sort(workersCollection);
    }

    /**
     * Method for removing all Worker objects in the workersCollection ArrayList whose salary is greater than that of another Worker object.
     *
     * @param enotherWorker a Worker object to compare salary with.
     */
    public void removeGreater(Worker enotherWorker) {
        workersCollection.removeIf(worker -> enotherWorker.compareTo(worker) > 0);
    }

    /**
     * Method for saving the workersCollection ArrayList to a file using FileControl.
     */
    public void saveCollection(String file) {
        try {
            fileControl.writeToFile(workersCollection, file);
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for filtering and returning a new ArrayList containing all Worker objects in workersCollection whose status is greater than that specified in the argument.
     *
     * @param line a String representing the Status object to compare with.
     * @return an ArrayList of Worker objects whose status is greater than the specified Status.
     * @throws IllegalArgumentException if the input String does not represent a valid Status object.
     */
    public ArrayList<Worker> filterGreaterThanStatus(String line) throws IllegalArgumentException {
        ArrayList<Worker> workerList = new ArrayList<>();
        try {
            Status person = Status.valueOf(line);


            for (Worker worker : workersCollection) {
                if (person.ordinal() < worker.getStatus().ordinal()) {
                    workerList.add(worker);
                }
            }
            return workerList;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Groups workers in the collection by their status and prints the number of workers for each status.
     */
    public void gropByStatus() {

        Map<Status, List<Worker>> workersByStatus = workersCollection.stream()
                .collect(Collectors.groupingBy(Worker::getStatus));
        for (Status status : workersByStatus.keySet()) {
            Console.writeln("Кол-во работников со статусом '" + status + "': " + workersByStatus.get(status).size());
        }
    }

    /**
     * Prints information about the collection, including its type, time of initialization, and number of elements.
     */
    public void getInfo() {
        Console.writeln("Тип: Worker" + "\n" +
                "Время инициализации: " + timeInitialization + "\n" +
                "количество элементов: " + workersCollection.size());

    }

    /**
     * Sorts the collection of Worker objects by name in ascending order and prints the associated Person objects.
     * The method retrieves the associated Person object for each Worker and sorts the Person objects using the compareTo method implemented in the Person class.
     * The sorted Person objects are printed to the console using the Console.writeln method.
     */
    public void sortPerson() {
        ArrayList<Person> sortedPerson = new ArrayList<>();
        for (Worker worker : workersCollection)
            sortedPerson.add(worker.getPerson());
        Collections.sort(sortedPerson);
        for (Person person: sortedPerson){
            Console.writeln(person.toString());
        }
    }

    /**
     * Removes the worker from the collection with the specified ID.
     *
     * @param id The ID of the worker to remove.
     */
    public void removeElementByID(int id) {
        try {
            workersCollection.remove(id);
        } catch (NoSuchElementException e) {
            Console.err("Элемента с такии id нет в коллекции");
        }
    }

    /**
     * Prints information about each worker in the collection.
     */
    public void show() {
        for (Worker worker : workersCollection) {
            Console.writeln(worker.toString());
        }
    }

    /**
     * Adds a worker to the collection.
     *
     * @param worker The worker to add to the collection.
     */
    public void addToCollection(Worker worker) {
        workersCollection.add(worker);
    }

    /**
     * Adds a worker to the collection if their salary is smaller than the minimum salary of all workers in the collection.
     *
     * @param newWorker The worker to add to the collection.
     * @return True if the worker was added to the collection, false otherwise.
     */
    public boolean addIfSmallerSalary(Worker newWorker) {
        if (workersCollection.isEmpty() || newWorker.getSalary() < Collections.min(workersCollection, Comparator.comparing(Worker::getSalary)).getSalary()) {
            workersCollection.add(newWorker);
            return true;
        }
        return false;
    }

    /**
     * Updates the worker in the collection with the specified ID with new information.
     *
     * @param id The ID of the worker to update.
     */
    public void updateByID(int id) {
        try {
            if (id > workersCollection.size()) throw new InputException();
            workersCollection.set(id - 1, new Worker(id, communicationControl.setName(),
                    communicationControl.setCoordinates(), communicationControl.setSalary(),
                    communicationControl.choosePosition(), communicationControl.chooseStatus(),
                    communicationControl.setPerson()).setID(id));

        } catch (InputException e) {
            Console.err("такого рабочего нет");
        }
    }

    /**
     * Updates the IDs of all workers in the collection, assigning them sequential IDs starting from 1.
     * This method is useful when workers have been added or removed from the collection and IDs need to be updated accordingly.
     */
    public void updateAllIDs() {
        for (int i = 0; i < workersCollection.size(); i++) {
            workersCollection.get(i).setID(i + 1);
        }
    }

}
