package pl.kurs.equationsolverapp.service;

import pl.kurs.equationsolverapp.model.EquationSolverEvent;

public interface IEquationSolverEventService {

    void saveEvent(EquationSolverEvent equationSolverEvent);
    EquationSolverEvent getEvent(long id);
}
