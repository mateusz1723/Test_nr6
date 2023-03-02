package pl.kurs.equationsolverapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exception.UnknownOperatorException;
import pl.kurs.equationsolverapp.model.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {EquationSolverService.class,
                AdditionOperator.class,
                DivisionOperator.class,
                MultiplicationOperator.class,
                SubtractionOperator.class},
        loader = AnnotationConfigContextLoader.class)
public class EquationSolverServiceTest {

    @Resource
    private IEquationSolverService equationSolverService;

    private SubtractionOperator subtractionOperator;
    private MultiplicationOperator multiplicationOperator;
    private DivisionOperator divisionOperator;
    private AdditionOperator additionOperator;

    @Before
    public void init() {
        subtractionOperator = new SubtractionOperator();
        multiplicationOperator = new MultiplicationOperator();
        divisionOperator = new DivisionOperator();
        additionOperator = new AdditionOperator();
        equationSolverService = new EquationSolverService(additionOperator, multiplicationOperator, subtractionOperator, divisionOperator);
    }

    @Test
    public void shouldSolveEquation2plus2multiply2AndReturn6() throws UnknownOperatorException, InvalidEquationFormatException {
        //when
        BigDecimal result = equationSolverService.solve("2 + 2 * 2");
        //then
        assertEquals(result, BigDecimal.valueOf(6.0));
    }

    @Test
    public void shouldReturnInvalidEquationFormatExceptionWhenNumberIsWithCharOrOnlyChar() {
        //when
        Throwable e = assertThrows(InvalidEquationFormatException.class, () -> equationSolverService.solve("2x + 2 * 2"));

        //then
        assertEquals(e.getClass(), InvalidEquationFormatException.class);
        assertEquals(e.getMessage(), "Bledne wyrazenie");
    }

    @Test
    public void shouldReturnMessageNieObslugujemyTakichOperatorowWhilePutThePrecentSign() {
        //when
        Throwable e = assertThrows(UnknownOperatorException.class, () -> equationSolverService.solve("2 % 2 + 2"));

        //then
        assertEquals(e.getMessage(), "Nie obslugujemy takich operatorow");

    }

    @Test
    public void shouldReturnNieMoznaDzielicPrzez0WhileTryingDivideByZero() {
        //when
        Throwable e = assertThrows(InvalidEquationFormatException.class, () -> equationSolverService.solve("2 / 0"));

        //then
        assertEquals(e.getMessage(), "Nie mozna dzielic przez 0");
    }




}