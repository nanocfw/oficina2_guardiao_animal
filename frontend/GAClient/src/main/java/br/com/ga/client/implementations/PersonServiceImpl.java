/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.client.implementations;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.web.rest.UrlMapping;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.ResponseCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Person>> response;
        HttpEntity<Person> httpPerson = new HttpEntity<>(person);

        response = rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_CREATE_UPDATE,
                HttpMethod.POST,
                httpPerson,
                new ParameterizedTypeReference<ResponseData<Person>>()
        {
        });

        if (response.getBody().getStatus() == ResponseCode.CREATED)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == InvalidEntity.class)
            throw new InvalidEntity(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public Person findById(long id) throws Exception
    {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Person>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("personId", id);

        response = rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Person>>()
        {
        },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public boolean emailInUse(long currentId, String email) throws Exception
    {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Boolean>> response;
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("currentId", String.valueOf(currentId));

        response = rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_EMAIL_IN_USE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Boolean>>()
        {
        },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.OK)
            return response.getBody().getValue();

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public Person findByEmailPassword(String email, String password) throws Exception
    {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Person>> response;
        HttpEntity<Person> httpPerson = new HttpEntity<>(new Person());
        httpPerson.getBody().setEmail(email);
        httpPerson.getBody().setPassword(password);

        response = rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_LOGIN,
                HttpMethod.POST,
                httpPerson,
                new ParameterizedTypeReference<ResponseData<Person>>()
        {
        });

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public List<Person> findList(boolean listClients, int rowsReturn, int rowsIgnore)
    {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<List<Person>> response;
        Map<String, String> params = new HashMap<>();
        params.put("listClients", String.valueOf(listClients));
        params.put("rowsReturn", String.valueOf(rowsReturn));
        params.put("rowsIgnore", String.valueOf(rowsIgnore));

        response = rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_GET_LIST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>()
        {
        },
                params
        );

        return response.getBody();
    }

    @Override
    public void delete(Person person) throws Exception
    {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        HttpEntity<Person> httpPerson = new HttpEntity<>(person);

        rest.exchange(
                getServerURL() + UrlMapping.PERSON + UrlMapping.PERSON_DELETE,
                HttpMethod.DELETE,
                httpPerson,
                void.class
        );
    }

}
