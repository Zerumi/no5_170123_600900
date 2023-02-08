package models.validators;

public class LocationNameValidator implements Validator<String>{
    @Override
    public boolean validate(String value) {
        if (value == null) return true;
        return !(value.isEmpty() || value.isBlank());
    }
}
