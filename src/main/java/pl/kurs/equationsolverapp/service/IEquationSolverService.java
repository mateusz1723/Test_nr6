package pl.kurs.equationsolverapp.service;

import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exception.UnknownOperatorException;

import java.math.BigDecimal;

public interface IEquationSolverService {

    BigDecimal solve(String equation) throws UnknownOperatorException, InvalidEquationFormatException;
}
