package data;

import java.util.Objects;

/**
 * The {@code Coordinates} class represents the coordinates of a location.
 * It contains the x and y values.
 */
public class Coordinates {
    private Integer x;
    private int y;

    /**
     * Constructs a new {@code Coordinates} object with the specified x and y values.
     *
     * @param x the x value of the coordinates
     * @param y the y value of the coordinates
     */
    public Coordinates(Integer x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of the coordinates.
     *
     * @return the x value of the coordinates
     */
    public Integer getX() {
        return x;
    }

    /**
     * Returns the y value of the coordinates.
     *
     * @return the y value of the coordinates
     */
    public int getY() {
        return y;
    }

    /**
     * Returns a string representation of the {@code Coordinates} object.
     *
     * @return a string representation of the {@code Coordinates} object
     */
    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    /**
     * Returns a hash code for the {@code Coordinates} object.
     *
     * @return a hash code for the {@code Coordinates} object
     */
    @Override
    public int hashCode() {
        return y + x;
    }

    /**
     * Compares the specified object with this {@code Coordinates} object for equality.
     *
     * @param obj the object to compare
     * @return true if the specified object is equal to this {@code Coordinates} object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates coordinatesObj) {
            return (Objects.equals(x, coordinatesObj.getX())) && (y == coordinatesObj.getY());
        }
        return false;
    }
}