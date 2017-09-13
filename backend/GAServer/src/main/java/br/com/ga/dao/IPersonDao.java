/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao;

import br.com.ga.entity.Person;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IPersonDao
{

    Person create(final Person person) throws Exception;

    Person findById(final long id);

    Person update(Person person) throws Exception;

    Person findByEmailPassword(String email, String password);

    List<Person> findList(boolean listClients, final int rowsReturn, int rowsIgnore);

    void delete(final Person person) throws Exception;
}
