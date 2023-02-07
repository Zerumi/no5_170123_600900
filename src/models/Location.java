package models;

/**
 * Model of Coordinated. Sub-model of the <code>Route</code>. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 */
public class Location {
    private float x;
    private Long y; //Поле не может быть null
    private Long z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null
}
