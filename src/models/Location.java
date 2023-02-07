package models;

import java.util.Objects;

/**
 * Model of Location. Sub-model of the <code>Route</code>. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 */
public class Location {
    private float x;
    private Long y;
    private Long z;
    private String name;

    /**
     *  No restrictions.
     * @return Value of field x
     */
    public float getX() {
        return x;
    }

    /**
     * Restrictions: Field cannot be null.
     * @return Value of field y
     */
    public Long getY() {
        return y;
    }

    /**
     * Restrictions: Field cannot be null.
     * @return Value of field z
     */
    public Long getZ() {
        return z;
    }

    /**
     * Restrictions: Field cannot be null. String must not be empty.
     * @return Value of field name
     */
    public String getName() {
        return name;
    }

    /**
     * No restrictions.
     * @param x Value of field x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Restrictions: Field cannot be null.
     * @param y Value of field y
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * Restrictions: Field cannot be null.
     * @param z Value of field z
     */
    public void setZ(Long z) {
        this.z = z;
    }

    /**
     * Restrictions: Field cannot be null. String must not be empty.
     * @param name Value of field name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.x, x) == 0 && y.equals(location.y) && z.equals(location.z) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
