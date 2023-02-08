package models.validators;

public class CoordYValidator implements Validator<Float> {
    @Override
    public boolean validate(Float value) {
        return value != null && value > -39;
    }
}
