package models.validators;

/**
 * Implementation of validator for Coordinates.x field.
 *
 * @since 1.0
 * @author Zerumi
 */
public class CoordXValidator implements Validator<Double> {
    /**
     * Checks if value greater than -107
     *
     * @see models.Coordinates
     * @param value x to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Double value) {
        return value > -107 && value <= Double.MAX_VALUE;
    }
}
