package models.validators;

public class LocationYZValidator implements Validator<Long> {
    @Override
    public boolean validate(Long value) {
        return value != null;
    }
}
