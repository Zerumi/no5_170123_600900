package models.validators;

/**
 * Implementation of validator for Coordinates.y field.
 *
 * @since 1.0
 * @author Zerumi
 */
public class CoordYValidator implements Validator<Float> {
    /**
     * Checks if value greater than -39
     *
     * @see models.Coordinates
     * @param value y to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Float value) {
        return value != null && value > -39 && value <= Float.MAX_VALUE;
    }
}
