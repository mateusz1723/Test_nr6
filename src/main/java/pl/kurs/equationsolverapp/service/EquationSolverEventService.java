package pl.kurs.equationsolverapp.service;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolverapp.dao.IEquationSolverEventDao;
import pl.kurs.equationsolverapp.model.EquationSolverEvent;

import java.util.Objects;
import java.util.Optional;

@Service
public class EquationSolverEventService implements IEquationSolverEventService{

    private IEquationSolverEventDao equationSolverEventDao;

    public EquationSolverEventService(IEquationSolverEventDao equationSolverEventDao) {
        this.equationSolverEventDao = equationSolverEventDao;
    }

    @Override
    public void saveEvent(EquationSolverEvent equationSolverEvent) {
        equationSolverEventDao.save(
                Optional.ofNullable(equationSolverEvent)
                        .filter(x -> Objects.isNull(x.getId()))
                        .orElseThrow(() -> new RuntimeException("Bledna encja"))
        );

    }

    @Override
    public EquationSolverEvent getEvent(long id) {
        if (id <= 0)
            throw new RuntimeException("Id nie moze byc mniejsze od 0");
        return equationSolverEventDao.get(id);
    }
}
