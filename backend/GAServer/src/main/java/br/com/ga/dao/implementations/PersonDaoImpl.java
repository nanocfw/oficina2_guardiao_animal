/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;

import java.util.List;

import br.com.ga.dao.intf.IPersonDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marciano
 */
@Transactional
@Repository
public class PersonDaoImpl implements IPersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person createUpdate(Person person) throws Exception {
        Person p = em.merge(person);
        em.flush();
        return p;
    }

    @Override
    public Person findById(long id) throws Exception {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);
        try {
            return (Person) qry
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public Person findByEmailPassword(String email, String password) throws Exception {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.email = :email AND p.password = :password",
                        Person.class);
        try {
            return qry
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore) {
        return em
                .createQuery("SELECT p FROM Person p WHERE p.serviceProvider = :service_provider")
                .setParameter("service_provider", !listClients)
                // caso não deva listar os clientes, filtra para exibir somente entidades que possuem serviceProvider == true
                .setFirstResult(rowsIgnore)
                .setMaxResults(rowsReturn)
                .getResultList();
    }

    @Override
    public void delete(Person person) throws Exception {
        em.remove(person);
        em.flush();
    }

    @Override
    public boolean emailInUse(long currentId, String email) {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.email = :email AND p.id <> :currentId", Person.class);
        return qry
                .setParameter("email", email)
                .setParameter("currentId", currentId)
                .getResultList().size() > 0;
    }

}
