package models;

import java.util.Date;
import java.util.Objects;

/**
 * Model of Route. Main model of the program. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 *
 * @since 1.0
 * @author zerumi
 */
public class Route implements Comparable<Route> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private Location from;
    private Location to;
    private int distance;

    /**
     * Restrictions: Field cannot be null. The value of this field should be unique, greater than 0 and automatically generated.
     * @return Value of field id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Restrictions: Field cannot be null. String must not be empty.
     * @return Value of field name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Restrictions: Field cannot be null.
     * @return Value of field coordinates
     */
    public Coordinates getCoordinates()
    {
        return coordinates;
    }

    /**
     * Restrictions: Field cannot be null and the value of this field should be automatically generated.
     * @return Value of field creationDate
     */
    public java.util.Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Restrictions: Field can be null.
     * @return Value of field from
     */
    public Location getFrom()
    {
        return from;
    }

    /**
     * Restrictions: Field can be null.
     * @return Value of field to
     */
    public Location getTo()
    {
        return to;
    }

    /**
     * Restrictions: The value of this field should be greater than 1.
     * @return Value of field distance
     */
    public int getDistance()
    {
        return distance;
    }

    /**
     * Restrictions: Field cannot be null.
     * @param coordinates Value of field coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Restrictions: Field cannot be null and the value of this field should be automatically generated.
     * @param creationDate Value of field creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Restrictions: The value of this field should be greater than 1.
     * @param distance Value of field distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Restrictions: Field can be null.
     * @param from Value of field from
     */
    public void setFrom(Location from) {
        this.from = from;
    }

    /**
     * Restrictions: Field cannot be null. The value of this field should be unique, greater than 0 and automatically generated.
     * @param id Value of field id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restrictions: Field cannot be null. String must not be empty.
     * @param name Value of field name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restrictions: Field can be null.
     * @param to Value of field to
     */
    public void setTo(Location to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return distance == route.distance && id.equals(route.id) && name.equals(route.name) && coordinates.equals(route.coordinates) && creationDate.equals(route.creationDate) && Objects.equals(from, route.from) && Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Route o) {
        if (o == null) return 1;
        if (o.id == null) return -1;
        if (this.distance - o.distance < 0)
            return -1;
        else if (this.distance == o.distance)
            return 0;
        else
            return 1;
    }
}
