/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.implementations;

import ga.com.br.client.rest.BasicAuthRestTemplate;
import ga.com.br.client.rest.Service;
import br.com.ga.Exceptions.EmailInUse;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.web.response.ResponseData;
import br.com.ga.web.response.ResponseCode;
import java.util.List;
import javax.ejb.Stateless;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Marciano
 */
@Stateless
public class PersonServiceImpl extends Service implements IPersonService
{

    @Override
    public Person createUpdate(Person person) throws Exception
    {
        final String uri = "person/save/";
        BasicAuthRestTemplate r = getNewRestTemplate();
        ResponseEntity<ResponseData<Person>> response;
        HttpEntity<Person> p = new HttpEntity<>(person);

        response = r.exchange(
                getServerURL() + uri,
                HttpMethod.POST,
                p,
                new ParameterizedTypeReference<ResponseData<Person>>()
        {
        });

        if (response.getBody().getStatus() == ResponseCode.CREATED)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EmailInUse.class)
            throw new EmailInUse(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                + " Message: " + response.getBody().getExceptionMessage());
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
