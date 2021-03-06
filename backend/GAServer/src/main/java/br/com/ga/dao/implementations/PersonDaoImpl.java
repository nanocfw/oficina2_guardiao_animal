/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.*;
import br.com.ga.entity.ServiceProviderAnimalType;
import br.com.ga.entity.ServiceProviderSearch;
import br.com.ga.exceptions.ExpiredToken;
import br.com.ga.util.Util;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IPictureDao pictureDao;

    @Autowired
    private IServiceProviderAnimalTypeDao serviceProviderAnimalTypeDao;

    @Autowired
    private IServiceTypeDao serviceTypeDao;

    @Autowired
    private IAnimalTypeDao animalTypeDao;

    @Override
    public Person createUpdate(Person person) throws Exception {
        Person p = em.merge(person);
        em.flush();
        return p;
    }

    @Override
    public Person findById(long personId) throws Exception {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.id = :personId", Person.class);
        try {
            return (Person) qry
                    .setParameter("personId", personId)
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
    public int deleteById(long personId) throws Exception {
        if (personId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM Person p WHERE p.id = :personId")
                .setParameter("personId", personId);
        return qry.executeUpdate();
    }

    private List<ServiceProviderSearch> getProviders(List<Person> list) {
        String picture;
        ServiceProviderSearch service;
        List<ServiceProviderSearch> lstAux = new ArrayList<>();
        for (Person p : list) {

            picture = "";
            try {
                picture = pictureDao.findById(p.getProfilePic_id()).asString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            service = new ServiceProviderSearch(p.getId(), p.getName(), p.getMessage(), p.getCountry(), p.getCity(), picture, p.getLatitude(), p.getLongitude());
            service.getServiceList().addAll(serviceProviderAnimalTypeDao.findListByProvider(p.getId(), 1000, 0));
            for (ServiceProviderAnimalType s : service.getServiceList()) {
                try {
                    s.setAnimalTypeDescription(animalTypeDao.findById(s.getAnimalType_id()).getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    s.setServiceTypeDescription(serviceTypeDao.findById(s.getServiceType_id()).getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lstAux.add(service);
        }

        return lstAux;
    }

    @Override
    public List<ServiceProviderSearch> getServiceProviderList(long currentId, String country, String city, int rowsReturn, int rowsIgnore) {
        List<Person> lstAuxPerson =
                em.createQuery("SELECT p FROM Person p WHERE p.id <> :currentId " +
                        " AND p.serviceProvider = :service_provider " +
                        " AND p.country = :country " +
                        " AND p.city = :city " +
                        " AND (SELECT COUNT(s) FROM ServiceProviderAnimalType s WHERE s.serviceProvider_id = p.id) > 0")
                        .setParameter("currentId", currentId)
                        .setParameter("service_provider", true)
                        .setParameter("country", country)
                        .setParameter("city", city)
                        .setFirstResult(rowsIgnore)
                        .setMaxResults(rowsReturn)
                        .getResultList();

        return getProviders(lstAuxPerson);
    }

    @Override
    public List<ServiceProviderSearch> getServiceProviderList(long currentId, double lat, double lng, int ray, int rowsReturn, int rowsIgnore) {
        double rayLat = Util.kmToDegree(lat, ray);
        double rayLng = Util.kmToDegree(lng, ray);
        List<Person> lstAuxPerson =
                em.createQuery("SELECT p FROM Person p WHERE p.id <> :currentId " +
                        " AND p.serviceProvider = :service_provider " +
                        " AND p.latitude BETWEEN :lat_ini AND :lat_fim" +
                        " AND p.longitude BETWEEN :lng_ini AND :lng_fim" +
                        " AND (SELECT COUNT(s) FROM ServiceProviderAnimalType s WHERE s.serviceProvider_id = p.id) > 0")
                        .setParameter("service_provider", true)
                        .setParameter("lat_ini", lat - rayLat)
                        .setParameter("lat_fim", lat + rayLat)
                        .setParameter("lng_ini", lng - rayLng)
                        .setParameter("lng_fim", lng + rayLng)
                        .setParameter("currentId", currentId)
                        .setFirstResult(rowsIgnore)
                        .setMaxResults(rowsReturn)
                        .getResultList();

        return getProviders(lstAuxPerson);
    }

    @Override
    public boolean emailInUse(long currentId, String email) {
        TypedQuery<Person> qry = em
                .createQuery("SELECT p FROM Person p WHERE p.email = :email AND p.id <> :currentId", Person.class);
        return qry
                .setParameter("email", email)
                .setParameter("currentId", currentId)
                .setMaxResults(1)
                .getResultList().size() > 0;
    }

}
