package models.validators;

public class CoordXValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value > -107;
    }
}
