/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Animal;

import java.util.List;
import javax.persistence.*;

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
    public Animal findById(long animalId) throws Exception {
        TypedQuery<Animal> qry = em.createQuery("SELECT a FROM Animal a WHERE a.id = :animalId", Animal.class);
        try {
            return (Animal) qry
                    .setParameter("animalId", animalId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public List<Animal> findList(final long ownerId, final int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT a FROM Animal a WHERE a.owner_id = :ownerId")
                .setParameter("ownerId", ownerId)
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
