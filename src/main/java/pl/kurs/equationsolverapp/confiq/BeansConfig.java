package pl.kurs.equationsolverapp.confiq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.equationsolverapp.model.AdditionOperator;
import pl.kurs.equationsolverapp.model.DivisionOperator;
import pl.kurs.equationsolverapp.model.MultiplicationOperator;
import pl.kurs.equationsolverapp.model.SubtractionOperator;

@Configuration
public class BeansConfig {


    @Bean
    public AdditionOperator getAdditionOperator(){
        return new AdditionOperator();
    }

    @Bean
    public DivisionOperator getDivisionOperator(){
        return new DivisionOperator();
    }

    @Bean
    public MultiplicationOperator getMultiplicationOperator(){
        return new MultiplicationOperator();
    }

    @Bean
    public SubtractionOperator getSubtractionOperator(){
        return new SubtractionOperator();
    }

}
