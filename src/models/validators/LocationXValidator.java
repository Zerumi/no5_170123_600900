package models.validators;

public class LocationXValidator implements Validator<Float> {
    @Override
    public boolean validate(Float value) {
        return value != Float.NEGATIVE_INFINITY && value != Float.POSITIVE_INFINITY;
    }
}
