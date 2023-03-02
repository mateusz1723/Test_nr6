package pl.kurs.equationsolverapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class EquationSolverEvent implements Serializable {

    private final static long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String phrase;
    private BigDecimal result;

    public EquationSolverEvent() {
    }

    public EquationSolverEvent(LocalDate date, String phrase, BigDecimal result) {
        this.date = date;
        this.phrase = phrase;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPhrase() {
        return phrase;
    }

    public BigDecimal getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "EquationSolverEvent{" +
                "id=" + id +
                ", date=" + date +
                ", phrase='" + phrase + '\'' +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquationSolverEvent that = (EquationSolverEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(phrase, that.phrase) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, phrase, result);
    }
}
