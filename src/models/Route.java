package models;

import java.util.Date;

/**
 * Model of Route. Main model of the program. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 *
 * @since 1.0
 * @author zerumi
 */
public class Route {
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
}
