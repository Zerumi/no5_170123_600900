package models.validators;

/**
 * Base validator. You should implement it for use in handlers.
 *
 * @param <T> Type of validation value.
 */
@FunctionalInterface
public interface Validator<T> {
    /**
     * Provides validation method.
     *
     * @param value value to validate
     * @return true if value is validate. Otherwise false.
     */
    boolean validate(T value);
}
