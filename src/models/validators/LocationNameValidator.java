package models.validators;

/**
 * Implementation of validator for name field. (Location)
 *
 * @since 1.0
 * @author Zerumi
 */
public class LocationNameValidator implements Validator<String>{
    /**
     * Checks if value not empty.
     *
     * @see models.Location
     * @param value name to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(String value) {
        if (value == null) return true;
        return !(value.isEmpty() || value.isBlank());
    }
}
