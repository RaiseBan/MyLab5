package data;

import java.time.LocalDateTime;
/**

 The Person class represents a person and contains information about their birthday, height, passport ID, and location.
 It implements the Comparable interface, allowing for comparison of two Person objects based on their height.
 */

public class Person implements Comparable<Person> {
    /**
     * The birthday of the person.
     */
    private LocalDateTime birthday;

    /**
     * The height of the person in centimeters.
     */
    private long height;

    /**
     * The passport ID of the person.
     */
    private String passportID;

    /**
     * The location of the person.
     */
    private Location location;

    /**
     * Constructs a new Person object with the specified birthday, height, passport ID, and location.
     *
     * @param birthday   the birthday of the person
     * @param height     the height of the person in centimeters
     * @param passportID the passport ID of the person
     * @param location   the location of the person
     */
    public Person(LocalDateTime birthday, long height, String passportID, Location location) {
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
        this.location = location;
    }

    /**
     * Returns the birthday of the person.
     *
     * @return the birthday of the person
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Returns the height of the person in centimeters.
     *
     * @return the height of the person in centimeters
     */
    public long getHeight() {
        return height;
    }

    /**
     * Returns the passport ID of the person.
     *
     * @return the passport ID of the person
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Returns the location of the person.
     *
     * @return the location of the person
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns a hash code for this person.
     *
     * @return a hash code for this person
     */
    @Override
    public int hashCode() {
        return birthday.hashCode() + (int) height + passportID.hashCode() + location.hashCode();
    }

    /**
     * Compares this person to another person based on their height.
     *
     * @param person the person to compare to
     * @return a negative integer, zero, or a positive integer as this person is less than, equal to, or greater than the specified person
     */
    @Override
    public int compareTo(Person person) {
        return this.birthday.compareTo(person.getBirthday());
    }

    /**
     * Returns true if this person is equal to the specified object.
     *
     * @param obj the object to compare to
     * @return true if this person is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person personObj = (Person) obj;
            return (height == personObj.getHeight()) && location.equals(personObj.getLocation()) &&
                    passportID.equals(personObj.getPassportID()) && birthday.equals(personObj.getBirthday());
        }
        return false;
    }

    ;

    /**
     * Returns a string representation of this person.
     *
     * @return a string representation of this person
     */
    @Override
    public String toString() {
        String info = "";
        info += "Birthday: " + birthday;
        info += ", Height: " + height + "cm";
        info += ", Passport ID: " + passportID;
        return info;
    }
}