package models;

import java.util.Objects;

/**
 * Model of Coordinates. Sub-model of the <code>Route</code>. Contains getters/setters of each class fields.
 * Some fields have restrictions. It's signed under every method of field.
 */
public class Coordinates {
    private double x;
    private Float y;

    /**
     * Restrictions: The value of this field should be greater than -107.
     * @return Value of field x
     */
    public double getX() {
        return x;
    }

    /**
     * Restrictions: Field cannot be null and the value of this field should be greater than -39.
     * @return Value of field y
     */
    public Float getY() {
        return y;
    }

    /**
     * Restrictions: The value of this field should be greater than -107.
     * @param x Value of field x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Restrictions: Field cannot be null and the value of this field should be greater than -39.
     * @param y Value of field y
     */
    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
