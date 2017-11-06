/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Animal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.ga.dao.intf.IAnimalDao;

/**
 * @author Marciano
 */
@Transactional
@Repository
public class AnimalDaoImpl implements IAnimalDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Animal createUpdate(Animal animal) throws Exception {
        Animal a = em.merge(animal);
        em.flush();
        return a;
    }

    @Override
    public Animal findById(long id) throws Exception {
        return (Animal) em
                .createQuery("SELECT a FROM Animal a WHERE a.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Animal> findList(final long ownerId, final int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT a FROM Animal a WHERE a.owner.id = :owner")
                .setParameter("owner", ownerId)
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public void delete(Animal animal) throws Exception {
        if (animal.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.remove(animal);
    }

    @Override
    public int deleteById(long animalId) throws Exception {
        if (animalId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM Animal a WHERE a.id = :animalId")
                .setParameter("animalId", animalId);
        return qry.executeUpdate();
    }
}
