/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.intf;

import br.com.ga.entity.Person;
import br.com.ga.entity.ServiceProviderSearch;

import java.util.List;
import java.util.UUID;
import javax.ejb.Local;

/**
 * @author Marciano
 */
@Local
public interface IPersonService {

    Person createUpdate(final Person person) throws Exception;

    Person findById(final long personId) throws Exception;

    boolean emailInUse(long currentId, final String email) throws Exception;

    Person findByEmailPassword(String email, String password) throws Exception;

    Person updateAuthToken(Person person) throws Exception;

    Person findByValidToken(UUID token) throws Exception;

    Boolean isValidToken(UUID token) throws Exception;

    List<Person> findList(boolean listClients, final int rowsReturn, int rowsIgnore);

    void delete(final Person person) throws Exception;

    int deleteById(long personId) throws Exception;

    List<ServiceProviderSearch> getServiceProviderList(long currentId, String country, String city, int rowsReturn, int rowsIgnore);

    List<ServiceProviderSearch> getServiceProviderList(long currentId, double lat, double lng, int ray, int rowsReturn, int rowsIgnore);
}
