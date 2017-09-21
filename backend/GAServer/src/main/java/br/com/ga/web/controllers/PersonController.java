/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.web.rest.UrlMapping;
import br.com.ga.exceptions.EmailInUse;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.ResponseCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marciano
 */
@RestController
@RequestMapping(UrlMapping.PERSON)
public class PersonController
{

    @Autowired
    IPersonService personService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> get(@PathVariable(value = "personId") long animalId)
    {
        try
        {
            Person person = personService.findById(animalId);
            return new ResponseData<>(Person.class, person, ResponseCode.FOUND);
        } catch (EntityNotFound e)
        {
            return new ResponseData<>(Person.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e)
        {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_GET_LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getList(
            @PathVariable(value = "listClients") boolean listClients,
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore)
    {
        return personService.findList(listClients, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> createUpdate(@RequestBody Person p)
    {
        try
        {
            Person person = personService.createUpdate(p);
            return new ResponseData<>(Person.class, person, ResponseCode.CREATED);
        } catch (EmailInUse e)
        {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, EmailInUse.class, e.getMessage());
        } catch (Exception ex)
        {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_LOGIN,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> login(@RequestBody Person p)
    {
        try
        {
            Person person = personService.findByEmailPassword(p.getEmail(), p.getPasword());
            return new ResponseData<>(Person.class, person, ResponseCode.FOUND);
        } catch (EntityNotFound e)
        {
            return new ResponseData<>(Person.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e)
        {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_EMAIL_IN_USE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Boolean> emailInUse(
            @PathVariable(value = "email") String email,
            @PathVariable(value = "currentId") long currentId)
    {
        try
        {
            boolean emailInUse = personService.emailInUse(currentId, email);
            return new ResponseData<>(Boolean.class, emailInUse, ResponseCode.OK);
        } catch (Exception e)
        {
            return new ResponseData<>(Boolean.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_DELETE,
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(Person p) throws Exception
    {
        personService.delete(p);
    }

}
