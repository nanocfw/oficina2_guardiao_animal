/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.entity.Person;
import br.com.ga.service.IPersonService;
import javafx.util.Pair;
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

    @ResponseStatus(HttpStatus.OK) //Por padrão responde com o status code 200 success
    @RequestMapping(
            value = "ga/person/find/{personId}",
            //Mapeia as requisições GET para localhost:8080/person/
            //recebendo um ID como @PathVariable
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    // Produz JSON como retorno
    public Person get(@PathVariable(value = "personId") long animalId)
    {
        return personService.findById(animalId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/person/login/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person login(@RequestBody Pair<String, String> p)
    {
        return personService.findByEmailPassword(p.getKey(), p.getValue());
    }

}
