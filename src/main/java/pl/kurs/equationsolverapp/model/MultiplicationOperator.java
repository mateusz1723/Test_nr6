package pl.kurs.equationsolverapp.model;


public class MultiplicationOperator implements IMultiplicationOperator{

    @Override
    public double multiply(double number1, double number2) {
        return number1 * number2;
    }
}
