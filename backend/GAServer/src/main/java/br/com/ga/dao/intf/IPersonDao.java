/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.dao.intf;

import br.com.ga.entity.Person;
import java.util.List;

/**
 *
 * @author Marciano
 */
public interface IPersonDao
{

    Person createUpdate(final Person person) throws Exception;

    Person findById(final long id) throws Exception;

    boolean emailInUse(long currentId, final String email);

    Person findByEmailPassword(String email, String password) throws Exception;

    List<Person> findList(boolean listClients, final int rowsReturn, int rowsIgnore);

    void delete(final Person person) throws Exception;
}
