package data;

/**
 * The Location class represents the location of a person or worker
 * /
 * public class Location
 * The x coordinate of the location
 */
public class Location {

    private float x;
    /**
     * The y coordinate of the location
     */
    private long y;
    /**
     * The z coordinate of the location
     */
    private int z;
    /**
     * The name of the location
     */
    private String name;

    /**
     * Constructs a Location with the specified x, y, z coordinates and name
     *
     * @param x    the x coordinate of the location
     * @param y    the y coordinate of the location
     * @param z    the z coordinate of the location
     * @param name the name of the location
     */
    public Location(float x, long y, int z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Returns the x coordinate of the location
     *
     * @return the x coordinate of the location
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the location
     *
     * @return the y coordinate of the location
     */
    public long getY() {
        return y;
    }

    /**
     * Returns the z coordinate of the location
     *
     * @return the z coordinate of the location
     */
    public int getZ() {
        return z;
    }

    /**
     * Returns the name of the location
     *
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hash code value for this location
     *
     * @return the hash code value for this location
     */
    @Override
    public int hashCode() {
        return name.hashCode() + (int) y + (int) x + z;
    }

    /**
     * Compares this location to the specified object. The result is true if and only if the argument is not null and
     * is a Location object that has the same x, y, and z coordinates as this object.
     *
     * @param obj the object to compare this Location against
     * @return true if the given object represents a Location equivalent to this location, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Location) {
            Location locationObj = (Location) obj;
            return (x == locationObj.getX()) && (y == locationObj.getY()) && (z == locationObj.getZ());
        }
        return false;
    }

    /**
     * Returns a string representation of the Location object.
     *
     * @return a string representation of the Location object
     */
    @Override
    public String toString() {
        String info = "";
        info += "Координата X: " + x;
        info += "Координата Y: " + y;
        info += "Координата Z: " + z;
        return info;
    }
}