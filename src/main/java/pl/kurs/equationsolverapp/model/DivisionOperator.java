package pl.kurs.equationsolverapp.model;

import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;

public class DivisionOperator implements IDivisionOperator{

    @Override
    public double divide(double number1, double number2) throws InvalidEquationFormatException {
        if (number2 == 0)
            throw new InvalidEquationFormatException("Nie mozna dzielic przez 0");

        return number1 / number2;
    }
}
