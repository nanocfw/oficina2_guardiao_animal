/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.implementations;

import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Marciano
 */
public class PersonServiceImpl extends Service implements IPersonService
{

    @Override
    public Person createUpdate(Person person) throws Exception
    {
        final String uri = "person/save/";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getServerURL() + uri, person, Person.class);
    }

    @Override
    public Person findById(long id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean emailInUse(long currentId, String email)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person findByEmailPassword(String email, String password) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Person person) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
