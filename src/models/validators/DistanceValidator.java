package models.validators;

public class DistanceValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer value) {
        return value > 1;
    }
}
