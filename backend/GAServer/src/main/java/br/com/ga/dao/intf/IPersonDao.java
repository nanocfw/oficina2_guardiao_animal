/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.intf;

import br.com.ga.entity.Person;
import br.com.ga.entity.ServiceProvider;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.ExpiredToken;

import java.util.List;
import java.util.UUID;

/**
 * @author Marciano
 */
public interface IPersonDao {

    Person createUpdate(final Person person) throws Exception;

    Person findById(final long id) throws Exception;

    boolean emailInUse(long currentId, final String email);

    Person findByEmailPassword(String email, String password) throws Exception;

    Person findByValidToken(UUID token) throws Exception;

    List<Person> findList(boolean listClients, final int rowsReturn, int rowsIgnore);

    void delete(final Person person) throws Exception;

    int deleteById(long personId) throws Exception;

    List<ServiceProvider> getServiceProviderList(String country, String city, int rowsReturn, int rowsIgnore);

    List<ServiceProvider> getServiceProviderList(double lat, double lng, int ray, int rowsReturn, int rowsIgnore);
}
