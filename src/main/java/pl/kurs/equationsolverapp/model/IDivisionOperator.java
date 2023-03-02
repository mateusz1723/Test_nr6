package pl.kurs.equationsolverapp.model;

import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;



public interface IDivisionOperator {

    double divide(double number1, double number2) throws InvalidEquationFormatException;
}
