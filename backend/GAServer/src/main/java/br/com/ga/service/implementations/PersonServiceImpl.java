/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.implementations;

import br.com.ga.entity.ServiceProvider;
import br.com.ga.util.Util;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;

import java.util.List;
import java.util.UUID;

import br.com.ga.service.intf.IPersonService;
import br.com.ga.dao.intf.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marciano
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    IPersonDao personDao;

    @Override
    public Person findById(long personId) throws Exception {
        return personDao.findById(personId);
    }

    @Override
    public Person createUpdate(Person person) throws Exception {
        if (person.getName() == null || person.getName().isEmpty() || person.getName().length() < 3)
            throw new InvalidEntity("Nome inválido.");

        if (person.getLastName() == null || person.getLastName().isEmpty() || person.getLastName().length() < 3)
            throw new InvalidEntity("Sobrenome inválido.");

        if (person.getCity() == null || person.getCity().isEmpty())
            throw new InvalidEntity("Cidade não foi informada.");

        if (person.getState() == null || person.getState().isEmpty())
            throw new InvalidEntity("Estado não foi informado.");

        if (person.getCountry() == null || person.getCountry().isEmpty())
            throw new InvalidEntity("País não foi informado.");

        if (!Util.isEmailValid(person.getEmail()))
            throw new InvalidEntity("E-mail inválido.");

        if (emailInUse(person.getId(), person.getEmail()))
            throw new InvalidEntity("E-mail em uso.");

        if (person.getPassword() == null || person.getPassword().length() < 8)
            throw new InvalidEntity("Senha inválida, mínimo 8 caracteres, atual: " + person.getPassword().length() + ".");

        return personDao.createUpdate(person);
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore) {
        return personDao.findList(listClients, rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(Person person) throws Exception {
        if (person.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        personDao.delete(person);
    }

    @Override
    public int deleteById(long personId) throws Exception {
        return personDao.deleteById(personId);
    }

    @Override
    public List<ServiceProvider> getServiceProviderList(String country, String city, int rowsReturn, int rowsIgnore) {
        return personDao.getServiceProviderList(country, city, rowsReturn, rowsIgnore);
    }

    @Override
    public List<ServiceProvider> getServiceProviderList(double lat, double lng, int ray, int rowsReturn, int rowsIgnore) {
        return personDao.getServiceProviderList(lat, lng, ray, rowsReturn, rowsIgnore);
    }

    @Override
    public Person findByEmailPassword(String email, String password) throws Exception {
        return personDao.findByEmailPassword(email, password);
    }

    @Override
    public Person updateAuthToken(Person person) throws Exception {
        return null;
    }

    @Override
    public Person findByValidToken(UUID token) throws Exception {
        return personDao.findByValidToken(token);
    }

    @Override
    public Boolean isValidToken(UUID token) throws Exception {
        return personDao.findByValidToken(token) != null;
    }

    @Override
    public boolean emailInUse(long currentId, String email) {
        return personDao.emailInUse(currentId, email);
    }

}
