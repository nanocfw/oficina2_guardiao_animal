/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.service;

import br.com.ga.entity.Person;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IPersonService
{

    Person create(final Person person);

    Person findById(final int id);

    Person update(Person person);

    Person findByEmailPassword(String email, String password);

    List<Person> findList(boolean listClients, final int rowsReturn, int rowsIgnore);

    boolean delete(final int id);
}
