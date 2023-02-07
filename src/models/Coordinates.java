package models;

/**
 * Model of Coordinated. Sub-model of the <code>Route</code>. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 */
public class Coordinates {
    private double x; //Значение поля должно быть больше -107
    private Float y; //Значение поля должно быть больше -39, Поле не может быть null
}
