package models;

public class Route {
    /**
     * Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    private Long id;
    /**
     * Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Поле не может быть null
     */
    private Coordinates coordinates;
    /**
     * Поле не может быть null, Значение этого поля должно генерироваться автоматически
     */
    private java.util.Date creationDate;
    /**
     * Поле может быть null
     */
    private Location from;
    /**
     * Поле может быть null
     */
    private Location to;
    /**
     * Значение поля должно быть больше 1
     */
    private int distance;
}
