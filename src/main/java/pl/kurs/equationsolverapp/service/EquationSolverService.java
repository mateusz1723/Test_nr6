package pl.kurs.equationsolverapp.service;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exception.UnknownOperatorException;
import pl.kurs.equationsolverapp.model.*;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EquationSolverService implements IEquationSolverService {


    private AdditionOperator additionOperator = new AdditionOperator();
    private MultiplicationOperator multiplicationOperator = new MultiplicationOperator();
    private SubtractionOperator subtractionOperator = new SubtractionOperator();
    private DivisionOperator divisionOperator = new DivisionOperator();

    public EquationSolverService() {
    }

    public EquationSolverService(AdditionOperator additionOperator, MultiplicationOperator multiplicationOperator, SubtractionOperator subtractionOperator, DivisionOperator divisionOperator) {
        this.additionOperator = additionOperator;
        this.multiplicationOperator = multiplicationOperator;
        this.subtractionOperator = subtractionOperator;
        this.divisionOperator = divisionOperator;
    }

    @Override
    public BigDecimal solve(String equation) throws UnknownOperatorException, InvalidEquationFormatException {
        checkEquation(equation);
        checkOperator(equation);
        String[] phrase = equation.trim().split(" ");
        List<String> phraseList = Arrays.stream(phrase).collect(Collectors.toList());
        List<String> phraseListAfterDivideAndMultiply = checkDivideAndMultiplyOperator(phraseList);
        List<String> resultList = checkAdditionAndSubtractOperator(phraseListAfterDivideAndMultiply);

        return BigDecimal.valueOf(Double.parseDouble(resultList.get(0)));

    }

    private void checkOperator(String equation) throws UnknownOperatorException {
        String[] phrase = equation.split(" ");
        for (int i = 1; i < phrase.length; i += 2) {
            if (!phrase[i].equals("/") && !phrase[i].equals("*") && !phrase[i].equals("+") && !phrase[i].equals("-"))
                throw new UnknownOperatorException("Nie obslugujemy takich operatorow");
        }
    }

    private void checkEquation(String equation) throws InvalidEquationFormatException {
        String[] phrase = equation.split(" ");
        for (int i = 0; i < phrase.length; i += 2) {
            if (phrase[i].matches("[a-zA-Z]") || phrase[i].matches("[0-9]+" + "[a-zA-Z]"))
                throw new InvalidEquationFormatException("Bledne wyrazenie");
        }

    }

    private List<String> checkDivideAndMultiplyOperator(List<String> collect) throws InvalidEquationFormatException {
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).equals("/")) {
                double divide = divisionOperator.divide(Double.parseDouble(collect.get(i - 1)), Double.parseDouble(collect.get(i + 1)));
                collect.remove(--i);
                collect.remove(i);
                collect.remove(i);
                collect.add(i, divide + "");
            }
            if (collect.get(i).equals("*")) {
                double multiply = multiplicationOperator.multiply(Double.parseDouble(collect.get(i - 1)), Double.parseDouble(collect.get(i + 1)));
                collect.remove(--i);
                collect.remove(i);
                collect.remove(i);
                collect.add(i, multiply + "");
            }
        }
        return collect;

    }

    private List<String> checkAdditionAndSubtractOperator(List<String> collect) {
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).equals("+")) {
                double add = additionOperator.plus(Double.parseDouble(collect.get(i - 1)), Double.parseDouble(collect.get(i + 1)));
                collect.remove(--i);
                collect.remove(i);
                collect.remove(i);
                collect.add(i, add + "");
            }
            if (collect.get(i).equals("-")) {
                double subtract = subtractionOperator.subtract(Double.parseDouble(collect.get(i - 1)), Double.parseDouble(collect.get(i + 1)));
                collect.remove(--i);
                collect.remove(i);
                collect.remove(i);
                collect.add(i, subtract + "");
            }

        }

        return collect;
    }


}
