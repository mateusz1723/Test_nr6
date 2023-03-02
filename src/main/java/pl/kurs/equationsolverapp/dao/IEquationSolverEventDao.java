package pl.kurs.equationsolverapp.dao;

import pl.kurs.equationsolverapp.model.EquationSolverEvent;

public interface IEquationSolverEventDao {

    void save(EquationSolverEvent equationSolverEvent);
    EquationSolverEvent get(Long id);

}
