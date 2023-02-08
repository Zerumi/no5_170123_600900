package models.validators;

/**
 * Implementation of validator for y and z field. (Location) / Same validation rules.
 *
 * @since 1.0
 * @author Zerumi
 */
public class LocationYZValidator implements Validator<Long> {
    /**
     * Checks if value not null.
     *
     * @see models.Location
     * @param value value to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Long value) {
        return value != null;
    }
}
