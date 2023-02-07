package models.validators;

public class NameValidator implements Validator<String> {

    @Override
    public boolean validate(String value) {
        return value != null;
    }
}
