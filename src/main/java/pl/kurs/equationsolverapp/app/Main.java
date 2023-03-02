package pl.kurs.equationsolverapp.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolverapp.exception.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exception.UnknownOperatorException;
import pl.kurs.equationsolverapp.model.EquationSolverEvent;
import pl.kurs.equationsolverapp.service.IEquationSolverEventService;
import pl.kurs.equationsolverapp.service.IEquationSolverService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

@ComponentScan(basePackages = "pl.kurs")
public class Main {
    public static void main(String[] args) {


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        IEquationSolverService equationSolverService = ctx.getBean(IEquationSolverService.class);
        IEquationSolverEventService equationSolverEventService = ctx.getBean(IEquationSolverEventService.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w aplikacji EquationSolverApp");


        try {
            System.out.println("Podaj wyrazenie: ");
            String equation = scanner.nextLine();
            System.out.println("Twoje wyrazenie: " + equation);
            BigDecimal result = equationSolverService.solve(equation);
            System.out.println("Wynik: " + result);
            equationSolverEventService.saveEvent(new EquationSolverEvent(
                            LocalDate.now(),
                            equation,
                            result
                    )
            );


        } catch (InvalidEquationFormatException e) {
            System.err.println(e.getMessage());
        } catch (UnknownOperatorException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }

}
