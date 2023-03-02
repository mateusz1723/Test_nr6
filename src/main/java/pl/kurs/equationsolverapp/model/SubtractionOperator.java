package pl.kurs.equationsolverapp.model;

public class SubtractionOperator implements ISubtractionOperator{

    @Override
    public double subtract(double number1, double number2) {
        return number1 - number2;
    }
}
