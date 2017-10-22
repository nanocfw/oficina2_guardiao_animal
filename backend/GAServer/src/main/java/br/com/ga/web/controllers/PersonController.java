/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.exceptions.ExpiredToken;
import br.com.ga.util.Util;
import br.com.ga.web.rest.UrlMapping;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.entity.Person;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.ResponseCode;

import java.util.List;
import java.util.UUID;

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
 * @author Marciano
 */
@RestController
@RequestMapping(UrlMapping.PERSON)
public class PersonController {

    @Autowired
    IPersonService personService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> get(@PathVariable(value = "personId") long personId) {
        try {
            Person person = personService.findById(personId);
            return new ResponseData<>(Person.class, person, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Person.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_VALID_TOKEN,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Boolean> isValidToken(@PathVariable(value = "token") UUID token) {
        try {
            if (personService.isValidToken(token))
                return new ResponseData<>(Boolean.class, true, ResponseCode.VALID);

            return new ResponseData<>(Boolean.class, false, ResponseCode.COOKIE_EXPIRED);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Boolean.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Boolean.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
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
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return personService.findList(listClients, rowsReturn, rowsIgnore);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_EMAIL_IN_USE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Boolean> emailInUse(
            @PathVariable(value = "email") String email,
            @PathVariable(value = "currentId") long currentId) {
        try {
            boolean emailInUse = personService.emailInUse(currentId, email);
            return new ResponseData<>(Boolean.class, emailInUse, ResponseCode.OK);
        } catch (Exception e) {
            return new ResponseData<>(Boolean.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> createUpdate(@RequestBody Person p) {
        try {
            Person person = personService.createUpdate(p);
            return new ResponseData<>(Person.class, person, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_UPDATE_AUTH_TOKEN,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> updateAuthToken(@RequestBody Person p) {
        try {
            p.setAuthToken(UUID.randomUUID());
            p.setAuthTokenExpiration(Util.incDay(Util.curDate(), 7));
            Person person = personService.createUpdate(p);
            return new ResponseData<>(Person.class, person, ResponseCode.UPDATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_LOGIN,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> login(@RequestBody Person p) {
        try {
            Person person = personService.findByEmailPassword(p.getEmail(), p.getPassword());
            return new ResponseData<>(Person.class, person, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Person.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_LOGIN_BY_VALID_TOKEN,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Person> loginByToken(@RequestBody Person person) {
        try {
            Person p = personService.findByValidToken(person.getAuthToken());
            return new ResponseData<>(Person.class, p, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Person.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (ExpiredToken e) {
            return new ResponseData<>(Person.class, ResponseCode.COOKIE_EXPIRED, ExpiredToken.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Person.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PERSON_DELETE,
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(Person p) throws Exception {
        personService.delete(p);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(
//            value = UrlMapping.PERSON_FETCH_SERVICE_PROVIDER,
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)

}
