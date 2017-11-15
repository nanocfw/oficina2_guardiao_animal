package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IAnimalTypeDao;
import br.com.ga.entity.AnimalType;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class AnimalTypeDaoImpl implements IAnimalTypeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AnimalType createUpdate(AnimalType animalType) throws Exception {
        AnimalType a = em.merge(animalType);
        em.flush();
        return a;
    }

    @Override
    public AnimalType findById(long id) throws Exception {
        return (AnimalType) em
                .createQuery("SELECT a FROM AnimalType a WHERE a.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public AnimalType findByDescription(String description) throws Exception {
        return (AnimalType) em
                .createQuery("SELECT a FROM AnimalType a WHERE a.description = :description")
                .setParameter("description", description)
                .getSingleResult();
    }

    @Override
    public List<AnimalType> findList(int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT a FROM AnimalType a")
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public void delete(AnimalType animalType) throws Exception {
        if (animalType.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.remove(animalType);
    }

    @Override
    public int deleteById(long animalTypeId) throws Exception {
        if (animalTypeId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM AnimalType a WHERE a.id = :animalTypeId")
                .setParameter("animalTypeId", animalTypeId);
        return qry.executeUpdate();
    }
}
