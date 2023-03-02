package pl.kurs.equationsolverapp.dao;

import org.springframework.stereotype.Repository;
import pl.kurs.equationsolverapp.model.EquationSolverEvent;

import javax.persistence.*;

@Repository
public class EquationSolverEventDao implements IEquationSolverEventDao{


    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    @Override
    public void save(EquationSolverEvent equationSolverEvent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(equationSolverEvent);
        tx.commit();
        entityManager.close();
    }

    @Override
    public EquationSolverEvent get(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EquationSolverEvent equationSolverEvent = entityManager.find(EquationSolverEvent.class, id);
        entityManager.close();
        return equationSolverEvent;
    }
}
