package pl.kurs.equationsolverapp.model;


public class AdditionOperator implements IAdditionOperator{

    @Override
    public double plus(double number1, double number2) {
        return number1 + number2;
    }
}
