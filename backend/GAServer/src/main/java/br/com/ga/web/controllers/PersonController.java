/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.dao.IPersonDao;
import br.com.ga.entity.Person;
import br.com.ga.service.IPersonService;
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
public class PersonController
{

    @Autowired
    IPersonService personService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/{personId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person get(@PathVariable(value = "personId") long animalId) throws Exception
    {
        return personService.findById(animalId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/{listClients}/{rowsReturn}/{rowsIgnore}",
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
            value = "ga/person/",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person createUpdate(@RequestBody Person p) throws Exception
    {
        return personService.createUpdate(p);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person login(@RequestBody Person p) throws Exception
    {
        return personService.findByEmailPassword(p.getEmail(), p.getPasword());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/{email}/{currentId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean emailInUse(
            @PathVariable(value = "email") String email,
            @PathVariable(value = "currentId") long currentId)
    {
        return personService.emailInUse(currentId, email);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(Person p) throws Exception
    {
        personService.delete(p);
    }

}
