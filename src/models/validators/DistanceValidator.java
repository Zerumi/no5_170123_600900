package models.validators;

/**
 * Implementation of validator for distance field. (Route)
 *
 * @since 1.0
 * @author Zerumi
 */
public class DistanceValidator implements Validator<Integer> {
    /**
     * Checks if value greater than 1
     *
     * @see models.Route
     * @param value distance to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Integer value) {
        return value > 1;
    }
}
