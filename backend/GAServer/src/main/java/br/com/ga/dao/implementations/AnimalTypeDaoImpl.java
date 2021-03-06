package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IAnimalTypeDao;
import br.com.ga.entity.AnimalType;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
    public AnimalType findById(int animalTypeId) throws Exception {
        TypedQuery<AnimalType> qry = em.createQuery("SELECT a FROM AnimalType a WHERE a.id = :animalTypeId", AnimalType.class);
        try {
            return (AnimalType) qry
                    .setParameter("animalTypeId", animalTypeId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
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
    public int deleteById(int animalTypeId) throws Exception {
        if (animalTypeId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM AnimalType a WHERE a.id = :animalTypeId")
                .setParameter("animalTypeId", animalTypeId);
        return qry.executeUpdate();
    }

    public void init() {
        AnimalType animalType = null;

        try {
            animalType = findByDescription("Aves");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Aves"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Répteis");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Répteis"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Cão");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Cão"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Gato");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Gato"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Peixe");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Peixe"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Cobra");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Cobra"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Cavalo");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Cavalo"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Mini-Porco");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Mini-Porco"));
            } catch (Exception ex) {
            }

        try {
            animalType = findByDescription("Vaca");
        } catch (Exception ex) {
        }
        if (animalType == null)
            try {
                createUpdate(new AnimalType("Vaca"));
            } catch (Exception ex) {
            }
    }
}
