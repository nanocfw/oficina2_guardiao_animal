/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service.implementations;

import br.com.ga.exceptions.EmailInUse;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import java.util.List;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.dao.intf.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marciano
 */
@Service
public class PersonServiceImpl implements IPersonService
{

    @Autowired
    IPersonDao personDao;

    @Override
    public Person findById(long id) throws Exception
    {
        return personDao.findById(id);
    }

    @Override
    public Person createUpdate(Person person) throws Exception
    {
        if (emailInUse(person.getId(), person.getEmail()))
            throw new EmailInUse();

        return personDao.createUpdate(person);
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore)
    {
        return personDao.findList(listClients, rowsReturn, rowsIgnore);
    }

    @Override
    public void delete(Person person) throws Exception
    {
        if (person.getId() <= 0)
            throw new EntityNotFound("Entidade não possui Id");

        personDao.delete(person);
    }

    @Override
    public Person findByEmailPassword(String email, String password) throws Exception
    {
        return personDao.findByEmailPassword(email, password);
    }

    @Override
    public boolean emailInUse(long currentId, String email)
    {
        return personDao.emailInUse(currentId, email);
    }

}
