/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.Exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import java.util.List;
import br.com.ga.dao.IPersonDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marciano
 */
@Transactional
@Repository
public class PersonDaoImpl implements IPersonDao
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person create(Person person) throws Exception
    {
        em.persist(person);
        return person;
    }

    @Override
    public Person findById(long id)
    {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);
        return (Person) qry
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Person update(Person person) throws Exception
    {
        if (person.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.persist(person);
        return person;
    }

    @Override
    public Person findByEmailPassword(String email, String password)
    {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.email = :email AND p.pasword = :password", Person.class);
        return qry
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore)
    {
        return em
                .createQuery("SELECT p FROM Person p")
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public void delete(Person person) throws Exception
    {
        if (person.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        em.remove(person);
    }

}
