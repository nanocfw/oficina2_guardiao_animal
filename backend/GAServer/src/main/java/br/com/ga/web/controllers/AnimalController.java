/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.controllers;

import br.com.ga.dao.intf.IAnimalDao;
import br.com.ga.entity.Animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marciano
 */
@RestController
public class AnimalController {

    @Autowired
    private IAnimalDao animalDao;

    @ResponseStatus(HttpStatus.OK) //Por padrão responde com o status code 200 success
    @RequestMapping(
            value = "ga/animal/find/{animalId}",
            //Mapeia as requisições GET para localhost:8080/person/
            //recebendo um ID como @PathVariable
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    // Produz JSON como retorno
    public Animal get(@PathVariable(value = "animalId") int animalId) {
        try {
            return animalDao.findById(animalId);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "ga/animal/list/{rowsReturn}/{rowsIgnore}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> getList(
            @PathVariable(value = "rowsReturn") int rowsReturn,
            @PathVariable(value = "rowsIgnore") int rowsIgnore) {
        return animalDao.findList(rowsReturn, rowsIgnore);
    }
}
