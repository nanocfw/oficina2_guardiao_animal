/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.entity.ServiceProvider;
import br.com.ga.exceptions.ExpiredToken;
import br.com.ga.util.Util;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public Person findByValidToken(UUID token) throws Exception {
        Person p;
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.authToken = :authToken",
                        Person.class);
        try {
            p = qry
                    .setParameter("authToken", token)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }

        if (p.getAuthTokenExpiration() == null || p.getAuthTokenExpiration().before(Util.curDate()))
            throw new ExpiredToken();

        return p;
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
    public List<ServiceProvider> getServiceProviderList(String country, String city, int rowsReturn, int rowsIgnore) {
        List<Person> lstAuxPerson =
                em.createQuery("SELECT p FROM Person p WHERE p.serviceProvider = :service_provider " +
                        " AND p.country = :country " +
                        " AND p.city = :city ")
                        .setParameter("service_provider", true)
                        .setParameter("country", country)
                        .setParameter("city", city)
                        .setFirstResult(rowsIgnore)
                        .setMaxResults(rowsReturn)
                        .getResultList();

        List<ServiceProvider> lstAux = new ArrayList<>();
        for (Person p : lstAuxPerson)
            lstAux.add(new ServiceProvider(p.getName(), p.getCountry(), p.getCity(), p.getLatitude(), p.getLongitude()));

        return lstAux;
    }

    @Override
    public List<ServiceProvider> getServiceProviderList(double lat, double lng, int ray, int rowsReturn, int rowsIgnore) {
        double rayLat = Util.kmToDegree(lat, ray);
        double rayLng = Util.kmToDegree(lng, ray);
        List<Person> lstAuxPerson =
                em.createQuery("SELECT p FROM Person p WHERE p.serviceProvider = :service_provider " +
                        " AND p.latitude BETWEEN :lat_ini AND :lat_fim" +
                        " AND p.longitude BETWEEN :lng_ini AND :lng_fim")
                        .setParameter("service_provider", true)
                        .setParameter("lat_ini", lat - rayLat)
                        .setParameter("lat_fim", lat + rayLat)
                        .setParameter("lng_ini", lng - rayLng)
                        .setParameter("lng_fim", lng + rayLng)
                        .setFirstResult(rowsIgnore)
                        .setMaxResults(rowsReturn)
                        .getResultList();
        List<ServiceProvider> lstAux = new ArrayList<>();
        for (Person p : lstAuxPerson)
            lstAux.add(new ServiceProvider(p.getName(), p.getCountry(), p.getCity(), p.getLatitude(), p.getLongitude()));

        return lstAux;
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
