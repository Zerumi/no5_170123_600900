package models.validators;

public class LocationXValidator implements Validator<Float> {
    @Override
    public boolean validate(Float value) {
        return value <= Float.MAX_VALUE && value >= Float.MIN_VALUE;
    }
}
