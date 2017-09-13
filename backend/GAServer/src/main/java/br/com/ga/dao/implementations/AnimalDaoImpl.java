/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.ga.dao.IAnimalDao;

/**
 *
 * @author Marciano
 */
@Transactional
@Repository
public class AnimalDaoImpl implements IAnimalDao
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Animal create(Animal animal)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal findById(int id)
    {
        return (Animal) em.createQuery("SELECT a FROM Animal a WHERE a.id = :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public Animal update(Animal animal)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> findList(int rowsReturn, int rowsIgnore)
    {
        return em.createQuery("SELECT a FROM Animal a").setFirstResult(rowsIgnore).setMaxResults(rowsReturn).getResultList();
    }

    @Override
    public boolean delete(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
